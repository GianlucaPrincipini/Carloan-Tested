package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceModello;
import business.entity.Modello;
import business.exception.CarloanException;

public class CommandAggiungiModello implements Command<Modello>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Modello execute(Modello entity) throws CarloanException {
		ApplicationServiceModello service;
		try{
			service = new ApplicationServiceModello();
			service.create(entity);
		} catch (Exception e) {
			throw new CarloanException(e.getMessage());
		}
		return entity;
	}

}
