package business.checker;
import business.exception.*;

/**
 * Interfaccia del Checker, generalizzata per ciascuna entità
 * @author Stefano
 *
 * @param <Data>
 */
public interface Checker<Data> {
	
	/**
	 * Controlla la validità dei campi relativi all'entità.
	 * @param entity entità da controllare.
	 */
	public void check(Data entity) throws IntegrityException;
	
	/**
	 * Controlla se un'entità è modificabile oppure no.
	 * @param entity entità da controllare.
	 */
	public void isModifiable(Data entity) throws IntegrityException;
}
