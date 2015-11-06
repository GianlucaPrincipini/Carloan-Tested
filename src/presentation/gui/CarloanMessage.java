package presentation.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CarloanMessage {
	
	/**
	 * Richiama la schermata di avvertenza di Carloan
	 * @param message messaggio da visualizzare
	 * @param type tipo di schermata da visualizzare
	 */
	public static void showMessage(AlertType type, String message) {
		Alert alert = new Alert(type);
		alert.setTitle("Errore");
		alert.setContentText(message);
		alert.showAndWait();
	}
}
