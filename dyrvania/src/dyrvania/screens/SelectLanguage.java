package dyrvania.screens;

import java.awt.Color;
import java.awt.Graphics;

import dyrvania.Game;
import dyrvania.generics.GameStatus;
import dyrvania.gui.GameButton;
import dyrvania.gui.GameText;
import dyrvania.resources.GameFont;
import dyrvania.resources.Translation;
import dyrvania.resources.Translation.Language;
import dyrvania.strings.StringGame;
import dyrvania.strings.StringScreen;

public class SelectLanguage extends Screen {

	public SelectLanguage(Game game) {
		super(game, StringGame.TITLE.getValue());

		int centerX = (game.getGameWidth() - GameButton.getWidth()) / 2;

		super.buttons.add(new GameButton(game, StringScreen.ENGLISH.getValue(), centerX, 120, () -> this.selectLanguage(Language.ENGLISH, game)));
		super.buttons.add(new GameButton(game, StringScreen.PORTUGUESE.getValue(), centerX, 220, () -> this.selectLanguage(Language.PORTUGUESE, game)));
		super.buttons.add(new GameButton(game, StringScreen.SPANISH.getValue(), centerX, 320, () -> this.selectLanguage(Language.SPANISH, game)));

		Graphics render = game.getRender();

		render.setFont(GameFont.getSmall());

		int fullScreenWidth = render.getFontMetrics().stringWidth(StringScreen.TUTORIAL_FULL_SCREEN.getValue());

		super.texts.add(new GameText(StringScreen.TUTORIAL_FULL_SCREEN.getValue(), (game.getGameWidth() - fullScreenWidth) / 2, 420, Color.WHITE, GameFont.getSmall()));
	}

	private void selectLanguage(Language language, Game game) {
		Translation.changeTheLanguage(language);

		game.initializeScreen();
		game.updateGameStatus(GameStatus.MAIN_MENU);
	}

	@Override
	public GameStatus getGameStatus() {
		return GameStatus.SELECT_LANGUAGE;
	}

}