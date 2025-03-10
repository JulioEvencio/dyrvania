package dyrvania.scenes.backgrounds;

import dyrvania.Game;
import dyrvania.resources.Spritesheet;

public class BackgroundMoon extends Background {

	public BackgroundMoon(Game game, int x, int y) {
		super(x, y, game.getGameWidth(), game.getGameHeight(), Spritesheet.getSpriteBackground(562, 334, 384, 224));
	}

}
