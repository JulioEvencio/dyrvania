package dyrvania.scenes.entities.enemies;

import java.awt.Graphics;

import dyrvania.generics.GameDamage;
import dyrvania.generics.GameRect;
import dyrvania.generics.GameRectEntity;
import dyrvania.generics.GameSpriteAnimation;
import dyrvania.generics.GameUtil;
import dyrvania.managers.GameManagerAudio;
import dyrvania.scenes.Scene;

public abstract class Enemy {

	protected final Scene scene;

	protected final GameRectEntity rect;

	private int hp;
	private final int hpMax;

	private GameDamage damage;

	private boolean hasAShield;

	protected float speedX;
	protected float speedY;

	protected boolean isDirRight;
	protected GameSpriteAnimation currentSprite;

	protected GameSpriteAnimation spriteRunRight;
	protected GameSpriteAnimation spriteRunLeft;
	protected GameSpriteAnimation spriteDeath;

	public Enemy(Scene scene, int x, int y, int width, int height, int hp, GameDamage damage, float speedX) {
		this.scene = scene;

		this.rect = new GameRectEntity(x, y, width, height);

		this.hpMax = hp;
		this.hp = this.hpMax;

		this.damage = damage;
		this.hasAShield = false;

		this.speedX = speedX;
		this.speedY = 0f;

		this.isDirRight = GameUtil.generateRandomNumber(0, 1) == 0;

		this.loadSprites();

		this.setCurrentSprite(this.spriteRunRight);
		this.setSpritePosition();
	}

	protected abstract void loadSprites();

	public GameRect getRect() {
		return this.rect.getRect();
	}

	public boolean isAnimationDead() {
		return this.currentSprite == this.spriteDeath;
	}

	public boolean isDead() {
		return this.hp <= 0 && this.currentSprite.finishedAnimation();
	}

	public GameDamage dealDamage() {
		return this.damage;
	}

	public void takeDamage(GameDamage damage) {
		if (!this.hasAShield) {
			GameManagerAudio.getAudioEnemyHit().play();

			this.hp -= damage.getDamage();
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

		if (this.speedY > 7f) {
			this.speedY = 7f;
		}

		for (float i = 0f; i <= this.speedY; i += 0.5f) {
			if (!this.isOnTheFloor()) {
				this.rect.setY(this.rect.getY() + 0.5f);
			} else {
				this.speedY = 0f;
				break;
			}
		}
	}

	protected void toMove() {
		if (this.isOnTheFloor()) {
			float vel;
			GameRectEntity newRect = new GameRectEntity(this.rect.getX(), this.rect.getY(), this.rect.getWidth(), this.rect.getHeight());

			if (this.isDirRight) {
				vel = 0.5f;
				newRect.setX(newRect.getX() + newRect.getWidth());
			} else {
				vel = -0.5f;
				newRect.setX(newRect.getX() - newRect.getWidth());
			}

			newRect.setY(newRect.getY() + 0.5f);

			for (float i = 0f; i <= this.speedX; i += 0.5f) {
				if (this.scene.isFree(new GameRectEntity(this.rect.getX() + vel, this.rect.getY(), this.rect.getWidth(), this.rect.getHeight()).getRect())) {
					this.rect.setX(this.rect.getX() + vel);

					if (!this.isOnTheFloor()) {
						this.rect.setY(this.rect.getY() + 0.5f);
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
		return !this.scene.isFree(new GameRectEntity(this.rect.getX(), this.rect.getY() + 0.5f, this.rect.getWidth(), this.rect.getHeight()).getRect());
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
			this.currentSprite.renderSpritesSecondary(render);
		} else {
			this.currentSprite.render(render);
		}
	}

}
