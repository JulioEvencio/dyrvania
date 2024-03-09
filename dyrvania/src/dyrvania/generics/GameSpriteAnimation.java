package dyrvania.generics;

import java.awt.AlphaComposite;
import java.awt.Color;
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
	private final BufferedImage[] spritesDamage;
	private final BufferedImage[] spritesPoisoned;

	public GameSpriteAnimation(GameRect rect, int maxFrames, BufferedImage[] sprites) {
		this.rect = rect;

		this.alpha = 1f;

		this.finishedAnimation = false;

		this.frames = 0;
		this.maxFrames = maxFrames;

		this.index = 0;
		this.maxIndex = sprites.length;

		this.sprites = sprites;
		this.spritesDamage = new BufferedImage[this.sprites.length];
		this.spritesPoisoned = new BufferedImage[this.sprites.length];

		for (int i = 0; i < this.spritesDamage.length; i++) {
			this.spritesDamage[i] = this.createSpritesColor(this.sprites[i], new Color(100, 0, 0, 128));
			this.spritesPoisoned[i] = this.createSpritesColor(this.sprites[i], new Color(0, 100, 0, 128));
		}
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

	private BufferedImage createSpritesColor(BufferedImage original, Color color) {
		BufferedImage sprite = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = sprite.createGraphics();

		g.drawImage(original, 0, 0, null);

		for (int y = 0; y < original.getHeight(); y++) {
			for (int x = 0; x < original.getWidth(); x++) {
				int pixel = original.getRGB(x, y);

				if ((pixel >> 24) != 0x00) {
					g.setColor(color);
					g.fillRect(x, y, 1, 1);
				}
			}
		}

		g.dispose();

		return sprite;
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

	public void renderDamage(Graphics render) {
		Graphics2D g = (Graphics2D) render;

		g.drawImage(this.spritesDamage[this.index], this.rect.getX() - Camera.x, this.rect.getY() - Camera.y, this.rect.getWidth(), this.rect.getHeight(), null);
	}

	public void renderPoisoned(Graphics render) {
		Graphics2D g = (Graphics2D) render;

		g.drawImage(this.spritesPoisoned[this.index], this.rect.getX() - Camera.x, this.rect.getY() - Camera.y, this.rect.getWidth(), this.rect.getHeight(), null);
	}

}