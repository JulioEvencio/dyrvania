package dyrvania.screens;

import java.awt.Color;
import java.awt.Graphics;

import dyrvania.Game;
import dyrvania.generics.GameStatus;
import dyrvania.gui.GameText;
import dyrvania.resources.GameFont;
import dyrvania.strings.StringGame;

public class OpeningScreen {

	private final Game game;

	private float alpha;
	private boolean sum;

	private final GameText title;

	public OpeningScreen(Game game) {
		this.game = game;

		this.alpha = 1.0f;
		this.sum = false;

		Graphics render = game.getRender();

		render.setFont(GameFont.getTitleLarge());

		int textWidth = render.getFontMetrics().stringWidth(StringGame.TITLE.getValue());
		int textHeight = render.getFontMetrics().getHeight();

		int textX = (game.getGameWidth() - textWidth) / 2;
		int textY = (game.getGameHeight() - textHeight) / 2 + render.getFontMetrics().getAscent();

		this.title = new GameText(StringGame.TITLE.getValue(), textX, textY, Color.WHITE, GameFont.getTitleLarge());
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
			this.game.setTransition(GameStatus.SELECT_LANGUAGE);
		}
	}

	public void render(Graphics render) {
		this.title.render(render);

		render.setColor(new Color(0.0f, 0.0f, 0.0f, this.alpha));
		render.fillRect(0, 0, this.game.getGameWidth(), this.game.getHeight());
	}

}
