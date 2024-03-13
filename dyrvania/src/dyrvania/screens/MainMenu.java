package dyrvania.screens;

import java.awt.Graphics;

import dyrvania.Game;
import dyrvania.generics.GameColors;
import dyrvania.generics.GameStatus;
import dyrvania.gui.GameButton;
import dyrvania.gui.GameText;
import dyrvania.resources.GameFont;
import dyrvania.scenes.levels.Tutorial;
import dyrvania.strings.StringGame;
import dyrvania.strings.StringScreen;

public class MainMenu extends Screen {

	public MainMenu(Game game) {
		super(game, StringGame.TITLE.getValue());

		int centerX = (game.getGameWidth() - GameButton.getWidth()) / 2;
		int leftX = game.getGameWidth() / 2 - 25 - GameButton.getWidth();
		int rightX = game.getGameWidth() / 2 + 25;

		super.buttons.add(new GameButton(game, StringScreen.NEW_GAME.getValue(), leftX, 120, () -> {
			game.initializeScene(new Tutorial(game, null));
			game.setTransition(GameStatus.RUN);
		}));

		super.buttons.add(new GameButton(game, StringScreen.LOAD_GAME.getValue(), rightX, 120, () -> System.out.println("Load Game")));

		super.buttons.add(new GameButton(game, StringScreen.CREDITS.getValue(), leftX, 220, () -> System.out.println("Credits")));
		super.buttons.add(new GameButton(game, StringScreen.SETTINGS.getValue(), rightX, 220, () -> System.out.println("Settings")));

		super.buttons.add(new GameButton(game, StringScreen.EXIT.getValue(), centerX, 320, () -> game.setGameStatus(GameStatus.EXIT)));

		Graphics render = game.getRender();

		render.setFont(GameFont.getSmall());

		int fullScreenWidth = render.getFontMetrics().stringWidth(StringScreen.TUTORIAL_FULL_SCREEN.getValue());

		super.texts.add(new GameText(StringScreen.TUTORIAL_FULL_SCREEN.getValue(), (game.getGameWidth() - fullScreenWidth) / 2, 420, GameColors.WHITE, GameFont.getSmall()));
	}

	@Override
	public GameStatus getGameStatus() {
		return GameStatus.MAIN_MENU;
	}

}