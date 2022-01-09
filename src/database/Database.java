package database;

import java.util.ArrayList;
import entities.Entity;

public class Database implements DatabaseInterface{
	protected ArrayList<Entity> entities;
	private static Database databaseInstance;
	
	private Database() {
		entities = new ArrayList<>();
	}
	
	public static Database getInstance() { 
		if(databaseInstance == null ) { 
			databaseInstance = new Database();
		}
		return databaseInstance;
	}

	@Override
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	public ArrayList<Entity> getEntities() {
		return this.entities;
	}
	
	public void removeEntity (int index) {
		entities.remove(index);
	}
}
