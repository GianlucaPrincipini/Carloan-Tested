package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceVettura;
import business.entity.Vettura;
import business.exception.CarloanException;

public class CommandAggiungiVettura implements Command<Vettura>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vettura execute(Vettura entity) throws CarloanException {
		ApplicationServiceVettura service;
		try{
			service = new ApplicationServiceVettura();
			service.create(entity);
		} catch (Exception e) {
			throw new CarloanException(e.getMessage());
		}
		return entity;
	}

}
