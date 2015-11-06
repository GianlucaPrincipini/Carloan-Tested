package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceAgenzia;
import business.entity.Agenzia;
import business.exception.CarloanException;

public class CommandModificaAgenzia implements Command<Agenzia> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Agenzia execute(Agenzia entity) throws CarloanException {
		try {
			new ApplicationServiceAgenzia().update(entity);
		} catch (Exception e) {
			throw new CarloanException(e.getMessage());
		}
		return entity;
	}
	
}
