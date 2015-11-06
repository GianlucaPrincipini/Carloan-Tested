package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceTariffario;
import business.entity.Tariffario;
import business.exception.CarloanException;

public class CommandModificaTariffario implements Command<Tariffario> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Tariffario execute(Tariffario entity) throws CarloanException {
		try {
			new ApplicationServiceTariffario().update(entity);
		} catch (Exception e) {
			throw new CarloanException(e.getMessage());
		}
		return entity;
	}
}
