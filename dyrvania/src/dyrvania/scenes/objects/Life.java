package dyrvania.scenes.objects;

import dyrvania.generics.GameRect;
import dyrvania.resources.Spritesheet;

public class Life extends GameObject {

	public Life() {
		super(new GameRect(0, 0, 524 / 20, 499 / 20), Spritesheet.getSpriteIcons(1655, 42, 524, 499));
	}

}
