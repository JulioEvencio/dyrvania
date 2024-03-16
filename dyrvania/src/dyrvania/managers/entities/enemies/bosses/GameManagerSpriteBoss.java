package dyrvania.managers.entities.enemies.bosses;

import java.awt.image.BufferedImage;

import dyrvania.generics.GameColors;
import dyrvania.generics.GameRect;
import dyrvania.generics.GameSpriteAnimation;
import dyrvania.generics.GameUtil;
import dyrvania.resources.Spritesheet;

public class GameManagerSpriteBoss {

	// Sprites Normal
	private final static BufferedImage[] spriteIdle;
	private final static BufferedImage[] spriteTeleport01;
	private final static BufferedImage[] spriteTeleport02;
	private final static BufferedImage[] spriteInvoking;

	// Sprites Damage
	private final static BufferedImage[] spriteIdleDamage;
	private final static BufferedImage[] spriteInvokingDamage;

	static {
		int spriteWidth = 64;
		int spriteHeight = 80;

		// Idle
		spriteIdle = new BufferedImage[7];
		spriteIdleDamage = new BufferedImage[7];

		for (int i = 0; i < spriteIdle.length; i++) {
			spriteIdle[i] = Spritesheet.getSpriteBoss(spriteWidth * i, 0, spriteWidth, spriteHeight);
			spriteIdleDamage[i] = GameUtil.createSpriteColor(spriteIdle[i], GameColors.DAMAGE);
		}

		// Teleport 01
		spriteWidth = 64;
		spriteHeight = 64;

		spriteTeleport01 = new BufferedImage[7];

		for (int i = 0; i < spriteTeleport01.length; i++) {
			spriteTeleport01[i] = Spritesheet.getSpriteBoss(spriteWidth * i, 160, spriteWidth, spriteHeight);
		}

		// Teleport 02
		spriteWidth = 64;
		spriteHeight = 48;

		spriteTeleport02 = new BufferedImage[6];

		for (int i = 0; i < spriteTeleport02.length; i++) {
			spriteTeleport02[i] = Spritesheet.getSpriteBoss(spriteWidth * i, 240, spriteWidth, spriteHeight);
		}

		// Invoking
		spriteWidth = 64;
		spriteHeight = 80;

		spriteInvoking = new BufferedImage[4];
		spriteInvokingDamage = new BufferedImage[4];

		for (int i = 0; i < spriteInvoking.length; i++) {
			spriteInvoking[i] = Spritesheet.getSpriteBoss(spriteWidth * i, 80, spriteWidth, spriteHeight);
			spriteInvokingDamage[i] = GameUtil.createSpriteColor(spriteIdle[i], GameColors.DAMAGE);
		}
	}

	public static GameSpriteAnimation createSpriteIdle(GameRect rect) {
		return new GameSpriteAnimation(rect, 10, GameManagerSpriteBoss.spriteIdle, GameManagerSpriteBoss.spriteIdleDamage);
	}

	public static GameSpriteAnimation createSpriteTeleport01(GameRect rect) {
		return new GameSpriteAnimation(rect, 10, GameManagerSpriteBoss.spriteTeleport01, GameManagerSpriteBoss.spriteTeleport01);
	}

	public static GameSpriteAnimation createSpriteTeleport02(GameRect rect) {
		return new GameSpriteAnimation(rect, 10, GameManagerSpriteBoss.spriteTeleport02, GameManagerSpriteBoss.spriteTeleport02);
	}

	public static GameSpriteAnimation createSpriteInvoking(GameRect rect) {
		return new GameSpriteAnimation(rect, 10, GameManagerSpriteBoss.spriteInvoking, GameManagerSpriteBoss.spriteInvokingDamage);
	}

}
