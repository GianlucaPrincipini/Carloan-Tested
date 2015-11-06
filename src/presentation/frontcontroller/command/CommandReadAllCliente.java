package presentation.frontcontroller.command;

import java.util.List;

import business.applicationservice.ApplicationServiceCliente;
import business.entity.Cliente;
import business.exception.CarloanException;

public class CommandReadAllCliente implements Command<List<Cliente>>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Cliente> execute(List<Cliente> entity) throws CarloanException {
		try {
			return new ApplicationServiceCliente().readAll();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new CarloanException(e.getMessage());
		}
	}
	
}
