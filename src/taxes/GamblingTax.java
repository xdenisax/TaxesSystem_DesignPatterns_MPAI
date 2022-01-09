package taxes;

import entities.Entity;

public class GamblingTax extends TaxDecorator {
	protected double incomeAmount;
	protected double taxPercent = 0.01;
	protected double taxToPay = 0;

	public GamblingTax(Entity entity, double incomeAmount) {
		super(entity);
		this.incomeAmount=incomeAmount;

		if(incomeAmount >= 0 && incomeAmount <= 66750 ) {
			taxToPay = incomeAmount*0.01;
		} else if(incomeAmount > 66750 && incomeAmount <= 445000) {
			taxToPay = incomeAmount*0.16+667.5;
		}else if(incomeAmount > 66750 && incomeAmount <= 445000) {
			taxToPay = incomeAmount*0.16+667.5;
		}else if(incomeAmount > 445000) {
			taxToPay = incomeAmount*0.25 + 61187.5;
		}
	}
	
	@Override
	public double calculateTaxes() {
		System.out.println(entity.getName() + " avea de plata " + entity.getTaxAmount());
		entity.setTaxAmount(taxToPay);
		System.out.println("Dupa aplicarea taxelor pe venit, totalul taxelor de plata a devenit " + entity.getTaxAmount() + "lei, crescand cu " + this.taxToPay + " lei.");
		return entity.getTaxAmount() ;
	}
	
}
