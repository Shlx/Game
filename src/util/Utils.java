package util;

import java.util.Random;

import logic.Game;

import static org.lwjgl.opengl.GL11.*;

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
	
	public static void glColor(String color) {
		switch (color.toLowerCase()) {
			case "red":
				glColor3f(1, 0, 0);
				break;
			case "green":
				glColor3f(0, 1, 0);
				break;
			case "blue":
				glColor3f(0, 0, 1);
				break;
			case "yellow":
				glColor3f(1, 1, 0);
				break;
			case "magenta":
				glColor3f(1, 0, 1);
				break;
			case "cyan":
				glColor3f(0, 1, 1);
				break;
			case "white":
				glColor3f(1, 1, 1);
				break;
			default:
				glColor3f(0.5f, 0.5f, 0.5f);
				break;
		}
	}
	
	// Change game coordinates to GL coordinates
	
	public static float x(float x) {
		return -1.0f + 2.0f * x / Game.WINDOW_X;
	}
	
	public static float y(float y) {
		return 1.0f - 2.0f * y / Game.WINDOW_Y;
	}
	
}
