package dyrvania.scenes.objects;

import dyrvania.generics.GameRect;
import dyrvania.scenes.Scene;

public class Teleport {

	private final int color;

	private final GameRect rect;

	private final boolean isJump;

	public Teleport(Scene scene, int x, int y, int color, boolean isJump) {
		this.color = color;

		this.rect = new GameRect(x, y, scene.getSizeBaseTiles(), scene.getSizeBaseTiles());

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
