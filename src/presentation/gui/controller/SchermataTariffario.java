package presentation.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import presentation.frontcontroller.CarloanFrontController;
import presentation.gui.CarloanMessage;
import business.entity.Rifornimento;
import business.entity.Tariffario;
import business.entity.TipoCarburante;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class SchermataTariffario extends SchermataDati<Tariffario>{

	
	@FXML
	private TextField costoGiornaliero;
	
	@FXML
	private TextField costoSettimanale;
	
	@FXML
	private TextField costoChilometrico;
	
	@FXML
	private TextField costoChilometraggioIllimitato;
	
	@FXML
	private TextField assicurazioneBase;
	
	@FXML
	private TextField assicurazioneAvanzata;
	
	@FXML
	private TextField costoBenzina;
	
	@FXML
	private TextField costoDiesel;
	
	@FXML
	private TextField costoElettricità;
	
	@FXML
	private TextField costoMetano;
	
	@FXML
	private TextField costoGpl;
	
	@FXML
	private TextField costoPienoAnticipo;
	
	
	@FXML
	private TextField moraChilometraggio;
	
	@FXML
	private TextField moraDurata;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		controller = CarloanFrontController.getInstance();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onConferma() {
		Tariffario tariffario = buildEntity();
		if (tariffario != null) {
			controller.processRequest("ModificaTariffario", tariffario);
			close();
		} else {
			CarloanMessage.showMessage(AlertType.WARNING, "I dati immessi non sono corretti");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initModifica(Tariffario entity) {
		edit = true;

		costoGiornaliero.setText(Double.toString(entity.getCostoGiornaliero()));
		costoSettimanale.setText(Double.toString(entity.getCostoSettimanale()));
		costoChilometrico.setText(Double.toString(entity.getCostoChilometrico()));
		costoChilometraggioIllimitato.setText(Double.toString(entity.getCostoChilometraggioIllimitato()));
		assicurazioneBase.setText(Double.toString(entity.getAssicurazioneBase()));
		assicurazioneAvanzata.setText(Double.toString(entity.getAssicurazioneAvanzata()));
		costoBenzina.setText(Double.toString(entity.getCostoLitro(TipoCarburante.BENZINA)));
		costoDiesel.setText(Double.toString(entity.getCostoLitro(TipoCarburante.DIESEL)));
		costoMetano.setText(Double.toString(entity.getCostoLitro(TipoCarburante.METANO)));
		costoElettricità.setText(Double.toString(entity.getCostoLitro(TipoCarburante.ELETTRICA)));
		costoGpl.setText(Double.toString(entity.getCostoLitro(TipoCarburante.GPL)));
		costoPienoAnticipo.setText(Double.toString(entity.getRifornimento(Rifornimento.PIENO_ANTICIPO)));
		moraDurata.setText(Double.toString(entity.getMoraDurata()));
		moraChilometraggio.setText(Double.toString(entity.getMoraChilometraggio()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Tariffario buildEntity() {
		Tariffario tariffario = Tariffario.getInstance();
		try {
			tariffario.setCostoGiornaliero(Double.parseDouble(costoGiornaliero.getText())); 
			tariffario.setCostoSettimanale(Double.parseDouble(costoSettimanale.getText()));
			tariffario.setCostoChilometrico(Double.parseDouble(costoChilometrico.getText()));
			tariffario.setCostoChilometraggioIllimitato(Double.parseDouble(costoChilometraggioIllimitato.getText()));
			tariffario.setAssicurazioneBase(Double.parseDouble(assicurazioneBase.getText()));
			tariffario.setAssicurazioneAvanzata(Double.parseDouble(assicurazioneAvanzata.getText()));
			tariffario.setCostoLitro(TipoCarburante.BENZINA, Double.parseDouble(costoBenzina.getText()));
			tariffario.setCostoLitro(TipoCarburante.METANO, Double.parseDouble(costoMetano.getText()));
			tariffario.setCostoLitro(TipoCarburante.ELETTRICA, Double.parseDouble(costoElettricità.getText()));
			tariffario.setCostoLitro(TipoCarburante.GPL, Double.parseDouble(costoGpl.getText()));
			tariffario.setCostoLitro(TipoCarburante.DIESEL, Double.parseDouble(costoDiesel.getText()));
			tariffario.setRifornimento(Rifornimento.PIENO_ANTICIPO, Double.parseDouble(costoPienoAnticipo.getText()));
			tariffario.setMoraChilometraggio(Double.parseDouble(moraChilometraggio.getText()));
			tariffario.setMoraDurata(Double.parseDouble(moraDurata.getText()));
			return tariffario;
		} catch (Exception e) {
			return null;
		}
	}		
}