package entity;

import java.util.ArrayList;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;

import items.Drop;
import items.Item;
import items.Stat;
import logic.Game;
import static util.Utils.*;

public class Enemy extends Creature {
	
	private float[] color = { random.nextFloat(), random.nextFloat(), random.nextFloat() };
	private Drop drop;

	public Enemy(float x, float y, float dx, float dy, int width, int height, int age, Map<Stat, Integer> stats,
			Drop drop) {
		super(x, y, dx, dy, width, height, age, stats);
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
		super.moveTowardsCharacter(getSpeed());
	}
	
	public void moveTowardsMouse() {
		super.moveTowardsMouse(getSpeed());
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
		glBegin(GL_LINES);
	        glVertex2f(x(this.getX()), y(this.getY() - 10));
			glVertex2f(x(this.getX() + this.getWidth()), y(this.getY() - 10));
	    glEnd();
	    
		glColor("green");
		glBegin(GL_LINES);
	        glVertex2f(x(this.getX()), y(this.getY() - 10));
			glVertex2f(x(this.getX() + (this.getWidth()*ratio)), y(this.getY() - 10));
	    glEnd();
		
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
