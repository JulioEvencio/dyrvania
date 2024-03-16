package dyrvania.screens;

import dyrvania.Game;
import dyrvania.generics.GameStatus;
import dyrvania.gui.GameButton;
import dyrvania.strings.StringGame;
import dyrvania.strings.StringScreen;

public class Pause extends Screen {

	public Pause(Game game) {
		super(game, StringGame.TITLE.getValue());

		int centerX = (game.getGameWidth() - GameButton.getWidth()) / 2;

		super.buttons.add(new GameButton(game, StringScreen.CONTINUE.getValue(), centerX, 120, () -> game.setGameStatus(GameStatus.RUN)));
		super.buttons.add(new GameButton(game, StringScreen.MENU.getValue(), centerX, 220, () -> game.setGameStatus(GameStatus.CONFIRM_MAIN_MENU)));
		super.buttons.add(new GameButton(game, StringScreen.EXIT.getValue(), centerX, 320, () -> game.setGameStatus(GameStatus.EXIT)));
	}

	@Override
	public GameStatus getGameStatus() {
		return GameStatus.PAUSE;
	}

}