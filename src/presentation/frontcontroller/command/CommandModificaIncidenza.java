package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceIncidenza;
import business.entity.IncidenzaFascia;
import business.exception.CarloanException;

public class CommandModificaIncidenza implements Command<IncidenzaFascia> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IncidenzaFascia execute(IncidenzaFascia entity) throws CarloanException {
		try{
			new ApplicationServiceIncidenza().update(entity);
		} catch(Exception e){
			throw new CarloanException(e.getMessage());
		}
		return entity;
	}

}
