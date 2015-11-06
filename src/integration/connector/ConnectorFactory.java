package integration.connector;

public class ConnectorFactory {
	/**
	 * Costruttore che si occupa di creare un MySQLConnector
	 * @param db
	 * @param user
	 * @param password
	 * @return Connector creato
	 */
	public static Connector buildMySQLConnector(String db, String user, String password) {
		return new MySQLConnector(db, user, password);
	}
}
