package dyrvania.managers;

import java.awt.image.BufferedImage;

import dyrvania.generics.GameRect;
import dyrvania.generics.GameSpriteAnimation;
import dyrvania.resources.Spritesheet;

public class GameManagerSpriteDeath {

	private final static BufferedImage[] spriteDeath;

	static {
		spriteDeath = new BufferedImage[5];

		for (int i = 0; i < spriteDeath.length; i++) {
			spriteDeath[i] = Spritesheet.getSpriteDeath(44 * i, 0, 44, 52);
		}
	}

	public static GameSpriteAnimation createSpriteDeath(GameRect rect) {
		return new GameSpriteAnimation(rect, 5, GameManagerSpriteDeath.spriteDeath, GameManagerSpriteDeath.spriteDeath);
	}

}
