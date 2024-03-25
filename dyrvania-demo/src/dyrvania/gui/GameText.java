package dyrvania.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GameText {

	private final String text;

	private final int x;
	private final int y;

	private final Color color;

	private final Font font;

	public GameText(String text, int x, int y, Color color, Font font) {
		this.text = text;

		this.x = x;
		this.y = y;

		this.color = color;

		this.font = font;
	}

	public String getText() {
		return this.text;
	}

	public void render(Graphics render) {
		render.setColor(this.color);
		render.setFont(this.font);
		render.drawString(this.text, this.x, this.y);
	}

}