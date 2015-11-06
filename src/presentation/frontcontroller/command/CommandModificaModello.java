package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceModello;
import business.entity.Modello;
import business.exception.CarloanException;

public class CommandModificaModello implements Command<Modello> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Modello execute(Modello entity) throws CarloanException {
		try {
			new ApplicationServiceModello().update(entity);
		} catch (Exception e) {
			throw new CarloanException(e.getMessage());
		}
		return entity;
	}
	
}
