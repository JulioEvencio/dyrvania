package dyrvania.screens;

import java.awt.Graphics;

import dyrvania.Game;
import dyrvania.Main;
import dyrvania.generics.GameColors;
import dyrvania.generics.GameStatus;
import dyrvania.gui.GameButton;
import dyrvania.gui.GameText;
import dyrvania.resources.GameFont;
import dyrvania.strings.StringGame;
import dyrvania.strings.StringScreen;

public class Exit extends Screen {

	public Exit(Game game) {
		super(game, StringGame.TITLE.getValue());

		Graphics render = game.getRender();

		render.setFont(GameFont.getDefault());

		int fullScreenWidth = render.getFontMetrics().stringWidth(StringScreen.EXIT_GAME.getValue());

		super.texts.add(new GameText(StringScreen.EXIT_GAME.getValue(), (game.getGameWidth() - fullScreenWidth) / 2, 180, GameColors.WHITE, GameFont.getDefault()));

		int leftX = game.getGameWidth() / 2 - 25 - GameButton.getWidth();
		int rightX = game.getGameWidth() / 2 + 25;

		super.buttons.add(new GameButton(game, StringScreen.YES.getValue(), leftX, 220, () -> Main.exit()));
		super.buttons.add(new GameButton(game, StringScreen.NO.getValue(), rightX, 220, () -> game.setGameStatus(game.getLastGameStatus())));
	}

	@Override
	public GameStatus getGameStatus() {
		return GameStatus.EXIT;
	}

}