package presentation.gui.controller.table;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import presentation.frontcontroller.CarloanFrontController;
import presentation.frontcontroller.FrontController;
import business.entity.Agenzia;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class TableAgenzia implements TableController{
	
	private FrontController controller;

	@FXML
	private TableView<Agenzia> tabAgenzie;
	
	@FXML
	private Pane root;
	
	@FXML
	private TableColumn<Agenzia,String> id;
	
	@FXML
	private TableColumn<Agenzia,String> citta;

	@FXML
	private TableColumn<Agenzia,String> indirizzo;
	
	@FXML
	private TableColumn<Agenzia,String> telefono;
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		controller = CarloanFrontController.getInstance();
		
		ObservableList<Agenzia> agenzie = FXCollections.observableList((List<Agenzia>) controller.processRequest("ReadAllAgenzia"));
		
        id.setCellValueFactory(new Callback<CellDataFeatures<Agenzia, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Agenzia, String> a) {
            	return new ReadOnlyObjectWrapper(Integer.toString(a.getValue().getId()));
     		}
        });		
        
        citta.setCellValueFactory(new Callback<CellDataFeatures<Agenzia, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Agenzia, String> a) {
            	return new ReadOnlyObjectWrapper(a.getValue().getCittà());
     		}
        });
        
        indirizzo.setCellValueFactory(new Callback<CellDataFeatures<Agenzia, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Agenzia, String> a) {
            	return new ReadOnlyObjectWrapper(a.getValue().getIndirizzo());
     		}
        });
        
        telefono.setCellValueFactory(new Callback<CellDataFeatures<Agenzia, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Agenzia, String> a) {
            	return new ReadOnlyObjectWrapper(a.getValue().getNumTelefono());
     		}
        });
        
        tabAgenzie.setItems(agenzie);
        tabAgenzie.getSelectionModel().select(0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPrimaryKey() {
		if (tabAgenzie.getSelectionModel().getSelectedIndex() == -1) {
			return null;
		}
		return id.getCellObservableValue(tabAgenzie.getSelectionModel().getSelectedIndex()).getValue().toString();
	}
}
