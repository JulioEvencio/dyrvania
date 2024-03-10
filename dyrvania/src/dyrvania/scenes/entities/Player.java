package dyrvania.scenes.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

import dyrvania.generics.Camera;
import dyrvania.generics.GameColors;
import dyrvania.generics.GameDamage;
import dyrvania.generics.GameDamage.GameDamageType;
import dyrvania.generics.GameRect;
import dyrvania.generics.GameRectEntity;
import dyrvania.generics.GameSpriteAnimation;
import dyrvania.managers.GameManagerAudio;
import dyrvania.resources.GameFont;
import dyrvania.resources.Spritesheet;
import dyrvania.scenes.Scene;

public class Player {

	private Scene scene;

	private final GameRectEntity rect;
	private final GameRect rectAttack;

	private int hp;
	private int hpMax;

	private int poisonControl;
	private boolean isPoisoning;

	protected long shieldTime;
	protected boolean shieldActive;
	protected LocalDateTime shieldDamage;

	private GameDamage damage;

	private boolean isAttacking;
	private boolean canDealDamage;

	private float speedX;
	private float speedY;

	private boolean keyRight;
	private boolean keyLeft;
	private boolean keyJump;
	private boolean keyAttack;

	private boolean isJump;
	private final float jumpHeight;
	private float jumpFrames;

	private boolean isDirRight;
	private GameSpriteAnimation currentSprite;

	private final GameSpriteAnimation spriteIdleRight;
	private final GameSpriteAnimation spriteIdleLeft;

	private final GameSpriteAnimation spriteJumpRight;
	private final GameSpriteAnimation spriteJumpLeft;

	private final GameSpriteAnimation spriteRunRight;
	private final GameSpriteAnimation spriteRunLeft;

	private final GameSpriteAnimation spriteAttackRight;
	private final GameSpriteAnimation spriteAttackLeft;

	public Player(Scene scene) {
		this.scene = scene;

		this.rect = new GameRectEntity(0, 0, 20, 44);
		this.rectAttack = new GameRect(0, 0, 31, 12);

		this.hpMax = 10;
		this.hp = this.hpMax;

		this.poisonControl = 0;
		this.isPoisoning = false;

		this.shieldTime = 2;
		this.shieldActive = false;
		this.shieldDamage = LocalDateTime.now().minusSeconds(this.shieldTime);

		this.damage = new GameDamage(1, GameDamageType.FIRE);
		this.isAttacking = false;
		this.canDealDamage = false;

		this.speedX = 3f;
		this.speedY = 0f;

		this.keyRight = false;
		this.keyLeft = false;
		this.keyJump = false;
		this.keyAttack = false;

		this.isJump = false;
		this.jumpHeight = 80f;
		this.jumpFrames = 0f;

		this.isDirRight = true;

		int spriteWidth = 100;
		int spriteHeight = 59;

		GameRect spriteRect = new GameRect(0, 0, spriteWidth, spriteHeight);

		this.currentSprite = null;

		// Idle Right
		BufferedImage[] idleRight = new BufferedImage[4];

		idleRight[0] = Spritesheet.getSpritePlayer(0, 0, spriteWidth, spriteHeight);
		idleRight[1] = Spritesheet.getSpritePlayer(100, 0, spriteWidth, spriteHeight);
		idleRight[2] = Spritesheet.getSpritePlayer(200, 0, spriteWidth, spriteHeight);
		idleRight[3] = Spritesheet.getSpritePlayer(300, 0, spriteWidth, spriteHeight);

		this.spriteIdleRight = new GameSpriteAnimation(spriteRect, 15, idleRight);

		// Idle Left
		BufferedImage[] idleLeft = new BufferedImage[4];

		idleLeft[0] = Spritesheet.getSpritePlayer(0, 59, spriteWidth, spriteHeight);
		idleLeft[1] = Spritesheet.getSpritePlayer(100, 59, spriteWidth, spriteHeight);
		idleLeft[2] = Spritesheet.getSpritePlayer(200, 59, spriteWidth, spriteHeight);
		idleLeft[3] = Spritesheet.getSpritePlayer(300, 59, spriteWidth, spriteHeight);

		this.spriteIdleLeft = new GameSpriteAnimation(spriteRect, 15, idleLeft);

		// Jump Right
		BufferedImage[] jumpRight = new BufferedImage[2];

		jumpRight[0] = Spritesheet.getSpritePlayer(400, 0, spriteWidth, spriteHeight);
		jumpRight[1] = Spritesheet.getSpritePlayer(500, 0, spriteWidth, spriteHeight);

		this.spriteJumpRight = new GameSpriteAnimation(spriteRect, 15, jumpRight);

		// Jump Left
		BufferedImage[] jumpLeft = new BufferedImage[2];

		jumpLeft[0] = Spritesheet.getSpritePlayer(400, 59, spriteWidth, spriteHeight);
		jumpLeft[1] = Spritesheet.getSpritePlayer(500, 59, spriteWidth, spriteHeight);

		this.spriteJumpLeft = new GameSpriteAnimation(spriteRect, 15, jumpLeft);

		// Run Right
		BufferedImage[] runRight = new BufferedImage[6];

		runRight[0] = Spritesheet.getSpritePlayer(0, 118, spriteWidth, spriteHeight);
		runRight[1] = Spritesheet.getSpritePlayer(100, 118, spriteWidth, spriteHeight);
		runRight[2] = Spritesheet.getSpritePlayer(200, 118, spriteWidth, spriteHeight);
		runRight[3] = Spritesheet.getSpritePlayer(300, 118, spriteWidth, spriteHeight);
		runRight[4] = Spritesheet.getSpritePlayer(400, 118, spriteWidth, spriteHeight);
		runRight[5] = Spritesheet.getSpritePlayer(500, 118, spriteWidth, spriteHeight);

		this.spriteRunRight = new GameSpriteAnimation(spriteRect, 10, runRight);

		// Run Left
		BufferedImage[] runLeft = new BufferedImage[6];

		runLeft[0] = Spritesheet.getSpritePlayer(0, 177, spriteWidth, spriteHeight);
		runLeft[1] = Spritesheet.getSpritePlayer(100, 177, spriteWidth, spriteHeight);
		runLeft[2] = Spritesheet.getSpritePlayer(200, 177, spriteWidth, spriteHeight);
		runLeft[3] = Spritesheet.getSpritePlayer(300, 177, spriteWidth, spriteHeight);
		runLeft[4] = Spritesheet.getSpritePlayer(400, 177, spriteWidth, spriteHeight);
		runLeft[5] = Spritesheet.getSpritePlayer(500, 177, spriteWidth, spriteHeight);

		this.spriteRunLeft = new GameSpriteAnimation(spriteRect, 10, runLeft);

		// Attack Right
		BufferedImage[] attackRight = new BufferedImage[5];

		attackRight[0] = Spritesheet.getSpritePlayer(0, 236, spriteWidth, spriteHeight);
		attackRight[1] = Spritesheet.getSpritePlayer(100, 236, spriteWidth, spriteHeight);
		attackRight[2] = Spritesheet.getSpritePlayer(200, 236, spriteWidth, spriteHeight);
		attackRight[3] = Spritesheet.getSpritePlayer(300, 236, spriteWidth, spriteHeight);
		attackRight[4] = Spritesheet.getSpritePlayer(400, 236, spriteWidth, spriteHeight);

		this.spriteAttackRight = new GameSpriteAnimation(spriteRect, 5, attackRight);

		// Attack Left
		BufferedImage[] attackLeft = new BufferedImage[5];

		attackLeft[0] = Spritesheet.getSpritePlayer(0, 295, spriteWidth, spriteHeight);
		attackLeft[1] = Spritesheet.getSpritePlayer(100, 295, spriteWidth, spriteHeight);
		attackLeft[2] = Spritesheet.getSpritePlayer(200, 295, spriteWidth, spriteHeight);
		attackLeft[3] = Spritesheet.getSpritePlayer(300, 295, spriteWidth, spriteHeight);
		attackLeft[4] = Spritesheet.getSpritePlayer(400, 295, spriteWidth, spriteHeight);

		this.spriteAttackLeft = new GameSpriteAnimation(spriteRect, 5, attackLeft);

		this.setCurrentSprite(this.spriteIdleRight);
		this.setSpritePosition();
	}

	public GameRect getRect() {
		return this.rect.getRect();
	}

	public boolean isDead() {
		return this.hp <= 0;
	}

	public boolean isAttacking() {
		return this.isAttacking && this.canDealDamage;
	}

	public GameRect getAreaAttack() {
		return this.rectAttack;
	}

	public GameDamage dealDamage() {
		return this.damage;
	}

	public boolean finishedAnimation() {
		return this.currentSprite.getIndex() == 0;
	}

	public void takeDamage(GameDamage damage) {
		if (!this.shieldActive) {
			GameManagerAudio.getAudioPlayerHit().play();

			this.hp -= damage.getDamage();
			this.shieldDamage = LocalDateTime.now().plusSeconds(this.shieldTime);

			if (damage.getType() == GameDamageType.POISON) {
				this.isPoisoning = true;
			}
		}
	}

	public void setPosition(int x, int y) {
		this.rect.setX(x);
		this.rect.setY(y);

		this.setSpritePosition();
	}

	public void moveRight() {
		this.keyRight = true;
	}

	public void stopRight() {
		this.keyRight = false;
	}

	public void moveLeft() {
		this.keyLeft = true;
	}

	public void stopLeft() {
		this.keyLeft = false;
	}

	public void toJump() {
		if (!this.isJump && !this.keyJump && this.isOnTheFloor()) {
			GameManagerAudio.getAudioPlayerJump().play();

			this.isJump = true;
			this.keyJump = true;
		}
	}

	public void keyJumpReleased() {
		this.keyJump = false;
	}

	public void toAttack() {
		if (!this.keyAttack && !this.isAttacking && this.isOnTheFloor()) {
			GameManagerAudio.getAudioPlayerAttack().play();

			this.keyAttack = true;
			this.isAttacking = true;
		}
	}

	public void keyAttackReleased() {
		this.keyAttack = false;
	}

	public void increaseHp() {
		if (this.hpMax < 999) {
			this.hpMax++;
		}
	}

	public void increaseAttack() {
		if (this.damage.getDamage() < 99) {
			this.damage.setDamage(this.damage.getDamage() + 1);
		}
	}

	public void setDir(boolean dir) {
		this.isDirRight = dir;
	}

	private void applyGravity() {
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

	private void toMove() {
		if (this.keyRight && !this.isAttacking) {
			this.isDirRight = true;

			for (float i = 0f; i <= this.speedX; i += 0.5f) {
				if (this.scene.isFree(new GameRectEntity(this.rect.getX() + 0.5f, this.rect.getY(), this.rect.getWidth(), this.rect.getHeight()).getRect())) {
					this.rect.setX(this.rect.getX() + 0.5f);

					if (!this.isJump && !this.isOnTheFloor()) {
						this.rect.setY(this.rect.getY() + 0.5f);
					}
				} else {
					break;
				}
			}
		}

		if (this.keyLeft && !this.isAttacking) {
			this.isDirRight = false;

			for (float i = 0f; i <= this.speedX; i += 0.5f) {
				if (this.scene.isFree(new GameRectEntity(this.rect.getX() - 0.5f, this.rect.getY(), this.rect.getWidth(), this.rect.getHeight()).getRect())) {
					this.rect.setX(this.rect.getX() - 0.5f);

					if (!this.isJump && !this.isOnTheFloor()) {
						this.rect.setY(this.rect.getY() + 0.5f);
					}
				} else {
					break;
				}
			}
		}
	}

	private void jump() {
		if (this.jumpFrames < 10f) {
			this.speedY = 8f;
		} else if (this.jumpFrames < 20f) {
			this.speedY = 7f;
		} else if (this.jumpFrames < 30f) {
			this.speedY = 6f;
		} else if (this.jumpFrames < 40f) {
			this.speedY = 5f;
		} else if (this.jumpFrames < 50f) {
			this.speedY = 4f;
		} else {
			this.speedY = 3f;
		}

		for (float i = 0f; i <= this.speedY; i += 0.5f) {
			if (this.jumpFrames < this.jumpHeight && this.scene.isFree(new GameRectEntity(this.rect.getX(), this.rect.getY() - 0.5f, this.rect.getWidth(), this.rect.getHeight()).getRect())) {
				this.rect.setY(this.rect.getY() - 0.5f);
				this.jumpFrames += 0.5f;
			} else {
				this.speedY = 0f;
				this.jumpFrames = 0f;
				this.isJump = false;
				break;
			}
		}
	}

	private boolean isOnTheFloor() {
		return !this.scene.isFree(new GameRectEntity(this.rect.getX(), this.rect.getY() + 0.5f, this.rect.getWidth(), this.rect.getHeight()).getRect());
	}

	private void setSpritePosition() {
		if (this.isDirRight) {
			this.currentSprite.setPosition(this.rect.getRect().getX() - 45, this.rect.getRect().getY() - 15);
		} else {
			this.currentSprite.setPosition(this.rect.getRect().getX() - 35, this.rect.getRect().getY() - 15);
		}
	}

	private void setCurrentSprite(GameSpriteAnimation newSprite) {
		if (this.currentSprite != newSprite) {
			if (this.currentSprite != null) {
				this.currentSprite.reset();
			}

			this.currentSprite = newSprite;
		}
	}

	private void setCamera() {
		Camera.x = this.rect.getRect().getX() - 400;
		Camera.y = this.rect.getRect().getY() - 225;
	}

	public void tick() {
		if (this.isPoisoning && this.hp > 1) {
			this.poisonControl++;

			if (this.poisonControl == 300) {
				GameManagerAudio.getAudioPlayerHit().play();

				this.hp--;
				this.poisonControl = 0;
			}
		} else {
			this.poisonControl = 0;
			this.isPoisoning = false;
		}

		if (this.shieldDamage.isBefore(LocalDateTime.now())) {
			this.shieldActive = false;
			this.currentSprite.setAlpha(1f);
		} else {
			this.shieldActive = true;
			this.currentSprite.setAlpha(0.5f);
		}

		if (this.isJump) {
			this.jump();
		} else {
			this.applyGravity();
		}

		this.toMove();

		if (!this.isOnTheFloor()) {
			if (this.isDirRight) {
				this.setCurrentSprite(this.spriteJumpRight);
			} else {
				this.setCurrentSprite(this.spriteJumpLeft);
			}
		} else if (this.isAttacking) {
			if (this.isDirRight) {
				this.setCurrentSprite(this.spriteAttackRight);
			} else {
				this.setCurrentSprite(this.spriteAttackLeft);
			}
		} else if (this.keyRight || this.keyLeft) {
			if (this.isDirRight) {
				this.setCurrentSprite(this.spriteRunRight);
			} else {
				this.setCurrentSprite(this.spriteRunLeft);
			}
		} else {
			if (this.isDirRight) {
				this.setCurrentSprite(this.spriteIdleRight);
			} else {
				this.setCurrentSprite(this.spriteIdleLeft);
			}
		}

		if (this.isDirRight) {
			this.rectAttack.setX((int) this.rect.getX() + 20);
		} else {
			this.rectAttack.setX((int) this.rect.getX() - 31);
		}

		this.rectAttack.setY((int) this.rect.getY() + 11);

		this.setSpritePosition();

		this.currentSprite.tick();

		if (this.isAttacking) {
			if (this.currentSprite.getIndex() == 2 || this.currentSprite.getIndex() == 3) {
				this.canDealDamage = true;
			} else {
				this.canDealDamage = false;
			}

			if (this.currentSprite.finishedAnimation()) {
				this.isAttacking = false;
				this.currentSprite.reset();
				this.canDealDamage = false;
			}
		}

		this.setCamera();
	}

	public void render(Graphics render) {
		if (this.isPoisoning) {
			this.currentSprite.renderPoisoned(render);
		} else {
			this.currentSprite.render(render);
		}

		this.renderHp(render);
		this.renderAttack(render);
	}

	private void renderHp(Graphics render) {
		render.setColor(GameColors.BLACK);
		render.fillRect(5, 5, 150, 20);

		render.setColor(GameColors.WHITE);
		render.setFont(GameFont.getTinyFont());
		render.drawString(String.format("HP: %03d | %03d", this.hp, this.hpMax), 15, 20);

		render.drawRect(5, 5, 150, 20);
	}

	private void renderAttack(Graphics render) {
		render.setColor(GameColors.BLACK);
		render.fillRect(165, 5, 90, 20);

		render.setColor(GameColors.WHITE);
		render.setFont(GameFont.getTinyFont());
		render.drawString(String.format("ATK: %02d", this.damage.getDamage()), 175, 20);

		render.drawRect(165, 5, 90, 20);
	}

}
