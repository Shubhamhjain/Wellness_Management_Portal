package Code;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Admin extends Person implements AdminOperations {

	public Admin(String name, String surname, String username, String password) 
	{
		super(name, surname, username, password);
	}

	public Admin() 
	{
		
	}

	public boolean addNewProduct(String name, String brand, int price) 
	{
		try {
			Connection con = DB.getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO products(NAME, BRAND, PRICE) VALUES(?, ?, ?)");
			ps.setString(1, name);
			ps.setString(2, brand);
			ps.setInt(3, price);
			ps.executeUpdate();
			Alert alert = new Alert(AlertType.INFORMATION);
		    alert.setHeaderText("Product Added Successfully!");
		    alert.showAndWait();
		    return true;
		} catch (Exception e) {}
		return false;
	}

	@Override
	public boolean addNewAppointment(String doctor, String address, String datetime) {
		
		try {
			Connection con = DB.getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO appointments(DOCTOR, ADDRESS, DATETIME) VALUES(?, ?, ?)");
			ps.setString(1, doctor);
			ps.setString(2, address);
			ps.setString(3, datetime);
			ps.executeUpdate();
			Alert alert = new Alert(AlertType.INFORMATION);
		    alert.setHeaderText("Appointment Added Successfully!");
		    alert.showAndWait();
		    return true;
		} catch (Exception e) {}
		return false;
	}
}
