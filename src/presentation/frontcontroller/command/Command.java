package presentation.frontcontroller.command;

import business.exception.CarloanException;
import presentation.frontcontroller.Target;

public interface Command <T> extends Target{
	/**
	 * Si occupa dell'esecuzione del comando descritto dal nome della classe.
	 * @param entity
	 * @return Risultato del comando inviato
	 * @throws CarloanException
	 */
	public T execute (T entity) throws CarloanException;
}