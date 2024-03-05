package dyrvania.scenes.entities.player;

import java.awt.Color;
import java.awt.Graphics;

import dyrvania.generics.GameRect;
import dyrvania.generics.GameRectEntity;
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

	public Player(Scene scene) {
		this.scene = scene;

		this.rect = new GameRectEntity(0, 0, scene.getSizeBaseTiles(), scene.getSizeBaseTiles());

		this.speedX = 1.5;
		this.speedY = 0;

		this.keyRight = false;
		this.keyLeft = false;
		this.keyJump = false;

		this.isJump = false;
		this.jumpHeight = scene.getSizeBaseTiles() * 2;
		this.jumpFrames = 0;
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

		if (this.speedY > 8) {
			this.speedY = 8;
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
		if (this.jumpFrames < 9) {
			this.speedY = 8;
		} else if (this.jumpFrames < 18) {
			this.speedY = 7;
		} else if (this.jumpFrames < 27) {
			this.speedY = 6;
		} else if (this.jumpFrames < 36) {
			this.speedY = 5;
		} else if (this.jumpFrames < 45) {
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
	}

	public void render(Graphics render) {
		render.setColor(Color.BLUE);
		GameRect newRect = this.rect.getRect();
		render.fillRect(newRect.getX(), newRect.getY(), newRect.getWidth(), newRect.getHeight());
	}

}
