package dyrvania.screens;

import java.awt.Graphics;

import dyrvania.Game;
import dyrvania.generics.GameColors;
import dyrvania.generics.GameStatus;
import dyrvania.gui.GameButton;
import dyrvania.gui.GameText;
import dyrvania.resources.GameFont;
import dyrvania.strings.StringScreen;

public class Credits extends Screen {

	public Credits(Game game) {
		super(game, StringScreen.CREDITS.getValue());

		this.addText("Game design and programmer: Júlio Igreja", 120);
		this.addText("GitHub: https://github.com/JulioEvencio", 150);
		this.addText("Sprites (Open Game Art):", 190);
		this.addText("StumpyStrust, itsmars, ansimuz and pauliuw", 230);
		this.addText("Music and sounds (Open Game Art):", 270);
		this.addText("qubodup, IgnasD, KASTLE Knight, TinyWorlds", 310);
		this.addText("Fupi, wobbleboxx and HydroGene", 350);

		int centerX = (game.getGameWidth() - GameButton.getWidth()) / 2;

		super.buttons.add(new GameButton(game, StringScreen.BACK.getValue(), centerX, 380, () -> game.setGameStatus(game.getLastGameStatus())));
	}

	private void addText(String text, int y) {
		Graphics render = super.game.getRender();

		render.setFont(GameFont.getSmall());

		int centerX = 25;

		super.texts.add(new GameText(text, centerX, y, GameColors.WHITE, GameFont.getSmall()));
	}

	@Override
	public GameStatus getGameStatus() {
		return GameStatus.CREDITS;
	}

}