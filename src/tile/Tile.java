package tile;

import static util.Utils.drawSquare;
import static util.Utils.glColor;

import logic.Game;

public class Tile {

	private int x, y;
	private boolean walkable;
	private int size = Game.TILE_SIZE;
	
	public Tile(int x, int y, boolean walkable) {
		this.x = x;
		this.y = y;
		this.walkable = walkable;
	}
	
	public Tile(boolean walkable) {
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
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

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
