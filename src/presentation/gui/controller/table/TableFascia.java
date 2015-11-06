package presentation.gui.controller.table;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import presentation.frontcontroller.CarloanFrontController;
import presentation.frontcontroller.FrontController;
import business.entity.Fascia;
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

public class TableFascia implements TableController{

	private FrontController controller;
	
	@FXML
	private Pane root;
	
	@FXML
	private TableColumn<Fascia,String> id;
	
	@FXML
	private TableColumn<Fascia,String> nome;
	
	@FXML
	private TableColumn<Fascia,String> tariffaBase;
	
	@FXML
	private TableColumn<Fascia,String> indiceMinimo;
	
	@FXML
	private TableColumn<Fascia,String> indiceMassimo;
	
	@FXML
	private TableView<Fascia> tabFasce;

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		controller = CarloanFrontController.getInstance();
		
		ObservableList<Fascia> fasce = FXCollections.observableList((List<Fascia>) controller.processRequest("ReadAllFascia"));
		
        id.setCellValueFactory(new Callback<CellDataFeatures<Fascia, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Fascia, String> f) {
            	return new ReadOnlyObjectWrapper(Integer.toString(f.getValue().getId()));
     		}
        });		
        
        nome.setCellValueFactory(new Callback<CellDataFeatures<Fascia, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Fascia, String> f) {
            	return new ReadOnlyObjectWrapper(f.getValue().getNome());
     		}
        });
        
        tariffaBase.setCellValueFactory(new Callback<CellDataFeatures<Fascia, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Fascia, String> f) {
            	return new ReadOnlyObjectWrapper(f.getValue().getTariffaBase());
     		}
        });
        
        indiceMinimo.setCellValueFactory(new Callback<CellDataFeatures<Fascia, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Fascia, String> f) {
            	return new ReadOnlyObjectWrapper(f.getValue().getMin());
     		}
        });
        
        indiceMassimo.setCellValueFactory(new Callback<CellDataFeatures<Fascia, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Fascia, String> f) {
            	return new ReadOnlyObjectWrapper(f.getValue().getMax());
     		}
        });
        
        tabFasce.setItems(fasce);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPrimaryKey() {
		if (tabFasce.getSelectionModel().getSelectedIndex() == -1) {
			return null;
		}
		return id.getCellObservableValue(tabFasce.getSelectionModel().getSelectedIndex()).getValue().toString();
	}
	
}
