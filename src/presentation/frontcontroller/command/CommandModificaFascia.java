package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceFascia;
import business.entity.Fascia;
import business.exception.CarloanException;

public class CommandModificaFascia implements Command<Fascia> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Fascia execute(Fascia entity) throws CarloanException {
		try {
			new ApplicationServiceFascia().update(entity);
		} catch (Exception e) {
			throw new CarloanException(e.getMessage());
		}
		return entity;
	}
	
}
