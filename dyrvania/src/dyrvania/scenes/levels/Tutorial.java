package dyrvania.scenes.levels;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import dyrvania.Game;
import dyrvania.Main;
import dyrvania.scenes.Scene;
import dyrvania.scenes.backgrounds.BackgroundCloud;
import dyrvania.scenes.objects.Teleport;
import dyrvania.strings.StringError;

public class Tutorial extends Scene {

	public Tutorial(Game game, Teleport teleport) {
		super(game, teleport, new ArrayList<>(List.of(new BackgroundCloud(game, 0, 0))));
	}

	@Override
	protected String currentLevelString() {
		return "tutorial";
	}

	@Override
	protected BufferedImage loadLevel() {
		try {
			return ImageIO.read(this.getClass().getResource("/levels/tutorial.png"));
		} catch (Exception e) {
			Main.exitWithError(StringError.ERROR_LOADING_FILES.getValue());
		}

		return null;
	}

	@Override
	protected Scene nextScene() {
		return new Level01(super.getGame(), super.getTeleportCurrent());
	}

}
