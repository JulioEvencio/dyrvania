package dyrvania.scenes.levels;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import dyrvania.Game;
import dyrvania.Main;
import dyrvania.scenes.Scene;
import dyrvania.scenes.objects.Teleport;
import dyrvania.strings.StringError;

public class Level06 extends Scene {

	public Level06(Game game, Teleport teleport) {
		super(game, teleport, new ArrayList<>());
	}

	@Override
	protected String currentLevelString() {
		return "level-06";
	}

	@Override
	protected BufferedImage loadLevel() {
		try {
			return ImageIO.read(this.getClass().getResource("/levels/level-06.png"));
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

		if (teleport.getColor() == 0xFF0000FF) {
			return new Level07(super.getGame(), teleport);
		}

		return new Level02(super.getGame(), teleport);
	}

}
