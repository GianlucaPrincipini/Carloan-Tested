package business.checker;

import java.util.List;

import integration.dao.DAOContratto;

import org.joda.time.LocalDate;

import business.entity.Contratto;
import business.entity.StatoVettura;
import business.entity.Vettura;
import business.exception.IntegrityException;

public class CheckerVettura implements Checker<Vettura>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void check(Vettura entity) throws IntegrityException {
		if (entity.getTarga() == null) throw new IntegrityException("Targa non inserita");
		if (entity.getModello() == null) throw new IntegrityException("Modello non specificato");
		if (entity.getAgenziaLocalizzazione() == null) throw new IntegrityException("Agenzia di localizzazione non indicata");
		if (entity.getStato() == null) throw new IntegrityException("Stato della vettura non indicato");
		if (entity.getChilometraggio() < 0) throw new IntegrityException("Il chilometraggio non può essere negativo");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void isModifiable(Vettura entity) throws IntegrityException {
		if (!isAvailable(null, entity, LocalDate.now(), LocalDate.now()))
			throw new IntegrityException();
	}
	
	/**
	 * Stabilisce se la vettura è disponibile oppure no nel periodo di tempo passato in input
	 * @param entity Vettura per cui effettuare il controllo
	 * @param inizio del periodo
	 * @param fine del periodo
	 * @return vero se è disponibile, falso se non lo è
	 */

	public boolean isAvailable(Contratto contratto, Vettura entity, LocalDate inizio, LocalDate fine) {
		
		if (inizio.isEqual(LocalDate.now()) && fine.isEqual(LocalDate.now())) {
			if (entity.getStato() == StatoVettura.DISPONIBILE) return true;
			else return false;
		} else {
			List<Contratto> contratti = new DAOContratto().readAll();
			for (Contratto c:contratti) {
				if (contratto != null && c.getId() == contratto.getId()) continue;
				
				if (c.getVettura().getTarga().equals(entity.getTarga())) {
					if (inizio.isBefore(c.getDataFineNoleggio()) && fine.isAfter(c.getDataInizioNoleggio())) {
						return false;
					} else if (inizio.isBefore(c.getDataInizioNoleggio()) && fine.isAfter(c.getDataInizioNoleggio())) {
						return false;
					} else if (inizio.isAfter(c.getDataInizioNoleggio()) && inizio.isBefore(c.getDataFineNoleggio())) {
						return false;
					} else if ((inizio.equals(c.getDataChiusura()) || inizio.isAfter(c.getDataFineNoleggio())) && c.getDataChiusura() == null){
						return false;
					}
				}
			}
		}
		return true;
	}

}
