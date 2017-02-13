package entity;

import java.util.ArrayList;
import java.util.Map;

import items.Item;
import items.Stat;
import logic.Game;
import skills.Skill;
import static util.Utils.*;

public class Player extends Creature {
	
	private ArrayList<Skill> skills;
	private ArrayList<Item> items;
	private Map<Stat, Integer> statsFromItems;
	private int level, xp;

	public Player(float x, float y, float dx, float dy, int width, int height, int age, Map<Stat, Integer> stats,
			ArrayList<Skill> skills, ArrayList<Item> items, Map<Stat, Integer> statsFromItems, int level, int xp) {
		super(x, y, dx, dy, width, height, age, stats);
		this.skills = skills;
		this.items = items;
		this.statsFromItems = statsFromItems;
		this.level = level;
		this.xp = xp;
	}
	
	public Player(float x, float y) {
		super(x, y);
	}

	public int calculateItemStat(Stat stat) {
		
		int value = 0;
		
		for (Item item : items) {
			
			value += item.getStats().get(stat);
			
		}
		
		return value;
	}
	
	public void calculateAllItemStats() {
		
		// Calculate item stats for every stat
		
		for (Stat stat : Stat.values()) {
			
			this.getStatsFromItems().put(stat, calculateItemStat(stat));
			
		}
		
	}
	
	public void shootTowards(int x, int y, float speed) {
		
		float xDistance = x - this.getCenterX();
		float yDistance = y - this.getCenterY();
			
		float sum = (Math.abs(xDistance) + Math.abs(yDistance));
		
		if (sum != 0) {
			
			new Shot(this.getCenterX(), this.getCenterY(), speed * (xDistance / sum), speed * (yDistance / sum));
	        
		} else {
			
			System.out.println("really?");
			
		}
		
	}
	
	public void draw() {
		glColor("blue");
		super.draw();
	}
	
	public void checkCollisions() {
		for (Entity e : Game.activeEntities) {
			
			// Collision with money: collect money
			
//			if (e instanceof Money) {
				if (this.collide(e)) {
					
					if (e instanceof Money ) {Game.money += ((Money) e).getAmount();
					e.delete();}
					
				}
//			}
		}
	}
	
	public float getDiagSpeed() {
		return (float) Math.sqrt(getSpeed() * 2);
	}

	////////// GETTERS / SETTERS //////////
	
	public ArrayList<Skill> getSkills() {
		return skills;
	}

	public void setSkills(ArrayList<Skill> skills) {
		this.skills = skills;
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	public Map<Stat, Integer> getStatsFromItems() {
		return statsFromItems;
	}

	public void setStatsFromItems(Map<Stat, Integer> statsFromItems) {
		this.statsFromItems = statsFromItems;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

}
