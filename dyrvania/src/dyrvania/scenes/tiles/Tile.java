package dyrvania.scenes.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dyrvania.generics.Camera;
import dyrvania.generics.GameRect;

public class Tile {

	private final GameRect rect;

	private final BufferedImage sprite;

	public Tile(int x, int y, int width, int height, BufferedImage sprite) {
		this.rect = new GameRect(x, y, width, height);

		this.sprite = sprite;
	}

	public GameRect getRect() {
		return this.rect;
	}

	public void render(Graphics render) {
		render.drawImage(this.sprite, this.rect.getX() - Camera.x, this.rect.getY() - Camera.y, this.rect.getWidth(), this.rect.getHeight(), null);
	}

}
