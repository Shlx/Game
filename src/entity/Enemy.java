package entity;

import java.util.ArrayList;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import items.Drop;
import items.Stat;
import logic.Game;
import logic.Logic;
import util.Utils;

public class Enemy extends Creature {

	private float[] color = { Utils.random.nextFloat(), Utils.random.nextFloat(), Utils.random.nextFloat() };
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
		ArrayList<Drop> dropped = new ArrayList<>();
		
		// TODO For every drop, check if it should drop
		
		// Drop the items that passed the check
		
		for (Drop drop : dropped) {
			
			// TODO Drop it
			
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
	
	public void draw() {
		Utils.setColor(this.getColor());
		super.draw();
		this.drawLifeBar();
	}
	
	public void drawLifeBar() {
		
		float ratio = (float) this.getCurrentHp() / this.getMaxHp();
	    
		Utils.setColor("red");
		GL11.glBegin(GL11.GL_LINES);
	        GL11.glVertex2f(x(this.getX()), y(this.getY() - 10));
			GL11.glVertex2f(x(this.getX() + this.getWidth()), y(this.getY() - 10));
	    GL11.glEnd();
	    
		Utils.setColor("green");
		GL11.glBegin(GL11.GL_LINES);
	        GL11.glVertex2f(x(this.getX()), y(this.getY() - 10));
			GL11.glVertex2f(x(this.getX() + (this.getWidth()*ratio)), y(this.getY() - 10));
	    GL11.glEnd();
		
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
