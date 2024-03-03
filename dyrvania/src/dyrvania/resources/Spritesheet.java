package dyrvania.resources;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import dyrvania.Main;
import dyrvania.strings.StringError;

public class Spritesheet {

	private static final BufferedImage spritesheetGUI;
	private static final BufferedImage spritesheetBackground;
	private static final BufferedImage spritesheetTiles;

	static {
		BufferedImage auxSpritesheetGUI = null;
		BufferedImage auxSpritesheetBackground = null;
		BufferedImage auxSpritesheetTiles = null;

		try {
			auxSpritesheetGUI = ImageIO.read(Spritesheet.class.getResource("/sprites/gui.png"));
			auxSpritesheetBackground = ImageIO.read(Spritesheet.class.getResource("/sprites/background.png"));
			auxSpritesheetTiles = ImageIO.read(Spritesheet.class.getResource("/sprites/tiles.png"));
		} catch (Exception e) {
			Main.exitWithError(StringError.ERROR_LOADING_SPRITES.getValue());
		}

		spritesheetGUI = auxSpritesheetGUI;
		spritesheetBackground = auxSpritesheetBackground;
		spritesheetTiles = auxSpritesheetTiles;
	}

	public static BufferedImage getSpriteGUI(int x, int y, int width, int height) {
		return Spritesheet.spritesheetGUI.getSubimage(x, y, width, height);
	}

	public static BufferedImage getSpriteBackground(int x, int y, int width, int height) {
		return Spritesheet.spritesheetBackground.getSubimage(x, y, width, height);
	}

	public static BufferedImage getSpriteTiles(int x, int y, int width, int height) {
		return Spritesheet.spritesheetTiles.getSubimage(x, y, width, height);
	}

}