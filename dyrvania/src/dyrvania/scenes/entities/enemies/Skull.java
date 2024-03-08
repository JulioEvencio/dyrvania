package dyrvania.scenes.entities.enemies;

import java.awt.image.BufferedImage;

import dyrvania.generics.GameRect;
import dyrvania.generics.GameRectEntity;
import dyrvania.generics.GameSpriteAnimation;
import dyrvania.generics.GameSpriteAnimationFactory;
import dyrvania.resources.Spritesheet;
import dyrvania.scenes.Scene;

public class Skull extends Enemy {

	public Skull(Scene scene, int x, int y) {
		super(scene, x, y, 15, 35, 1, 1, 1);
		
		super.speedY = 1;
	}

	@Override
	public void loadSprites() {
		int spriteWidth = 96;
		int spriteHeight = 112;

		GameRect spriteRect = new GameRect(0, 0, spriteWidth / 3, spriteHeight / 3);

		// Run Right
		BufferedImage[] runRight = new BufferedImage[8];

		for (int i = 0; i < runRight.length; i++) {
			runRight[i] = Spritesheet.getSpriteSkull(spriteWidth * i, 112, spriteWidth, spriteHeight);
		}

		super.spriteRunRight = new GameSpriteAnimation(spriteRect, 5, runRight);

		// Run Left
		BufferedImage[] runLeft = new BufferedImage[8];

		for (int i = 0; i < runLeft.length; i++) {
			runLeft[i] = Spritesheet.getSpriteSkull(spriteWidth * i, 0, spriteWidth, spriteHeight);
		}

		super.spriteRunLeft = new GameSpriteAnimation(spriteRect, 5, runLeft);

		super.spriteDeath = GameSpriteAnimationFactory.createSpriteDeath(spriteRect);
	}
	
	@Override
	protected void applyGravity() {
		for (double i = 0; i <= super.speedY; i += 0.5) {
			if (!super.isOnTheFloor()) {
				super.rect.setY(this.rect.getY() + 0.5);
			} else {
				super.speedY = 0;
				break;
			}
		}
	}

	@Override
	protected void toMove() {
		double velX;
		double velY = super.speedY;

		if (this.isDirRight) {
			velX = 0.5;
		} else {
			velX = -0.5;
		}

		for (double i = 0; i <= this.speedX; i += 0.5) {
			if (this.scene.isFree(new GameRectEntity(this.rect.getX() + velX, this.rect.getY(), this.rect.getWidth(), this.rect.getHeight()).getRect())) {
				this.rect.setX(this.rect.getX() + velX);
			} else {
				this.isDirRight = !this.isDirRight;
				break;
			}
		}

		for (double i = 0; i <= this.speedY; i += 0.5) {
			if (this.scene.isFree(new GameRectEntity(this.rect.getX(), this.rect.getY() + velY, this.rect.getWidth(), this.rect.getHeight()).getRect())) {
				this.rect.setY(this.rect.getY() + velY);
			} else {
				super.speedY *= -1;
				break;
			}
		}
	}

	@Override
	protected void setSpritePosition() {
		if (this.isDirRight) {
			this.currentSprite.setPosition(this.rect.getRect().getX() - 8, this.rect.getRect().getY() - 10);
		} else {
			this.currentSprite.setPosition(this.rect.getRect().getX() - 9, this.rect.getRect().getY() - 10);
		}
	}

}
