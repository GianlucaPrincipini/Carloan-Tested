package presentation.gui;

import presentation.gui.controller.SchermataSelezione;

public class CarloanSelezione extends CarloanStage{

	private String value;
	
	/**
	 * @return value
	 */
	public String getValue() {
		if (value != null) return value;
		return "";
	}
	
	/**
	 * imposta value
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * Richiama la schermata di selezione per le entità aggregate
	 * @param selezione Nome della tabella da caricare 
	 */
	public CarloanSelezione(Object selezione) {
		super("SchermataSelezione.fxml", null);
		SchermataSelezione controller = fxmlLoader.<SchermataSelezione>getController();
		controller.loadTable((String)selezione);
	}

}
