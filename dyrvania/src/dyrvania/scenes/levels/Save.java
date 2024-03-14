package dyrvania.scenes.levels;

import java.awt.event.KeyEvent;

import dyrvania.Game;
import dyrvania.saves.GameSaveManager;
import dyrvania.scenes.Scene;
import dyrvania.scenes.objects.Teleport;

public abstract class Save extends Scene {

	public Save(Game game, Teleport teleport, String lastScene) {
		super(game, teleport);

		GameSaveManager.getSave().setLastScene(lastScene);
	}

	protected abstract boolean isSceneRight();

	@Override
	protected Scene nextScene() {
		return GameSaveManager.getScene(super.getGame());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			super.savePlayer();
			GameSaveManager.saveData();
		}
	}

}