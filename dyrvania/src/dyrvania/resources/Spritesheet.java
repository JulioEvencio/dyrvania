package dyrvania.resources;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import dyrvania.Main;
import dyrvania.strings.StringError;

public class Spritesheet {

	private static final BufferedImage spritesheetGUI;
	private static final BufferedImage spritesheetTiles;
	private static final BufferedImage spritesheetDeath;
	private static final BufferedImage spritesheetPlayer;
	private static final BufferedImage spritesheetSkeleton;
	private static final BufferedImage spritesheetBackground;

	static {
		BufferedImage auxSpritesheetGUI = null;
		BufferedImage auxSpritesheetTiles = null;
		BufferedImage auxSpritesheetDeath = null;
		BufferedImage auxSpritesheetPlayer = null;
		BufferedImage auxSpritesheetSkeleton = null;
		BufferedImage auxSpritesheetBackground = null;

		try {
			auxSpritesheetGUI = ImageIO.read(Spritesheet.class.getResource("/sprites/gui.png"));
			auxSpritesheetTiles = ImageIO.read(Spritesheet.class.getResource("/sprites/tiles.png"));
			auxSpritesheetDeath = ImageIO.read(Spritesheet.class.getResource("/sprites/death.png"));
			auxSpritesheetPlayer = ImageIO.read(Spritesheet.class.getResource("/sprites/player.png"));
			auxSpritesheetSkeleton = ImageIO.read(Spritesheet.class.getResource("/sprites/skeleton.png"));
			auxSpritesheetBackground = ImageIO.read(Spritesheet.class.getResource("/sprites/background.png"));
		} catch (Exception e) {
			Main.exitWithError(StringError.ERROR_LOADING_SPRITES.getValue());
		}

		spritesheetGUI = auxSpritesheetGUI;
		spritesheetTiles = auxSpritesheetTiles;
		spritesheetDeath = auxSpritesheetDeath;
		spritesheetPlayer = auxSpritesheetPlayer;
		spritesheetSkeleton = auxSpritesheetSkeleton;
		spritesheetBackground = auxSpritesheetBackground;
	}

	public static BufferedImage getSpriteGUI(int x, int y, int width, int height) {
		return Spritesheet.spritesheetGUI.getSubimage(x, y, width, height);
	}

	public static BufferedImage getSpriteTiles(int x, int y, int width, int height) {
		return Spritesheet.spritesheetTiles.getSubimage(x, y, width, height);
	}

	public static BufferedImage getSpriteDeath(int x, int y, int width, int height) {
		return Spritesheet.spritesheetDeath.getSubimage(x, y, width, height);
	}

	public static BufferedImage getSpritePlayer(int x, int y, int width, int height) {
		return Spritesheet.spritesheetPlayer.getSubimage(x, y, width, height);
	}

	public static BufferedImage getSpriteSkeleton(int x, int y, int width, int height) {
		return Spritesheet.spritesheetSkeleton.getSubimage(x, y, width, height);
	}

	public static BufferedImage getSpriteBackground(int x, int y, int width, int height) {
		return Spritesheet.spritesheetBackground.getSubimage(x, y, width, height);
	}

}