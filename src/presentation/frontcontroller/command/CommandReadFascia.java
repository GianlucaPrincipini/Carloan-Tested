package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceFascia;
import business.entity.Fascia;
import business.exception.CarloanException;

@SuppressWarnings("rawtypes")
public class CommandReadFascia implements Command {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Fascia execute(Object entity) throws CarloanException {
		try {
			Fascia fascia = new ApplicationServiceFascia().read((String) entity);
			if (fascia != null) return fascia;
			throw new CarloanException("Impossibile leggere la fascia");
		} catch (InstantiationException | IllegalAccessException e) {
			throw new CarloanException(e.getMessage());
		}
	}

}
