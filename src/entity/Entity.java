package entity;

import org.lwjgl.opengl.GL11;

import logic.Game;

public class Entity {
	
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
		
		// Add the entity to the 'active' list
		
		Game.spawnEntities.add(this);
	}
	
	public Entity(float x, float y, float dx, float dy) {
		this(x, y, dx, dy, 20, 20, 0);
	}
	
	public Entity(float x, float y) {
		this(x, y, 0, 0, 20, 20, 0);
	}

	public Entity() {
		this(0, 0, 0, 0, 20, 20, 0);
	}
	
	public void delete() {
		
		// Add the entity to the 'delete' list
		
		Game.deleteEntities.add(this);
		
	}
	
	public void nextFrame() {
		this.setAge(this.getAge() + 1);	
		this.move();
	}
	
	public void checkCollisions() {
		
	}
	
	public void move() {
			
		// Move the entity by its speed
		
		this.setX(this.getX() + this.getDx());
		this.setY(this.getY() + this.getDy());
	
		// Don't let it move out of bounds
		
		if (this.getX() < 0) { this.setX(0); }
		if (this.getX() + this.getWidth() > Game.SIZE_X) { this.setX(Game.SIZE_X - this.getWidth()); }
		
		if (this.getY() < 0) { this.setY(0); }
		if (this.getY() + this.getHeight() > Game.SIZE_Y) { this.setY(Game.SIZE_Y - this.getHeight()); }
		
	}
	
	public void MoveTowards(float x, float y, float speed) {
		
		float xDistance = x - this.getX();
		float yDistance = y - this.getY();
			
		float sum = (Math.abs(xDistance) + Math.abs(yDistance));
		
		if (sum != 0) {
			
	        this.setSpeed(speed * (xDistance / sum), speed * (yDistance / sum));
	        
		} else {
			
			this.setSpeed(0, 0);
			
		}
	}
	
	public void MoveTowardsCharacter(int speed) {
		MoveTowards(Game.character.getX(), Game.character.getY(), speed);
	}
	
	public void MoveTowardsMouse(float speed) {
		MoveTowards(Game.MOUSE_X, Game.MOUSE_Y, speed);
	}
	
	public void draw() {
		
		// Clear the screen and depth buffer
		//GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	
		//glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); 
		
	    // set the color of the quad (R,G,B,A)
 
	    // draw quad
	    GL11.glBegin(GL11.GL_QUADS);
	        GL11.glVertex2f(x(this.getX()), y(this.getY()));
			GL11.glVertex2f(x(this.getX() + this.getWidth()), y(this.getY()));
			GL11.glVertex2f(x(this.getX() + this.getWidth()), y(this.getY() + this.getHeight()));
			GL11.glVertex2f(x(this.getX()), y(this.getY() + this.getHeight()));
	    GL11.glEnd();
		
	}
	
	public boolean collide(Entity e) {
		if (e == null) { return false; }
		
		return this.getY() < e.getY() + e.getHeight() && this.getY() + this.getHeight() > e.getY() &&
				this.getX() < e.getX() + e.getWidth() && this.getX() + this.getWidth() > e.getX();
				
		
	}
	
	public static float x(float x) {
		return -1.0f + 2.0f * x / Game.WINDOW_X;
	}
	
	public static float y(float y) {
		return 1.0f - 2.0f * y / Game.WINDOW_Y;
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
