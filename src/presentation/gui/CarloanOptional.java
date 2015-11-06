package presentation.gui;

public class CarloanOptional extends CarloanStage{
	
	/**
	 * Richiama la schermata dell'optional
	 */
	public CarloanOptional(){
		super("SchermataOptional.fxml", null);
		setTitle("Immissione Optional");
	}
	
	/**
	 * Richiama la schermata dell'optional passando un'entità
	 */
	public CarloanOptional(Object entity) {
		super("SchermataOptional.fxml", entity);
		setTitle("Immissione Optional");
	}
}
