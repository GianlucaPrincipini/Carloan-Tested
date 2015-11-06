package presentation.gui;

public class CarloanOperatore extends CarloanStage{
	
	/**
	 * Richiama la schermata dell'operatore
	 */
	public CarloanOperatore(){
		super("SchermataOperatore.fxml", null);
		setTitle("Immissione Operatore");
	}
	
	/**
	 * Richiama la schermata dell'operatore passando un'entità
	 */
	public CarloanOperatore(Object entity) {
		super("SchermataOperatore.fxml", entity);
		setTitle("Immissione Operatore");
	}
}
