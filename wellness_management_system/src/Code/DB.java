package Code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DB { 
	static Connection myConn;
	
	public static Connection getConnection() throws Exception{
        if(myConn == null){
           Class.forName("com.mysql.cj.jdbc.Driver"); 
           myConn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/wellness_management_system", "root", "Indianhuskies@74"); //Please put user name and password 
        }
        return myConn;
    }
	
	public static ResultSet executeSelect(String query) throws Exception
    {
        PreparedStatement select = myConn.prepareStatement(query);
        ResultSet result = select.executeQuery();
        return result;
    }
    
	public static void executeProductDelete(String productName) throws Exception
    {
        PreparedStatement delete = myConn.prepareStatement("DELETE FROM products WHERE NAME = ?");
        delete.setString(1, productName);
        delete.executeUpdate();
    }
	
	public static void executeAppointmentDelete(String datetime, String doctor) throws Exception
    {
        PreparedStatement delete = myConn.prepareStatement("DELETE FROM appointments WHERE DOCTOR = ? AND DATETIME = ?");
        delete.setString(1, doctor);
        delete.setString(2, datetime);
        delete.executeUpdate();
    }
	
	public static void executeProductInsert(String username, String name, String brand, int price) throws Exception
    {
        PreparedStatement insert = myConn.prepareStatement("INSERT INTO pastorders(username, name, brand, price) VALUES(?, ?, ?, ?)");
        insert.setString(1, username);
        insert.setString(2, name);
        insert.setString(3, brand);
        insert.setInt(4, price);
        insert.executeUpdate();
    }
	
	public static void executeAppointmentInsert(String username, String doctor, String address, String datetime) throws Exception
    {
        PreparedStatement insert = myConn.prepareStatement("INSERT INTO pastappointments(username, doctor, address, datetime) VALUES(?, ?, ?, ?)");
        insert.setString(1, username);
        insert.setString(2, doctor);
        insert.setString(3, address);
        insert.setString(4, datetime);
        insert.executeUpdate();
    }
	
	public static ResultSet executeProfileOrders(String username) throws Exception
	{
		PreparedStatement insert = myConn.prepareStatement("SELECT * FROM pastorders WHERE username = ?");
		insert.setString(1, username);
        ResultSet result = insert.executeQuery();
        return result;
	}
	
    public static ResultSet executeProfileAppointments(String username) throws Exception
	{
		PreparedStatement insert = myConn.prepareStatement("SELECT * FROM pastappointments WHERE username = ?");
		insert.setString(1, username);
        ResultSet result = insert.executeQuery();
        return result;
		
	}
}