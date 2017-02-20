package entity;

import java.util.Map;

import static org.lwjgl.opengl.GL11.*;

import items.Drop;
import items.Item;
import items.Stat;
import logic.Game;
import static util.Utils.*;
import static renderer.Renderer.drawLineH;

public class Enemy extends Creature {
	
	private float[] color = { random.nextFloat(), random.nextFloat(), random.nextFloat() };
	private Drop drop;

	public Enemy(float x, float y, float dx, float dy, int width, int height, Map<Stat, Integer> stats,
			Drop drop) {
		super(x, y, dx, dy, width, height, stats);
		this.drop = Drop.defaultDrop;
	}
	
	public Enemy(float x, float y) {
		super(x, y);
		this.drop = Drop.defaultDrop;
	}

	public void drop() {

		for (Item i : this.getDrop().getItems().keySet()) {
			
			// Drop items that pass the roll
			
		}

		// Award XP and drop money
		
		Game.xp += this.getDrop().getXp();		
		new Money(this.getCenterX(), this.getCenterY(), this.getDrop().getMoney());

	}
	
	@Override
	public void delete() {
		
		// Add the enemy to the 'delete' list and drop its items
		
		Game.deleteEntities.add(this);
		this.drop();
		
	}
	
	public void moveTowardsCharacter() {
		super.moveTowardsCharacter(getStraightSpeed());
	}
	
	public void moveTowardsMouse() {
		super.moveTowardsMouse(getStraightSpeed());
	}
	
	@Override
	public void checkCollisions() {
		// TODO
	}
	
	public void draw() {
		glColor3fv(this.getColor());
		super.draw();
		this.drawLifeBar();
	}
	
	public void drawLifeBar() {
		
		float ratio = (float) this.getCurrentHp() / this.getMaxHp();
		
		glLineWidth(2);
	    
		glColor("red");
		drawLineH(this.getX(), this.getY() - 10, this.getWidth());
	    
		glColor("green");
		drawLineH(this.getX(), this.getY() - 10, (int) (this.getWidth() * ratio));
		
	}
	
	////////// GETTERS / SETTERS //////////
	
	public Drop getDrop() {
		return drop;
	}

	public void setDrop(Drop drop) {
		this.drop = drop;
	}
	
	public float[] getColor() {
		return color;
	}

	public void setColor(float[] color) {
		this.color = color;
	}
	
}
