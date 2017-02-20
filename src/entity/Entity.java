package entity;

import logic.Game;

import static renderer.Renderer.drawSquare;

import gameobject.GameObject;

public class Entity extends GameObject {
	
	private float dx, dy;	
	private int age;

	public Entity(float x, float y, float dx, float dy, int width, int height) {
		super(x, y, width, height);
		this.dx = dx;
		this.dy = dy;
		this.age = 0;
		
		// Add the entity to the 'spawn' list
		
		Game.spawnEntities.add(this);
	}
	
	// Add the entity to the 'delete' list
	
	public void delete() {
		
		Game.deleteEntities.add(this);
		
	}
	
	// Compute next frame, i.e. increase age and move
	
	public void nextFrame() {
		this.setAge(this.getAge() + 1);	
		
		if (this.getAge() > Game.ENTITY_MAX_AGE) {
			
			this.delete();
			
		}
		
		this.move();
		
	}
	
	public void move() {
		
		int sizeX = Game.map.getSizeX();
		int sizeY = Game.map.getSizeY();
		
		this.checkWallCollisions();
			
		// Move the entity by its speed
		
		this.setX(this.getX() + this.getDx());
		this.setY(this.getY() + this.getDy());
	
		// Don't let it move out of bounds

		if (this.getX() < 1) { this.setX(1); }
		if (this.getX() + this.getWidth() > sizeX) { this.setX(sizeX - this.getWidth()); }
		
		if (this.getY() < 1) { this.setY(1); }
		if (this.getY() + this.getHeight() > sizeY) { this.setY(sizeY - this.getHeight()); }
		
	}
	
	public void checkWallCollisions() {
		
		// Collision with unpassable tiles
		// TODO: Make this good
		
		for (GameObject go : Game.collisionEntities) {
			
			if (this.collide(go)) {
				
				if (this.collideLeft(go)) { System.out.println("left"); this.setRight(go.getLeft()); this.setDx(0); }
				if (this.collideRight(go)) { System.out.println("right"); this.setLeft(go.getRight()); this.setDx(0); }
				if (this.collideTop(go)) { System.out.println("top"); this.setBottom(go.getTop()); this.setDy(0); }
				if (this.collideBottom(go)) { System.out.println("bot"); this.setTop(go.getBottom()); this.setDy(0); }
				
			}	
		}	
	}
	
	public void checkCollisions() {}
	
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
		
		drawSquare(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		
	}
	
	public boolean collide(GameObject go) {
		if (go == null) { return false; }
		
		return  this.getY() < go.getY() + go.getHeight() &&
				this.getY() + this.getHeight() > go.getY() &&
				this.getX() < go.getX() + go.getWidth() &&
				this.getX() + this.getWidth() > go.getX();	
	}
	
	public boolean collideTop(GameObject go) {
		return this.getBottom() > go.getTop();
	}
	
	public boolean collideBottom(GameObject go) {
		return this.getTop() > go.getBottom();
	}
	
	public boolean collideLeft(GameObject go) {
		return this.getRight() > go.getLeft();
	}
	
	public boolean collideRight(GameObject go) {
		return this.getLeft() > go.getRight();
	}
	
	public void setSpeed(float dx, float dy) {
		this.setDx(dx);
		this.setDy(dy);
	}
	
	////////// GETTERS / SETTERS //////////

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
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}
