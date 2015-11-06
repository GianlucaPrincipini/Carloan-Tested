package presentation.gui;

public class CarloanAgenzia extends CarloanStage{
	
	/**
	 * Richiama la schermata dell'agenzia
	 */
	public CarloanAgenzia(){
		super("SchermataAgenzia.fxml", null);
		setTitle("Immissione Agenzia");
	}
	
	/**
	 * Richiama la schermata dell'agenzia passando un'entità
	 */
	public CarloanAgenzia(Object entity) {
		super("SchermataAgenzia.fxml", entity);
		setTitle("Immissione Agenzia");
	}
}
