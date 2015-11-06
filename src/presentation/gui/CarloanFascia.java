package presentation.gui;

public class CarloanFascia extends CarloanStage{
	
	/**
	 * Richiama la schermata della fascia
	 */
	public CarloanFascia(){
		super("SchermataFascia.fxml", null);
		setTitle("Immissione Fascia");
	}
	
	/**
	 * Richiama la schermata della fascia passando un'entità
	 */
	public CarloanFascia(Object entity) {
		super("SchermataFascia.fxml", entity);
		setTitle("Immissione Fascia");
	}
}
