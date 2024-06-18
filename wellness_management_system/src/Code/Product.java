package Code;

public class Product {
	
	private String name;
	private String brand;
	private int price;

	public Product(String name, String brand, int price) 
	{
		this.name = name;
		this.brand = brand;
		this.price = price;
	}
	
	public String getName() 
	{
		return this.name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getBrand() 
	{
		return this.brand;
	}

	public void setBrand(String brand) 
	{
		this.brand = brand;
	}
	
	public int getPrice() 
	{
		return this.price;
	}

	public void setPrice(int price) 
	{
		this.price = price;
	}
}
