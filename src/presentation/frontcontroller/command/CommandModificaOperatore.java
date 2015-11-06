package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceOperatore;
import business.entity.Operatore;
import business.exception.CarloanException;

public class CommandModificaOperatore implements Command<Operatore> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Operatore execute(Operatore entity) throws CarloanException {
		try {
			new ApplicationServiceOperatore().update(entity);
		} catch (Exception e) {
			throw new CarloanException(e.getMessage());
		}
		return entity;
	}
	
}
