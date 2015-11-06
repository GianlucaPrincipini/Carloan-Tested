package integration.dao;

import java.util.List;

public interface DAO<Data> {
	/**
	 * Aggiunge al DAO una tupla dell'entit� considerata
	 * @param entity
	 */
	public void create(Data entity);
	
	/**
	 * Aggiorna una tupla del DAO con l'entit� passata in input
	 * @param entity
	 */
	public void update(Data entity);
	
	/**
	 * Legge dal dao una particolare entit� 
	 * @param pk
	 * @return
	 */
	public Data read(String pk);
	/**
	 * Elimina dal database la tupla caratterizzata dalla chiave primaria passata in input
	 * @param pk chiave primaria dell'entit�
	 */
	public void delete(String pk);
	
	/**
	 * Legge tutte le tuple di una data entit�
	 * @return Lista delle entit�
	 */
	public List<Data> readAll();
	
}
