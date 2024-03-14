package dyrvania.scenes.backgrounds;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dyrvania.generics.GameRect;

public abstract class Background {

	private final GameRect rect;

	private final BufferedImage sprite;

	public Background(int x, int y, int width, int height, BufferedImage sprite) {
		this.rect = new GameRect(x, y, width, height);

		this.sprite = sprite;
	}

	public GameRect getRect() {
		return this.rect;
	}

	public void render(Graphics render) {
		render.drawImage(this.sprite, this.rect.getX(), this.rect.getY(), this.rect.getWidth(), this.rect.getHeight(), null);
	}

}
