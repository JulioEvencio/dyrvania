package dyrvania.scenes.objects;

import dyrvania.generics.GameRect;

public class Teleport {

	private final int color;

	private final GameRect rect;

	private final boolean isJump;

	public Teleport(int x, int y, int color, boolean isJump) {
		this.color = color;

		this.rect = new GameRect(x, y, 32, 32);

		this.isJump = isJump;
	}

	public int getColor() {
		return this.color;
	}

	public GameRect getRect() {
		return this.rect;
	}

	public boolean isJump() {
		return this.isJump;
	}

}
