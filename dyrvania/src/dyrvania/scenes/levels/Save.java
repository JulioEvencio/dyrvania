package dyrvania.scenes.levels;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import dyrvania.Game;
import dyrvania.gui.GameTextRender;
import dyrvania.saves.GameSaveManager;
import dyrvania.scenes.Scene;
import dyrvania.scenes.objects.Teleport;
import dyrvania.strings.StringLevel;

public abstract class Save extends Scene {

	public Save(Game game, Teleport teleport, String lastScene) {
		super(game, teleport, new ArrayList<>());

		this.addText(StringLevel.INFO_SAVE.getValue(), 150);

		GameSaveManager.getSave().setLastScene(lastScene);
	}

	private void addText(String text, int y) {
		GameTextRender textRender = new GameTextRender(super.getGame(), text, 0, y);

		textRender.getRect().setX((super.getGame().getGameWidth() - textRender.getRect().getWidth()) / 2);

		super.texts.add(textRender);
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

			GameSaveManager.getSave().setSceneSaveRight(this.isSceneRight());
			GameSaveManager.saveData();

			this.addText(StringLevel.INFO_GAME_SAVED.getValue(), 190);
			this.addText(StringLevel.INFO_LIFE_RESTORED.getValue(), 230);
			this.addText(StringLevel.INFO_NEGATIVE_EFFECTS_REMOVED.getValue(), 270);
		}
	}

}