package presentation.gui.controller.table;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import presentation.frontcontroller.CarloanFrontController;
import presentation.frontcontroller.FrontController;
import business.entity.Modello;
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

public class TableModello implements TableController{

	private FrontController controller;
	
	@FXML
	private Pane root;
	
	@FXML
	private TableColumn<Modello,String> id;
	
	@FXML
	private TableColumn<Modello,String> marca;
	
	@FXML
	private TableColumn<Modello,String> nome;
	
	@FXML
	private TableColumn<Modello,String> tipoCarburante;
	
	@FXML
	private TableColumn<Modello,String> capacita;
	
	@FXML
	private TableColumn<Modello,String> numeroPosti;
	
	@FXML
	private TableColumn<Modello,String> numeroPorte;
	
	@FXML
	private TableColumn<Modello,String> potenza;
	
	@FXML
	private TableColumn<Modello,String> peso;
	
	@FXML
	private TableColumn<Modello,String> trasmissioneAutomatica;
	
	@FXML
	private TableColumn<Modello,String> emissioni;
	
	@FXML
	private TableColumn<Modello,String> fascia;
	
	@FXML
	private TableView<Modello> tabModelli;

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		controller = CarloanFrontController.getInstance();
		
		ObservableList<Modello> modelli = FXCollections.observableList((List<Modello>) controller.processRequest("ReadAllModello"));
        id.setCellValueFactory(new Callback<CellDataFeatures<Modello, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Modello, String> m) {
            	return new ReadOnlyObjectWrapper(Integer.toString(m.getValue().getId()));
     		}
        });	
        
        marca.setCellValueFactory(new Callback<CellDataFeatures<Modello, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Modello, String> m) {
            	return new ReadOnlyObjectWrapper(m.getValue().getMarca());
     		}
        });
        
        nome.setCellValueFactory(new Callback<CellDataFeatures<Modello, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Modello, String> m) {
            	return new ReadOnlyObjectWrapper(m.getValue().getNome());
     		}
        });
        
        tipoCarburante.setCellValueFactory(new Callback<CellDataFeatures<Modello, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Modello, String> m) {
            	return new ReadOnlyObjectWrapper(m.getValue().getTipoCarburante());
     		}
        });
        
        capacita.setCellValueFactory(new Callback<CellDataFeatures<Modello, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Modello, String> m) {
            	return new ReadOnlyObjectWrapper(m.getValue().getCapacit‡Bagagliaio());
     		}
        });
        
        numeroPosti.setCellValueFactory(new Callback<CellDataFeatures<Modello, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Modello, String> m) {
            	return new ReadOnlyObjectWrapper(m.getValue().getNumeroPosti());
     		}
        });
        
        numeroPorte.setCellValueFactory(new Callback<CellDataFeatures<Modello, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Modello, String> m) {
            	return new ReadOnlyObjectWrapper(m.getValue().getNumeroPorte());
     		}
        });
        
        potenza.setCellValueFactory(new Callback<CellDataFeatures<Modello, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Modello, String> m) {
            	return new ReadOnlyObjectWrapper(m.getValue().getPotenza());
     		}
        });
        
        peso.setCellValueFactory(new Callback<CellDataFeatures<Modello, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Modello, String> m) {
            	return new ReadOnlyObjectWrapper(m.getValue().getPeso());
     		}
        });
        
        trasmissioneAutomatica.setCellValueFactory(new Callback<CellDataFeatures<Modello, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Modello, String> m) {
            	return new ReadOnlyObjectWrapper(m.getValue().isTrasmissioneAutomatica());
     		}
        });
        
        emissioni.setCellValueFactory(new Callback<CellDataFeatures<Modello, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Modello, String> m) {
            	return new ReadOnlyObjectWrapper(m.getValue().getEmissioniCO2());
     		}
        });
        
        fascia.setCellValueFactory(new Callback<CellDataFeatures<Modello, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Modello, String> m) {

            	return new ReadOnlyObjectWrapper(m.getValue().getFascia().getId());
     		}
        });
        
        tabModelli.setItems(modelli);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPrimaryKey() {
		if (tabModelli.getSelectionModel().getSelectedIndex() == -1) {
			return null;
		}
		return id.getCellObservableValue(tabModelli.getSelectionModel().getSelectedIndex()).getValue().toString();
	}
	
}
