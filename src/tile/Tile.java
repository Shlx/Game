package tile;

import static renderer.Renderer.drawSquare;
import static util.Utils.glColor;

import gameobject.GameObject;
import logic.Game;

public class Tile extends GameObject {

	private static int size = Game.TILE_SIZE;
	
	private boolean walkable;
	
	public Tile(int x, int y, boolean walkable) {
		super(x, y, size, size);
		this.walkable = walkable;
	}
	
	public void draw() {
		
		// Draw a grey rectangle if it's a wall
		if (this instanceof Wall) { glColor("grey"); }
		
		// Draw a white rectangle otherwise
		else if (this instanceof Floor) { glColor("white"); }
		
		drawSquare(this.getX(), this.getY(), size, size);
	}

	////////// GETTERS / SETTERS //////////

	public boolean isWalkable() {
		return walkable;
	}

	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}
	
	public int getSize() {
		return size;
	}
	
}
