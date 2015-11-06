package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceModello;
import business.entity.Modello;
import business.exception.CarloanException;

@SuppressWarnings("rawtypes")
public class CommandReadModello implements  Command{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Modello execute(Object entity) throws CarloanException {
		try {
			Modello modello = new ApplicationServiceModello().read((String) entity);
			if (modello!= null) return modello;
			throw new CarloanException("Impossibile leggere il modello");
		} catch (InstantiationException | IllegalAccessException e) {
			throw new CarloanException(e.getMessage());
		}
	}

}
