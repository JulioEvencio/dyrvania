package dyrvania.screens;

import java.awt.Color;
import java.awt.Graphics;

import dyrvania.Game;
import dyrvania.generics.GameColors;
import dyrvania.generics.GameStatus;
import dyrvania.gui.GameText;
import dyrvania.resources.GameFont;
import dyrvania.saves.GameSaveManager;
import dyrvania.scenes.levels.SaveLeft;
import dyrvania.scenes.levels.SaveRight;
import dyrvania.scenes.levels.Tutorial;
import dyrvania.scenes.objects.Teleport;
import dyrvania.strings.StringLevel;

public class GameOver {

	private final Game game;

	private float alpha;
	private boolean sum;

	private final GameText title;

	public GameOver(Game game) {
		this.game = game;

		this.alpha = 1.0f;
		this.sum = false;

		Graphics render = game.getRender();

		render.setFont(GameFont.getTitleLarge());

		int textWidth = render.getFontMetrics().stringWidth(StringLevel.INFO_GAME_OVER.getValue());
		int textHeight = render.getFontMetrics().getHeight();

		int textX = (game.getGameWidth() - textWidth) / 2;
		int textY = (game.getGameHeight() - textHeight) / 2 + render.getFontMetrics().getAscent();

		this.title = new GameText(StringLevel.INFO_GAME_OVER.getValue(), textX, textY, GameColors.WHITE, GameFont.getTitleLarge());
	}

	public void tick() {
		if (this.sum) {
			this.alpha += 0.01f;
		} else {
			this.alpha -= 0.01f;
		}

		if (this.alpha <= 0.0f) {
			this.alpha = 0.0f;
			this.sum = true;
		} else if (this.alpha >= 1.0f) {
			this.alpha = 1.0f;
			this.sum = false;

			if (GameSaveManager.saveIsEmpty()) {
				GameSaveManager.resetSave();

				this.game.initializeScene(new Tutorial(this.game, null));
				this.game.setTransition(GameStatus.RUN);
			} else {
				GameSaveManager.loadData();

				if (GameSaveManager.getSave().isSceneSaveRight()) {
					this.game.initializeScene(new SaveRight(this.game, new Teleport(0, 0, 0xFF0000FF, false), GameSaveManager.getSave().getLastScene()));
				} else {
					this.game.initializeScene(new SaveLeft(this.game, new Teleport(0, 0, 0xFFFF006C, false), GameSaveManager.getSave().getLastScene()));
				}

				this.game.setTransition(GameStatus.RUN);
			}
		}
	}

	public void render(Graphics render) {
		this.title.render(render);

		render.setColor(new Color(0.0f, 0.0f, 0.0f, this.alpha));
		render.fillRect(0, 0, this.game.getGameWidth(), this.game.getHeight());
	}

}
