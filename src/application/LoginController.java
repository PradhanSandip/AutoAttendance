package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class LoginController implements Initializable{
	@FXML
	Button resetButton, loginButton;
	@FXML
	TextField passwordField;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//set title of the label
		
		
		
	}
	
	@FXML
	private void login() throws Exception {
		
		//check if user entered password
		if(passwordField.getText().length() < 8) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Programme Password too short");
			alert.setContentText("Programme Password must be at lead 8 character long");
			alert.showAndWait();
			return;
		}
		
		//get the user data
		System.out.println("test");
		FileManager fm = new FileManager();
		AES aes = new AES();
		
		
		try {
			//get the hash
			aes.init();
			String userPassword = aes.hashPassword(passwordField.getText());
			String hash = fm.readBytes();
			String[] h = hash.split(" ");
			//check the password
			if(userPassword.equals(h[2])) {
				//grant login
				Main m = new Main();
				m.setMainScene();
			
			}else {
				//alert user
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Wrong password");
				alert.setContentText("If you forgot your password, redo the signup with new password");
				alert.showAndWait();
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
