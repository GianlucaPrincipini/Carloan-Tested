package presentation.gui.controller;

import presentation.frontcontroller.CarloanFrontController;
import presentation.frontcontroller.FrontController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

public abstract class SchermataDati<T> implements Initializable{

	@FXML
	private Node root;
	
	/**
	 * istanza di CarloanFrontController usata per prcessare le richieste
	 */
	protected FrontController controller = CarloanFrontController.getInstance();
	
	/**
	 * boolean che indica se è stata richiesta un'operazione di modifica
	 */
	protected boolean edit;	
	
	/**
	 * id dell'entità
	 */
	protected int id;
	
	/**
	 * Ascoltatore del pulsante di conferma
	 */
	public abstract void onConferma();
	
	/**
	 * Funzione che carica la schermata di modifica caricando i dati dell'entità
	 * @param entity
	 */
	public abstract void initModifica(T entity);
	
	/**
	 * Si occupa di costruire l'entità a partire dalle informazioni immesse nell'interfaccia
	 * @return
	 */
	protected abstract T buildEntity();

	/**
	 * Chiude la schermata attuale
	 */
	protected void close() {
		edit = false;
		((Stage)root.getScene().getWindow()).close();
	}

	/**
	 * imposta l' abilitazione della modifica
	 * @param edit
	 */
	public void setEdit(boolean edit) {
		this.edit = edit;
	}
	
	/**
	 * imposta l'id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
}
