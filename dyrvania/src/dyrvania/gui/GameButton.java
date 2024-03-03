package dyrvania.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dyrvania.Game;
import dyrvania.gui.events.EventOnClick;
import dyrvania.resources.GameFont;
import dyrvania.resources.Spritesheet;

public class GameButton {

	private final Game game;

	private final GameText text;

	private final int x;
	private final int y;

	private static final int widthPressed;
	private static final int heightPressed;

	private static final int widthReleased;
	private static final int heightReleased;

	private boolean buttonIsPressed;

	private static final BufferedImage spriteButtonPressed;
	private static final BufferedImage spriteButtonReleased;

	private final EventOnClick eventOnClick;

	static {
		widthPressed = 46 * 5;
		heightPressed = 13 * 5;

		widthReleased = 46 * 5;
		heightReleased = 14 * 5;

		spriteButtonPressed = Spritesheet.getSpriteGUI(145, 98, 46, 13);
		spriteButtonReleased = Spritesheet.getSpriteGUI(145, 81, 46, 14);
	}

	public GameButton(Game game, String text, int x, int y, EventOnClick eventOnClick) {
		this.game = game;

		game.getRender().setFont(GameFont.getDefault());

		int textWidth = game.getRender().getFontMetrics().stringWidth(text);
		int textHeight = game.getRender().getFontMetrics().getHeight();

		int textX = x + (GameButton.widthPressed - textWidth) / 2;
		int textY = y + (GameButton.heightPressed - textHeight) / 2 + game.getRender().getFontMetrics().getAscent();

		this.text = new GameText(text, textX, textY, Color.WHITE, GameFont.getDefault());

		this.x = x;
		this.y = y;

		this.buttonIsPressed = false;

		this.eventOnClick = eventOnClick;
	}

	public String getText() {
		return this.text.getText();
	}

	public static int getWidth() {
		return GameButton.widthPressed;
	}

	public static int getHeight() {
		return GameButton.heightPressed;
	}

	public void onClick() {
		this.eventOnClick.onClick();
	}

	public boolean wasClicked(int x, int y) {
		if (this.game.isFullscreen()) {
			x -= this.game.getRendererX();
			y -= this.game.getRendererY();

			x *= (double) this.game.getGameWidth() / (double) this.game.getRendererWidth();
			y *= (double) this.game.getGameHeight() / (double) this.game.getRendererHeight();
		}

		return x >= this.x && x <= this.x + GameButton.widthPressed && y >= this.y && y <= this.y + GameButton.heightPressed;
	}

	public void setButtonPressed() {
		this.buttonIsPressed = true;
	}

	public void setButtonReleased() {
		this.buttonIsPressed = false;
	}

	public void render(Graphics render) {
		if (this.buttonIsPressed) {
			render.drawImage(GameButton.spriteButtonPressed, this.x, this.y, GameButton.widthPressed, GameButton.heightPressed, null);
		} else {
			render.drawImage(GameButton.spriteButtonReleased, this.x, this.y, GameButton.widthReleased, GameButton.heightReleased, null);
		}

		this.text.render(render);
	}

}