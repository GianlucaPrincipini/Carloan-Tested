package presentation.frontcontroller.command;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import business.applicationservice.ApplicationServiceOperatore;
import business.entity.Operatore;
import business.exception.CarloanException;
import presentation.frontcontroller.CarloanFrontController;

public class CommandLogin implements Command<Operatore>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Operatore execute(Operatore entity) throws CarloanException {
		ApplicationServiceOperatore service;
		Operatore operatore = null;
		try {
			service = new ApplicationServiceOperatore();
			if (service.login(entity)) {
				operatore = service.read(entity.getUsername());
				CarloanFrontController.getInstance().setUserAuthenticated(operatore);
			}
		} catch (InstantiationException | IllegalAccessException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new CarloanException(e.getMessage());
		}
		return operatore;
	}

}
