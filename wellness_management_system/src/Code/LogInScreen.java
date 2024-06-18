package Code;

public class LogInScreen {				
	
	static public int checkLogin(String username, String password) {
		
		LogIn user = new LogIn();
		return user.get(username, password);
	}
	
	static public boolean checkRegisterForUser(String username, String password) {
		
		LogIn user = new LogIn();
		boolean usernameExists = user.checkUserNameExists(username);
		if (usernameExists == false && password.length() >= 5) {
			return true;
		}
		return false;
	}
	
	static public int addPerson(String name, String surname, String username, String password) {
		
		LogIn user = new LogIn();
		user.add(name, surname, username, password);
		return user.get(username, password);
		
	}

}
