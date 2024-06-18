package Code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LogIn implements LogInOperations {

	@Override
	public void add(String name, String surname, String username, String password) {
		try {
			Connection con = DB.getConnection();
			String sql = "INSERT INTO person(name, surname, username, password) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, surname);
			ps.setString(3, username);
			ps.setString(4, password);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int get(String username, String password) 
	{
		try {
			Connection con = DB.getConnection();
			PreparedStatement user = con.prepareStatement("SELECT * FROM person WHERE username = ? AND password = ?");
			user.setString(1, username);
			user.setString(2, password);
			ResultSet rs = user.executeQuery();
			while(rs.next()) 
			{
				return rs.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public boolean checkUserNameExists(String username) {
		
		try {

			Connection con = DB.getConnection();
			PreparedStatement user = con.prepareStatement("SELECT * FROM person WHERE username = ?");
			user.setString(1, username);
			ResultSet rs = user.executeQuery();
			if(!rs.next())
			{
				return false;
			}
			return true;
		}catch (Exception e) {}
		
		return false;
	}
}
	
