package presentation.frontcontroller.command;

import java.util.List;

import presentation.frontcontroller.CarloanFrontController;
import business.applicationservice.ApplicationServiceVettura;
import business.entity.Vettura;
import business.exception.CarloanException;

public class CommandReadAllVettura implements Command<List<Vettura>>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Vettura> execute(List<Vettura> entity) throws CarloanException {
		try {
			if (CarloanFrontController.getInstance().isAdmin())
				return new ApplicationServiceVettura().readAll();
			else 
				return new ApplicationServiceVettura().filtra(CarloanFrontController.getInstance().getUserAuthenticated().getAgenzia());
		} catch (InstantiationException | IllegalAccessException e) {
			throw new CarloanException(e.getMessage());
		}
	}
	
}
