package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceAgenzia;
import business.entity.Agenzia;
import business.exception.CarloanException;

public class CommandAggiungiAgenzia implements Command<Agenzia>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Agenzia execute(Agenzia entity) throws CarloanException {
		ApplicationServiceAgenzia service;
		try{
			service = new ApplicationServiceAgenzia();
			service.create(entity);
		} catch (Exception e) {
			throw new CarloanException(e.getMessage());
		}
		return entity;
	}

}
