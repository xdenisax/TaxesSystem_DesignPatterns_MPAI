package entities;

import visitor.Visitor;

public class Individual extends Entity {
	public Individual(String name, String identifier, String address) {
		setName(name);
		setIdentifier("CNP: "+ identifier);
		setAddress("Adresa: " + address);
		setTaxAmount(0);
	}

	@Override
	public double calculateTaxes() {
		return getTaxAmount();
	}

	@Override
	public String accept(Visitor visitor) {
		return visitor.visitIndividual(this);
	}
}
