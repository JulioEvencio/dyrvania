package dyrvania.scenes.objects;

import dyrvania.generics.GameRect;

public class Spawn {

	private final GameRect rect;

	public Spawn(int x, int y, int width, int height) {
		this.rect = new GameRect(x, y, width, height);
	}

	public GameRect getRect() {
		return this.rect;
	}

}
