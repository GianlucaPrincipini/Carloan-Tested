package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceAgenzia;
import business.entity.Agenzia;
import business.exception.CarloanException;

public class CommandRimuoviAgenzia implements Command<Agenzia>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Agenzia execute(Agenzia entity) throws CarloanException {
		try {
			new ApplicationServiceAgenzia().delete(entity);
		} catch (InstantiationException | IllegalAccessException e) {
			throw new CarloanException(e.getMessage());
		}
		return entity;
	}

}
