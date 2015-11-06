package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceOptional;
import business.entity.Optional;
import business.exception.CarloanException;

public class CommandRimuoviOptional implements Command<Optional>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional execute(Optional entity) throws CarloanException {
		try {
			new ApplicationServiceOptional().delete(entity);
		} catch (InstantiationException | IllegalAccessException e) {
			throw new CarloanException(e.getMessage());
		}
		return entity;
	}

}
