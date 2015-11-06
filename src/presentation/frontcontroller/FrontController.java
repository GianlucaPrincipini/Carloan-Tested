package presentation.frontcontroller;

public interface FrontController {
	
	/**
	 * Invia una richiesta senza bisogno di passare un entit�
	 */
	public Object processRequest(String request);
	
	/**
	 * Invia una richiesta con trasferimento di entit�
	 */
	public Object processRequest(String request, Object entity);
}
