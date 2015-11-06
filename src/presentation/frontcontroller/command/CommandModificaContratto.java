package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceContratto;
import business.entity.Contratto;
import business.exception.CarloanException;

public class CommandModificaContratto implements Command<Contratto> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Contratto execute(Contratto entity) throws CarloanException {
		try {
			new ApplicationServiceContratto().update(entity);
		} catch (Exception e) {
			throw new CarloanException(e.getMessage());
		}
		return entity;
	}
	
}
