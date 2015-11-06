package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceOptional;
import business.entity.Optional;
import business.exception.CarloanException;

@SuppressWarnings("rawtypes")
public class CommandReadOptional implements Command {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional execute(Object entity) throws CarloanException {
		try {
			Optional optional = new ApplicationServiceOptional().read((String) entity);
			if (optional != null) return optional;
			throw new CarloanException("Impossibile leggere l'optional");
		} catch (InstantiationException | IllegalAccessException e) {
			throw new CarloanException(e.getMessage());
		}
	}

}
