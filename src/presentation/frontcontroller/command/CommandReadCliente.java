package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceCliente;
import business.entity.Cliente;
import business.exception.CarloanException;

@SuppressWarnings("rawtypes")
public class CommandReadCliente implements Command {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cliente execute(Object entity) throws CarloanException {
		try {
			return new ApplicationServiceCliente().read((String) entity);
		} catch (InstantiationException | IllegalAccessException e) {
			throw new CarloanException(e.getMessage());
		}
	}
}
