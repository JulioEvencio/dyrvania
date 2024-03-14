package dyrvania.scenes.levels;

import java.awt.event.KeyEvent;

import dyrvania.Game;
import dyrvania.saves.GameSaveManager;
import dyrvania.scenes.Scene;
import dyrvania.scenes.objects.Teleport;

public abstract class Save extends Scene {

	private String lastScene;

	public Save(Game game, Teleport teleport, String lastScene) {
		super(game, teleport);

		this.lastScene = lastScene;
	}

	protected abstract boolean isSceneRight();

	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			GameSaveManager.getSave().setLastScene(this.lastScene);
			GameSaveManager.saveData();
		}
	}

}