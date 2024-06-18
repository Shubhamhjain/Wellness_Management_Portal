package application;
import java.io.IOException;
import java.net.URI;
import Code.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.awt.*;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import java.awt.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

@SuppressWarnings("unused")
 public class sceneController {
	
	private Stage stage;
	private Scene scene;
	private Parent mainscreen;
	public static User user = new User();
	public static Admin admin = new Admin();
	 
	private static final ArrayList<Admin> adminList = new ArrayList<>();
 
 	@FXML
	private TextField password;
	@FXML
	private TextField username;
	@FXML
	private TextField name;
	@FXML
	private TextField surname;	
 	@FXML
 	private AnchorPane parent;
 	
 	@FXML
 	private Button blue;
 	@FXML
 	private BorderPane bp;
 	@FXML private
 	BorderPane ap;
	@FXML
	private Button exitProgram;
	
	LogIn pıD = new LogIn();
	
	
	@FXML
	private void home(MouseEvent event) throws IOException {
		loadPage("home");
	}
	@FXML
	private void addProduct(MouseEvent event) throws IOException {
		loadPage("addProduct");
	}
	@FXML
	private void userProfile(MouseEvent event) throws IOException {
		loadPage("userProfile");
	}
	@FXML
	private void buyProduct(MouseEvent event) throws IOException {
		loadPage("buyProduct");
	}

	@FXML
	private void addAppointment(MouseEvent event) throws IOException {
		loadPage("addAppointment");
	}
	@FXML
	private void switchUserProfile(MouseEvent event) throws IOException {
		loadPage("userProfile");
	}
	@FXML
	private void bookAppointment(MouseEvent event) throws IOException {
		loadPage("bookAppointment");
	}

	@FXML
	private void onEnterForRegisterUser(ActionEvent ae) throws IOException{
		switchUserLogInForSignUp(ae);
	}
	@FXML
	private void onEnterForLogInUser(ActionEvent ae) throws IOException{
	   switchUserLogIn(ae);
	}

	public void switchUserSignUp(ActionEvent event) throws IOException {

		 mainscreen = FXMLLoader.load(getClass().getResource("UserSignUp.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(mainscreen);
		 stage.setScene(scene);
		 stage.show();
		 
	 }
	
	 public void switchAdminScreen(ActionEvent event) throws IOException {
		 
		 sceneController.adminList.clear();
		 sceneController.adminList.add(new Admin("Harry", "Potter", "harry", "harry"));
		 sceneController.adminList.add(new Admin("Ron", "Weasley", "ron", "ron"));
		 mainscreen = FXMLLoader.load(getClass().getResource("AdminScreen.fxml"));
	     stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	     scene = new Scene(mainscreen);
		 stage.setScene(scene);
	     stage.show();
	     
	 }

	 public void switchUserScreen(ActionEvent event) throws IOException {
		 
		 mainscreen = FXMLLoader.load(getClass().getResource("UserScreen.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(mainscreen);
		 stage.setScene(scene);
		 stage.show();
	 }
 
 	public void switchHomeScreen(ActionEvent event) throws IOException {
	 
 		mainscreen = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
 		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
 		scene = new Scene(mainscreen);
 		stage.setScene(scene);
 		stage.show();
 	}

 	private void loadPage(String page) throws IOException {
	 
 		Parent root = null;
 		root = FXMLLoader.load(getClass().getResource(page+".fxml"));
 		bp.setCenter(root);
 	}

 
	 public void switchUserLogIn(ActionEvent event) throws IOException {
		 try {
			 String usernameString = username.getText();
			 String passwordString = password.getText();
			 int id = registerLogin.LogInUser(usernameString, passwordString);
			 if(id != 0)
			 {
				
				Connection con = DB.getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT * FROM person WHERE id = ?");
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					user.setName(rs.getString("name"));
					user.setSurname(rs.getString("surname"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
				}
	
				mainscreen = FXMLLoader.load(getClass().getResource("userLogIn.fxml"));
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(mainscreen);
				stage.setScene(scene);
				stage.show();	  
			 }
			 else
			 {
				 Alert alert = new Alert(AlertType.ERROR);
			     alert.setHeaderText("Invalid Credentials!");
			     alert.showAndWait();
			     username.setText("");
			     password.setText("");
			 }
		} 
		catch (Exception e) {}
	 }
 
	 public void switchUserLogInForSignUp(ActionEvent event) throws IOException {
		 try {
				String nameString = name.getText();
				String surnameString = surname.getText();
				String usernameString = username.getText();
				String passwordString = password.getText();
				
				if(nameString.length() == 0 || surnameString.length() == 0 || usernameString.length() == 0 || passwordString.length() == 0)
				{
					 Alert alert = new Alert(AlertType.ERROR);
				     alert.setHeaderText("Please fill all details!");
				     alert.showAndWait();
				     name.setText("");
				     surname.setText("");
				     username.setText("");
				     password.setText("");
				}
				else
				{
					int id = registerLogin.RegisterUser(nameString, surnameString, usernameString, passwordString);
					if(id != 0)
					{
						Connection con = DB.getConnection();
						PreparedStatement ps = con.prepareStatement("SELECT * FROM person WHERE id = ?");
						ps.setInt(1, id);
						ResultSet rs = ps.executeQuery();
						while(rs.next())
						{
							user.setName(rs.getString("name"));
							user.setSurname(rs.getString("surname"));
							user.setUsername(rs.getString("username"));
							user.setPassword(rs.getString("password"));
						}
		
						mainscreen = FXMLLoader.load(getClass().getResource("userLogIn.fxml"));
						stage = (Stage)((Node)event.getSource()).getScene().getWindow();
						scene = new Scene(mainscreen);
						stage.setScene(scene);
						stage.show();
					 }
					 else
					 {
						 Alert alert = new Alert(AlertType.ERROR);
					     alert.setHeaderText("Username exists!");
					     alert.showAndWait();
					     name.setText("");
					     surname.setText("");
					     username.setText("");
					     password.setText("");
					 }
				}

		 }catch (Exception e) {}
	 }
	 
	 public void switchAdminLogIn(ActionEvent event) throws IOException {
		 
		 String usernameString = username.getText();
		 String passwordString = password.getText();
		 boolean found = false;
		 
		 for(Admin a : sceneController.adminList)
		 {
			 if(a.getUsername().equals(usernameString) && a.getPassword().equals(passwordString))
			 {
				 admin = a;
				 found = true;
				 mainscreen = FXMLLoader.load(getClass().getResource("adminLogIn.fxml"));
				 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				 scene = new Scene(mainscreen);
				 stage.setScene(scene);
				 stage.show();
			 }
		 }
		 if(!found)
		 {
			 Alert alert = new Alert(AlertType.ERROR);
		     alert.setHeaderText("Invalid Credentials!");
		     alert.showAndWait();
		     username.setText("");
		     password.setText("");
		 }

	 }
 
	 public void exitProgram(ActionEvent event) {
			
		 Alert alert = new Alert(AlertType.CONFIRMATION);
		 alert.setTitle("Exit");
		 alert.setHeaderText("You're about to exit!");
		
		 if(alert.showAndWait().get() == ButtonType.OK){
			 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			 stage.close();
		 }
	 }
}