package integration.dao;


public class DAOFactory {
	/**
	 * Costruisce un'istanza di DAO definendo il parametro di tipo come il tipo di entit� passata in input 
	 * @param entityClass Classe dell'entit�
	 * @return DAO dell'entit�
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("rawtypes")
	public static DAO buildDao(Class<?> entityClass) throws InstantiationException, IllegalAccessException{
		String name = "integration.dao.DAO"+entityClass.getSimpleName();
		Class<?> dao = null;
		try {
			dao = Class.forName(name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return (DAO) dao.newInstance();
	}
}
