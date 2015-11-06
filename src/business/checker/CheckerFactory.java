package business.checker;

/**
 * Factory del Checker. Separa la procedura di creazione da quella di esecuzione
 * @author Stefano
 *
 */
public class CheckerFactory {
	@SuppressWarnings("rawtypes")
	/**
	 * Costruisce il Checker del tipo di entità passata in input
	 * @param entityClass
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static Checker buildChecker(Class entityClass) throws InstantiationException, IllegalAccessException {
		String name = "business.checker.Checker" + entityClass.getSimpleName();
		Class checker = null;
		try {
			checker = Class.forName(name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return (Checker) checker.newInstance();
	}
}
