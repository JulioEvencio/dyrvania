package dyrvania.screens;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import dyrvania.Game;
import dyrvania.generics.GameColors;
import dyrvania.generics.GameStatus;
import dyrvania.gui.GameText;
import dyrvania.resources.GameFont;
import dyrvania.strings.StringScreen;

public class Lore {

	private final Game game;

	private int control;
	private boolean canUpdateScreen;

	private final GameText infoNext;
	private final GameText[] lore;

	public Lore(Game game) {
		this.game = game;

		this.control = 0;
		this.canUpdateScreen = false;

		this.lore = new GameText[17];

		this.lore[0] = this.createGameText(StringScreen.LORE_LINE_01.getValue());
		this.lore[1] = this.createGameText(StringScreen.LORE_LINE_02.getValue());
		this.lore[2] = this.createGameText(StringScreen.LORE_LINE_03.getValue());
		this.lore[3] = this.createGameText(StringScreen.LORE_LINE_04.getValue());
		this.lore[4] = this.createGameText(StringScreen.LORE_LINE_05.getValue());
		this.lore[5] = this.createGameText(StringScreen.LORE_LINE_06.getValue());
		this.lore[6] = this.createGameText(StringScreen.LORE_LINE_07.getValue());
		this.lore[7] = this.createGameText(StringScreen.LORE_LINE_08.getValue());
		this.lore[8] = this.createGameText(StringScreen.LORE_LINE_09.getValue());
		this.lore[9] = this.createGameText(StringScreen.LORE_LINE_10.getValue());
		this.lore[10] = this.createGameText(StringScreen.LORE_LINE_11.getValue());
		this.lore[11] = this.createGameText(StringScreen.LORE_LINE_12.getValue());
		this.lore[12] = this.createGameText(StringScreen.LORE_LINE_13.getValue());
		this.lore[13] = this.createGameText(StringScreen.LORE_LINE_14.getValue());
		this.lore[14] = this.createGameText(StringScreen.LORE_LINE_15.getValue());
		this.lore[15] = this.createGameText(StringScreen.LORE_LINE_16.getValue());
		this.lore[16] = this.createGameText(StringScreen.LORE_LINE_17.getValue());

		this.infoNext = this.createGameText(StringScreen.ENTER_CONTINUE.getValue(), 420);
	}

	private GameText createGameText(String text) {
		Graphics render = this.game.getRender();

		render.setFont(GameFont.getSmall());

		int textWidth = render.getFontMetrics().stringWidth(text);
		int textHeight = render.getFontMetrics().getHeight();

		int textX = (this.game.getGameWidth() - textWidth) / 2;
		int textY = (this.game.getGameHeight() - textHeight) / 2 + render.getFontMetrics().getAscent();

		return new GameText(text, textX, textY, GameColors.WHITE, GameFont.getSmall());
	}

	private GameText createGameText(String text, int y) {
		Graphics render = this.game.getRender();

		render.setFont(GameFont.getSmall());

		int textWidth = render.getFontMetrics().stringWidth(text);

		int textX = (this.game.getGameWidth() - textWidth) / 2;

		return new GameText(text, textX, y, GameColors.WHITE, GameFont.getSmall());
	}

	public void tick() {
		if (this.canUpdateScreen) {
			this.control++;
			this.canUpdateScreen = false;
		}

		if (this.control >= this.lore.length) {
			this.control = 0;

			this.game.setTransition(GameStatus.RUN);
		}
	}

	public void render(Graphics render) {
		if (this.control < this.lore.length) {
			render.setColor(GameColors.BLACK);
			render.fillRect(0, 0, this.game.getGameWidth(), this.game.getHeight());

			this.lore[this.control].render(render);

			this.infoNext.render(render);
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			this.canUpdateScreen = true;
		}
	}

}
