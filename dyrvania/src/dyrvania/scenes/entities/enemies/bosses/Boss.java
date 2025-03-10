package dyrvania.scenes.entities.enemies.bosses;

import java.awt.Graphics;

import dyrvania.generics.GameDamage;
import dyrvania.generics.GameRect;
import dyrvania.generics.GameSpriteAnimation;
import dyrvania.managers.GameManagerAudio;
import dyrvania.managers.GameManagerSpriteDeath;
import dyrvania.managers.entities.enemies.bosses.GameManagerSpriteBoss;
import dyrvania.scenes.Scene;
import dyrvania.scenes.entities.enemies.Skeleton;
import dyrvania.scenes.objects.Spawn;

public class Boss {

	private Scene scene;

	private int newX;
	private int newY;

	private final GameRect rect;

	private int hp;
	private final int hpMax;

	private boolean hasAShield;

	private GameSpriteAnimation currentSprite;

	private final GameSpriteAnimation spriteIdle;
	private final GameSpriteAnimation spriteTeleport01;
	private final GameSpriteAnimation spriteTeleport02;
	private final GameSpriteAnimation spriteInvoking;

	private final GameSpriteAnimation spriteDeath;

	public Boss() {
		this.newX = 650;
		this.newY = 150;

		this.rect = new GameRect(this.newX, this.newY, 32, 32);

		this.hpMax = 20;
		this.hp = this.hpMax;

		GameRect spriteRect = new GameRect(0, 0, 128, 128);

		this.spriteIdle = GameManagerSpriteBoss.createSpriteIdle(spriteRect);
		this.spriteTeleport01 = GameManagerSpriteBoss.createSpriteTeleport01(spriteRect);
		this.spriteTeleport02 = GameManagerSpriteBoss.createSpriteTeleport02(spriteRect);
		this.spriteInvoking = GameManagerSpriteBoss.createSpriteInvoking(spriteRect);

		this.spriteDeath = GameManagerSpriteDeath.createSpriteDeathBlue(spriteRect);

		this.currentSprite = this.spriteTeleport02;

		this.setSpritePosition();
	}

	private void setSpritePosition() {
		if (this.currentSprite == this.spriteIdle) {
			this.currentSprite.setPosition(this.rect.getX() - 50, this.rect.getY() - 70);
		} else if (this.currentSprite == this.spriteTeleport01) {
			this.currentSprite.setPosition(this.rect.getX() - 50, this.rect.getY() - 95);
		} else if (this.currentSprite == this.spriteTeleport02) {
			this.currentSprite.setPosition(this.rect.getX() - 50, this.rect.getY() - 95);
		} else if (this.currentSprite == this.spriteInvoking) {
			this.currentSprite.setPosition(this.rect.getX() - 50, this.rect.getY() - 70);
		}
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public GameRect getRect() {
		return this.rect;
	}
	
	public boolean isAnimationDead() {
		return this.currentSprite == this.spriteDeath;
	}

	public boolean isDead() {
		return this.hp <= 0 && this.currentSprite.finishedAnimation();
	}

	public void takeDamage(GameDamage damage) {
		if (!this.hasAShield && this.currentSprite == this.spriteIdle) {
			GameManagerAudio.getAudioEnemyHit().play();

			this.hp -= damage.getDamage();
			this.hasAShield = true;

			this.newX = (this.newX == 384) ? 650 : 384;
			this.newY = (this.newY == 192) ? 150 : 192;

			this.currentSprite = this.spriteTeleport01;
		}
	}
	
	public void resetShield() {
		this.hasAShield = false;
	}

	public void tick() {
		if (this.hp > 0) {
			if (this.spriteInvoking.finishedAnimation()) {
				this.spriteInvoking.reset();

				this.currentSprite = this.spriteIdle;

				for (Spawn spawn : this.scene.getSpawns()) {
					this.scene.getEnemies().add(new Skeleton(this.scene, spawn.getRect().getX(), spawn.getRect().getY() - 8));
				}
			}

			if (this.spriteTeleport01.finishedAnimation()) {
				this.rect.setX(this.newX);
				this.rect.setY(this.newY);

				this.spriteTeleport01.reset();

				this.currentSprite = this.spriteTeleport02;
			}

			if (this.spriteTeleport02.finishedAnimation()) {
				this.spriteTeleport02.reset();

				this.currentSprite = this.spriteInvoking;
			}

			if (this.scene.getEnemies().isEmpty() && this.currentSprite == this.spriteIdle) {
				this.newX = (this.newX == 384) ? 650 : 384;
				this.newY = (this.newY == 192) ? 150 : 192;

				this.currentSprite = this.spriteTeleport01;
			}
		} else {
			this.currentSprite = this.spriteDeath;
		}

		this.currentSprite.tick();

		this.setSpritePosition();
	}

	public void render(Graphics render) {
		if (this.hasAShield) {
			this.currentSprite.renderSpritesSecondary(render);
		} else {
			this.currentSprite.render(render);
		}
	}

}
