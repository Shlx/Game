package items;

import items.Stat;

import java.util.Map;

public class Item {

	private int id;
	private String name;
	private Map<Stat, Integer> stats;

	public Item(int id, String name, Map<Stat, Integer> stats) {
		this.id = id;
		this.name = name;
		this.stats = stats;
	}

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
