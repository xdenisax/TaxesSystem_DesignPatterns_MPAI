package database;

import entities.Entity;

public class DatabaseProxy implements DatabaseInterface {

	Database database = Database.getInstance();
	@Override
	public void addEntity(Entity entity) {
		database.addEntity(entity);
	}

}
