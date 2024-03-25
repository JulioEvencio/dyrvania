package dyrvania.scenes.objects;

import dyrvania.generics.GameRect;
import dyrvania.resources.Spritesheet;

public class Sword extends GameObject {

	public Sword() {
		super(new GameRect(0, 0, 524 / 20, 499 / 20), Spritesheet.getSpriteIcons(1115, 45, 524, 499));
	}

}
