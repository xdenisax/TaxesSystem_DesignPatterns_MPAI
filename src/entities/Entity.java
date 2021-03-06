package entities;

import visitor.Visitable;
import visitor.Visitor;

public abstract class Entity implements EntityInterface, Visitable {
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
	
	public String presentEntity() {
		 return getName() + " with identifier " + getIdentifier() + " with address "+ getAddress() + " plateste taxe in valoare de " + getTaxAmount() + " lei";
	}

}
