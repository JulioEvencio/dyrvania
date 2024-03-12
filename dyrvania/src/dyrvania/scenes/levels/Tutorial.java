package dyrvania.scenes.levels;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import dyrvania.Game;
import dyrvania.Main;
import dyrvania.scenes.Scene;
import dyrvania.strings.StringError;

public class Tutorial extends Scene {

	public Tutorial(Game game) {
		super(game);
	}

	@Override
	protected BufferedImage loadLevel() {
		try {
			// return ImageIO.read(this.getClass().getResource("/levels/level-01.png"));
			return ImageIO.read(this.getClass().getResource("/levels/tutorial.png"));
		} catch (Exception e) {
			Main.exitWithError(StringError.ERROR_LOADING_FILES.getValue());
		}

		return null;
	}

}
