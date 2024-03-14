package dyrvania.scenes.levels;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import dyrvania.Game;
import dyrvania.Main;
import dyrvania.scenes.objects.Teleport;
import dyrvania.strings.StringError;

public class SaveLeft extends Save {

	public SaveLeft(Game game, Teleport teleport, String lastScene) {
		super(game, teleport, lastScene);
	}

	@Override
	protected boolean isSceneRight() {
		return false;
	}

	@Override
	protected String currentLevelString() {
		return "save-left";
	}

	@Override
	protected BufferedImage loadLevel() {
		try {
			return ImageIO.read(this.getClass().getResource("/levels/save-01.png"));
		} catch (Exception e) {
			Main.exitWithError(StringError.ERROR_LOADING_FILES.getValue());
		}

		return null;
	}

}
