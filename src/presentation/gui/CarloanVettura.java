package presentation.gui;

public class CarloanVettura extends CarloanStage{
	
	/**
	 * Richiama la schermata della vettura
	 */
	public CarloanVettura(){
		super("SchermataVettura.fxml", null);
		setTitle("Immissione Vettura");
	}
	
	/**
	 * Richiama la schermata della vettura passando un'entit�
	 */
	public CarloanVettura(Object entity) {
		super("SchermataVettura.fxml", entity);
		setTitle("Immissione Vettura");
	}
}
