package dyrvania.scenes.levels;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import dyrvania.Game;
import dyrvania.Main;
import dyrvania.saves.GameSaveManager;
import dyrvania.scenes.Scene;
import dyrvania.scenes.backgrounds.Background;
import dyrvania.scenes.backgrounds.BackgroundMoon;
import dyrvania.scenes.entities.enemies.bosses.Boss;
import dyrvania.scenes.objects.Teleport;
import dyrvania.strings.StringError;
import dyrvania.strings.StringLevel;

public class Boss01 extends Scene {

	public Boss01(Game game, Teleport teleport) {
		super(game, teleport, new Boss(), Boss01.getBackgrounds(game));
	}

	private static List<Background> getBackgrounds(Game game) {
		List<Background> backgrounds = new ArrayList<>();

		backgrounds.add(new BackgroundMoon(game, 0, 0));

		return backgrounds;
	}

	@Override
	public String currentLevelString() {
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

	@Override
	public void tick() {
		super.tick();

		if (GameSaveManager.getSave().isBossDefeated() && super.texts.isEmpty()) {
			super.addText(StringLevel.INFO_DOUBLE_JUMP.getValue(), 100);
			super.addText(StringLevel.TUTORIAL_DOUBLE_JUMP.getValue(), 130);
		}
	}

}
