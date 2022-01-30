package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class Controller implements Initializable{
	@FXML
	MenuButton coursesMenuButton,deleteCourseMenuButton;
	@FXML
	TextField attendanceCode, courseName, attendanceLink;
	CourseParser cp;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		 cp = new CourseParser(); //Course object to get list of coursesMenuButton
		 
		 addItem(coursesMenuButton);
		 addItem(deleteCourseMenuButton);
		 selectCourse();


	}
	
	//add course from json as menu items
	private void addItem(MenuButton m) {
		JSONArray allCourse = cp.getCourse(); //get all the course saved in Json
		for(int i=0; i < allCourse.size(); i++) {
			JSONObject course = (JSONObject) allCourse.get(i); //convert to Json object from Json array
			MenuItem mi = new MenuItem(course.get("name").toString()); //set the course in menu list
			m.getItems().add(mi); //add the menu item
		}
	}
	@FXML
	private void addNewCourse() {
		//check if input box is empty
		if(!courseName.getText().isEmpty() && !attendanceLink.getText().isEmpty()) {
			if(attendanceLink.getText().startsWith("https://learningedge.edgehill.ac.uk")) {
				cp.addCourse(courseName.getText().toString(), attendanceLink.getText().toString());
				//refresh menuButton
				coursesMenuButton.getItems().clear();
				deleteCourseMenuButton.getItems().clear();
				cp = new CourseParser();
				addItem(coursesMenuButton);
				addItem(deleteCourseMenuButton);
				//clear input box
				courseName.clear();
				attendanceLink.clear();
				//alert user
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Added new attendance");
				alert.setContentText("Sucessfully added new register link");
				alert.showAndWait();
				selectCourse();
			}else {
				//alert invalid link
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR");
				alert.setHeaderText("Wrong link!");
				alert.setContentText("Link is invalid, please copy the correct link from course page.");
				alert.showAndWait();
				selectCourse();
			}
		}
		
		//warn users if fields are empty
		else {
			if(courseName.getText().isEmpty() && attendanceLink.getText().isEmpty()) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Missing fields");
				alert.setHeaderText("Please add a course name and link");
				alert.setContentText("Please enter course name and valid register link");
				alert.showAndWait();
			}
			else if(attendanceLink.getText().isEmpty()) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Missing field");
				alert.setHeaderText("Please add a link");
				alert.setContentText("Please enter a valid attendance link");
				alert.showAndWait();
			}
			else if(courseName.getText().isEmpty()) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Missing field");
				alert.setHeaderText("Please add a name");
				alert.setContentText("Please enter a course name");
				alert.showAndWait();
			}
			
		}
		
	
	}
	
	private void selectCourse() {
		List<MenuItem> c = coursesMenuButton.getItems();
		List<MenuItem> d = deleteCourseMenuButton.getItems();
		
		EventHandler<ActionEvent> name = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				coursesMenuButton.setText(((MenuItem)e.getSource()).getText());
			
			}
			
		};
		EventHandler<ActionEvent> delete = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				deleteCourseMenuButton.setText(((MenuItem)e.getSource()).getText());
			
			}
			
		};
		for(int i = 0; i < c.size(); i++) {
			c.get(i).setOnAction(name);
			d.get(i).setOnAction(delete);
		}
		coursesMenuButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println(e);
			}
		});
	}
	
	//delete course 
	@FXML
	private void removeAttendance() {
		if(deleteCourseMenuButton.getText().equals("Select course")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("No Course Selected");
			alert.setContentText("Please select a course to delete");
			alert.showAndWait();
		}else {
			if(coursesMenuButton.getText().equals(deleteCourseMenuButton.getText())) {
				coursesMenuButton.setText("Select course");
			}
			cp.deleteCourse(deleteCourseMenuButton.getText());
			
			//Refresh lists
			coursesMenuButton.getItems().clear();
			deleteCourseMenuButton.getItems().clear();
			cp = new CourseParser();
			addItem(coursesMenuButton);
			addItem(deleteCourseMenuButton);
			deleteCourseMenuButton.setText("Select course");
			selectCourse();
			
		}
	}
	@FXML
	private void register() {
		if(!coursesMenuButton.getText().equals("Select course")) {
			Attendance a = new Attendance(cp.getLink(coursesMenuButton.getText()));
			coursesMenuButton.setText("Select course");
		}
	}

}
