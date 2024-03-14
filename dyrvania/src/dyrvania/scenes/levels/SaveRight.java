package dyrvania.scenes.levels;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import dyrvania.Game;
import dyrvania.Main;
import dyrvania.scenes.Scene;
import dyrvania.scenes.objects.Teleport;
import dyrvania.strings.StringError;

public class SaveRight extends Scene {

	public SaveRight(Game game, Teleport teleport) {
		super(game, teleport);
	}

	@Override
	protected String currentLevelString() {
		return "save-right";
	}

	@Override
	protected BufferedImage loadLevel() {
		try {
			return ImageIO.read(this.getClass().getResource("/levels/save-02.png"));
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

	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			System.out.println("Jogo Salvo");
		}
	}

}
