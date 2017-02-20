package logic;

import entity.Entity;

import java.io.PrintWriter;
import java.util.Iterator;

import entity.Enemy;

public class Logic {
	
	static long tStart = System.currentTimeMillis();
	
	public static void nextFrame() {
		
		if (!Game.PAUSED) { Game.FRAME_COUNT++; }
		
		if (Game.DEBUG) { gameDebug(); }
		
		// 1. Spawn new entities
		spawnEntities();
		
		// 2. Handle updating active entities		
		activeEntities();
		
		// 3. Check collisions between entities
		checkCollisions();
		
		// 4. Handle deleting entities		
		deleteEntities();
		
	}
	
	public static void spawnEntities() {
		
		Iterator<Entity> iterator = Game.spawnEntities.listIterator();
		while (iterator.hasNext()) {
			
		    Entity entity = iterator.next();
			
			Game.activeEntities.add(entity);
			iterator.remove();
			
		}		
	}
	
	public static void activeEntities() {
		int i = 1;
		for (Entity entity : Game.activeEntities) {
			
		    if (Game.DEBUG) { entityDebug(i++, entity); }
			
			if (entity instanceof Enemy) {
				Enemy enemy = (Enemy) entity;
				
				if (Game.character != null) {
					enemy.moveTowardsCharacter();
				} else {
					enemy.moveTowardsMouse();
				}
				
			}
			
			// Do not calculate the next frame if the game is paused
			if (!Game.PAUSED) { entity.nextFrame(); }
			
		}
		
	}
	
	public static void checkCollisions() {
		
		for (Entity entity : Game.activeEntities) {
			entity.checkCollisions();
		}
		
	}
	
	public static void deleteEntities() {
		
		Iterator<Entity> iterator = Game.deleteEntities.listIterator();
		while (iterator.hasNext()) {
		
			Entity entity = iterator.next();
			
			Game.activeEntities.remove(entity);
			
			iterator.remove();		
		}
	}
	
	// TODO: Pause timer during pause
	public static void gameDebug() {
		
		if (!Game.PAUSED) {
		
		long tEnd = System.currentTimeMillis();
		long tDelta = tEnd - tStart;
		double elapsedSeconds = tDelta / 1000.0;
		
		System.out.println("Frame " + Game.FRAME_COUNT + " (" + elapsedSeconds + "s):");
		System.out.println("\tMoney: " + Game.money + ", XP: " + Game.xp);
		System.out.println("\tEntities: a: " + Game.activeEntities.size() + " d: " + Game.deleteEntities.size() + " s: " + Game.spawnEntities.size());
		System.out.println("Mouse: " + Game.MOUSE_X + " | " + Game.MOUSE_Y);
		
		if (Game.RUN_SERVER && Game.FRAME_COUNT % 60 == 0) { debugToServer(); }
		
		}
		
	}
	
	public static void entityDebug(int i, Entity entity) {
		
		if (!Game.PAUSED) {
			System.out.print("\tHandling entity " + i + " (" + entity.getClass().getSimpleName());
    		if (entity instanceof Enemy) { System.out.print(", " + ((Enemy) entity).getHpString()); }
    		System.out.println(" at " + entity.getX() + " | " + entity.getY() + ")");
		}
    	
	}
	
	// TODO: Not working properly
	public static void debugToServer() {
		PrintWriter out;
		try {
			out = new PrintWriter(Game.socket.getOutputStream(), true);
			out.println("Frame " + Game.FRAME_COUNT + ":");
			out.println("\tMoney: " + Game.money + ", XP: " + Game.xp);
			out.println("\tEntities: " + Game.activeEntities.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
