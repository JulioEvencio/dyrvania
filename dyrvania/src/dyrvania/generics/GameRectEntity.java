package dyrvania.generics;

public class GameRectEntity {

	private float x;
	private float y;

	private int width;
	private int height;

	public GameRectEntity(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;

		this.width = width;
		this.height = height;
	}

	public float getX() {
		return this.x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return this.y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public GameRect getRect() {
		return new GameRect((int) this.x, (int) this.y, this.width, this.height);
	}

}