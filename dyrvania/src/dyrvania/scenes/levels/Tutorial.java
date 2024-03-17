package dyrvania.scenes.levels;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import dyrvania.Game;
import dyrvania.Main;
import dyrvania.gui.GameTextRender;
import dyrvania.scenes.Scene;
import dyrvania.scenes.backgrounds.Background;
import dyrvania.scenes.backgrounds.BackgroundMoon;
import dyrvania.scenes.objects.Teleport;
import dyrvania.strings.StringError;
import dyrvania.strings.StringLevel;

public class Tutorial extends Scene {

	public Tutorial(Game game, Teleport teleport) {
		super(game, teleport, Tutorial.getBackgrounds(game));

		super.texts.add(new GameTextRender(game, StringLevel.TUTORIAL_PAUSE.getValue(), 80, 110));

		super.texts.add(new GameTextRender(game, StringLevel.TUTORIAL_MOVE.getValue(), 80, 150));
		super.texts.add(new GameTextRender(game, StringLevel.TUTORIAL_JUMP.getValue(), 80, 190));
		super.texts.add(new GameTextRender(game, StringLevel.TUTORIAL_ATTACK.getValue(), 80, 230));

		super.texts.add(new GameTextRender(game, StringLevel.TUTORIAL_OBJECT_SWORD.getValue(), 80, 270));

		super.texts.add(new GameTextRender(game, StringLevel.TUTORIAL_OBJECT_LIFE.getValue(), 80, 310));
	}

	private static List<Background> getBackgrounds(Game game) {
		List<Background> backgrounds = new ArrayList<>();

		backgrounds.add(new BackgroundMoon(game, 0, 0));

		return backgrounds;
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
