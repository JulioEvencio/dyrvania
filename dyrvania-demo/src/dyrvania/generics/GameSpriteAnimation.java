package dyrvania.generics;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GameSpriteAnimation {

	private final GameRect rect;

	private float alpha;

	private boolean finishedAnimation;

	private int frames;
	private final int maxFrames;

	private int index;
	private final int maxIndex;

	private final BufferedImage[] sprites;
	private final BufferedImage[] spritesSecondary;

	public GameSpriteAnimation(GameRect rect, int maxFrames, BufferedImage[] sprites, BufferedImage[] spritesSecondary) {
		this.rect = rect;

		this.alpha = 1f;

		this.finishedAnimation = false;

		this.frames = 0;
		this.maxFrames = maxFrames;

		this.index = 0;
		this.maxIndex = sprites.length;

		this.sprites = sprites;
		this.spritesSecondary = spritesSecondary;
	}

	public int getIndex() {
		return this.index;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

	public void setPosition(int x, int y) {
		this.rect.setX(x);
		this.rect.setY(y);
	}

	public void reset() {
		this.frames = 0;
		this.index = 0;
		this.finishedAnimation = false;
	}

	public boolean finishedAnimation() {
		return this.finishedAnimation;
	}

	public void tick() {
		this.frames++;

		if (this.frames >= this.maxFrames) {
			this.frames = 0;
			this.index++;

			if (this.index >= this.maxIndex) {
				this.index = 0;
				this.finishedAnimation = true;
			}
		}
	}

	public void render(Graphics render) {
		if (this.alpha == 1f) {
			render.drawImage(this.sprites[this.index], this.rect.getX() - Camera.x, this.rect.getY() - Camera.y, this.rect.getWidth(), this.rect.getHeight(), null);
		} else if (this.frames % 2 == 0) {
			Graphics2D g = (Graphics2D) render;

			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.alpha));
			g.drawImage(this.sprites[this.index], this.rect.getX() - Camera.x, this.rect.getY() - Camera.y, this.rect.getWidth(), this.rect.getHeight(), null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
	}

	public void renderSpritesSecondary(Graphics render) {
		Graphics2D g = (Graphics2D) render;

		g.drawImage(this.spritesSecondary[this.index], this.rect.getX() - Camera.x, this.rect.getY() - Camera.y, this.rect.getWidth(), this.rect.getHeight(), null);
	}

}