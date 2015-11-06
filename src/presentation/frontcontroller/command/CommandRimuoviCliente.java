package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceCliente;
import business.entity.Cliente;
import business.exception.CarloanException;

public class CommandRimuoviCliente implements Command<Cliente>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cliente execute(Cliente entity) throws CarloanException {
		try {
			new ApplicationServiceCliente().delete(entity);
		} catch (InstantiationException | IllegalAccessException e) {
			throw new CarloanException(e.getMessage());
		}
		return null;
	}

}
