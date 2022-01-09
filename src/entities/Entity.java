package entities;

public abstract class Entity implements EntityInterface {
	private String name; 
	private String identifier;
	private String address;
	private double taxAmount;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	} 
	public double getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}
	
	public void presentEntity() {
		System.out.println(getName() + " with identifier " + getIdentifier() + " with address "+ getAddress());
	}
	
}
