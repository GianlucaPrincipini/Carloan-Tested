package integration.connector;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Interfaccia per il connettore del database
 * @author Stefano
 *
 */
public interface Connector {
	
	/**
	 * Esegue una query di lettura
	 * @param query
	 * @return Risultato della query
	 */
    public abstract ResultSet executeReadQuery(String query);
    
    /**
     * esegue una query di aggiornamento
     * @param query
     * @return Risultato della query
     */
    public abstract ResultSet executeUpdateQuery(String query);

    public void finalize() throws SQLException;
}