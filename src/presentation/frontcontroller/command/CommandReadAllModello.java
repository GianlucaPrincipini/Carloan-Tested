package presentation.frontcontroller.command;

import java.util.List;

import business.applicationservice.ApplicationServiceModello;
import business.entity.Modello;
import business.exception.CarloanException;

public class CommandReadAllModello implements Command<List<Modello>>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Modello> execute(List<Modello> entity) throws CarloanException {
		try {
			return new ApplicationServiceModello().readAll();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new CarloanException(e.getMessage());

		}
	}
	
}
