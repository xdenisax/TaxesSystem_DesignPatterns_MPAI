package entities;

import visitor.Visitor;

public class JuridicalEntity extends Entity{
	public JuridicalEntity(String name, String identifier, String address) {
		setName(name);
		setIdentifier("CUI: "+identifier);
		setAddress("Sediu: " +address);
		setTaxAmount(0);
	}

	@Override
	public double calculateTaxes() {
		return getTaxAmount();
	}

	@Override
	public String accept(Visitor visitor) {
		return visitor.visitJuridical(this);
	}
}
