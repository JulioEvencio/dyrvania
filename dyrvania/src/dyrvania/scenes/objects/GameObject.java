package dyrvania.scenes.objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dyrvania.generics.Camera;
import dyrvania.generics.GameRect;

public abstract class GameObject {

	private final GameRect rect;
	private final BufferedImage sprite;

	public GameObject(GameRect rect, BufferedImage sprite) {
		this.rect = rect;
		this.sprite = sprite;
	}

	public GameRect getRect() {
		return this.rect;
	}

	public void setPosition(int x, int y) {
		this.rect.setX(x);
		this.rect.setY(y);
	}

	public void render(Graphics render) {
		render.drawImage(this.sprite, this.rect.getX() - Camera.x, this.rect.getY() - Camera.y, this.rect.getWidth(), this.rect.getHeight(), null);
	}

}
