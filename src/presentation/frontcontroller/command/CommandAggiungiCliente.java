package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceCliente;
import business.entity.Cliente;
import business.exception.CarloanException;

public class CommandAggiungiCliente implements Command<Cliente>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cliente execute(Cliente entity) throws CarloanException {
		ApplicationServiceCliente service;
		try{
			service = new ApplicationServiceCliente();
			service.create(entity);
		} catch (Exception e) {
			throw new CarloanException(e.getMessage());
		}
		return entity;
	}

}
