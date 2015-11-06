package business.checker;

import business.entity.Agenzia;
import business.exception.IntegrityException;

public class CheckerAgenzia implements Checker<Agenzia>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void check(Agenzia entity) throws IntegrityException {
		if (entity.getCittà() == null || entity.getIndirizzo() == null || entity.getNumTelefono() == null) throw new IntegrityException("Alcuni dati sono mancanti, assicurarsi del corretto inserimento.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void isModifiable(Agenzia entity) throws IntegrityException {
		
	}

}
