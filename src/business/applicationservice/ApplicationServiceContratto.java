package business.applicationservice;

import integration.dao.DAOFactory;

import java.util.Iterator;
import java.util.List;

import org.joda.time.Days;
import business.checker.CheckerFactory;
import business.checker.CheckerContratto;
import business.entity.Agenzia;
import business.entity.Contratto;
import business.entity.Optional;
import business.entity.Rifornimento;
import business.entity.Tariffario;
import business.exception.CarloanException;
import business.exception.IntegrityException;

public class ApplicationServiceContratto extends ApplicationServiceEntity<Contratto> {

	/**
	 * Si occupa della chiusura di un contratto
	 * @param contratto
	 * @throws CarloanException
	 */
	public void chiudi(Contratto contratto) throws CarloanException {
		((CheckerContratto)checker).checkChiusura(contratto);
		contratto.setChiuso(true);
		dao.update(contratto);
		contratto.getVettura().setChilometraggio(contratto.getVettura().getChilometraggio() + contratto.getChilometriPercorsi());
		try {
			new ApplicationServiceVettura().update(contratto.getVettura());
		} catch (InstantiationException | IllegalAccessException e1) {
			throw new CarloanException("Impossibile aggiornare il chilometraggio della vettura");
		}
		try {
			new ApplicationServiceVettura().update(contratto.getVettura());
		} catch (IntegrityException | InstantiationException
				| IllegalAccessException e) {
			throw new CarloanException("Impossibile chiudere il contratto");
		}
	}
	
	/**
	 * Costruisce il DAO e il checker relativi al contratto.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public ApplicationServiceContratto() throws InstantiationException, IllegalAccessException {
		super(DAOFactory.buildDao(Contratto.class), CheckerFactory.buildChecker(Contratto.class));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void create(Contratto entity) throws IntegrityException {
			checker.check(entity);
			dao.create(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Contratto read(String pk) {
		return dao.read(pk);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Contratto entity) throws IntegrityException {
			checker.isModifiable(dao.read(Integer.toString(entity.getId())));
			checker.check(entity);
			dao.update(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Contratto> readAll() {
		return dao.readAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Contratto entity) {
		dao.delete(Integer.toString(entity.getId()));
	}
	
	/**
	 * Calcola il costo in seguito alla chiusura del contratto
	 * @param contratto
	 * @return double indicante il costo finale del contratto
	 * @throws CarloanException
	 */
	public double calcolaCostoChiusura(Contratto contratto) throws CarloanException {
		double costo = calcolaCosto(contratto);
		Tariffario tariffario = contratto.getTariffario();
		int chilometriConsiderati;
		if (contratto.isChilometraggioLimitato() && contratto.getChilometriPercorsi() <= contratto.getChilometriPrevisti())
			chilometriConsiderati = contratto.getChilometriPrevisti();
		else chilometriConsiderati = contratto.getChilometriPercorsi();
		
		if (contratto.getRifornimento() == Rifornimento.STANDARD) {
			costo += tariffario.getCostoLitro(contratto.getVettura().getModello().getTipoCarburante()) * chilometriConsiderati;
		} else if (contratto.getRifornimento() == Rifornimento.PIENO_ANTICIPO) {
			costo += tariffario.getRifornimento(contratto.getRifornimento());
		} 
		
		if (contratto.getDataChiusura().isAfter(contratto.getDataFineNoleggio())) {
			costo += tariffario.getMoraDurata() * Days.daysBetween(contratto.getDataFineNoleggio(), contratto.getDataChiusura()).getDays();
		}
		
		if (contratto.isChilometraggioLimitato() && contratto.getChilometriPercorsi() > contratto.getChilometriPrevisti()) {
			costo += tariffario.getMoraChilometraggio() * (contratto.getChilometriPercorsi() - contratto.getChilometriPrevisti());
		}
		return costo;
	}
	
	/**
	 * Calcola il costo preventivo del contratto
	 * @param contratto
	 * @return double del costo preventivo
	 * @throws CarloanException
	 */
	public double calcolaCosto(Contratto contratto) throws CarloanException {
		try {
			double costo = 0;
			Tariffario tariffario = contratto.getTariffario();
			// il tariffario è vuoto all'inizio!
			costo += contratto.getVettura().getModello().getFascia().getTariffaBase();
			for (Optional o:contratto.getOptionals()) {
				costo += o.getCosto();
			}
			int durataNoleggio = Days.daysBetween(contratto.getDataInizioNoleggio(), contratto.getDataFineNoleggio()).getDays();
			if (durataNoleggio % 7 == 0) 
				costo += durataNoleggio/7 * tariffario.getCostoSettimanale();
			else 
				costo += durataNoleggio * tariffario.getCostoGiornaliero();
			
			if (contratto.isAssicurazioneAvanzata())
				costo += tariffario.getAssicurazioneAvanzata();
			else 
				costo += tariffario.getAssicurazioneBase();
	
			if (contratto.isChilometraggioLimitato()) {
				costo += tariffario.getCostoChilometrico() * contratto.getChilometriPrevisti();
			} else {
				costo += tariffario.getCostoChilometraggioIllimitato();
			}
			
			costo -= contratto.getAcconto();
			contratto.setCosto(costo);
			return costo;
		} catch (Exception e) {
			throw new CarloanException("Impossibile calcolare costo del contratto");
		}
	}
	

	/**
	 * Filtra i contratti restituendo solo quelli di interesse per l'agenzia attuale.
	 * @param agenzia
	 * @return Lista dei contratti filtrati
	 */
	public List<Contratto> filtra(Agenzia agenzia) {
		List<Contratto> contrattiFiltrati = dao.readAll();
		synchronized(contrattiFiltrati) {
			for (Iterator<Contratto> ic = contrattiFiltrati.iterator(); ic.hasNext(); ) {
				Contratto c = ic.next();
				if (c.getAgenziaConsegna().getId() != agenzia.getId() && c.getAgenziaNoleggio().getId() != agenzia.getId()) {
					ic.remove();
				}
			}
		}
		return contrattiFiltrati;
	}
}
