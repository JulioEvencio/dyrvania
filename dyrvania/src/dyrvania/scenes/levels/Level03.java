package dyrvania.scenes.levels;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import dyrvania.Game;
import dyrvania.Main;
import dyrvania.scenes.Scene;
import dyrvania.scenes.objects.Teleport;
import dyrvania.strings.StringError;

public class Level03 extends Scene {

	public Level03(Game game, Teleport teleport) {
		super(game, teleport);
	}

	@Override
	protected String currentLevelString() {
		return "level-03";
	}

	@Override
	protected BufferedImage loadLevel() {
		try {
			return ImageIO.read(this.getClass().getResource("/levels/level-03.png"));
		} catch (Exception e) {
			Main.exitWithError(StringError.ERROR_LOADING_FILES.getValue());
		}

		return null;
	}

	@Override
	protected Scene nextScene() {
		Teleport teleport = super.getTeleportCurrent();

		if (teleport.getColor() == 0xFFFF006C) {
			return new SaveLeft(super.getGame(), teleport, this.currentLevelString());
		}

		return new Level04(super.getGame(), teleport);
	}

}
