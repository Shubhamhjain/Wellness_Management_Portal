package application;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import Code.Product;
import Code.Appointment;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class userProfile implements Initializable{
	
	@FXML private Text idProfileName;
	@FXML private Text idProfileSurname;
	@FXML private TableView<Product> pastOrders;
	@FXML private TableColumn<Product, String> name;
	@FXML private TableColumn<Product, String> brand;
	@FXML private TableColumn<Product, Integer> price;
	
	@FXML private TableView<Appointment> pastAppointments;
	@FXML private TableColumn<Appointment, String> doctor;
	@FXML private TableColumn<Appointment, String> address;
	@FXML private TableColumn<Appointment, String> datetime;
	
	private Stage stage;
	private Scene scene;
	private Parent mainscreen;
	
	private void fillProductTable(String username)
	{
		ObservableList<Product> productList = FXCollections.observableArrayList();
		try {
			ResultSet result = DB.executeProfileOrders(username);
			while(result.next())
			{
				productList.add(new Product(result.getString("NAME"), result.getString("BRAND"), result.getInt("PRICE")));
			}
			name.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
			brand.setCellValueFactory(new PropertyValueFactory<Product, String>("brand"));
			price.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
			pastOrders.setItems(productList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void fillAppointmentTable(String username)
	{
		ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
		try {
			ResultSet result = DB.executeProfileAppointments(username);
			while(result.next())
			{
				appointmentList.add(new Appointment(result.getString("DOCTOR"), result.getString("ADDRESS"), result.getString("DATETIME")));
			}
			doctor.setCellValueFactory(new PropertyValueFactory<Appointment, String>("doctor"));
			address.setCellValueFactory(new PropertyValueFactory<Appointment, String>("address"));
			datetime.setCellValueFactory(new PropertyValueFactory<Appointment, String>("datetime"));
			pastAppointments.setItems(appointmentList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb)  {

		idProfileName.setText(sceneController.user.getName());
		idProfileSurname.setText(sceneController.user.getSurname());
		String username = sceneController.user.getUsername();
		fillProductTable(username);
		fillAppointmentTable(username);
	 
	}
	
	public void switchUserScreen(ActionEvent event) throws IOException{

		mainscreen = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(mainscreen);
		stage.setScene(scene);
	    stage.show();
	    
	 }
}