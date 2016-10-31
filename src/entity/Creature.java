package entity;

import java.util.Map;

import items.Stat;

public class Creature extends Entity {

	private Map<Stat, Integer> stats;
	private int currentHp = 10;

	public Creature(float x, float y, float dx, float dy, int width, int height, int age, Map<Stat, Integer> stats) {
		super(x, y, dx, dy, width, height, age);
		this.stats = stats;
	}
	
	public Creature(float x, float y, float dx, float dy) {
		super(x, y, dx, dy, 20, 20, 0);
		this.stats = Stat.defaultStats;
	}

	public Creature(float x, float y) {
		super(x, y, 0, 0, 20, 20, 0);
		this.stats = Stat.defaultStats;
	}

	public Creature() {
		super();
	}
	
	public void draw() {
		super.draw();
	}
	
	public int getSpeed() {
		return this.getStats().get(Stat.SPD);
	}
	
	public int getMaxHp() {
		return this.getStats().get(Stat.HP);
	}
	
	public String getHpString() {
		return this.getCurrentHp() + " / " + this.getMaxHp() + " HP";
	}
	
	////////// GETTERS / SETTERS //////////

	public Map<Stat, Integer> getStats() {
		return stats;
	}

	public void setStats(Map<Stat, Integer> stats) {
		this.stats = stats;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		if (currentHp < 0) { this.delete(); }
		else { this.currentHp = currentHp; }
	}
	
}
