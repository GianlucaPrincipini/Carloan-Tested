package presentation.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import presentation.frontcontroller.CarloanFrontController;
import presentation.frontcontroller.FrontController;
import presentation.gui.CarloanMessage;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import business.entity.Agenzia;

public class SchermataAgenzia extends SchermataDati<Agenzia>{

	private FrontController controller;
	
	@FXML
	private TextField città;
	
	@FXML
	private TextField indirizzo;
	
	@FXML
	private TextField numTelefono;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		controller = CarloanFrontController.getInstance();		
	}

	/**
	 * {@inheritDoc}
	 */
	@FXML
	@Override
	public void onConferma() {
		Agenzia agenzia = buildEntity();
		if (agenzia!=null) {
			if (edit) {
				controller.processRequest("ModificaAgenzia", agenzia);
			} else {
				controller.processRequest("AggiungiAgenzia", agenzia);
			}
			close();
		}else {
			CarloanMessage.showMessage(AlertType.WARNING, "I dati immessi non sono corretti");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initModifica(Agenzia entity) {
		edit = true;
		id = entity.getId();
		città.setText(entity.getCittà());
		indirizzo.setText(entity.getIndirizzo());
		numTelefono.setText(entity.getNumTelefono());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Agenzia buildEntity() {
		Agenzia agenzia;
		try {
			agenzia= new Agenzia();
			if (id != 0) {
				agenzia.setId(id);
			}
			if (città.getText().isEmpty()|| indirizzo.getText().isEmpty() || numTelefono.getText().isEmpty()) return null;
			agenzia.setCittà(città.getText());
			agenzia.setIndirizzo(indirizzo.getText());
			agenzia.setNumTelefono(numTelefono.getText());
			return agenzia;
		} catch (Exception e) {
			return null;
		}
	}

}
