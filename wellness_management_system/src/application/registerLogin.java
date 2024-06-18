package application;

import Code.LogInScreen;

public class registerLogin {
	
	public static int RegisterUser(String name, String surname, String username, String password) 
	{
		if(LogInScreen.checkRegisterForUser(username, password)) {
			return LogInScreen.addPerson(name, surname, username, password);
		}
		return 0;
	}

	public static int LogInUser(String username, String password) {

		return LogInScreen.checkLogin(username, password);
			
	}

}
