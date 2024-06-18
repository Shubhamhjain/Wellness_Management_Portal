package application;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import Code.DB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Code.Appointment;

public class bookAppointment implements Initializable {
	
	@FXML private TableView<Appointment> doctorTable;
	@FXML private TableColumn<Appointment, String> doctor;
	@FXML private TableColumn<Appointment, String> address;
	@FXML private TableColumn<Appointment, String> datetime;
	
	private Stage stage;
	private Scene scene;
	private Parent mainscreen;
	
	@Override
	public void initialize(URL url, ResourceBundle rb)  {	
		
		listAppointments();
		
	}
	
	private void listAppointments()
	{
		ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
		try {
			ResultSet result = DB.executeSelect("SELECT * FROM wellness_management_system.appointments");
			
			while(result.next())
			{

				appointmentList.add(new Appointment(result.getString("DOCTOR"), result.getString("ADDRESS"), result.getString("DATETIME")));
			}
			
			doctor.setCellValueFactory(new PropertyValueFactory<Appointment, String>("doctor"));
			address.setCellValueFactory(new PropertyValueFactory<Appointment, String>("address"));
			datetime.setCellValueFactory(new PropertyValueFactory<Appointment, String>("datetime"));
			doctorTable.setItems(appointmentList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	 public void bookAction(ActionEvent event) throws IOException{
		 
		 ObservableList<Appointment> selectedAppointment = doctorTable.getSelectionModel().getSelectedItems();
		 if(selectedAppointment.size() > 0)
		 {
	           
			 System.out.print(selectedAppointment.get(0).getDoctor());
			 Alert alert = new Alert(AlertType.INFORMATION);
		     alert.setHeaderText("Appointment Booked Successfully!");
		     alert.showAndWait();
		     try {
		    	String username = sceneController.user.getUsername();
				DB.executeAppointmentInsert(username, selectedAppointment.get(0).getDoctor(), selectedAppointment.get(0).getAddress(), selectedAppointment.get(0).getDatetime());
				DB.executeAppointmentDelete(selectedAppointment.get(0).getDatetime(), selectedAppointment.get(0).getDoctor());
				listAppointments();
			} catch (Exception e) {}
		 }
		 else
		 {
			 Alert alert = new Alert(AlertType.ERROR);
		     alert.setHeaderText("Please select an appointment to book!");
		     alert.showAndWait();
		 }
	 }
	 
	 public void switchUserScreen(ActionEvent event) throws IOException{

		mainscreen = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(mainscreen);
		stage.setScene(scene);
	    stage.show();
	    
	 }
}
