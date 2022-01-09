package entities;

public class EntityFactory {
	public Entity createEntity(String entityType, String name, String identifier, String address) {
		Entity entity = null; 
		
		switch(entityType) {
		  case "f":
			  entity = new Individual(name, identifier, address);
		    break;
		  case "j":
			  entity = new JuridicalEntity(name, identifier, address);
			break;
		  default:
			  System.out.println("Tipul nu este suportat.");
		}
		return entity;
	}
}
