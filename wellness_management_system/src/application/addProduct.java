
package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class addProduct implements Initializable {
	
	@FXML private TextField nameAddProduct;
	@FXML private TextField brandAddProduct;
	@FXML private TextField priceAddProduct;
	private Stage stage;
	private Scene scene;
	private Parent mainscreen;

	public void addAProduct(ActionEvent event) {
		
		String name = nameAddProduct.getText();
		String brand = brandAddProduct.getText();
		String stringPrice =  priceAddProduct.getText();
		
		if(name.length() == 0 || brand.length() == 0 || stringPrice.length() == 0)
		{
			Alert alert = new Alert(AlertType.ERROR);
		    alert.setHeaderText("Please fill all details!");
		    alert.showAndWait();
		}
		else
		{
			int price = Integer.parseInt(stringPrice);
			if(sceneController.admin.addNewProduct(name, brand, price))
			{
				nameAddProduct.setText("");
				brandAddProduct.setText("");
				priceAddProduct.setText("");
			}
		}
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
