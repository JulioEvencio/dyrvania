package dyrvania.scenes.tiles;

import dyrvania.resources.Spritesheet;

public class BackgroundTile extends Tile {

	public BackgroundTile(int x, int y, int width, int height) {
		super(x, y, width, height, Spritesheet.getSpriteTiles(32, 0, 32, 25));
	}

}
