package presentation.gui.controller.table;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import presentation.frontcontroller.CarloanFrontController;
import presentation.frontcontroller.FrontController;
import utils.DateHelper;
import business.entity.Amministratore;
import business.entity.Operatore;
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

public class TableOperatore implements TableController{

	private FrontController controller;
	
	@FXML
	private Pane root;
	
	@FXML
	private TableColumn<Operatore,String> id;
	
	@FXML
	private TableColumn<Operatore,String> nome;
	
	@FXML
	private TableColumn<Operatore,String> cognome;
	
	@FXML
	private TableColumn<Operatore,String> username;
	
	@FXML
	private TableColumn<Operatore,String> agenzia;
	
	@FXML
	private TableColumn<Operatore,String> amministratore;
	
	@FXML
	private TableColumn<Operatore,String> dataDiNascita;
	
	@FXML
	private TableColumn<Operatore,String> telefono;
	
	@FXML
	private TableColumn<Operatore,String> email;
	
	@FXML
	private TableView<Operatore> tabOperatori;

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		controller = CarloanFrontController.getInstance();
		
		ObservableList<Operatore> operatori = FXCollections.observableList((List<Operatore>) controller.processRequest("ReadAllOperatore"));
		id.setCellValueFactory(new Callback<CellDataFeatures<Operatore, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Operatore, String> o) {
            	return new ReadOnlyObjectWrapper(Integer.toString(o.getValue().getId()));
     		}
        });		
        
        username.setCellValueFactory(new Callback<CellDataFeatures<Operatore, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Operatore, String> o) {
            	return new ReadOnlyObjectWrapper(o.getValue().getUsername());
     		}
        });
        
        nome.setCellValueFactory(new Callback<CellDataFeatures<Operatore, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Operatore, String> o) {
            	return new ReadOnlyObjectWrapper(o.getValue().getNome());
     		}
        });
        
        cognome.setCellValueFactory(new Callback<CellDataFeatures<Operatore, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Operatore, String> o) {
            	return new ReadOnlyObjectWrapper(o.getValue().getCognome());
     		}
        });
        
        agenzia.setCellValueFactory(new Callback<CellDataFeatures<Operatore, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Operatore, String> o) {
            	return new ReadOnlyObjectWrapper(o.getValue().getAgenzia().getId());
     		}
        });
        
        amministratore.setCellValueFactory(new Callback<CellDataFeatures<Operatore, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Operatore, String> o) {
            	return new ReadOnlyObjectWrapper(o.getValue() instanceof Amministratore);
     		}
        });
        
        dataDiNascita.setCellValueFactory(new Callback<CellDataFeatures<Operatore, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Operatore, String> o) {
            	return new ReadOnlyObjectWrapper(DateHelper.dateAsString(o.getValue().getDataNascita()));
     		}
        });
        
        telefono.setCellValueFactory(new Callback<CellDataFeatures<Operatore, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Operatore, String> o) {
            	return new ReadOnlyObjectWrapper(o.getValue().getNumTelefono());
     		}
        });
        
        email.setCellValueFactory(new Callback<CellDataFeatures<Operatore, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Operatore, String> o) {
            	return new ReadOnlyObjectWrapper(o.getValue().getEMail());
     		}
        });
        
        tabOperatori.setItems(operatori);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPrimaryKey() {
		if (tabOperatori.getSelectionModel().getSelectedIndex() == -1) {
			return null;
		}
		return username.getCellObservableValue(tabOperatori.getSelectionModel().getSelectedIndex()).getValue().toString();

	}
	
}
