package presentation.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import presentation.gui.CarloanMessage;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import business.entity.IncidenzaFascia;

public class SchermataIncidenzaFascia extends SchermataDati<IncidenzaFascia> {

	
	@FXML
	private TextField capacit‡Bagagliaio;

	@FXML
	private TextField numeroPorte;
	
	@FXML
	private TextField numeroPosti;
	
	@FXML
	private TextField potenzaSuPeso;
	
	@FXML
	private TextField emissioniCO2;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onConferma() {
		IncidenzaFascia incidenza = buildEntity();
		if (incidenza != null) {
			controller.processRequest("ModificaIncidenza", incidenza);
			close();
		} else {
			CarloanMessage.showMessage(AlertType.WARNING, "I dati immessi non sono corretti");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initModifica(IncidenzaFascia entity) {
		edit = true;
		capacit‡Bagagliaio.setText(Double.toString(entity.getCapacit‡Bagagliaio()));
		emissioniCO2.setText(Double.toString(entity.getEmissioniCO2()));
		numeroPorte.setText(Double.toString(entity.getNumeroPorte()));
		numeroPosti.setText(Double.toString(entity.getNumeroPosti()));
		potenzaSuPeso.setText(Double.toString(entity.getPotenzaSuPeso()));
		controller.processRequest("ModificaIncidenza", entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected IncidenzaFascia buildEntity() {
		IncidenzaFascia incidenza = IncidenzaFascia.getInstance();
		try {
			incidenza.setCapacit‡Bagagliaio(Double.parseDouble(capacit‡Bagagliaio.getText()));
			incidenza.setEmissioniCO2(Double.parseDouble(emissioniCO2.getText()));
			incidenza.setNumeroPorte(Double.parseDouble(numeroPorte.getText()));
			incidenza.setNumeroPosti(Double.parseDouble(numeroPosti.getText()));
			incidenza.setPotenzaSuPeso(Double.parseDouble(potenzaSuPeso.getText()));
			return incidenza;
		} catch (Exception e) {
			return null;
		}
	}
}
