package dyrvania.scenes.backgrounds;

import dyrvania.Game;
import dyrvania.resources.Spritesheet;

public class BackgroundCastle extends Background {

	public BackgroundCastle(Game game, int x, int y) {
		super(x, y, game.getGameWidth(), game.getGameHeight(), Spritesheet.getSpriteBackground(9, 7, 499, 304));
	}

}
