package dyrvania.scenes.levels;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import dyrvania.Game;
import dyrvania.Main;
import dyrvania.scenes.Scene;
import dyrvania.scenes.backgrounds.BackgroundMoon;
import dyrvania.scenes.objects.Teleport;
import dyrvania.strings.StringError;

public class Boss01 extends Scene {

	public Boss01(Game game, Teleport teleport) {
		super(game, teleport, new ArrayList<>(List.of(new BackgroundMoon(game, 0, 0))));
	}

	@Override
	protected String currentLevelString() {
		return "boss-01";
	}

	@Override
	protected BufferedImage loadLevel() {
		try {
			return ImageIO.read(this.getClass().getResource("/levels/boss-01.png"));
		} catch (Exception e) {
			Main.exitWithError(StringError.ERROR_LOADING_FILES.getValue());
		}

		return null;
	}

	@Override
	protected Scene nextScene() {
		Teleport teleport = super.getTeleportCurrent();

		return new Level07(super.getGame(), teleport);
	}

}
