package items;

import java.util.HashMap;
import java.util.Map;

import static util.Utils.*;

public class Drop {

	private Map<Item, Float> items = new HashMap<>();
	int money;
	int xp;
	
	public static Drop defaultDrop = new Drop(intBetween(1, 10), intBetween(10, 20));
	
	public Drop(int money, int xp) {
		// Test, remove this
		this.items.put(new Item(1, "Test"), 50f);
		this.money = money;
		this.xp = xp;
	}

	////////// GETTERS / SETTERS //////////
	
	public Map<Item, Float> getItems() {
		return items;
	}

	public void setItems(Map<Item, Float> items) {
		this.items = items;
	}
	
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}
		
}
