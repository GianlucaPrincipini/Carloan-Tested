package presentation.frontcontroller.command;

import java.util.List;

import business.applicationservice.ApplicationServiceOptional;
import business.entity.Optional;
import business.exception.CarloanException;

public class CommandReadAllOptional implements Command<List<Optional>>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Optional> execute(List<Optional> entity) throws CarloanException {
		try {
			return new ApplicationServiceOptional().readAll();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new CarloanException(e.getMessage());
		}
	}
	
}
