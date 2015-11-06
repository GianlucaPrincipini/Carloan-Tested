package presentation.gui;

public class CarloanCliente extends CarloanStage{
	
	/**
	 * Richiama la schermata del Cliente
	 */
	public CarloanCliente(){
		super("SchermataCliente.fxml", null);
		setTitle("Immissione cliente");
	}
	
	/**
	 * Richiama la schermata del Cliente passando un'entità
	 */
	public CarloanCliente(Object entity) {
		super("SchermataCliente.fxml", entity);
		setTitle("Immissione cliente");
	}
}
