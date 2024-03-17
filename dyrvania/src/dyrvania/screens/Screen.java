package dyrvania.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import dyrvania.Game;
import dyrvania.generics.GameColors;
import dyrvania.generics.GameStatus;
import dyrvania.gui.GameButton;
import dyrvania.gui.GameText;
import dyrvania.resources.GameFont;
import dyrvania.resources.Spritesheet;

public abstract class Screen {

	protected final Game game;

	private static final BufferedImage background;

	private final GameText title;

	protected final List<GameText> texts;
	protected final List<GameButton> buttons;

	private boolean mousePressed;
	private boolean mouseReleased;

	private int mouseX;
	private int mouseY;

	static {
		background = Spritesheet.getSpriteBackground(12, 332, 512, 224);
	}

	public Screen(Game game, String title) {
		this.game = game;

		Graphics render = game.getRender();

		render.setFont(GameFont.getTitle());

		int titleWidth = render.getFontMetrics().stringWidth(title);

		this.title = new GameText(title, (game.getGameWidth() - titleWidth) / 2, 80, GameColors.WHITE, GameFont.getTitle());

		this.texts = new ArrayList<>();
		this.buttons = new ArrayList<>();

		this.mousePressed = false;
		this.mouseReleased = false;

		this.mouseX = 0;
		this.mouseY = 0;

		this.texts.add(new GameText(String.format("v %s", this.game.getVersion()), 25, 25, GameColors.WHITE, GameFont.getSmall()));
	}

	public abstract GameStatus getGameStatus();

	public void tick() {
		if (this.mousePressed) {
			for (GameButton button : this.buttons) {
				if (button.wasClicked(this.mouseX, this.mouseY)) {
					button.setButtonPressed();
				}
			}

			this.mousePressed = false;
		}

		if (this.mouseReleased) {
			for (GameButton button : this.buttons) {
				if (button.wasClicked(this.mouseX, this.mouseY)) {
					button.onClick();
				}

				button.setButtonReleased();
			}

			this.mouseReleased = false;
		}
	}

	public void render(Graphics render) {
		render.drawImage(Screen.background, 0, 0, this.game.getGameWidth(), this.game.getGameHeight(), null);

		render.setColor(new Color(0, 0, 0, 0.5f));
		render.fillRect(0, 0, this.game.getGameWidth(), this.game.getGameHeight());

		this.title.render(render);

		for (GameText text : this.texts) {
			text.render(render);
		}

		for (GameButton button : this.buttons) {
			button.render(render);
		}
	}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			this.mousePressed = true;

			this.mouseX = e.getX();
			this.mouseY = e.getY();
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			this.mouseReleased = true;

			this.mouseX = e.getX();
			this.mouseY = e.getY();
		}
	}

}