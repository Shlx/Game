package entity;
import org.lwjgl.opengl.GL11;

import logic.Game;


public class Shot extends Entity {

	public Shot(float x, float y, float dx, float dy) {	
		super(x, y, dx, dy, 3, 3, 0);
	}
	
	public Shot(float x, float y) {	
		super(x, y, 0, 0, 3, 3, 0);
	}
	
	public void draw() {
		GL11.glColor3f(1.0f, 0.0f, 0.0f);
		super.draw();
	}
	
	public void move() {
		
		// Move the entity by its speed
		
		this.setX(this.getX() + this.getDx());
		this.setY(this.getY() + this.getDy());
	
		// Don't let it move out of bounds
		
		if (this.getX() < 0 || this.getX() + this.getWidth() > Game.SIZE_X) { this.delete(); }
		
		if (this.getY() < 0 || this.getY() + this.getHeight() > Game.SIZE_Y) { this.delete(); }
		
	}
	
	public void checkCollisions() {
		for (Entity e : Game.activeEntities) {
			
			// Collision with money: collect money
			
			if (e instanceof Enemy) {
				if (this.collide(e)) {
					((Enemy) e).setCurrentHp(((Enemy) e).getCurrentHp() - 1);
				}
			}
		}
	}
	
	//////////GETTERS / SETTERS //////////
	
	/*public float getX() {
		float x = super.getX();
		if (x < 0 ||x + this.getWidth() > Game.SIZE_Y) {
			this.delete();
		}
		return x;
	}

	public float getY() {
		float y = super.getY();
		if (y < 0 || y + this.getHeight() > Game.SIZE_Y) {
			this.delete();
		}
		
		return y;
	}*/
	
}
