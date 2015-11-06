package presentation.frontcontroller.command;

import presentation.frontcontroller.CarloanFrontController;
import business.applicationservice.ApplicationServiceOperatore;
import business.entity.Operatore;
import business.exception.CarloanException;

public class CommandAggiungiOperatore implements Command<Operatore>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Operatore execute(Operatore entity) throws CarloanException {
		ApplicationServiceOperatore service;
		if (CarloanFrontController.getInstance().isAdmin()) {
			try{
				service = new ApplicationServiceOperatore();
				service.create(entity);
			} catch (Exception e) {
				throw new CarloanException(e.getMessage());
			}
		}
		return entity;
	}

}
