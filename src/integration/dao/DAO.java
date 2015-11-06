package integration.dao;

import java.util.List;

public interface DAO<Data> {
	/**
	 * Aggiunge al DAO una tupla dell'entità considerata
	 * @param entity
	 */
	public void create(Data entity);
	
	/**
	 * Aggiorna una tupla del DAO con l'entità passata in input
	 * @param entity
	 */
	public void update(Data entity);
	
	/**
	 * Legge dal dao una particolare entità 
	 * @param pk
	 * @return
	 */
	public Data read(String pk);
	/**
	 * Elimina dal database la tupla caratterizzata dalla chiave primaria passata in input
	 * @param pk chiave primaria dell'entità
	 */
	public void delete(String pk);
	
	/**
	 * Legge tutte le tuple di una data entità
	 * @return Lista delle entità
	 */
	public List<Data> readAll();
	
}
