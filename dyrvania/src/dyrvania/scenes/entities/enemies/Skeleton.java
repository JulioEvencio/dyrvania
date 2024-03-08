package dyrvania.scenes.entities.enemies;

import java.awt.image.BufferedImage;

import dyrvania.generics.GameRect;
import dyrvania.generics.GameSpriteAnimation;
import dyrvania.generics.GameSpriteAnimationFactory;
import dyrvania.resources.Spritesheet;
import dyrvania.scenes.Scene;

public class Skeleton extends Enemy {

	public Skeleton(Scene scene, int x, int y) {
		super(scene, x, y, 10, 40, 3, 1, 0.5);
	}

	@Override
	public void loadSprites() {
		int spriteWidth = 44;
		int spriteHeight = 52;

		GameRect spriteRect = new GameRect(0, 0, spriteWidth, spriteHeight);

		// Run Right
		BufferedImage[] runRight = new BufferedImage[8];

		for (int i = 0; i < runRight.length; i++) {
			runRight[i] = Spritesheet.getSpriteSkeleton(spriteWidth * i, 156, spriteWidth, spriteHeight);
		}

		super.spriteRunRight = new GameSpriteAnimation(spriteRect, 5, runRight);

		// Run Left
		BufferedImage[] runLeft = new BufferedImage[8];

		for (int i = 0; i < runLeft.length; i++) {
			runLeft[i] = Spritesheet.getSpriteSkeleton(spriteWidth * i, 104, spriteWidth, spriteHeight);
		}

		super.spriteRunLeft = new GameSpriteAnimation(spriteRect, 5, runLeft);

		super.spriteDeath = GameSpriteAnimationFactory.createSpriteDeath(spriteRect);
	}

	@Override
	protected void setSpritePosition() {
		if (this.isDirRight) {
			this.currentSprite.setPosition(this.rect.getRect().getX() - 14, this.rect.getRect().getY() - 12);
		} else {
			this.currentSprite.setPosition(this.rect.getRect().getX() - 20, this.rect.getRect().getY() - 12);
		}
	}

}
