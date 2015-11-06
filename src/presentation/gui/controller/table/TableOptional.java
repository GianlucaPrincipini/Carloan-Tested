package presentation.gui.controller.table;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import presentation.frontcontroller.CarloanFrontController;
import presentation.frontcontroller.FrontController;
import business.entity.Optional;
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

public class TableOptional implements TableController{

	private FrontController controller;
	
	@FXML
	private Pane root;
	
	@FXML
	private TableColumn<Optional,String> id;
	
	@FXML
	private TableColumn<Optional,String> tipo;
	
	@FXML
	private TableColumn<Optional,String> costo;
	
	@FXML
	private TableView<Optional> tabOptionals;

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		controller = CarloanFrontController.getInstance();
		
		ObservableList<Optional> optionals = FXCollections.observableList((List<Optional>) controller.processRequest("ReadAllOptional"));
		
        id.setCellValueFactory(new Callback<CellDataFeatures<Optional, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Optional, String> o) {
            	return new ReadOnlyObjectWrapper(Integer.toString(o.getValue().getId()));
     		}
        });		
        
        tipo.setCellValueFactory(new Callback<CellDataFeatures<Optional, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Optional, String> o) {
            	return new ReadOnlyObjectWrapper(o.getValue().getTipo());
     		}
        });
        
        costo.setCellValueFactory(new Callback<CellDataFeatures<Optional, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Optional, String> o) {
            	return new ReadOnlyObjectWrapper(o.getValue().getCosto());
     		}
        });
        
        tabOptionals.setItems(optionals);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPrimaryKey() {
		if (tabOptionals.getSelectionModel().getSelectedIndex() == -1) {
			return null;
		}
		return id.getCellObservableValue(tabOptionals.getSelectionModel().getSelectedIndex()).getValue().toString();

	}
	
}
