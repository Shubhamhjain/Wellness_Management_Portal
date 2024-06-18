package Code;

public interface AdminOperations {
	
	public boolean addNewProduct(String name, String brand, int price);
	public boolean addNewAppointment(String doctor, String address, String datetime);

}