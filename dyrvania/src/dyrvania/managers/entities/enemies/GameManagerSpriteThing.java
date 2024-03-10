package dyrvania.managers.entities.enemies;

import java.awt.image.BufferedImage;

import dyrvania.generics.GameColors;
import dyrvania.generics.GameRect;
import dyrvania.generics.GameSpriteAnimation;
import dyrvania.generics.GameUtil;
import dyrvania.resources.Spritesheet;

public class GameManagerSpriteThing {

	// Sprites Normal
	private final static BufferedImage[] spriteRunRight;
	private final static BufferedImage[] spriteRunLeft;

	// Sprites Damage
	private final static BufferedImage[] spriteRunRightDamage;
	private final static BufferedImage[] spriteRunLeftDamage;

	static {
		int spriteWidth = 33;
		int spriteHeight = 45;

		// Run Right
		spriteRunRight = new BufferedImage[4];
		spriteRunRightDamage = new BufferedImage[8];

		for (int i = 0; i < spriteRunRight.length; i++) {
			spriteRunRight[i] = Spritesheet.getSpriteThing(spriteWidth * i, 45, spriteWidth, spriteHeight);
			spriteRunRightDamage[i] = GameUtil.createSpriteColor(spriteRunRight[i], GameColors.DAMAGE);
		}

		// Run Left
		spriteRunLeft = new BufferedImage[4];
		spriteRunLeftDamage = new BufferedImage[8];

		for (int i = 0; i < spriteRunLeft.length; i++) {
			spriteRunLeft[i] = Spritesheet.getSpriteThing(spriteWidth * i, 0, spriteWidth, spriteHeight);
			spriteRunLeftDamage[i] = GameUtil.createSpriteColor(spriteRunLeft[i], GameColors.DAMAGE);
		}
	}

	public static GameSpriteAnimation createSpriteRunRight(GameRect rect) {
		return new GameSpriteAnimation(rect, 5, GameManagerSpriteThing.spriteRunRight, GameManagerSpriteThing.spriteRunRightDamage);
	}

	public static GameSpriteAnimation createSpriteRunLeft(GameRect rect) {
		return new GameSpriteAnimation(rect, 5, GameManagerSpriteThing.spriteRunLeft, GameManagerSpriteThing.spriteRunLeftDamage);
	}

}
