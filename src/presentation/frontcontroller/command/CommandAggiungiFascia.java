package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceFascia;
import business.entity.Fascia;
import business.exception.CarloanException;

public class CommandAggiungiFascia implements Command<Fascia>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Fascia execute(Fascia entity) throws CarloanException {
		ApplicationServiceFascia service;
		try{
			service = new ApplicationServiceFascia();
			service.create(entity);
		} catch (Exception e) {
			throw new CarloanException(e.getMessage());
		}
		return entity;
	}

}
