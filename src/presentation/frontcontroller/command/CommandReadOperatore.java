package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceOperatore;
import business.entity.Operatore;
import business.exception.CarloanException;

@SuppressWarnings("rawtypes")
public class CommandReadOperatore implements Command{
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Operatore execute(Object entity) throws CarloanException {
		try {
			Operatore operatore = new ApplicationServiceOperatore().read((String) entity);
			if (operatore != null) return operatore;
			throw new CarloanException("Impossibile leggere l'operatore");
		} catch (InstantiationException | IllegalAccessException e) {
			throw new CarloanException(e.getMessage());
		}
	}
}
