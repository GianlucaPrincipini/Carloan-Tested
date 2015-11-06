package presentation.gui;

public class CarloanChiusuraContratto extends CarloanStage {
	
	public CarloanChiusuraContratto() {
		super("",null);
	}
	
	/**
	 * Richiama la schermata della chiusura del contratto passando un'entità
	 */
	public CarloanChiusuraContratto(Object contratto) {
		super("SchermataChiusura.fxml", contratto);
		setTitle("Chiudi Contratto");
	}
}
