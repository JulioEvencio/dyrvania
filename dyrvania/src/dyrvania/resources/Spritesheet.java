package dyrvania.resources;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import dyrvania.Main;
import dyrvania.strings.StringError;

public class Spritesheet {

	private static final BufferedImage spritesheetGUI;
	private static final BufferedImage spritesheetTiles;
	private static final BufferedImage spritesheetIcons;
	private static final BufferedImage spritesheetDeath;
	private static final BufferedImage spritesheetPlayer;
	private static final BufferedImage spritesheetThing;
	private static final BufferedImage spritesheetSkull;
	private static final BufferedImage spritesheetSkeleton;
	private static final BufferedImage spritesheetBoss;
	private static final BufferedImage spritesheetBackground;

	static {
		BufferedImage auxSpritesheetGUI = null;
		BufferedImage auxSpritesheetTiles = null;
		BufferedImage auxSpritesheetIcons = null;
		BufferedImage auxSpritesheetDeath = null;
		BufferedImage auxSpritesheetPlayer = null;
		BufferedImage auxSpritesheetThing = null;
		BufferedImage auxSpritesheetSkull = null;
		BufferedImage auxSpritesheetSkeleton = null;
		BufferedImage auxSpritesheetBoss = null;
		BufferedImage auxSpritesheetBackground = null;

		try {
			auxSpritesheetGUI = ImageIO.read(Spritesheet.class.getResource("/sprites/gui.png"));
			auxSpritesheetTiles = ImageIO.read(Spritesheet.class.getResource("/sprites/tiles.png"));
			auxSpritesheetIcons = ImageIO.read(Spritesheet.class.getResource("/sprites/icons.png"));
			auxSpritesheetDeath = ImageIO.read(Spritesheet.class.getResource("/sprites/death.png"));
			auxSpritesheetPlayer = ImageIO.read(Spritesheet.class.getResource("/sprites/player.png"));
			auxSpritesheetThing = ImageIO.read(Spritesheet.class.getResource("/sprites/thing.png"));
			auxSpritesheetSkull = ImageIO.read(Spritesheet.class.getResource("/sprites/skull.png"));
			auxSpritesheetSkeleton = ImageIO.read(Spritesheet.class.getResource("/sprites/skeleton.png"));
			auxSpritesheetBoss = ImageIO.read(Spritesheet.class.getResource("/sprites/boss.png"));
			auxSpritesheetBackground = ImageIO.read(Spritesheet.class.getResource("/sprites/background.png"));
		} catch (Exception e) {
			Main.exitWithError(StringError.ERROR_LOADING_SPRITES.getValue());
		}

		spritesheetGUI = auxSpritesheetGUI;
		spritesheetTiles = auxSpritesheetTiles;
		spritesheetIcons = auxSpritesheetIcons;
		spritesheetDeath = auxSpritesheetDeath;
		spritesheetPlayer = auxSpritesheetPlayer;
		spritesheetThing = auxSpritesheetThing;
		spritesheetSkull = auxSpritesheetSkull;
		spritesheetSkeleton = auxSpritesheetSkeleton;
		spritesheetBoss = auxSpritesheetBoss;
		spritesheetBackground = auxSpritesheetBackground;
	}

	public static BufferedImage getSpriteGUI(int x, int y, int width, int height) {
		return Spritesheet.spritesheetGUI.getSubimage(x, y, width, height);
	}

	public static BufferedImage getSpriteTiles(int x, int y, int width, int height) {
		return Spritesheet.spritesheetTiles.getSubimage(x, y, width, height);
	}

	public static BufferedImage getSpriteIcons(int x, int y, int width, int height) {
		return Spritesheet.spritesheetIcons.getSubimage(x, y, width, height);
	}

	public static BufferedImage getSpriteDeath(int x, int y, int width, int height) {
		return Spritesheet.spritesheetDeath.getSubimage(x, y, width, height);
	}

	public static BufferedImage getSpritePlayer(int x, int y, int width, int height) {
		return Spritesheet.spritesheetPlayer.getSubimage(x, y, width, height);
	}

	public static BufferedImage getSpriteThing(int x, int y, int width, int height) {
		return Spritesheet.spritesheetThing.getSubimage(x, y, width, height);
	}

	public static BufferedImage getSpriteSkull(int x, int y, int width, int height) {
		return Spritesheet.spritesheetSkull.getSubimage(x, y, width, height);
	}

	public static BufferedImage getSpriteSkeleton(int x, int y, int width, int height) {
		return Spritesheet.spritesheetSkeleton.getSubimage(x, y, width, height);
	}

	public static BufferedImage getSpriteBoss(int x, int y, int width, int height) {
		return Spritesheet.spritesheetBoss.getSubimage(x, y, width, height);
	}

	public static BufferedImage getSpriteBackground(int x, int y, int width, int height) {
		return Spritesheet.spritesheetBackground.getSubimage(x, y, width, height);
	}

}