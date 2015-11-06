package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceOperatore;
import business.entity.Operatore;
import business.exception.CarloanException;

public class CommandRimuoviOperatore implements Command<Operatore>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Operatore execute(Operatore entity) throws CarloanException {
		try {
			new ApplicationServiceOperatore().delete(entity);
		} catch (InstantiationException | IllegalAccessException e) {
			throw new CarloanException(e.getMessage());
		}
		return null;
	}

}
