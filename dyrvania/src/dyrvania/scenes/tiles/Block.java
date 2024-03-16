package dyrvania.scenes.tiles;

import dyrvania.resources.Spritesheet;

public class Block extends Tile {

	public Block(int x, int y, int width, int height) {
		super(x, y, width, height, Spritesheet.getSpriteTiles(96, 0, 32, 25));
	}

}
