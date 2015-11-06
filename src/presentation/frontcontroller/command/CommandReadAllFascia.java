package presentation.frontcontroller.command;

import java.util.List;

import business.applicationservice.ApplicationServiceFascia;
import business.entity.Fascia;
import business.exception.CarloanException;

public class CommandReadAllFascia implements Command<List<Fascia>>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Fascia> execute(List<Fascia> entity) throws CarloanException {
		try {
			return new ApplicationServiceFascia().readAll();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new CarloanException(e.getMessage());

		}
	}
	
}
