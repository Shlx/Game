package items;

import java.util.HashMap;
import java.util.Map;

public enum Stat { 
	
	STR, DEX, INT, LUK, HP, ATK, DEF, SPD; 
	
	public static Map<Stat, Integer> defaultStats = new HashMap<Stat, Integer>() {
		private static final long serialVersionUID = 1L;
	{
		put(HP, 10);
		put(ATK, 1);
		put(DEF, 1);
		put(SPD, 5);
	}};
	
	public static Map<Stat, Integer> defaultItemStats = new HashMap<Stat, Integer>() {
		private static final long serialVersionUID = 1L;
	{
		put(ATK, 2);
		put(STR, 2);
		put(DEX, 1);
	}};
	
}
