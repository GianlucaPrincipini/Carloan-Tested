package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceAgenzia;
import business.entity.Agenzia;
import business.exception.CarloanException;

@SuppressWarnings("rawtypes")
public class CommandReadAgenzia implements Command{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Agenzia execute(Object entity) throws CarloanException {
		try {
			Agenzia agenzia = new ApplicationServiceAgenzia().read((String) entity);
			 if (agenzia!=null) return agenzia;
			 throw new CarloanException("Impossibile leggere l'agenzia");
		} catch (InstantiationException | IllegalAccessException e) {
			throw new CarloanException(e.getMessage());
		}
	}

}
