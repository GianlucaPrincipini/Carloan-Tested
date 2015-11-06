package presentation.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import presentation.gui.CarloanMessage;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import business.entity.Optional;

public class SchermataOptional extends SchermataDati<Optional>{

	@FXML
	private TextField tipo;
	
	@FXML
	private TextField costo;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onConferma() {
		Optional optional = buildEntity();
		if (optional != null) {
			if (edit) {
				controller.processRequest("ModificaOptional", buildEntity());
			} else {
				controller.processRequest("AggiungiOptional", buildEntity());
			}
			close();
		} else {
			CarloanMessage.showMessage(AlertType.WARNING, "I dati immessi non sono corretti");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initModifica(Optional entity) {
		edit = true;
		id = entity.getId();
		tipo.setText(entity.getTipo());
		costo.setText(Double.toString(entity.getCosto()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Optional buildEntity() {
		Optional optional;
		try {
			optional = new Optional();
			optional.setId(id);
			optional.setTipo(tipo.getText());
			optional.setCosto(Double.parseDouble(costo.getText()));
			return optional;
		} catch (Exception e) {
			return null;
		}
	}

}
