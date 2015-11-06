package presentation.gui.controller.table;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import presentation.frontcontroller.CarloanFrontController;
import presentation.frontcontroller.FrontController;
import utils.DateHelper;
import business.entity.Cliente;
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



public class TableCliente implements TableController{

	private FrontController controller;
	
	@FXML
	private Pane root;
	
	@FXML
	private TableColumn<Cliente,String> id;
	
	@FXML
	private TableColumn<Cliente,String> nome;
	
	@FXML
	private TableColumn<Cliente,String> cognome;
	
	@FXML
	private TableColumn<Cliente,String> telefono;
	
	@FXML
	private TableColumn<Cliente,String> email;
	
	@FXML
	private TableColumn<Cliente,String> dataDiNascita;
	
	@FXML
	private TableColumn<Cliente,String> codicePatente;
	
	@FXML
	private TableView<Cliente> tabClienti;

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		controller = CarloanFrontController.getInstance();
		
		ObservableList<Cliente> clienti = FXCollections.observableList((List<Cliente>) controller.processRequest("ReadAllCliente"));
        id.setCellValueFactory(new Callback<CellDataFeatures<Cliente, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Cliente, String> c) {
            	return new ReadOnlyObjectWrapper(c.getValue().getId());
     		}
        });		
        
        nome.setCellValueFactory(new Callback<CellDataFeatures<Cliente, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Cliente, String> c) {
            	return new ReadOnlyObjectWrapper(c.getValue().getNome());
     		}
        });
        
        cognome.setCellValueFactory(new Callback<CellDataFeatures<Cliente, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Cliente, String> c) {
            	return new ReadOnlyObjectWrapper(c.getValue().getCognome());
     		}
        });
        
        dataDiNascita.setCellValueFactory(new Callback<CellDataFeatures<Cliente, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Cliente, String> c) {
            	return new ReadOnlyObjectWrapper(DateHelper.dateAsString(c.getValue().getDataNascita()));
     		}
        });
        
        codicePatente.setCellValueFactory(new Callback<CellDataFeatures<Cliente, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Cliente, String> c) {
            	return new ReadOnlyObjectWrapper(c.getValue().getCodicePatente());
     		}
        });
        
        telefono.setCellValueFactory(new Callback<CellDataFeatures<Cliente, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Cliente, String> c) {
            	return new ReadOnlyObjectWrapper(c.getValue().getNumTelefono());
     		}
        });
        
        email.setCellValueFactory(new Callback<CellDataFeatures<Cliente, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Cliente, String> c) {
            	return new ReadOnlyObjectWrapper(c.getValue().getEMail());
     		}
        });
        
        tabClienti.setItems(clienti);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPrimaryKey() {
		return codicePatente.getCellObservableValue(tabClienti.getSelectionModel().getSelectedIndex()).getValue().toString();
	}
	
	
}
