package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;



public class Main extends Application {
	public static Stage main;
	public static Scene home;
	@Override
	public void start(Stage primaryStage) throws IOException {
		main = primaryStage;
		FileManager fm = new FileManager();
		Parent main = FXMLLoader.load(getClass().getResource("signup.fxml")); //load the main ui
		Parent login = FXMLLoader.load(getClass().getResource("login.fxml"));//load login ui
		Parent signup = FXMLLoader.load(getClass().getResource("signup.fxml"));//login signup ui
		Scene mainScene = new Scene(main, 600,650); //set the ui and size
		Scene loginScene = new Scene(login, 600,650);
		Scene signupScene = new Scene(signup, 600,650);
		home = mainScene;
		String css = this.getClass().getResource("Main.css").toExternalForm();
		mainScene.getStylesheets().add(css);
		loginScene.getStylesheets().add(css);
		signupScene.getStylesheets().add(css);
		if(fm.isUserExist()) {
			primaryStage.setScene(loginScene);
		}else {
			primaryStage.setScene(signupScene);
		}
		
//		primaryStage.setScene(new FXMLLoader.load(getClass().getResource("login.fxml")));
		primaryStage.setResizable(false);
		primaryStage.setTitle("AutoAttendance");
		primaryStage.show();
	}
	public Stage getStage() {
		return main;
	}
	
	public Scene getMainScene() {
		return home;
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
}
