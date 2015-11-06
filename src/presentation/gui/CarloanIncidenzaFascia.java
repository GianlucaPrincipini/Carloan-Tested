package presentation.gui;

public class CarloanIncidenzaFascia extends CarloanStage{

	/**
	 * Richiama la schermata dell'incidenza della fasce
	 */
	public CarloanIncidenzaFascia(){
		super("SchermataIncidenza.fxml", null);
		setTitle("Modifica Incidenze di fascia");
	}
	
	/**
	 * Richiama la schermata dell'incidenza delle fasce passando i dati caricati da file
	 */
	public CarloanIncidenzaFascia(Object entity){
		super("SchermataIncidenza.fxml", entity);
		setTitle("Modifica Incidenze di fascia");
	}
}
