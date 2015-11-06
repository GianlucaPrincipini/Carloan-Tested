package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceVettura;
import business.entity.Vettura;
import business.exception.CarloanException;

public class CommandModificaVettura implements Command<Vettura> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vettura execute(Vettura entity) throws CarloanException {
		try {
			new ApplicationServiceVettura().update(entity);
		} catch (Exception e) {
			throw new CarloanException(e.getMessage());
		}
		return entity;
	}
	
}
