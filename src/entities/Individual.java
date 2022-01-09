package entities;

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
}
