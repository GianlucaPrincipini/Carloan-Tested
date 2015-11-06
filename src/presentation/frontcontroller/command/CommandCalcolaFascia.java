package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceModello;
import business.entity.Modello;
import business.exception.CarloanException;
import business.exception.FasciaIndexException;

@SuppressWarnings("rawtypes")
public class CommandCalcolaFascia implements Command{

	/**
	 * Command che si occupa di calcolare la fascia di un modello
	 * {@inheritDoc}
	 */
	@Override
	public Object execute(Object entity) throws CarloanException {
		try {
			new ApplicationServiceModello().calcolaFascia((Modello) entity);
		} catch (FasciaIndexException | InstantiationException
				| IllegalAccessException e) {
			throw new CarloanException(e.getMessage());
		}
		return ((Modello)entity).getFascia().getId();
	}
	
}
