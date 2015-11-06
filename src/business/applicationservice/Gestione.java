package business.applicationservice;

import java.util.List;

/**
 * 
 * Interfaccia usata per generalizzare le operazioni degli application service.
 * @param <Data>
 */
public interface Gestione<Data> {

	/**
	 * Richiama il Checker e il DAO, per controllare l'entità e aggiungerla al database
	 * @param entity
	 * @throws Exception
	 */
	public void create(Data entity) throws Exception;
	
	/**
	 * Controlla l'entità e aggiorna il database
	 * @param entity
	 * @throws Exception
	 */
	public void update(Data entity) throws Exception;
	
	/**
	 * rimuove l'entità dal database
	 * @param entity
	 * @throws Exception
	 */
	public void delete(Data entity) throws Exception;
	
	/**
	 *  Legge tutte le entità di un determinato tipo
	 * @return Lista delle entità richieste
	 */
	public List<Data> readAll();
	
	/**
	 * legge una singola entità dal database
	 * @param pk
	 * @return entità
	 */
	public Data read(String pk);
	
}
