package presentation.gui;

public class CarloanContratto extends CarloanStage{
	
	/**
	 * Richiama la schermata del contratto 
	 */
	public CarloanContratto(){
		super("SchermataContratto.fxml", null);
		setTitle("Immissione Contratto");
	}
	
	/**
	 * Richiama la schermata del contratto passando un'entità
	 */
	public CarloanContratto(Object entity) {
		super("SchermataContratto.fxml", entity);
		setTitle("Immissione Contratto");
	}
}
