package dyrvania.scenes.entities.enemies;

import java.awt.Graphics;

import dyrvania.generics.GameRect;
import dyrvania.generics.GameRectEntity;
import dyrvania.generics.GameSpriteAnimation;
import dyrvania.generics.GameUtil;
import dyrvania.resources.GameAudio;
import dyrvania.scenes.Scene;

public abstract class Enemy {

	protected final Scene scene;

	protected final GameRectEntity rect;

	private int hp;
	private final int hpMax;

	private int damage;
	private boolean hasAShield;

	protected double speedX;
	protected double speedY;

	protected boolean isDirRight;
	protected GameSpriteAnimation currentSprite;

	protected GameSpriteAnimation spriteRunRight;
	protected GameSpriteAnimation spriteRunLeft;
	protected GameSpriteAnimation spriteDeath;

	private final GameAudio audioHit;

	public Enemy(Scene scene, int x, int y, int width, int height, int hp, int damage, double speedX) {
		this.scene = scene;

		this.rect = new GameRectEntity(x, y, width, height);

		this.hpMax = hp;
		this.hp = this.hpMax;

		this.damage = damage;
		this.hasAShield = false;

		this.speedX = speedX;
		this.speedY = 0;

		this.isDirRight = GameUtil.generateRandomNumber(0, 1) == 0;

		this.loadSprites();

		this.setCurrentSprite(this.spriteRunRight);
		this.setSpritePosition();

		this.audioHit = new GameAudio("/audios/hit.wav", -15f);
	}

	protected abstract void loadSprites();

	public GameRect getRect() {
		return this.rect.getRect();
	}

	public boolean isDead() {
		return this.hp <= 0 && this.currentSprite.finishedAnimation();
	}

	public int dealDamage() {
		return this.damage;
	}

	public void takeDamage(int damage) {
		if (!this.hasAShield) {
			this.audioHit.stop();
			this.audioHit.play();

			this.hp -= damage;
			this.hasAShield = true;
		}
	}

	public void resetShield() {
		this.hasAShield = false;
	}

	public void setPosition(int x, int y) {
		this.rect.setX(x);
		this.rect.setY(y);

		this.setSpritePosition();
	}

	protected void applyGravity() {
		this.speedY += this.scene.getGravity();

		if (this.speedY > 7) {
			this.speedY = 7;
		}

		for (double i = 0; i <= this.speedY; i += 0.5) {
			if (!this.isOnTheFloor()) {
				this.rect.setY(this.rect.getY() + 0.5);
			} else {
				this.speedY = 0;
				break;
			}
		}
	}

	protected void toMove() {
		if (this.isOnTheFloor()) {
			double vel;
			GameRectEntity newRect = new GameRectEntity(this.rect.getX(), this.rect.getY(), this.rect.getWidth(), this.rect.getHeight());

			if (this.isDirRight) {
				vel = 0.5;
				newRect.setX(newRect.getX() + newRect.getWidth());
			} else {
				vel = -0.5;
				newRect.setX(newRect.getX() - newRect.getWidth());
			}

			newRect.setY(newRect.getY() + 0.5);

			for (double i = 0; i <= this.speedX; i += 0.5) {
				if (this.scene.isFree(new GameRectEntity(this.rect.getX() + vel, this.rect.getY(), this.rect.getWidth(), this.rect.getHeight()).getRect())) {
					this.rect.setX(this.rect.getX() + vel);

					if (!this.isOnTheFloor()) {
						this.rect.setY(this.rect.getY() + 0.5);
					}
				} else {
					this.isDirRight = !this.isDirRight;
					break;
				}
			}

			if (this.scene.isFree(newRect.getRect())) {
				this.isDirRight = !this.isDirRight;
			}
		}
	}

	protected boolean isOnTheFloor() {
		return !this.scene.isFree(new GameRectEntity(this.rect.getX(), this.rect.getY() + 0.5, this.rect.getWidth(), this.rect.getHeight()).getRect());
	}

	protected abstract void setSpritePosition();

	private void setCurrentSprite(GameSpriteAnimation newSprite) {
		if (this.currentSprite != newSprite) {
			if (this.currentSprite != null) {
				this.currentSprite.reset();
			}

			this.currentSprite = newSprite;
		}
	}

	public void tick() {
		if (this.hp > 0) {
			this.applyGravity();
			this.toMove();
			this.setSpritePosition();

			if (this.isDirRight) {
				this.setCurrentSprite(this.spriteRunRight);
			} else {
				this.setCurrentSprite(this.spriteRunLeft);
			}
		} else {
			this.setCurrentSprite(this.spriteDeath);
		}

		this.currentSprite.tick();
	}

	public void render(Graphics render) {
		if (this.hasAShield) {
			this.currentSprite.renderDamage(render);
		} else {
			this.currentSprite.render(render);
		}
	}

}
