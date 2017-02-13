package logic;

import entity.Entity;

import java.util.Iterator;

import entity.Enemy;

public class Logic {
	
	static long tStart = System.currentTimeMillis();
	
	public static void nextFrame() {
		
		Game.FRAME_COUNT++;
		
		if (Game.DEBUG) { gameDebug(); }
		
		// Handle updating active entities		
		activeEntities();
		
		// Handle deleting entities		
		deleteEntities();
		
		// Handle spawning entities
		spawnEntities();
		
	}
	
	public static void activeEntities() {
		int i = 1;
		for (Entity entity : Game.activeEntities) {
			
		    if (Game.DEBUG) { entityDebug(i++, entity); }
			
			if (entity instanceof Enemy) {
				Enemy enemy = (Enemy) entity;
				
				// Testing purposes
				//enemy.setCurrentHp(enemy.getCurrentHp() - 1);
				
				if (Game.character != null) {
					enemy.moveTowardsCharacter();
				} else {
					enemy.moveTowardsMouse();
				}
				
			}
			
			entity.checkCollisions(); //TODO Shot x Enemy
			
			// Do not calculate the next frame if the game is paused
			if (!Game.PAUSE) { entity.nextFrame(); }
			
			if (entity.getAge() > Game.ENTITY_MAX_AGE) {
				
				entity.delete();
				
			}
			
			entity.draw();
			
		}
		
		// So the character ends up on top	
		if (Game.character != null) { Game.character.draw(); }
		
	}
	
	public static void deleteEntities() {
		
		for (Entity entity : Game.deleteEntities) {
			
			Game.activeEntities.remove(entity);
			
			if (entity instanceof Enemy) {
				
				Enemy enemy = (Enemy) entity;
				
			}		
		}
	}
	
	public static void spawnEntities() {
		
		Iterator<Entity> iterator = Game.spawnEntities.listIterator();
		while (iterator.hasNext()) {
			System.out.println(Game.spawnEntities.size());
		    Entity entity = iterator.next();
			
			Game.activeEntities.add(entity);
			iterator.remove();
			
		}		
	}
	
	public static void entityDebug(int i, Entity entity) {
		
    	System.out.print("\tHandling entity " + i + " (" + entity.getClass().getSimpleName());
    	if (entity instanceof Enemy) { System.out.print(", " + ((Enemy) entity).getHpString()); }
    	System.out.println(")");
    	
	}
	
	public static void gameDebug() {
		
		if (Game.PAUSE) {
			System.out.println("----- GAME IS PAUSED -----");
		}
		
		long tEnd = System.currentTimeMillis();
		long tDelta = tEnd - tStart;
		double elapsedSeconds = tDelta / 1000.0;
		
		System.out.println("Frame " + Game.FRAME_COUNT + " (" + elapsedSeconds + "s):");
		System.out.println("\tMoney: " + Game.money + " XP: " + Game.xp);
		System.out.println("\tEntities: " + Game.activeEntities.size());
		
	}
	
}
