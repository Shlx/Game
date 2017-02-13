package items;

import items.Stat;

import java.util.Map;

import entity.Entity;

public class Item {

	private int id;
	private String name;
	private Map<Stat, Integer> stats;
	private Entity entity;

	public Item(int id, String name, Map<Stat, Integer> stats) {
		this.id = id;
		this.name = name;
		this.stats = stats;
		//this.entity = new Entity(this);
	}
	
	public Item(int id, String name) {
		this.id = id;
		this.name = name;
		this.stats = Stat.defaultItemStats;
	}
	
	// items definieren und benennen: item itemX = ...

	////////// GETTERS / SETTERS //////////
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<Stat, Integer> getStats() {
		return stats;
	}

	public void setStats(Map<Stat, Integer> stats) {
		this.stats = stats;
	}	
	
}
