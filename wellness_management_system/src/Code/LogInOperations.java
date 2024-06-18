package Code;

public interface LogInOperations {
	
	public void add(String name, String surname, String username, String password);

	public int get(String username, String password);
	
	public boolean checkUserNameExists(String username);
	
}
