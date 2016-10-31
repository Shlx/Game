package logic;

import entity.Entity;

import java.util.Iterator;

import entity.Enemy;

public class Logic {
	
	static long tStart = System.currentTimeMillis();
	
	public static void nextFrame() {
		
		Game.FRAME_COUNT++;
		
		int i = 1;
		if (Game.DEBUG){
			long tEnd = System.currentTimeMillis();
			long tDelta = tEnd - tStart;
			double elapsedSeconds = tDelta / 1000.0;
			
			System.out.println("Frame " + Game.FRAME_COUNT + " (" + elapsedSeconds + "s):");
			System.out.println("\tMoney: " + Game.money + " XP: " + Game.xp);
			System.out.println("\tEntities: " + Game.activeEntities.size());
		}
		
		// Handle updating active entities
		
		for (Entity entity : Game.activeEntities) {
			
		    if (Game.DEBUG) {
		    	System.out.print("\tHandling entity " + i++ + " (" + entity.getClass().getSimpleName());
		    	if (entity instanceof Enemy) { System.out.print(", " + ((Enemy) entity).getHpString()); }
		    	System.out.println(")");
		    }
			
			if (entity instanceof Enemy) {
				Enemy enemy = (Enemy) entity;
				
				// Testing purposes
				//enemy.setCurrentHp(enemy.getCurrentHp() - 1);
				
				if (Game.character != null) {
					enemy.MoveTowardsCharacter(enemy.getSpeed());
				} else {
					enemy.MoveTowardsMouse(enemy.getSpeed());
				}
				
			}
			
			entity.checkCollisions(); //TODO Shot x Enemy
			
			entity.nextFrame();
			
			if (entity.getAge() > Game.ENTITY_MAX_AGE) {
				
				entity.delete();
				
			}
			
			entity.draw();
			
		}
		
		// So the character ends up on top
		
		if (Game.character != null) { Game.character.draw(); }
		
		// Handle deleting entities
		
		for (Entity entity : Game.deleteEntities) {
			
			Game.activeEntities.remove(entity);
			
			if (entity instanceof Enemy) {
				
				Enemy enemy = (Enemy) entity;
				
			}
			
		}
		
		Iterator<Entity> iterator = Game.spawnEntities.listIterator();
		while (iterator.hasNext()) {
			System.out.println(Game.spawnEntities.size());
		    Entity entity = iterator.next();
			
			Game.activeEntities.add(entity);
			iterator.remove();
			
		}
		
	}
	
}
