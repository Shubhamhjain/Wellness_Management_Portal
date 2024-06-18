package application;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import Code.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Code.DB;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class buyProduct implements Initializable {
	
	@FXML private TableView<Product> searchTable;
	@FXML private TableColumn<Product ,String> name;
	@FXML private TableColumn<Product, String> brand;
	@FXML private TableColumn<Product, Integer> price;
	
	private Stage stage;
	private Scene scene;
	private Parent mainscreen;

	@Override
	public void initialize(URL url, ResourceBundle rb)  {
		
		listProducts();
		
	}
	
	private void listProducts()
	{
		ObservableList<Product> productList = FXCollections.observableArrayList();
		try {
			ResultSet result = DB.executeSelect("SELECT * FROM products");
			while(result.next())
			{
				productList.add(new Product(result.getString("NAME"), result.getString("BRAND"), result.getInt("PRICE")));
			}
			name.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
			brand.setCellValueFactory(new PropertyValueFactory<Product, String>("brand"));
			price.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
			searchTable.setItems(productList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	 public void buyAction(ActionEvent event) throws IOException{
		 
		 ObservableList<Product> selectedProduct = searchTable.getSelectionModel().getSelectedItems();
		 if(selectedProduct.size() > 0)
		 {		 
			 Alert alert = new Alert(AlertType.INFORMATION);
		     alert.setHeaderText("Product Bought Successfully!");
		     alert.showAndWait();
		     try {
		    	String username = sceneController.user.getUsername();
			    DB.executeProductInsert(username, selectedProduct.get(0).getName(), selectedProduct.get(0).getBrand(), selectedProduct.get(0).getPrice());
				DB.executeProductDelete(selectedProduct.get(0).getName());
				listProducts();
			} catch (Exception e) {}
		 }
		 else
		 {
			 Alert alert = new Alert(AlertType.ERROR);
		     alert.setHeaderText("Please select a product to buy!");
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
