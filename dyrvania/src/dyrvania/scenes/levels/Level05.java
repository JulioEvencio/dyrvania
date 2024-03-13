package dyrvania.scenes.levels;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import dyrvania.Game;
import dyrvania.Main;
import dyrvania.scenes.Scene;
import dyrvania.scenes.objects.Teleport;
import dyrvania.strings.StringError;

public class Level05 extends Scene {

	public Level05(Game game, Teleport teleport) {
		super(game, teleport);
	}

	@Override
	protected BufferedImage loadLevel() {
		try {
			return ImageIO.read(this.getClass().getResource("/levels/level-05.png"));
		} catch (Exception e) {
			Main.exitWithError(StringError.ERROR_LOADING_FILES.getValue());
		}

		return null;
	}

	@Override
	protected Scene nextScene() {
		Teleport teleport = super.getTeleportCurrent();

		if (teleport.getColor() == 0xFFFF3900) {
			return new Level07(super.getGame(), teleport);
		}

		return new Level04(super.getGame(), teleport);
	}

}
