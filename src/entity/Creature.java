package entity;

import java.util.Map;

import items.Stat;
import logic.Game;

public class Creature extends Entity {

	private Map<Stat, Integer> stats;
	private int currentHp;

	public Creature(float x, float y, float dx, float dy, int width, int height, Map<Stat, Integer> stats) {
		super(x, y, dx, dy, width, height);
		this.stats = stats;
		this.currentHp = stats.get(Stat.HP);
	}
	
	public Creature(float x, float y, float dx, float dy) {
		super(x, y, dx, dy, 20, 20);
		this.stats = Stat.defaultStats;
		this.currentHp = stats.get(Stat.HP);
	}

	public Creature(float x, float y) {
		super(x, y, 0, 0, 20, 20);
		this.stats = Stat.defaultStats;
		this.currentHp = stats.get(Stat.HP);
	}
	
	public void draw() {
		super.draw();
	}
	
	public float getSpeed() {
		if (Game.DIAG_MOVE) { return getDiagSpeed(); }
		else { return getStraightSpeed(); }
	}
	
	public int getStraightSpeed() {
		return this.getStats().get(Stat.SPD);
	}
	
	public float getDiagSpeed() {
		return (float) Math.sqrt(getStraightSpeed() * 2);
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
		if (currentHp <= 0) { this.delete(); }
		else { this.currentHp = currentHp; }
	}
	
}
