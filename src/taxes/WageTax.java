package taxes;

import entities.Entity;

public class WageTax extends TaxDecorator {
	protected double incomeAmount;
	final double taxPercent = 0.1;

	public WageTax(Entity entity, double incomeAmount) {
		super(entity);
		this.incomeAmount=incomeAmount;
	}
	

	@Override
	public double calculateTaxes() {
		System.out.println(entity.getName() + " avea de platit " + entity.getTaxAmount());
		entity.setTaxAmount(entity.getTaxAmount() + this.incomeAmount*this.taxPercent);
		System.out.println("Dupa aplicarea taxelor pe venit, totalul taxelor de plata a devenit " + entity.getTaxAmount() + "lei, crescand cu " + this.incomeAmount*this.taxPercent+ " lei.");
		return entity.getTaxAmount() ;
	}
	
}
