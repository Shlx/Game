package logic;

import entity.Entity;

import java.io.PrintWriter;
import java.util.Iterator;

import entity.Enemy;

public class Logic {
	
	static long tStart = System.currentTimeMillis();
	public static int xScroll, yScroll;
	
	public static void nextFrame() {
		
		if (Game.PAUSED) { Game.FRAME_COUNT++; }
		
		if (Game.DEBUG) { gameDebug(); }
		
		xScroll = xScroll();
		yScroll = yScroll();
		
		Game.map.draw();
		
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
			if (!Game.PAUSED) { entity.nextFrame(); }
			
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
			
		    Entity entity = iterator.next();
			
			Game.activeEntities.add(entity);
			iterator.remove();
			
		}		
	}
	
	// Calculate how much the screen should be scrolled horizontally
	private static int xScroll() {
		if (Game.character == null) { return 0; }
		else { return 
				Math.max(
					0, 
					Math.min(
						(int) Game.character.getCenterX() - Game.WINDOW_X / 2,
						Game.map.getSizeX() - Game.WINDOW_X
					)
				);
		}
	}
	
	// Calculate how much the screen should be scrolled vertically
	private static int yScroll() {
		if (Game.character == null) { return 0; }
		else { return
				Math.max(
					0,
					Math.min(
						(int) Game.character.getCenterY() - Game.WINDOW_Y / 2,
						Game.map.getSizeY() - Game.WINDOW_Y
					)
				);
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
		System.out.println("\tEntities: " + Game.activeEntities.size());
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
