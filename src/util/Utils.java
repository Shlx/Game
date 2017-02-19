package util;

import java.util.Random;

import logic.Logic;

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
			case "grey":
				glColor3f(0.5f, 0.5f, 0.5f);
				break;
			default:
				glColor3f(0, 0, 0);
				break;
		}
	}
	
	public static void drawSquare (float x, float y, int width, int height) {
		
		int xScroll = Logic.xScroll;
		int yScroll = Logic.yScroll;
		
		glBegin(GL_QUADS);
	        glVertex2f(x - xScroll, y - yScroll);
			glVertex2f(x + width - xScroll, y - yScroll);
			glVertex2f(x + width - xScroll, y + height - yScroll);
			glVertex2f(x - xScroll, y + height - yScroll);
		glEnd();
	}
	
	public static void drawLineH (float x, float y, int length) {
		
		int xScroll = Logic.xScroll;
		int yScroll = Logic.yScroll;
		
		glBegin(GL_LINES);
        	glVertex2f(x - xScroll, y - yScroll);
			glVertex2f(x + length - xScroll, y - yScroll);
		glEnd();
		
	}
	
}
