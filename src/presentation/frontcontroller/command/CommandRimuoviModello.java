package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceModello;
import business.entity.Modello;
import business.exception.CarloanException;

public class CommandRimuoviModello implements Command<Modello>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Modello execute(Modello entity) throws CarloanException {
		try {
			new ApplicationServiceModello().delete(entity);
		} catch (InstantiationException | IllegalAccessException e) {
			throw new CarloanException(e.getMessage());
		}
		return null;
	}

}
