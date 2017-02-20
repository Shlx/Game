package gameobject;

public class GameObject {

	private float x, y;
	private int width, height;
	
	public GameObject(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void setPosition(float x, float y) {
		this.setX(x);
		this.setY(y);
	}
	
	public float getCenterX() {
		return this.getX() + this.getWidth() / 2;
	}
	
	public float getCenterY() {
		return this.getY() + this.getHeight() / 2;
	}
	
	public float getTop() {
		return this.getY();
	}
	
	public float getBottom() {
		return this.getY() + this.getHeight();
	}
	
	public float getLeft() {
		return this.getX();
	}
	
	public float getRight() {
		return this.getX() + this.getWidth();
	}
	
	public void setTop(float top) {
		this.setY(top);
	}
	
	public void setBottom(float bottom) {
		this.setY(bottom - this.getHeight());
	}
	
	public void setLeft(float left) {
		this.setX(left);
	}
	
	public void setRight(float right) {
		this.setX(right - this.getWidth());
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

	
}
