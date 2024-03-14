package dyrvania.scenes.levels;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import dyrvania.Game;
import dyrvania.Main;
import dyrvania.scenes.Scene;
import dyrvania.scenes.objects.Teleport;
import dyrvania.strings.StringError;

public class Level02 extends Scene {

	public Level02(Game game, Teleport teleport) {
		super(game, teleport);
	}

	@Override
	protected String currentLevelString() {
		return "level-02";
	}

	@Override
	protected BufferedImage loadLevel() {
		try {
			return ImageIO.read(this.getClass().getResource("/levels/level-02.png"));
		} catch (Exception e) {
			Main.exitWithError(StringError.ERROR_LOADING_FILES.getValue());
		}

		return null;
	}

	@Override
	protected Scene nextScene() {
		Teleport teleport = super.getTeleportCurrent();

		if (teleport.getColor() == 0xFF0000FF) {
			return new SaveRight(super.getGame(), teleport, this.currentLevelString());
		}

		if (teleport.getColor() == 0xFF7AFF00) {
			return new Level03(super.getGame(), teleport);
		}

		if (teleport.getColor() == 0xFFFF006C || teleport.getColor() == 0xFF00FF93) {
			return new Level01(super.getGame(), teleport);
		}

		return new Level06(super.getGame(), teleport);
	}

}
