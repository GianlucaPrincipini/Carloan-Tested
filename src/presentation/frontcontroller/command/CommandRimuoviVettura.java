package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceVettura;
import business.entity.Vettura;
import business.exception.CarloanException;

public class CommandRimuoviVettura implements Command<Vettura>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vettura execute(Vettura entity) throws CarloanException {
		try {
			new ApplicationServiceVettura().delete(entity);
		} catch (InstantiationException | IllegalAccessException e) {
			throw new CarloanException(e.getMessage());
		}
		return null;
	}

}
