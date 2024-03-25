package dyrvania.gui;

import java.awt.Graphics;

import dyrvania.Game;
import dyrvania.generics.Camera;
import dyrvania.generics.GameColors;
import dyrvania.generics.GameRect;
import dyrvania.resources.GameFont;

public class GameTextRender {

	private final String text;

	private final GameRect rect;

	public GameTextRender(Game game, String text, int x, int y) {
		this.text = text;

		Graphics render = game.getRender();

		render.setFont(GameFont.getTinyFont());

		int width = render.getFontMetrics().stringWidth(text);
		int height = render.getFontMetrics().getHeight();

		this.rect = new GameRect(x, y, width, height);
	}

	public String getText() {
		return this.text;
	}

	public GameRect getRect() {
		return this.rect;
	}

	public void render(Graphics render) {
		int backgroundX = this.rect.getX() - 10 - Camera.x;
		int backgroundY = this.rect.getY() - 15 - Camera.y;
		int backgroundWidth = this.rect.getWidth() + 20;
		int backgroundHeight = this.rect.getHeight() + 15;

		render.setColor(GameColors.BLACK);
		render.fillRect(backgroundX, backgroundY, backgroundWidth, backgroundHeight);

		render.setColor(GameColors.WHITE);
		render.setFont(GameFont.getTinyFont());
		render.drawString(this.text, this.rect.getX() - Camera.x, this.rect.getY() - Camera.y);

		render.drawRect(backgroundX, backgroundY, backgroundWidth, backgroundHeight);
	}

}