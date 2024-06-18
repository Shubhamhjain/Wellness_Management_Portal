package Code;

public class Appointment {
	
	private String doctor;
	private String address;
	private String datetime;
	
	public Appointment(String doctor, String address, String datetime)
	{
		this.doctor = doctor;
		this.address = address;
		this.datetime = datetime;
	}
	
	public String getDoctor()
	{
		return this.doctor;
	}
	
	public void setdoctor(String doctor)
	{
		this.doctor = doctor;
	}
	
	public String getAddress()
	{
		return this.address;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public String getDatetime()
	{
		return this.datetime;
	}
	
	public void setDatetime(String datetime)
	{
		this.datetime = datetime;
	}

}
