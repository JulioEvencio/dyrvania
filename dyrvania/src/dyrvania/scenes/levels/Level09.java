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
import dyrvania.strings.StringLevel;

public class Level09 extends Scene {

	public Level09(Game game, Teleport teleport) {
		super(game, teleport, new ArrayList<>(List.of(new BackgroundMoon(game, 0, 0))));

		super.addText(StringLevel.INFO_THANK_YOU.getValue(), 300);
		super.addText(StringLevel.INFO_SOON.getValue(), 330);
	}

	@Override
	protected String currentLevelString() {
		return "level-09";
	}

	@Override
	protected BufferedImage loadLevel() {
		try {
			return ImageIO.read(this.getClass().getResource("/levels/level-09.png"));
		} catch (Exception e) {
			Main.exitWithError(StringError.ERROR_LOADING_FILES.getValue());
		}

		return null;
	}

	@Override
	protected Scene nextScene() {
		Teleport teleport = super.getTeleportCurrent();

		return new Level08(super.getGame(), teleport);
	}

}
