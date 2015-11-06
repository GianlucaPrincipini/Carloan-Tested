package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceContratto;
import business.entity.Contratto;
import business.exception.CarloanException;

public class CommandChiudiContratto implements Command<Contratto> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Contratto execute(Contratto entity) throws CarloanException {
		try {
			new ApplicationServiceContratto().chiudi(entity);
		} catch (Exception e) {
			throw new CarloanException(e.getMessage());
		}
		return entity;
	}
	
}
