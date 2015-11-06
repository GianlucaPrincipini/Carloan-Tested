package business.exception;

@SuppressWarnings("serial")
public class IntegrityException extends CarloanException{
	/**
	 * Costruttore vuoto
	 */
	public IntegrityException(){}
	/**
	 * Costruttore stringa
	 * @param message
	 */
	public IntegrityException(String message) {
		super(message);
	}

}
