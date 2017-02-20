package entity;

import static util.Utils.*;

public class Money extends Entity {

	int amount;

	public Money(float x, float y, float dx, float dy, int width, int height, int amount) {
		super(x, y, dx, dy, width, height);
		this.amount = amount;
	}
	
	public Money(float x, float y, int amount) {
		super(x, y, 3, 3, 15, 15);
		this.amount = amount;
	}
	
	@Override
	public void nextFrame() {
		this.setAge(this.getAge() + 1);
		this.slowDown();
		this.move();
	}
	
	public void slowDown() {
		this.setDx(this.getDx() * 0.9f);
		this.setDy(this.getDy() * 0.9f);
	}
	
	public void draw() {
		glColor("yellow");
		super.draw();
	}
	
	@Override
	public void checkCollisions() {
		// TODO
	}
	
	////////// GETTERS / SETTERS //////////

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
