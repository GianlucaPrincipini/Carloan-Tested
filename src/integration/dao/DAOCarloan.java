package integration.dao;


import integration.connector.ConnectorFactory;
import integration.connector.Connector;

public abstract class DAOCarloan<Data> implements DAO<Data> {
	/**
	 * URL del database
	 */
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/carloan";
    /**
     * Username usato per l'autenticazione
     */
    private static final String DATABASE_USER = "CarloanUser";
    /**
     * Password per l'accesso 
     */
    private static final String DATABASE_PASSWORD = "carloan15";
    /**
     * Connessione al database MySQL
     */
	protected Connector connection = ConnectorFactory.buildMySQLConnector(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);

}


