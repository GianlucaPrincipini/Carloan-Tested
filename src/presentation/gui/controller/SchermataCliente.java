package presentation.gui.controller;


import java.net.URL;
import java.util.ResourceBundle;

import org.joda.time.LocalDate;

import business.entity.Cliente;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

public class SchermataCliente extends SchermataDati<Cliente>{


	
	@FXML
	private Node root;
	
	@FXML
	private Button conferma;
	
	@FXML
	private TextField nome;
	
	@FXML
	private TextField cognome;
	
	@FXML
	private TextField codicePatente;
	
	@FXML
	private TextField telefono;
	
	@FXML
	private TextField email;
	
	@FXML
	private DatePicker dataDiNascita;
	
	
	/**
	 * {@inheritDoc}
	 */
	@FXML
	public void onConferma() {
		Cliente cliente = buildEntity(); 
		if (cliente != null) {
			if (!edit)
				controller.processRequest("AggiungiCliente", cliente);
			else {
				edit = false;
				controller.processRequest("ModificaCliente", cliente);
			}
			close();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initModifica(Cliente cliente) {
		edit = true;
		id = cliente.getId();
		nome.setText(cliente.getNome());
		cognome.setText(cliente.getCognome());
		email.setText(cliente.getEMail());
		telefono.setText(cliente.getNumTelefono());
		codicePatente.setText(cliente.getCodicePatente());
		dataDiNascita.setValue(java.time.LocalDate.of(cliente.getDataNascita().getYear(), cliente.getDataNascita().getMonthOfYear(), cliente.getDataNascita().getDayOfMonth()));
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Cliente buildEntity() {
		Cliente cliente; 
		try {
			cliente = new Cliente();
			cliente.setId(id);
			cliente.setNome(nome.getText());
			cliente.setCognome(cognome.getText());
			cliente.setEMail(email.getText());
			cliente.setNumTelefono(telefono.getText());
			cliente.setCodicePatente(codicePatente.getText());
			java.time.LocalDate datanascita = dataDiNascita.getValue();
			cliente.setDataNascita(new LocalDate(datanascita.getYear(), datanascita.getMonthValue(), datanascita.getDayOfMonth()));
			return cliente;
		} catch (Exception e) {
			return null;
		}
	}


}
