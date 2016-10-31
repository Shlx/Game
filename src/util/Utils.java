package util;

import java.util.Random;

import org.lwjgl.opengl.GL11;

public class Utils {
	
	public static Random random = new Random();

	public static boolean percentChance(float chance) {
		
		return chance > random.nextFloat() * 100;
		
	}
	
	// Returns a number between from and to (inclusive)
	
	public static int intBetween(int from, int to) {
		
		if (from > to) { return intBetween(to, from); }
		
		return random.nextInt(to - from + 1) + from;
		
	}
	
	public static void setColor(float[] color) {
		GL11.glColor3f(color[0], color[1], color[2]);
	}
	
	public static void setColor(String color) {
		switch (color.toLowerCase()) {
			case "green":
				GL11.glColor3f(0, 1, 0);
				break;
			case "red":
				GL11.glColor3f(1, 0, 0);
				break;
			default:
				GL11.glColor3f(0, 0, 0);
				break;
		}
	}
}
