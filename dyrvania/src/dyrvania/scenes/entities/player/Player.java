package dyrvania.scenes.entities.player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dyrvania.generics.GameRect;
import dyrvania.generics.GameRectEntity;
import dyrvania.generics.GameSpriteAnimation;
import dyrvania.resources.Spritesheet;
import dyrvania.scenes.Scene;

public class Player {

	private Scene scene;

	private final GameRectEntity rect;

	private double speedX;
	private double speedY;

	private boolean keyRight;
	private boolean keyLeft;
	private boolean keyJump;

	private boolean isJump;
	private final double jumpHeight;
	private double jumpFrames;

	private final GameSpriteAnimation spriteIdle;

	public Player(Scene scene) {
		this.scene = scene;

		this.rect = new GameRectEntity(0, 0, 100, 59);

		this.speedX = 3;
		this.speedY = 0;

		this.keyRight = false;
		this.keyLeft = false;
		this.keyJump = false;

		this.isJump = false;
		this.jumpHeight = 80;
		this.jumpFrames = 0;

		BufferedImage[] idle = new BufferedImage[4];

		idle[0] = Spritesheet.getSpritePlayer(0, 0, 100, 59);
		idle[1] = Spritesheet.getSpritePlayer(100, 0, 100, 59);
		idle[2] = Spritesheet.getSpritePlayer(200, 0, 100, 59);
		idle[3] = Spritesheet.getSpritePlayer(300, 0, 100, 59);

		this.spriteIdle = new GameSpriteAnimation(this.rect.getRect(), 15, idle);
	}

	public GameRect getRect() {
		return this.rect.getRect();
	}

	public void setPosition(int x, int y) {
		this.rect.setX(x);
		this.rect.setY(y);
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
		if (!this.isJump && !this.keyJump && !this.scene.isFree(new GameRectEntity(this.rect.getX(), this.rect.getY() + 0.5, this.rect.getWidth(), this.rect.getHeight()).getRect())) {
			this.isJump = true;
			this.keyJump = true;
		}
	}

	public void keyJumpReleased() {
		this.keyJump = false;
	}

	private void applyGravity() {
		this.speedY += this.scene.getGravity();

		if (this.speedY > 7) {
			this.speedY = 7;
		}

		for (double i = 0; i <= this.speedY; i += 0.5) {
			if (this.scene.isFree(new GameRectEntity(this.rect.getX(), this.rect.getY() + 0.5, this.rect.getWidth(), this.rect.getHeight()).getRect())) {
				this.rect.setY(this.rect.getY() + 0.5);
			} else {
				this.speedY = 0;
			}
		}
	}

	private void toMove() {
		if (this.keyRight) {
			for (double i = 0; i <= this.speedX; i += 0.5) {
				if (this.scene.isFree(new GameRectEntity(this.rect.getX() + 0.5, this.rect.getY(), this.rect.getWidth(), this.rect.getHeight()).getRect())) {
					this.rect.setX(this.rect.getX() + 0.5);

					if (!this.isJump && this.scene.isFree(new GameRectEntity(this.rect.getX(), this.rect.getY() + 0.5, this.rect.getWidth(), this.rect.getHeight()).getRect())) {
						this.rect.setY(this.rect.getY() + 0.5);
					}
				}
			}
		}

		if (this.keyLeft) {
			for (double i = 0; i <= this.speedX; i += 0.5) {
				if (this.scene.isFree(new GameRectEntity(this.rect.getX() - 0.5, this.rect.getY(), this.rect.getWidth(), this.rect.getHeight()).getRect())) {
					this.rect.setX(this.rect.getX() - 0.5);

					if (!this.isJump && this.scene.isFree(new GameRectEntity(this.rect.getX(), this.rect.getY() + 0.5, this.rect.getWidth(), this.rect.getHeight()).getRect())) {
						this.rect.setY(this.rect.getY() + 0.5);
					}
				}
			}
		}
	}

	public void jump() {
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
			}
		}
	}

	public void tick() {
		if (this.isJump) {
			this.jump();
		} else {
			this.applyGravity();
		}

		this.toMove();

		this.spriteIdle.setPosition(this.rect.getRect().getX(), this.rect.getRect().getY());
		this.spriteIdle.tick();
	}

	public void render(Graphics render) {
		render.setColor(Color.BLUE);
		GameRect newRect = this.rect.getRect();
		render.fillRect(newRect.getX(), newRect.getY(), newRect.getWidth(), newRect.getHeight());

		this.spriteIdle.render(render);
	}

}
