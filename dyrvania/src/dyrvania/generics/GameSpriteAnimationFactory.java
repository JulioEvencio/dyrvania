package dyrvania.generics;

import java.awt.image.BufferedImage;

import dyrvania.resources.Spritesheet;

public class GameSpriteAnimationFactory {

	public static GameSpriteAnimation createSpriteDeath(GameRect rect) {
		BufferedImage[] death = new BufferedImage[5];

		for (int i = 0; i < death.length; i++) {
			death[i] = Spritesheet.getSpriteDeath(44 * i, 0, 44, 52);
		}

		return new GameSpriteAnimation(rect, 5, death);
	}

}
