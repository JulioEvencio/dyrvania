package dyrvania.scenes.levels;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import dyrvania.Game;
import dyrvania.saves.GameSaveManager;
import dyrvania.scenes.Scene;
import dyrvania.scenes.objects.Teleport;
import dyrvania.strings.StringLevel;

public abstract class Save extends Scene {

	public Save(Game game, Teleport teleport, String lastScene) {
		super(game, teleport, new ArrayList<>());

		super.addText(StringLevel.INFO_SAVE.getValue(), 150);

		GameSaveManager.getSave().setLastScene(lastScene);
	}

	@Override
	protected Scene nextScene() {
		return GameSaveManager.getScene(super.getGame());
	}

	protected abstract boolean isSceneSaveRight();

	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			GameSaveManager.getSave().setSceneSaveRight(this.isSceneSaveRight());

			super.gameSave();
		}
	}

}