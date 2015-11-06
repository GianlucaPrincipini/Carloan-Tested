package presentation.gui;

public class CarloanModello extends CarloanStage{
	
	/**
	 * Richiama la schermata del modello
	 */
	public CarloanModello() {
		super("SchermataModello.fxml", null);
	}
	
	/**
	 * Richiama la schermata del modello passando un'entit�
	 */
	public CarloanModello(Object entity) {
		super("SchermataModello.fxml", entity);
	}
}
