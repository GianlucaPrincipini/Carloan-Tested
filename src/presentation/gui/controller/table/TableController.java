package presentation.gui.controller.table;

import javafx.fxml.Initializable;

/**
 * Interfaccia che rappresenta una tabella generica di Carloan, valida per tutte le entità
 * @author Stefano
 *
 */
public interface TableController extends Initializable{
	
	/**
	 * Ritorna la chiave primaria della tabella
	 * @return Stringa contenente la chiave primaria
	 */
	public String getPrimaryKey();
}
