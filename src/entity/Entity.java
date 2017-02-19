package entity;

import logic.Game;
import static util.Utils.*;

import items.Item;

//Entity root class. Pretty much everything extends this

public class Entity {
	
	// Position and speed
	private float x, y, dx, dy;
	
	private int width, height, age;

	public Entity(float x, float y, float dx, float dy, int width, int height, int age) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.width = width;
		this.height = height;
		this.age = age;
		
		// Add the entity to the 'spawn' list
		
		Game.spawnEntities.add(this);
	}
	
	// Instantiate with default width and height of 20
	
	public Entity(float x, float y, float dx, float dy) {
		this(x, y, dx, dy, 20, 20, 0);
	}
	
	// TODO: What was this for?
	
	public Entity(float x, float y, float dx, float dy, Item item) {
		this(x, y, dx, dy, 10, 10, 0);
	}
	
	// Instantiate with default width, height and speed
	
	public Entity(float x, float y) {
		this(x, y, 0, 0, 20, 20, 0);
	}
	
	// TODO: What would you need this for?

	public Entity() {
		this(0, 0, 0, 0, 20, 20, 0);
	}
	
	// Add the entity to the 'delete' list
	
	public void delete() {
		
		Game.deleteEntities.add(this);
		
	}
	
	// Compute next frame, i.e. increase age and move
	
	public void nextFrame() {
		this.setAge(this.getAge() + 1);	
		this.move();
	}
	
	public void checkCollisions() {
		
	}
	
	public void move() {
		
		int sizeX = Game.map.getSizeX();
		int sizeY = Game.map.getSizeY();
			
		// Move the entity by its speed
		
		this.setX(this.getX() + this.getDx());
		this.setY(this.getY() + this.getDy());
	
		// Don't let it move out of bounds
		
		if (this.getX() < 1) { this.setX(1); }
		if (this.getX() + this.getWidth() > sizeX) { this.setX(sizeX - this.getWidth()); }
		
		if (this.getY() < 1) { this.setY(1); }
		if (this.getY() + this.getHeight() > sizeY) { this.setY(sizeY - this.getHeight()); }
		
	}
	
	// Recalculate speed so the entity moves towards some coordinates
	
	public void moveTowards(float x, float y, float speed) {
		
		// TODO: Something's wrong when entities are moving diagonally (slower than expected)
		
		float xDistance = x - this.getX();
		float yDistance = y - this.getY();
			
		float sum = (Math.abs(xDistance) + Math.abs(yDistance));
		
		if (sum != 0) {
			
	        this.setSpeed(speed * (xDistance / sum), speed * (yDistance / sum));
	        
		} else {
			
			// Entity is already at its destination, no need to move
			
			this.setSpeed(0, 0);
			
		}
	}
	
	public void moveTowardsCharacter(float speed) {
		moveTowards(Game.character.getX(), Game.character.getY(), speed);
	}
	
	public void moveTowardsMouse(float speed) {
		moveTowards(Game.MOUSE_X, Game.MOUSE_Y, speed);
	}
	
	public void draw() {
		
		// Clear the screen and depth buffer
		//glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); 
		
	    // set the color of the quad (R,G,B,A)
 
	    // draw quad
		drawSquare(this.getX(), this.getY(), this.getWidth(), this.getHeight());
//	    glBegin(GL_QUADS);
//	        glVertex2f(this.getX(), this.getY());
//			glVertex2f(this.getX() + this.getWidth(), this.getY());
//			glVertex2f(this.getX() + this.getWidth(), this.getY() + this.getHeight());
//			glVertex2f(this.getX(), this.getY() + this.getHeight());
//	    glEnd();
		
	}
	
	public boolean collide(Entity e) {
		if (e == null) { return false; }
		
		return  this.getY() < e.getY() + e.getHeight() &&
				this.getY() + this.getHeight() > e.getY() &&
				this.getX() < e.getX() + e.getWidth() &&
				this.getX() + this.getWidth() > e.getX();
				
		
	}
	
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void setSpeed(float dx, float dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public float getCenterX() {
		return this.getX() + this.getWidth() / 2;
	}
	
	public float getCenterY() {
		return this.getY() + this.getHeight() / 2;
	}
	
	////////// GETTERS / SETTERS //////////

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getDx() {
		return dx;
	}

	public void setDx(float dx) {
		this.dx = dx;
	}

	public float getDy() {
		return dy;
	}

	public void setDy(float dy) {
		this.dy = dy;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}
