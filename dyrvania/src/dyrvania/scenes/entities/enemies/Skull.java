package dyrvania.scenes.entities.enemies;

import java.awt.image.BufferedImage;

import dyrvania.generics.GameRect;
import dyrvania.generics.GameRectEntity;
import dyrvania.generics.GameSpriteAnimation;
import dyrvania.generics.GameSpriteAnimationFactory;
import dyrvania.generics.GameUtil;
import dyrvania.resources.Spritesheet;
import dyrvania.scenes.Scene;

public class Skull extends Enemy {

	public Skull(Scene scene, int x, int y) {
		super(scene, x, y, 16, 18, 1, 1, 1);

		super.speedY = (GameUtil.generateRandomNumber(0, 1) == 1) ? 1 : -1;
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
		if (super.scene.isFree(new GameRectEntity(super.rect.getX(), super.rect.getY() + super.speedY, super.rect.getWidth(), super.rect.getHeight()).getRect())) {
			super.rect.setY(super.rect.getY() + super.speedY);
		} else {
			super.speedY *= -1;
		}
	}

	@Override
	protected void toMove() {
		double velX;

		if (super.isDirRight) {
			velX = 0.5;
		} else {
			velX = -0.5;
		}

		for (double i = 0; i <= super.speedX; i += 0.5) {
			if (super.scene.isFree(new GameRectEntity(super.rect.getX() + velX, super.rect.getY(), super.rect.getWidth(), super.rect.getHeight()).getRect())) {
				super.rect.setX(super.rect.getX() + velX);
			} else {
				super.isDirRight = !super.isDirRight;
				break;
			}
		}
	}

	@Override
	protected void setSpritePosition() {
		if (super.isDirRight) {
			super.currentSprite.setPosition(super.rect.getRect().getX() - 8, super.rect.getRect().getY() - 15);
		} else {
			super.currentSprite.setPosition(super.rect.getRect().getX() - 8, super.rect.getRect().getY() - 15);
		}
	}

}
