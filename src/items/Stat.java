package items;

import java.util.HashMap;
import java.util.Map;

public enum Stat { 
	
	STR, DEX, INT, LUK, HP, ATK, DEF, SPD; 
	
	public static Map<Stat, Integer> defaultStats = new HashMap<Stat, Integer>() {{
		put(HP, 10);
		put(ATK, 1);
		put(DEF, 1);
		put(SPD, 5);
	}};
	
}
