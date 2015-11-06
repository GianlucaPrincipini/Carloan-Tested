package presentation.frontcontroller;

import presentation.frontcontroller.command.Command;
import presentation.gui.CarloanSelezione;
import presentation.gui.CarloanStage;
import business.entity.Amministratore;
import business.entity.Operatore;
import business.exception.CarloanException;

public class CarloanFrontController implements FrontController{

	/**
	 * Operatore autenticato
	 */
	private static Operatore userAuthenticated;
	
	/**
	 * Istanza statica della classe stessa
	 */
	private static CarloanFrontController frontController;

	/**
	 * Costruttore della classe singleton
	 */
	private CarloanFrontController(){};
	
	/**
	 * Verifica se l'operatore è un amministratore
	 * @return vero se è un amministratore
	 */
	public boolean isAdmin() {
		return userAuthenticated instanceof Amministratore;
	}
	
	/**
	 * ritorna un'istanza dell'operatore
	 * @return
	 */
	public Operatore getUserAuthenticated() {
		return userAuthenticated;
	}
	
	/**
	 * imposta l'operatore
	 * @param userAuthenticated
	 */
	public void setUserAuthenticated(Operatore userAuthenticated) {
		CarloanFrontController.userAuthenticated = userAuthenticated;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	public Object processRequest(String request) {
		return processRequest(request, null);
	}
	
	/**
	 * Ritorna un'istanza del frontcontroller
	 * @return CarloanFrontController
	 */
	public static CarloanFrontController getInstance() {
		if (frontController == null) {
			frontController = new CarloanFrontController();
		}
		return frontController;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object processRequest(String request, Object entity) {
		try {
			ApplicationController ac =  new CarloanApplicationController();
			Target targetRequest = ac.handleRequest(request, entity);
			if (targetRequest instanceof Command) {
				return ((Command) targetRequest).execute(entity);
			} else if (targetRequest instanceof CarloanSelezione) {
				CarloanSelezione target = (CarloanSelezione) targetRequest;
				target.showAndWait();
				return target.getValue();
			} else if (targetRequest instanceof CarloanStage){
				return ((CarloanStage)targetRequest).showStage();
			}
		} catch (CarloanException e) {
			e.showError();
		}
		return null;
	}
}
