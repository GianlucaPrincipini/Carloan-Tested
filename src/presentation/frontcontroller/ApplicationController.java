package presentation.frontcontroller;


public interface ApplicationController {
	/**
	 * Gestisce una richiesta passata in input eventualmente trasferendo un entità
	 * @param request richiesta da eseguire
	 * @param entity
	 * @return Target Commmand o CarloanStage
	 */
	public Target handleRequest(String request, Object entity);
}
