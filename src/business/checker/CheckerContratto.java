package business.checker;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import business.entity.Contratto;
import business.entity.StatoVettura;
import business.exception.IntegrityException;
import business.exception.UnmodifiableEntityException;

public class CheckerContratto implements Checker<Contratto>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void check(Contratto entity) throws IntegrityException {
		if (entity.getOperatore() == null) throw new IntegrityException("Alcuni dati sono mancanti, assicurarsi del corretto inserimento.");
		if (entity.getVettura() == null) throw new IntegrityException("Alcuni dati sono mancanti, assicurarsi del corretto inserimento.");
		if (entity.getDataStipula() == null) throw new IntegrityException("Alcuni dati sono mancanti, assicurarsi del corretto inserimento.");
		if (entity.getDataInizioNoleggio() == null) throw new IntegrityException("Alcuni dati sono mancanti, assicurarsi del corretto inserimento.");
		if (entity.getDataFineNoleggio() == null) throw new IntegrityException("Alcuni dati sono mancanti, assicurarsi del corretto inserimento.");
		if (entity.getDataFineNoleggio().isBefore(entity.getDataInizioNoleggio())) throw new IntegrityException("Alcuni dati sono mancanti, assicurarsi del corretto inserimento.");
		if (entity.getVettura().getStato().equals(StatoVettura.MANUTENZIONE))  throw new IntegrityException("Alcuni dati sono mancanti, assicurarsi del corretto inserimento.");
		if (!new CheckerCliente().isAvailable(entity, entity.getCliente(), entity.getDataInizioNoleggio(), entity.getDataFineNoleggio())) throw new IntegrityException("Il cliente non è disponibile nel periodo di tempo indicato per il noleggi1o");
		if (!new CheckerVettura().isAvailable(entity, entity.getVettura(), entity.getDataInizioNoleggio(), entity.getDataFineNoleggio())) throw new IntegrityException("La vettura non è disponibile nel periodo di tempo indicato per il noleggio");
		if (entity.getCosto() == 0) throw new IntegrityException("Costo non calcolato");
	}
	
	/**
	 * Controlla i valori immessi prima di consentire la chiusura di un contratto
	 * @param entity
	 * @throws IntegrityException
	 */
	public void checkChiusura(Contratto entity) throws IntegrityException {
		if (entity.getDataChiusura().isBefore(entity.getDataFineNoleggio())) throw new IntegrityException("Un contratto non si può chiudere prima della fine del suo noleggio"); 
		if (entity.getCosto() == 0) throw new IntegrityException("Costo non calcolato");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void isModifiable(Contratto entity) throws IntegrityException {
		if ((LocalDate.now().equals(entity.getDataFineNoleggio()) || 
			LocalDate.now().isAfter(entity.getDataFineNoleggio())) && 
			!entity.isChiuso()) return;
		if (Days.daysBetween(LocalDate.now(), entity.getDataInizioNoleggio()).getDays() <= 2) throw new UnmodifiableEntityException("Il noleggio inizia tra due giorni o meno, il contratto non è modificabile");
	}
}
