package dyrvania.generics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class GameSpriteAnimation {

	private final GameRect rect;

	private int frames;
	private final int maxFrames;

	private int index;
	private final int maxIndex;

	private final BufferedImage[] sprites;

	public GameSpriteAnimation(GameRect rect, int maxFrames, BufferedImage[] sprites) {
		this.rect = rect;

		this.frames = 0;
		this.maxFrames = maxFrames;

		this.index = 0;
		this.maxIndex = sprites.length;

		this.sprites = sprites;
	}

	public void setPosition(int x, int y) {
		this.rect.setX(x);
		this.rect.setY(y);
	}

	public void tick() {
		this.frames++;

		if (this.frames >= this.maxFrames) {
			this.frames = 0;
			this.index++;

			if (this.index >= this.maxIndex) {
				this.index = 0;
			}
		}
	}

	public void render(Graphics graphics) {
		graphics.drawImage(this.sprites[this.index], this.rect.getX() - Camera.x, this.rect.getY() - Camera.y, this.rect.getWidth(), this.rect.getHeight(), null);
	}

}