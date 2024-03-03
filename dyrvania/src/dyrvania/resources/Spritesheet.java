package dyrvania.resources;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import dyrvania.Main;
import dyrvania.strings.StringError;

public class Spritesheet {

	private static final BufferedImage spritesheetGUI;

	static {
		BufferedImage auxSpritesheetGUI = null;

		try {
			auxSpritesheetGUI = ImageIO.read(Spritesheet.class.getResource("/sprites/gui.png"));
		} catch (Exception e) {
			Main.exitWithError(StringError.ERROR_LOADING_SPRITES.getValue());
		}

		spritesheetGUI = auxSpritesheetGUI;
	}

	public static BufferedImage getSpriteGUI(int x, int y, int width, int height) {
		return Spritesheet.spritesheetGUI.getSubimage(x, y, width, height);
	}

}