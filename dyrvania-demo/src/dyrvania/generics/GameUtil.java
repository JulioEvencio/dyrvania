package dyrvania.generics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GameUtil {

	private final static Random random;

	static {
		random = new Random();
	}

	public static int generateRandomNumber(int x, int y) {
		return GameUtil.random.nextInt((y - x) + 1) + x;
	}

	public static BufferedImage createSpriteColor(BufferedImage original, Color color) {
		BufferedImage sprite = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = sprite.createGraphics();

		g.drawImage(original, 0, 0, null);

		for (int y = 0; y < original.getHeight(); y++) {
			for (int x = 0; x < original.getWidth(); x++) {
				int pixel = original.getRGB(x, y);

				if ((pixel >> 24) != 0x00) {
					g.setColor(color);
					g.fillRect(x, y, 1, 1);
				}
			}
		}

		g.dispose();

		return sprite;
	}

}
