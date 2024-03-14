package dyrvania.scenes.levels;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import dyrvania.Game;
import dyrvania.Main;
import dyrvania.scenes.Scene;
import dyrvania.scenes.objects.Teleport;
import dyrvania.strings.StringError;

public class Level01 extends Scene {

	public Level01(Game game, Teleport teleport) {
		super(game, teleport, new ArrayList<>());
	}

	@Override
	protected String currentLevelString() {
		return "level-01";
	}

	@Override
	protected BufferedImage loadLevel() {
		try {
			return ImageIO.read(this.getClass().getResource("/levels/level-01.png"));
		} catch (Exception e) {
			Main.exitWithError(StringError.ERROR_LOADING_FILES.getValue());
		}

		return null;
	}

	@Override
	protected Scene nextScene() {
		Teleport teleport = super.getTeleportCurrent();

		if (teleport.getColor() == 0xFFFF006C) {
			return new Tutorial(super.getGame(), teleport);
		}

		return new Level02(super.getGame(), teleport);
	}

}
