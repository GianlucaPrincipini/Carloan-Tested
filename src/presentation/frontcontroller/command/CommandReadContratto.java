package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceContratto;
import business.entity.Contratto;
import business.exception.CarloanException;

@SuppressWarnings("rawtypes")
public class CommandReadContratto implements Command{
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Contratto execute(Object entity) throws CarloanException {
		try {
			Contratto contratto = new ApplicationServiceContratto().read((String) entity);
			if (contratto != null) return contratto;
			throw new CarloanException("Impossibile leggere il contratto");
		} catch (InstantiationException | IllegalAccessException e) {
			throw new CarloanException(e.getMessage());
		}
	}
}
