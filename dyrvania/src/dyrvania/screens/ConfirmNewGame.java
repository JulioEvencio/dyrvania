package dyrvania.screens;

import java.awt.Graphics;

import dyrvania.Game;
import dyrvania.generics.GameColors;
import dyrvania.generics.GameStatus;
import dyrvania.gui.GameButton;
import dyrvania.gui.GameText;
import dyrvania.resources.GameFont;
import dyrvania.scenes.levels.Tutorial;
import dyrvania.strings.StringScreen;

public class ConfirmNewGame extends Screen {

	public ConfirmNewGame(Game game) {
		super(game, StringScreen.NEW_GAME.getValue());

		Graphics render = game.getRender();

		render.setFont(GameFont.getDefault());

		int fullScreenWidthLine1 = render.getFontMetrics().stringWidth(StringScreen.CONFIRM_NEW_GAME_LINE_1.getValue());
		int fullScreenWidthLine2 = render.getFontMetrics().stringWidth(StringScreen.CONFIRM_NEW_GAME_LINE_2.getValue());

		super.texts.add(new GameText(StringScreen.CONFIRM_NEW_GAME_LINE_1.getValue(), (game.getGameWidth() - fullScreenWidthLine1) / 2, 160, GameColors.WHITE, GameFont.getDefault()));
		super.texts.add(new GameText(StringScreen.CONFIRM_NEW_GAME_LINE_2.getValue(), (game.getGameWidth() - fullScreenWidthLine2) / 2, 200, GameColors.WHITE, GameFont.getDefault()));

		int leftX = game.getGameWidth() / 2 - 25 - GameButton.getWidth();
		int rightX = game.getGameWidth() / 2 + 25;

		super.buttons.add(new GameButton(game, StringScreen.YES.getValue(), leftX, 220, () -> {
			game.initializeScene(new Tutorial(game, null));
			game.setTransition(GameStatus.RUN);
		}));
		super.buttons.add(new GameButton(game, StringScreen.NO.getValue(), rightX, 220, () -> game.setGameStatus(game.getLastGameStatus())));
	}

	@Override
	public GameStatus getGameStatus() {
		return GameStatus.CONFIRM_NEW_GAME;
	}

}