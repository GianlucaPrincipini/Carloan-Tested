package presentation.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import presentation.gui.CarloanMessage;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import business.entity.Fascia;
import business.entity.Modello;
import business.entity.TipoCarburante;

public class SchermataModello extends SchermataDati<Modello>{

	@FXML
	private TextField marca;
	
	@FXML
	private TextField nome;
	
	@FXML
	private TextField capacit‡Bagagliaio;
	
	@FXML
	private TextField numeroPosti;
	
	@FXML
	private TextField potenza;
	
	@FXML
	private TextField emissioniCO2;
	
	@FXML 
	private TextField numeroPorte;
	
	@FXML
	private TextField peso;
	
	@FXML
	private Label fascia;
	
	@FXML
	private ChoiceBox<TipoCarburante> tipoCarburante;
	
	@FXML
	private CheckBox trasmissioneAutomatica;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tipoCarburante.setItems(FXCollections.observableArrayList(TipoCarburante.values()));
		tipoCarburante.getSelectionModel().select(0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onConferma() {
		Modello modello = buildEntity();
		if (modello.getFascia() == null) {
			Integer idFascia = (Integer) controller.processRequest("CalcolaFascia", buildEntity());
			if (idFascia != null) fascia.setText(Integer.toString(idFascia));
			modello = buildEntity();
		}
		if (modello != null) {
			if (edit) {
				controller.processRequest("ModificaModello", modello);
			} else {
				controller.processRequest("AggiungiModello", modello);
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
	public void initModifica(Modello entity) {
		edit = true;
		id = entity.getId();
		capacit‡Bagagliaio.setText(Integer.toString(entity.getCapacit‡Bagagliaio()));
		numeroPorte.setText(Integer.toString(entity.getNumeroPorte()));
		numeroPosti.setText(Integer.toString(entity.getNumeroPosti()));
		marca.setText(entity.getMarca());
		nome.setText(entity.getNome());
		emissioniCO2.setText(Double.toString(entity.getEmissioniCO2()));
		potenza.setText(Integer.toString(entity.getPotenza()));
		peso.setText(Integer.toString(entity.getPeso()));
		fascia.setText(Integer.toString(entity.getFascia().getId()));
		trasmissioneAutomatica.selectedProperty().set(entity.isTrasmissioneAutomatica());
	}

	/**
	 * Ascoltatore del pulsante di calcolo della fascia relativo al modello
	 */
	@FXML
	public void onCalcolaFascia() {
		Integer idFascia = (Integer) controller.processRequest("CalcolaFascia", buildEntity());
		if (idFascia != null) fascia.setText(Integer.toString(idFascia));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Modello buildEntity() {
		Modello modello = new Modello();
		try {
			if (id!=0) {
				modello.setId(id);
			}
			modello.setTipoCarburante(tipoCarburante.getSelectionModel().getSelectedItem());
			modello.setCapacit‡Bagagliaio(Integer.parseInt(capacit‡Bagagliaio.getText()));
			modello.setNumeroPorte(Integer.parseInt(numeroPorte.getText()));
			modello.setMarca(marca.getText());
			modello.setNome(nome.getText());
			modello.setEmissioniCO2(Double.parseDouble(emissioniCO2.getText()));
			modello.setNumeroPosti(Integer.parseInt(numeroPosti.getText()));
			modello.setPotenza(Integer.parseInt(potenza.getText()));
			modello.setPeso(Integer.parseInt(peso.getText()));
			modello.setTrasmissioneAutomatica(trasmissioneAutomatica.selectedProperty().get());
			if (!fascia.getText().isEmpty())
				modello.setFascia((Fascia) controller.processRequest("ReadFascia", fascia.getText()));
			else modello.setFascia(null);
			return modello;
		} catch (Exception e) {
			return null;
		}
	}

}
