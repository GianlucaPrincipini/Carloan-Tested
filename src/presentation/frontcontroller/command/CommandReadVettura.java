package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceVettura;
import business.entity.Vettura;
import business.exception.CarloanException;

@SuppressWarnings("rawtypes")
public class CommandReadVettura implements Command{
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vettura execute(Object entity) throws CarloanException {
		try {
			Vettura vettura = new ApplicationServiceVettura().read((String) entity);
			if (vettura != null) return vettura;
			throw new CarloanException("Impossibile leggere la vettura");
		} catch (InstantiationException | IllegalAccessException e) {
			throw new CarloanException(e.getMessage());
		}
	}
}
