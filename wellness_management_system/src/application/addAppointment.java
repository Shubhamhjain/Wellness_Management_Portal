package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class addAppointment implements Initializable {
	
	@FXML private DatePicker datePicker;
	@FXML private TextField doctor;
	@FXML private TextField address;
	private Stage stage;
	private Scene scene;
	private Parent mainscreen;
	private LocalDate date = null;
	
	public void addAAppointment(ActionEvent event) {
		
		String doctorString = doctor.getText();
		String addressString = address.getText();
		
		if(doctorString.length() == 0 || addressString.length() == 0 || date == null)
		{
			Alert alert = new Alert(AlertType.ERROR);
		    alert.setHeaderText("Please fill all details!");
		    alert.showAndWait();
		}
		else
		{
			String dateString = date.toString();
			if(sceneController.admin.addNewAppointment(doctorString, addressString, dateString))
			{
				doctor.setText("");
				address.setText("");
				datePicker.setValue(null);
			}
		}
		
	}
	
	public void getDate(ActionEvent event) {
		
		LocalDate selectedDate = datePicker.getValue();	
		date = selectedDate;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb)  {
	
	}
	
	 public void switchUserScreen(ActionEvent event) throws IOException{

		mainscreen = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(mainscreen);
		stage.setScene(scene);
	    stage.show();
	    
	 }
}
