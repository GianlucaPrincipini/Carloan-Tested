package presentation.gui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import presentation.frontcontroller.CarloanFrontController;
import presentation.gui.CarloanMessage;
import utils.DateHelper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import business.entity.Agenzia;
import business.entity.Cliente;
import business.entity.Contratto;
import business.entity.Operatore;
import business.entity.Optional;
import business.entity.Rifornimento;
import business.entity.Vettura;

public class SchermataContratto extends SchermataDati<Contratto>{
	
	@FXML
	private TextField acconto;
	
	@FXML
	private TextField chilometriPrevisti;
	
	@FXML
	private DatePicker dataInizioNoleggio;
	
	@FXML
	private DatePicker dataFineNoleggio;

	@FXML
	private DatePicker dataStipula;
	
	@FXML
	private Label operatore;
	
	@FXML
	private Label costo;
	
	@FXML
	private Label cliente;
	
	@FXML
	private Label vettura;
	
	@FXML
	private Label agenziaNoleggio;
	
	@FXML
	private Label agenziaConsegna;
	
	@FXML
	private CheckBox assicurazioneAvanzata;
	
	@FXML
	private CheckBox chilometraggioLimitato;
	
	@FXML
	private ChoiceBox<Rifornimento> rifornimento;
	
	@FXML
	private ListView<String> optionals;
	
	@FXML
	private ObservableList<String> listaOptionals;

	/**
	 * Chiama la schermata per la selezione della vettura
	 */
	@FXML
	public void onSelezioneVettura() {
		vettura.setText((String) controller.processRequest("MostraSelezione", "Vettura"));
	}
	
	/**
	 * Chiama la schermata per la selezione del cliente
	 */
	@FXML
	public void onSelezioneCliente() {
		cliente.setText((String) controller.processRequest("MostraSelezione", "Cliente"));
	}
	
	/**
	 * Chiama la schermata per la selezione dell'agenzia di consegna
	 */
	@FXML
	public void onSelezioneAgenziaConsegna() {
		agenziaConsegna.setText((String) controller.processRequest("MostraSelezione", "Agenzia"));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		acconto.setText("0");
		dataStipula.setValue(java.time.LocalDate.now());
		agenziaNoleggio.setText(Integer.toString(((CarloanFrontController)controller).getUserAuthenticated().getAgenzia().getId()));
		chilometriPrevisti.setText("0");
		operatore.setText(CarloanFrontController.getInstance().getUserAuthenticated().getUsername());
		rifornimento.setItems(FXCollections.observableArrayList(Rifornimento.values()));
		rifornimento.getSelectionModel().select(0);
		chilometraggioLimitato.selectedProperty().set(true);
		chilometraggioLimitato.selectedProperty().addListener(new ChangeListener<Boolean>(){
		    @Override
		    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				chilometriPrevisti.setDisable(!newValue);
		    }
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onConferma() {
		Contratto contratto = buildEntity();
		if (contratto != null) {
			Double costoC = null;
			if (contratto.getCosto() == 0)
				 costoC = (Double) controller.processRequest("CalcolaCosto", contratto);
			if (costoC != null)
				costo.setText(Double.toString(costoC));
			if (edit) controller.processRequest("ModificaContratto", buildEntity());
			else controller.processRequest("AggiungiContratto", buildEntity());
			close();
		} else {
			CarloanMessage.showMessage(AlertType.WARNING, "I dati immessi non sono corretti");
		}
	}

	/**
	 * Ascoltatore del pulsante di calcola costo che provvede a calcolare il costo preventivo del contratto
	 */
	@FXML
	public void onCalcolaCosto() {
		Double costoContratto = (Double) controller.processRequest("CalcolaCosto", buildEntity());
		if (costoContratto != null) costo.setText(costoContratto.toString());
		else costo.setText("0");
	}
	
	/**
	 * Chiama la schermata per la selezione dell'Optional
	 */
	@FXML
	public void onSelezioneOptional() {
		optionals.getItems().add((String) controller.processRequest("MostraSelezione", "Optional"));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initModifica(Contratto entity) {
		edit = true;
		setId(entity.getId());
		costo.setText(Double.toString(entity.getCosto()));
		acconto.setText(Double.toString(entity.getAcconto()));
		dataInizioNoleggio.setValue(java.time.LocalDate.of(entity.getDataInizioNoleggio().getYear(), entity.getDataInizioNoleggio().getMonthOfYear(), entity.getDataInizioNoleggio().getDayOfMonth()));
		dataFineNoleggio.setValue(java.time.LocalDate.of(entity.getDataFineNoleggio().getYear(), entity.getDataFineNoleggio().getMonthOfYear(), entity.getDataFineNoleggio().getDayOfMonth()));
		dataStipula.setValue(java.time.LocalDate.of(entity.getDataStipula().getYear(), entity.getDataStipula().getMonthOfYear(), entity.getDataStipula().getDayOfMonth()));
		agenziaNoleggio.setText(Integer.toString(entity.getAgenziaNoleggio().getId()));
		agenziaConsegna.setText(Integer.toString(entity.getAgenziaConsegna().getId()));
		chilometriPrevisti.setText(Integer.toString(entity.getChilometriPrevisti()));
		rifornimento.getSelectionModel().select(entity.getRifornimento());
		chilometraggioLimitato.selectedProperty().set(entity.isChilometraggioLimitato());
		assicurazioneAvanzata.selectedProperty().set(entity.isAssicurazioneAvanzata());		
		operatore.setText(entity.getOperatore().getUsername());
		vettura.setText(entity.getVettura().getTarga());
		cliente.setText(entity.getCliente().getCodicePatente());
		List<String> opts = new ArrayList<String>();
		for (Optional o:entity.getOptionals()) {
			opts.add(Integer.toString(o.getId()));
		}
		optionals.setItems(FXCollections.observableArrayList(opts));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Contratto buildEntity() {
		Contratto contratto = new Contratto();
		contratto.setId(id);
		try {
			contratto.setOperatore((Operatore) controller.processRequest("ReadOperatore", operatore.getText()));
			contratto.setAgenziaConsegna((Agenzia) controller.processRequest("ReadAgenzia", agenziaConsegna.getText()));
			contratto.setAgenziaNoleggio((Agenzia) controller.processRequest("ReadAgenzia", agenziaNoleggio.getText()));
			contratto.setCliente((Cliente) controller.processRequest("ReadCliente", cliente.getText()));
			contratto.setVettura((Vettura) controller.processRequest("ReadVettura", vettura.getText()));
			contratto.setDataFineNoleggio(DateHelper.dateParse(dataFineNoleggio.getValue()));
			contratto.setDataStipula(DateHelper.dateParse(dataStipula.getValue()));
			contratto.setDataInizioNoleggio(DateHelper.dateParse(dataInizioNoleggio.getValue()));
			contratto.setAcconto(Double.parseDouble(acconto.getText()));
			contratto.setRifornimento(rifornimento.getSelectionModel().getSelectedItem());
			contratto.setAssicurazioneAvanzata(assicurazioneAvanzata.selectedProperty().get());
			contratto.setChilometraggioLimitato(chilometraggioLimitato.selectedProperty().get());
			contratto.setChilometriPrevisti(Integer.parseInt(chilometriPrevisti.getText()));
			List<Optional> opts = new ArrayList<Optional>();
			for (String o:optionals.getItems()) {
				if (!o.isEmpty()) opts.add((Optional)controller.processRequest("ReadOptional", o)); 
			}
			contratto.setOptionals(opts);
			if (costo.getText().isEmpty()) {
				contratto.setCosto(0);
			} else {
				contratto.setCosto(Double.parseDouble(costo.getText()));
			}
			return contratto;
		} catch (Exception e) {
			return null;
		}
	}
	
}
