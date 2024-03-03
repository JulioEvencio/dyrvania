package dyrvania.scenes.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dyrvania.generics.Camera;
import dyrvania.generics.GameRect;
import dyrvania.resources.Spritesheet;

public class Floor {

	private final GameRect rect;

	private static final BufferedImage sprite;

	static {
		sprite = Spritesheet.getSpriteTiles(48, 40, 32, 32);
	}

	public Floor(int x, int y, int width, int height) {
		this.rect = new GameRect(x, y, width, height);
	}

	public GameRect getRect() {
		return this.rect;
	}

	public void render(Graphics render) {
		render.drawImage(Floor.sprite, this.rect.getX() - Camera.x, this.rect.getY() - Camera.y, this.rect.getWidth(), this.rect.getHeight(), null);
	}

}
