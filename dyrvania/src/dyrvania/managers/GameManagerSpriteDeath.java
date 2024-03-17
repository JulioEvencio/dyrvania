package dyrvania.managers;

import java.awt.image.BufferedImage;

import dyrvania.generics.GameRect;
import dyrvania.generics.GameSpriteAnimation;
import dyrvania.resources.Spritesheet;

public class GameManagerSpriteDeath {

	private final static BufferedImage[] spriteDeath;
	private final static BufferedImage[] spriteDeathBlue;

	static {
		spriteDeath = new BufferedImage[5];

		for (int i = 0; i < spriteDeath.length; i++) {
			spriteDeath[i] = Spritesheet.getSpriteDeath(44 * i, 0, 44, 52);
		}

		spriteDeathBlue = new BufferedImage[6];

		for (int i = 0; i < spriteDeathBlue.length; i++) {
			spriteDeathBlue[i] = Spritesheet.getSpriteDeathBlue(39 * i, 0, 39, 49);
		}
	}

	public static GameSpriteAnimation createSpriteDeath(GameRect rect) {
		return new GameSpriteAnimation(rect, 5, GameManagerSpriteDeath.spriteDeath, GameManagerSpriteDeath.spriteDeath);
	}

	public static GameSpriteAnimation createSpriteDeathBlue(GameRect rect) {
		return new GameSpriteAnimation(rect, 5, GameManagerSpriteDeath.spriteDeathBlue, GameManagerSpriteDeath.spriteDeathBlue);
	}

}
