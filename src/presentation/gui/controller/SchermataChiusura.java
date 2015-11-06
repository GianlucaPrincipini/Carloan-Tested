package presentation.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import business.entity.Contratto;
import presentation.frontcontroller.CarloanFrontController;
import presentation.frontcontroller.FrontController;
import utils.DateHelper;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SchermataChiusura extends SchermataDati<Contratto> {

	private FrontController controller = CarloanFrontController.getInstance();
	
	
	private Contratto contratto;
	
	@FXML
	private Node root;

	@FXML
	private DatePicker dataChiusura;
	
	@FXML
	private TextField chilometriPercorsi;
	
	@FXML
	private Label costo;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dataChiusura.setValue(java.time.LocalDate.now());
		chilometriPercorsi.setDisable(true);
	}
	
	/**
	 * Ascoltatore del pulsante per calcolare il costo finale del contratto
	 */
	@FXML
	public void onCalcolaCosto() {
		Contratto contratto = buildEntity();
		if (contratto != null)
		costo.setText(Double.toString((Double) controller.processRequest("CalcolaCostoChiusura", contratto)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onConferma() {
		Contratto contratto = buildEntity();
		if (contratto != null) {
			controller.processRequest("ChiudiContratto", contratto);
			close();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initModifica(Contratto entity) {
		contratto = entity;
		if (contratto.isChilometraggioLimitato()) {
			chilometriPercorsi.setDisable(false);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Contratto buildEntity() {
		Contratto c = contratto;
		try {
			c.setDataChiusura(DateHelper.dateParse(dataChiusura.getValue()));
			c.setChilometriPercorsi(Integer.parseInt(chilometriPercorsi.getText()));
			if (costo.getText()!=null) {
				costo.setText(Double.toString((Double) controller.processRequest("CalcolaCostoChiusura", c)));
				c.setCosto(Double.parseDouble(costo.getText()));
			}
			return c;
		} catch (Exception e) {
			return null;
		}
	}

}
