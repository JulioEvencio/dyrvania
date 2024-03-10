package dyrvania.managers.entities.enemies;

import java.awt.image.BufferedImage;

import dyrvania.generics.GameColors;
import dyrvania.generics.GameRect;
import dyrvania.generics.GameSpriteAnimation;
import dyrvania.generics.GameUtil;
import dyrvania.resources.Spritesheet;

public class GameManagerSpriteSkeleton {

	// Sprites Normal
	private final static BufferedImage[] spriteRunRight;
	private final static BufferedImage[] spriteRunLeft;

	// Sprites Damage
	private final static BufferedImage[] spriteRunRightDamage;
	private final static BufferedImage[] spriteRunLeftDamage;

	static {
		int spriteWidth = 44;
		int spriteHeight = 52;

		// Run Right
		spriteRunRight = new BufferedImage[8];
		spriteRunRightDamage = new BufferedImage[8];

		for (int i = 0; i < spriteRunRight.length; i++) {
			spriteRunRight[i] = Spritesheet.getSpriteSkeleton(spriteWidth * i, 156, spriteWidth, spriteHeight);
			spriteRunRightDamage[i] = GameUtil.createSpriteColor(spriteRunRight[i], GameColors.DAMAGE);
		}

		// Run Left
		spriteRunLeft = new BufferedImage[8];
		spriteRunLeftDamage = new BufferedImage[8];

		for (int i = 0; i < spriteRunLeft.length; i++) {
			spriteRunLeft[i] = Spritesheet.getSpriteSkeleton(spriteWidth * i, 104, spriteWidth, spriteHeight);
			spriteRunLeftDamage[i] = GameUtil.createSpriteColor(spriteRunLeft[i], GameColors.DAMAGE);
		}
	}

	public static GameSpriteAnimation createSpriteRunRight(GameRect rect) {
		return new GameSpriteAnimation(rect, 5, GameManagerSpriteSkeleton.spriteRunRight, GameManagerSpriteSkeleton.spriteRunRightDamage);
	}

	public static GameSpriteAnimation createSpriteRunLeft(GameRect rect) {
		return new GameSpriteAnimation(rect, 5, GameManagerSpriteSkeleton.spriteRunLeft, GameManagerSpriteSkeleton.spriteRunLeftDamage);
	}

}
