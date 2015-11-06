package business.checker;

import business.entity.Operatore;
import business.exception.IntegrityException;

public class CheckerOperatore implements Checker<Operatore>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void check(Operatore entity) throws IntegrityException {
		if (entity.getUsername().equals("")) throw new IntegrityException("L'username non può essere vuoto");
		if (entity.getAgenzia() == null || entity.getAgenzia().getId() == 0) throw new IntegrityException("L'operatore deve lavorare in un'agenzia");
		if (entity.getNome().equals("")) throw new IntegrityException("Nome non valido");
		if (entity.getCognome().equals("")) throw new IntegrityException("Cognome non valido");
		if (!entity.getEMail().contains("@")) throw new IntegrityException("Email non valida");
		if (entity.getNumTelefono().equals("")) throw new IntegrityException("Numero non valido");
		if (entity.getPassword().equals("")) throw new IntegrityException("Password non valida");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void isModifiable(Operatore entity) throws IntegrityException {
	}

}
