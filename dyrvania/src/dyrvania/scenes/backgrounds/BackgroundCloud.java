package dyrvania.scenes.backgrounds;

import dyrvania.Game;
import dyrvania.resources.Spritesheet;

public class BackgroundCloud extends Background {

	public BackgroundCloud(Game game, int x, int y) {
		super(x, y, game.getGameWidth(), game.getGameHeight(), Spritesheet.getSpriteBackground(16, 572, 256, 176));
	}

}
