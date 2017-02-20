package renderer;

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

import entity.Entity;
import logic.Game;

public class Renderer {

	public static int xScroll, yScroll;
	
	public static void nextFrame() {
		
		xScroll = xScroll();
		yScroll = yScroll();
		
		Game.map.draw();
		
		for (Entity entity : Game.activeEntities) {
			entity.draw();
		}	
		
		// Draw the player character last so it will end up on top
		if (Game.character != null) { Game.character.draw(); }
		
		if (Game.PAUSED) {
			// TODO: Draw screen greyed out (transparent layer on top)
		}
		
	}
	
	// Calculate how much the screen should be scrolled horizontally
	private static int xScroll() {
		if (Game.character == null) { return 0; }
		else { return 
				Math.max(
					0, 
					Math.min(
						(int) Game.character.getCenterX() - Game.WINDOW_X / 2,
						Game.map.getSizeX() - Game.WINDOW_X
					)
				);
		}
	}
	
	// Calculate how much the screen should be scrolled vertically
	private static int yScroll() {
		if (Game.character == null) { return 0; }
		else { return
				Math.max(
					0,
					Math.min(
						(int) Game.character.getCenterY() - Game.WINDOW_Y / 2,
						Game.map.getSizeY() - Game.WINDOW_Y
					)
				);
		}
	}
	
	public static void drawSquare(float x, float y, int width, int height) {
		
		glBegin(GL_QUADS);
	        glVertex2f(x - xScroll, y - yScroll);
			glVertex2f(x + width - xScroll, y - yScroll);
			glVertex2f(x + width - xScroll, y + height - yScroll);
			glVertex2f(x - xScroll, y + height - yScroll);
		glEnd();
	}
	
	public static void drawLineH(float x, float y, int length) {
		
		glBegin(GL_LINES);
        	glVertex2f(x - xScroll, y - yScroll);
			glVertex2f(x + length - xScroll, y - yScroll);
		glEnd();
		
	}
	
	
}
