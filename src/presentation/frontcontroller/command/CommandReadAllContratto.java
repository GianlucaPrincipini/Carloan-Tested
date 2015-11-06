package presentation.frontcontroller.command;

import java.util.List;

import presentation.frontcontroller.CarloanFrontController;
import business.applicationservice.ApplicationServiceContratto;
import business.entity.Contratto;
import business.exception.CarloanException;

public class CommandReadAllContratto implements Command<List<Contratto>>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Contratto> execute(List<Contratto> entity) throws CarloanException {
		try {
			if (CarloanFrontController.getInstance().isAdmin())
				return new ApplicationServiceContratto().readAll();
			else 
				return new ApplicationServiceContratto().filtra(CarloanFrontController.getInstance().getUserAuthenticated().getAgenzia());
		} catch (InstantiationException | IllegalAccessException e) {
			throw new CarloanException(e.getMessage());

		}
	}
	
}
