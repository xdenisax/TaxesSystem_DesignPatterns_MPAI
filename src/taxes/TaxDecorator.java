package taxes;

import entities.Entity;
import entities.EntityInterface;

public abstract class TaxDecorator implements EntityInterface{
	protected Entity entity;
	
	public TaxDecorator(Entity entity) {
		this.entity = entity;
	}

	@Override
	public double calculateTaxes() {
		return entity.getTaxAmount();
	}

}
