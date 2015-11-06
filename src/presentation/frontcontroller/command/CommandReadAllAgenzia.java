package presentation.frontcontroller.command;

import java.util.List;

import business.applicationservice.ApplicationServiceAgenzia;
import business.entity.Agenzia;
import business.exception.CarloanException;

public class CommandReadAllAgenzia implements Command<List<Agenzia>>{

	@Override
	public List<Agenzia> execute(List<Agenzia> entity) throws CarloanException {
		try {
			return new ApplicationServiceAgenzia().readAll();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new CarloanException(e.getMessage());

		}
	}
	
}
