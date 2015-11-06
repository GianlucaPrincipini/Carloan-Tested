package presentation.frontcontroller.command;

import java.util.List;

import business.applicationservice.ApplicationServiceOperatore;
import business.entity.Operatore;
import business.exception.CarloanException;

public class CommandReadAllOperatore implements Command<List<Operatore>>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Operatore> execute(List<Operatore> entity) throws CarloanException {
		try {
			return new ApplicationServiceOperatore().readAll();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new CarloanException(e.getMessage());
		}
	}

}
