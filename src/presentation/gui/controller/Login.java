package presentation.gui.controller;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import presentation.frontcontroller.CarloanFrontController;
import presentation.frontcontroller.FrontController;
import utils.Encrypt;
import business.entity.Operatore;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Login implements Initializable {
	
	@FXML
	private Node root;
	
	@FXML
	private TextField username;
	
	@FXML
	private TextField password;

	@FXML
	private Button login;

	@FXML
	public void onLogin() {
		FrontController fController = CarloanFrontController.getInstance();
		Operatore operatore = new Operatore();
		operatore.setUsername(username.getText());
		operatore.setPassword(password.getText());

		if (fController.processRequest("Login", operatore) != null) {
			((Stage) root.getScene().getWindow()).close();
			fController.processRequest("MostraMainStage");
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	public static void main(String [] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		System.out.println(Encrypt.getEncryptedString("password"));
	}
}
