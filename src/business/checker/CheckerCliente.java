package business.checker;

import java.util.List;

import integration.dao.DAOContratto;

import org.joda.time.LocalDate;
import org.joda.time.Years;

import business.entity.Cliente;
import business.entity.Contratto;
import business.exception.IntegrityException;
import business.exception.UnmodifiableEntityException;

public class CheckerCliente implements Checker<Cliente>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void check(Cliente entity) throws IntegrityException {
		if (entity.getCodicePatente() == null) throw new IntegrityException("Codice patente non inserito");
		if (entity.getNome() == null) throw new IntegrityException("Nome non inserito");
		if (entity.getCognome() == null) throw new IntegrityException("Cognome non inserito");
		if (entity.getDataNascita() == null) throw new IntegrityException("Data di nascita non inserita");
		if (entity.getEMail() == null || entity.getNumTelefono()  == null) throw new IntegrityException("Deve essere inserito almeno un recapitoo");
		if (Years.yearsBetween(entity.getDataNascita(), LocalDate.now()).getYears() < 18) throw new IntegrityException("Il cliente è minorenne");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void isModifiable(Cliente entity) throws UnmodifiableEntityException {
		// Un cliente può essere modificato se non ha un noleggio attivo
		if (!isAvailable(null, entity, LocalDate.now(), LocalDate.now()))
			throw new UnmodifiableEntityException("Il clente è impegnato in un noleggio e le sue informazioni non possono essere modificate");
	}
	
	/**
	 * Funzione che verifica se un cliente è disponibile nell'intervallo di tempo
	 * @param entity cliente
	 * @param inizio data inizio periodo di verifica
	 * @param fine data fine periodo di verifica
	 * @return vero se il cliente ha un noleggio attivo nell'intervallo di tempo tra inizio e fine
	 */
	public boolean isAvailable(Contratto contratto, Cliente entity, LocalDate inizio, LocalDate fine) {
		List<Contratto> contratti = new DAOContratto().readAll();
		for (Contratto c:contratti) {
			if (contratto != null && contratto.getId() == c.getId()) continue;
			if (c.getCliente().getCodicePatente().equals(entity.getCodicePatente())) {
				if (inizio.isBefore(c.getDataFineNoleggio()) && fine.isAfter(c.getDataInizioNoleggio())) {
					return false;
				}
				if (inizio.isBefore(c.getDataInizioNoleggio()) && fine.isAfter(c.getDataInizioNoleggio())) {
					return false;
				}
				if (inizio.isAfter(c.getDataInizioNoleggio()) && inizio.isBefore(c.getDataFineNoleggio())) {
					return false;
				}
				if ((inizio.equals(c.getDataChiusura()) || inizio.isAfter(c.getDataFineNoleggio()))
						&& c.getDataChiusura() == null)
					return false;
				
			}
		}
		return true;
	}
	
}
