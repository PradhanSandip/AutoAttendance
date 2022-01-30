package application;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class SignupController implements Initializable{
	
	@FXML
	Label title;
	@FXML
	TextField studentId;
	@FXML
	PasswordField password, programmePassword;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//set title of the label
		title.setText("First time login");
		
	}
	
	public void signup() {
		String id = studentId.getText();
		String pass = password.getText();
		String pPass = programmePassword.getText();
		//student number must be all integers
		if(!id.matches("\\d+")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Invaild student id");
			alert.setContentText("Student id must only contain numbers");
			alert.showAndWait();
		}
		//id must be 8 digit long
		else if(id.length() != 8) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Student id not valid");
			alert.setContentText("Student id must be atleast 8 digit long");
			alert.showAndWait();
		}
		//password must not be empty
		else if(pass.length() == 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Missing password");
			alert.setContentText("Please enter your Edgehill password");
			alert.showAndWait();
		}
		//Programme password must not be less than 8 character long
		else if(pPass.length() < 8) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Programme Password too short");
			alert.setContentText("Programme Password must be at lead 8 character long");
			alert.showAndWait();
		}else {
			
			 try {
		            AES aes = new AES();
		            aes.init();
		            //encrypt password
		            String ePass = aes.encrypt(pass);
		            //encrypt student id
		            String eId = aes.encrypt(id);
		            //hash program password
		            String hashProgrammePassword = aes.hashPassword(pPass);
		            FileManager fm = new FileManager();
		            fm.writeBytes(eId+" "+pass+" "+hashProgrammePassword);
		            Main m = new Main();
		            m.setMainScene();
		            
		        } catch (Exception e) {
		        	System.out.println(e.toString());
		        }
			
		}
		
		
		
	}
	
}
