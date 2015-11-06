package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceOptional;
import business.entity.Optional;
import business.exception.CarloanException;

public class CommandModificaOptional implements Command<Optional> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional execute(Optional entity) throws CarloanException {
		try {
			new ApplicationServiceOptional().update(entity);
		} catch (Exception e) {
			throw new CarloanException(e.getMessage());
		}
		return entity;
	}
	
}
