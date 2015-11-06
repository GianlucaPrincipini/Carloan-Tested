package presentation.gui;

import business.entity.Tariffario;

public class CarloanTariffario extends CarloanStage{
	
	/**
	 * Schermata del tariffario che carica l'ultimo tariffario caricato da file 
	 */
	public CarloanTariffario() {
		super("SchermataTariffario", Tariffario.getInstance());
		setTitle("Modifica Tariffario");
	}
	
	/**
	 * Richiama la schermata del tariffario caricando le informazioni da file
	 * @param entity Dati del tariffario caricati dal file
	 */
	public CarloanTariffario(Object entity){
		super("SchermataTariffario.fxml", entity);
		setTitle("Modifica Tariffario");
	}
}
