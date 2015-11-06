package business.applicationservice;

import java.util.List;

/**
 * 
 * Interfaccia usata per generalizzare le operazioni degli application service.
 * @param <Data>
 */
public interface Gestione<Data> {

	/**
	 * Richiama il Checker e il DAO, per controllare l'entit� e aggiungerla al database
	 * @param entity
	 * @throws Exception
	 */
	public void create(Data entity) throws Exception;
	
	/**
	 * Controlla l'entit� e aggiorna il database
	 * @param entity
	 * @throws Exception
	 */
	public void update(Data entity) throws Exception;
	
	/**
	 * rimuove l'entit� dal database
	 * @param entity
	 * @throws Exception
	 */
	public void delete(Data entity) throws Exception;
	
	/**
	 *  Legge tutte le entit� di un determinato tipo
	 * @return Lista delle entit� richieste
	 */
	public List<Data> readAll();
	
	/**
	 * legge una singola entit� dal database
	 * @param pk
	 * @return entit�
	 */
	public Data read(String pk);
	
}
