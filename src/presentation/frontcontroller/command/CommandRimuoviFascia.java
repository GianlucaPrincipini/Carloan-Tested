package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceFascia;
import business.entity.Fascia;
import business.exception.CarloanException;

public class CommandRimuoviFascia implements Command<Fascia>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Fascia execute(Fascia entity) throws CarloanException {
		try {
			new ApplicationServiceFascia().delete(entity);
		} catch (InstantiationException | IllegalAccessException e) {
			throw new CarloanException(e.getMessage());
		}
		return null;
	}

}
