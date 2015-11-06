package business.exception;

@SuppressWarnings("serial")
public class UnmodifiableEntityException extends IntegrityException {
	
	/**
	 * Costruttore stringa
	 * @param string
	 */
	public UnmodifiableEntityException(String string) {
		super(string);
	}

}
