package dyrvania.scenes.entities.player;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dyrvania.generics.Camera;
import dyrvania.generics.GameRect;
import dyrvania.generics.GameRectEntity;
import dyrvania.generics.GameSpriteAnimation;
import dyrvania.resources.Spritesheet;
import dyrvania.scenes.Scene;

public class Player {

	private Scene scene;

	private final GameRectEntity rect;

	private double hp;
	private final double hpMax;

	private double damage;
	private boolean isAttacking;

	private double speedX;
	private double speedY;

	private boolean keyRight;
	private boolean keyLeft;
	private boolean keyJump;
	private boolean keyAttack;

	private boolean isJump;
	private final double jumpHeight;
	private double jumpFrames;

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

		this.hpMax = 10;
		this.hp = this.hpMax;

		this.damage = 1;
		this.isAttacking = false;

		this.speedX = 3;
		this.speedY = 0;

		this.keyRight = false;
		this.keyLeft = false;
		this.keyJump = false;
		this.keyAttack = false;

		this.isJump = false;
		this.jumpHeight = 80;
		this.jumpFrames = 0;

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

		this.updateCurrentSprite(this.spriteIdleRight);
		this.updateSpritePosition();
	}

	public GameRect getRect() {
		return this.rect.getRect();
	}

	public void setPosition(int x, int y) {
		this.rect.setX(x);
		this.rect.setY(y);

		this.updateSpritePosition();
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
		if (!this.isJump && this.isOnTheFloor()) {
			this.isJump = true;
			this.keyJump = true;
		}
	}

	public void keyJumpReleased() {
		this.keyJump = false;
	}

	public void toAttack() {
		if (!this.keyAttack && !this.isAttacking && this.isOnTheFloor()) {
			this.keyAttack = true;
			this.isAttacking = true;
		}
	}

	public void keyAttackReleased() {
		this.keyAttack = false;
	}

	public void updateDir(boolean dir) {
		this.isDirRight = dir;
	}

	private void applyGravity() {
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

	private void toMove() {
		if (this.keyRight && !this.isAttacking) {
			this.isDirRight = true;

			for (double i = 0; i <= this.speedX; i += 0.5) {
				if (this.scene.isFree(new GameRectEntity(this.rect.getX() + 0.5, this.rect.getY(), this.rect.getWidth(), this.rect.getHeight()).getRect())) {
					this.rect.setX(this.rect.getX() + 0.5);

					if (!this.isJump && !this.isOnTheFloor()) {
						this.rect.setY(this.rect.getY() + 0.5);
					}
				} else {
					break;
				}
			}
		}

		if (this.keyLeft && !this.isAttacking) {
			this.isDirRight = false;

			for (double i = 0; i <= this.speedX; i += 0.5) {
				if (this.scene.isFree(new GameRectEntity(this.rect.getX() - 0.5, this.rect.getY(), this.rect.getWidth(), this.rect.getHeight()).getRect())) {
					this.rect.setX(this.rect.getX() - 0.5);

					if (!this.isJump && !this.isOnTheFloor()) {
						this.rect.setY(this.rect.getY() + 0.5);
					}
				} else {
					break;
				}
			}
		}
	}

	private void jump() {
		if (this.jumpFrames < 10) {
			this.speedY = 8;
		} else if (this.jumpFrames < 20) {
			this.speedY = 7;
		} else if (this.jumpFrames < 30) {
			this.speedY = 6;
		} else if (this.jumpFrames < 40) {
			this.speedY = 5;
		} else if (this.jumpFrames < 50) {
			this.speedY = 4;
		} else {
			this.speedY = 3;
		}

		for (double i = 0; i <= this.speedY; i += 0.5) {
			if (this.jumpFrames < this.jumpHeight && this.scene.isFree(new GameRectEntity(this.rect.getX(), this.rect.getY() - 0.5, this.rect.getWidth(), this.rect.getHeight()).getRect())) {
				this.rect.setY(this.rect.getY() - 0.5);
				this.jumpFrames += 0.5;
			} else {
				this.speedY = 0;
				this.jumpFrames = 0;
				this.isJump = false;
				break;
			}
		}
	}

	private boolean isOnTheFloor() {
		return !this.scene.isFree(new GameRectEntity(this.rect.getX(), this.rect.getY() + 0.5, this.rect.getWidth(), this.rect.getHeight()).getRect());
	}

	private void updateSpritePosition() {
		if (this.isDirRight) {
			this.currentSprite.setPosition(this.rect.getRect().getX() - 45, this.rect.getRect().getY() - 15);
		} else {
			this.currentSprite.setPosition(this.rect.getRect().getX() - 35, this.rect.getRect().getY() - 15);
		}
	}

	private void updateCurrentSprite(GameSpriteAnimation newSprite) {
		if (this.currentSprite != newSprite) {
			if (this.currentSprite != null) {
				this.currentSprite.reset();
			}

			this.currentSprite = newSprite;
		}
	}

	private void updateCamera() {
		Camera.x = this.rect.getRect().getX() - 400;
		Camera.y = this.rect.getRect().getY() - 225;
	}

	public void tick() {
		if (this.isJump) {
			this.jump();
		} else {
			this.applyGravity();
		}

		this.toMove();

		if (!this.isOnTheFloor()) {
			if (this.isDirRight) {
				this.updateCurrentSprite(this.spriteJumpRight);
			} else {
				this.updateCurrentSprite(this.spriteJumpLeft);
			}
		} else if (this.isAttacking) {
			if (this.isDirRight) {
				this.updateCurrentSprite(this.spriteAttackRight);
			} else {
				this.updateCurrentSprite(this.spriteAttackLeft);
			}
		} else if (this.keyRight || this.keyLeft) {
			if (this.isDirRight) {
				this.updateCurrentSprite(this.spriteRunRight);
			} else {
				this.updateCurrentSprite(this.spriteRunLeft);
			}
		} else {
			if (this.isDirRight) {
				this.updateCurrentSprite(this.spriteIdleRight);
			} else {
				this.updateCurrentSprite(this.spriteIdleLeft);
			}
		}

		this.updateSpritePosition();

		this.currentSprite.tick();

		if (this.isAttacking && this.currentSprite.finishedAnimation()) {
			this.isAttacking = false;
			this.currentSprite.reset();
		}

		this.updateCamera();
	}

	public void render(Graphics render) {
		this.currentSprite.render(render);
	}

}
