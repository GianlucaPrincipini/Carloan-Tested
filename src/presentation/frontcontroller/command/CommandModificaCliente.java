package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceCliente;
import business.entity.Cliente;
import business.exception.CarloanException;

public class CommandModificaCliente implements Command<Cliente>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cliente execute(Cliente entity) throws CarloanException {
		try {
			new ApplicationServiceCliente().update(entity);
		} catch (InstantiationException | IllegalAccessException e) {
			throw new CarloanException(e.getMessage());

		}
		return entity;
	}

}
