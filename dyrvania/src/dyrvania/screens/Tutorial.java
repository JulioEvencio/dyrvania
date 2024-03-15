package dyrvania.screens;

import dyrvania.Game;
import dyrvania.generics.GameColors;
import dyrvania.generics.GameStatus;
import dyrvania.gui.GameButton;
import dyrvania.gui.GameText;
import dyrvania.resources.GameFont;
import dyrvania.strings.StringLevel;
import dyrvania.strings.StringScreen;

public class Tutorial extends Screen {

	public Tutorial(Game game) {
		super(game, StringScreen.TUTORIAL.getValue());

		super.texts.add(new GameText(StringScreen.TUTORIAL_FULL_SCREEN.getValue(), 20, 150, GameColors.WHITE, GameFont.getSmall()));
		super.texts.add(new GameText(StringLevel.TUTORIAL_FPS.getValue(), 20, 180, GameColors.WHITE, GameFont.getSmall()));
		super.texts.add(new GameText(StringLevel.TUTORIAL_MUSIC.getValue(), 20, 210, GameColors.WHITE, GameFont.getSmall()));
		super.texts.add(new GameText(StringLevel.TUTORIAL_PAUSE.getValue(), 20, 240, GameColors.WHITE, GameFont.getSmall()));

		int centerX = (game.getGameWidth() - GameButton.getWidth()) / 2;

		super.buttons.add(new GameButton(game, StringScreen.BACK.getValue(), centerX, 350, () -> game.setGameStatus(game.getLastGameStatus())));
	}

	@Override
	public GameStatus getGameStatus() {
		return GameStatus.TUTORIAL;
	}

}