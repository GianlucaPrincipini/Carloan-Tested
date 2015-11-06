package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceOptional;
import business.entity.Optional;
import business.exception.CarloanException;

public class CommandAggiungiOptional implements Command<Optional>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional execute(Optional entity) throws CarloanException {
		ApplicationServiceOptional service;
		try{
			service = new ApplicationServiceOptional();
			service.create(entity);
		} catch (Exception e) {
			throw new CarloanException(e.getMessage());
		}
		return entity;
	}

}
