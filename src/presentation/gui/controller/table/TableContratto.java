package presentation.gui.controller.table;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import presentation.frontcontroller.CarloanFrontController;
import presentation.frontcontroller.FrontController;
import utils.DateHelper;
import business.entity.Contratto;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;


public class TableContratto implements TableController{

	private FrontController controller;
	
	@FXML
	private Pane root;
	
	@FXML
	private TableColumn<Contratto,String> id;
	
	@FXML
	private TableColumn<Contratto,String> operatore;
	
	@FXML
	private TableColumn<Contratto,String> cliente;
	
	@FXML
	private TableColumn<Contratto,String> targaVettura;
	
	@FXML
	private TableColumn<Contratto,String> agenziaNoleggio;
	
	@FXML
	private TableColumn<Contratto,String> agenziaConsegna;
	
	@FXML
	private TableColumn<Contratto,String> dataStipula;
	
	@FXML
	private TableColumn<Contratto,String> dataInizioNoleggio;
	
	@FXML
	private TableColumn<Contratto,String> dataFineNoleggio;
	
	@FXML
	private TableColumn<Contratto,String> dataChiusura;
	
	@FXML
	private TableColumn<Contratto,String> limitazioneChilometraggio;
	
	@FXML
	private TableColumn<Contratto,String> chilometriPrevisti;
	
	@FXML
	private TableColumn<Contratto,String> chilometriPercorsi;
	
	@FXML
	private TableColumn<Contratto,String> rifornimento;
	
	@FXML
	private TableColumn<Contratto,String> assicurazioneAvanzata;
	
	@FXML
	private TableColumn<Contratto,String> optionals;
	
	@FXML
	private TableColumn<Contratto,String> acconto;
	
	@FXML
	private TableColumn<Contratto,String> costo;
	
	@FXML
	private TableColumn<Contratto,String> chiuso;
	
	@FXML
	private TableView<Contratto> tabContratti;
	
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		controller = CarloanFrontController.getInstance();
		
		ObservableList<Contratto> contratti = FXCollections.observableList((List<Contratto>) controller.processRequest("ReadAllContratto"));
		
        id.setCellValueFactory(new Callback<CellDataFeatures<Contratto, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Contratto, String> c) {
            	return new ReadOnlyObjectWrapper(Integer.toString(c.getValue().getId()));
     		}
        });
      
        operatore.setCellValueFactory(new Callback<CellDataFeatures<Contratto, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Contratto, String> c) {
            	return new ReadOnlyObjectWrapper(c.getValue().getOperatore().getUsername());
     		}
        });
        cliente.setCellValueFactory(new Callback<CellDataFeatures<Contratto, String>, ObservableValue<String>>() {
        	public ObservableValue<String> call(CellDataFeatures<Contratto, String> c) {
            	return new ReadOnlyObjectWrapper(c.getValue().getCliente().getCodicePatente());
     		}
        });
        targaVettura.setCellValueFactory(new Callback<CellDataFeatures<Contratto, String>, ObservableValue<String>>() {
        	public ObservableValue<String> call(CellDataFeatures<Contratto, String> c) {
            	return new ReadOnlyObjectWrapper(c.getValue().getVettura().getTarga());
     		}
        });
        agenziaNoleggio.setCellValueFactory(new Callback<CellDataFeatures<Contratto, String>, ObservableValue<String>>() {
        	public ObservableValue<String> call(CellDataFeatures<Contratto, String> c) {
            	return new ReadOnlyObjectWrapper(c.getValue().getAgenziaNoleggio().getId());
     		}
        });
        agenziaConsegna.setCellValueFactory(new Callback<CellDataFeatures<Contratto, String>, ObservableValue<String>>() {
        	public ObservableValue<String> call(CellDataFeatures<Contratto, String> c) {
            	return new ReadOnlyObjectWrapper(c.getValue().getAgenziaConsegna().getId());
     		}
        });
        dataStipula.setCellValueFactory(new Callback<CellDataFeatures<Contratto, String>, ObservableValue<String>>() {
        	public ObservableValue<String> call(CellDataFeatures<Contratto, String> c) {
            	return new ReadOnlyObjectWrapper(DateHelper.dateAsString(c.getValue().getDataStipula()));
     		}
        });
        dataInizioNoleggio.setCellValueFactory(new Callback<CellDataFeatures<Contratto, String>, ObservableValue<String>>() {
        	public ObservableValue<String> call(CellDataFeatures<Contratto, String> c) {
            	return new ReadOnlyObjectWrapper(DateHelper.dateAsString(c.getValue().getDataInizioNoleggio()));
     		}
        });
        dataFineNoleggio.setCellValueFactory(new Callback<CellDataFeatures<Contratto, String>, ObservableValue<String>>() {
        	public ObservableValue<String> call(CellDataFeatures<Contratto, String> c) {
            	return new ReadOnlyObjectWrapper(DateHelper.dateAsString(c.getValue().getDataFineNoleggio()));
     		}
        });
        
        dataChiusura.setCellValueFactory(new Callback<CellDataFeatures<Contratto, String>, ObservableValue<String>>() {
        	public ObservableValue<String> call(CellDataFeatures<Contratto, String> c) {
        		if (c.getValue().getDataChiusura()!= null)
        			return new ReadOnlyObjectWrapper(DateHelper.dateAsString(c.getValue().getDataChiusura()));
        		return null;
     		}
        });
        limitazioneChilometraggio.setCellValueFactory(new Callback<CellDataFeatures<Contratto, String>, ObservableValue<String>>() {
        	public ObservableValue<String> call(CellDataFeatures<Contratto, String> c) {
            	return new ReadOnlyObjectWrapper(c.getValue().isChilometraggioLimitato());
     		}
        });
        chilometriPrevisti.setCellValueFactory(new Callback<CellDataFeatures<Contratto, String>, ObservableValue<String>>() {
        	public ObservableValue<String> call(CellDataFeatures<Contratto, String> c) {
            	return new ReadOnlyObjectWrapper(c.getValue().getChilometriPrevisti());
     		}
        });
        chilometriPercorsi.setCellValueFactory(new Callback<CellDataFeatures<Contratto, String>, ObservableValue<String>>() {
        	public ObservableValue<String> call(CellDataFeatures<Contratto, String> c) {
            	return new ReadOnlyObjectWrapper(c.getValue().getChilometriPercorsi());
     		}
        });
        rifornimento.setCellValueFactory(new Callback<CellDataFeatures<Contratto, String>, ObservableValue<String>>() {
        	public ObservableValue<String> call(CellDataFeatures<Contratto, String> c) {
            	return new ReadOnlyObjectWrapper(c.getValue().getRifornimento());
     		}
        });
        assicurazioneAvanzata.setCellValueFactory(new Callback<CellDataFeatures<Contratto, String>, ObservableValue<String>>() {
        	public ObservableValue<String> call(CellDataFeatures<Contratto, String> c) {
            	return new ReadOnlyObjectWrapper(c.getValue().isAssicurazioneAvanzata());
     		}
        });
        optionals.setCellValueFactory(new Callback<CellDataFeatures<Contratto, String>, ObservableValue<String>>() {
        	public ObservableValue<String> call(CellDataFeatures<Contratto, String> c) {
            	return new ReadOnlyObjectWrapper(c.getValue().getOptionals());
     		}
        });
        acconto.setCellValueFactory(new Callback<CellDataFeatures<Contratto, String>, ObservableValue<String>>() {
        	public ObservableValue<String> call(CellDataFeatures<Contratto, String> c) {
            	return new ReadOnlyObjectWrapper(c.getValue().getAcconto());
     		}
        });
        costo.setCellValueFactory(new Callback<CellDataFeatures<Contratto, String>, ObservableValue<String>>() {
        	public ObservableValue<String> call(CellDataFeatures<Contratto, String> c) {
            	return new ReadOnlyObjectWrapper(c.getValue().getCosto());
     		}
        });
        chiuso.setCellValueFactory(new Callback<CellDataFeatures<Contratto, String>, ObservableValue<String>>() {
        	public ObservableValue<String> call(CellDataFeatures<Contratto, String> c) {
            	return new ReadOnlyObjectWrapper(c.getValue().isChiuso());
     		}
        });
        
		tabContratti.setItems(contratti);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPrimaryKey() {
		if (tabContratti.getSelectionModel().getSelectedIndex() == -1) {
			return null;
		}
		return id.getCellObservableValue(tabContratti.getSelectionModel().getSelectedIndex()).getValue().toString();
	}
}
