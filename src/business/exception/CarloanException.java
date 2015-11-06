package business.exception;

import presentation.gui.CarloanMessage;
import javafx.scene.control.Alert.AlertType;

@SuppressWarnings("serial")
public class CarloanException extends Exception {
	
	/**
	 * Costruttore vuoto
	 */
	public CarloanException(){}
	
	/**
	 * Costruttore Stringa
	 * @param message
	 */
	public CarloanException(String message) {
		super(message);
	}
	
	/**
	 * Mostra una schermata di errore
	 */
	public void showError() {
		CarloanMessage.showMessage(AlertType.ERROR, getMessage());
		return;
	}
}
