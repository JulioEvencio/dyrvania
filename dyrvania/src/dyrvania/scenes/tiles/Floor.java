package dyrvania.scenes.tiles;

import dyrvania.resources.Spritesheet;

public class Floor extends Tile {

	public Floor(int x, int y, int width, int height) {
		super(x, y, width, height, Spritesheet.getSpriteTiles(64, 0, 32, 32));
	}
}
