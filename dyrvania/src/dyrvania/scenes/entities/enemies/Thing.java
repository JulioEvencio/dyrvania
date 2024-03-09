package dyrvania.scenes.entities.enemies;

import java.awt.image.BufferedImage;

import dyrvania.generics.GameRect;
import dyrvania.generics.GameSpriteAnimation;
import dyrvania.generics.GameSpriteAnimationFactory;
import dyrvania.resources.Spritesheet;
import dyrvania.scenes.Scene;

public class Thing extends Enemy {

	public Thing(Scene scene, int x, int y) {
		super(scene, x, y, 15, 35, 10, 1, 0.1);
	}

	@Override
	public void loadSprites() {
		int spriteWidth = 33;
		int spriteHeight = 45;

		GameRect spriteRect = new GameRect(0, 0, spriteWidth, spriteHeight);

		// Run Right
		BufferedImage[] runRight = new BufferedImage[4];

		for (int i = 0; i < runRight.length; i++) {
			runRight[i] = Spritesheet.getSpriteThing(spriteWidth * i, 45, spriteWidth, spriteHeight);
		}

		super.spriteRunRight = new GameSpriteAnimation(spriteRect, 5, runRight);

		// Run Left
		BufferedImage[] runLeft = new BufferedImage[4];

		for (int i = 0; i < runLeft.length; i++) {
			runLeft[i] = Spritesheet.getSpriteThing(spriteWidth * i, 0, spriteWidth, spriteHeight);
		}

		super.spriteRunLeft = new GameSpriteAnimation(spriteRect, 5, runLeft);

		super.spriteDeath = GameSpriteAnimationFactory.createSpriteDeath(spriteRect);
	}

	@Override
	protected void setSpritePosition() {
		if (super.isDirRight) {
			super.currentSprite.setPosition(super.rect.getRect().getX() - 8, super.rect.getRect().getY() - 10);
		} else {
			super.currentSprite.setPosition(super.rect.getRect().getX() - 9, super.rect.getRect().getY() - 10);
		}
	}

}
