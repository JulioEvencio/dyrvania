package dyrvania.scenes;

import java.awt.Color;
import java.awt.Graphics;

import dyrvania.Game;

public abstract class Scene {

	private final Game game;

	protected char[][] map;

	public Scene(Game game) {
		this.game = game;

		this.buildGame();
	}

	protected abstract void initializeLevel();

	private void buildGame() {
		this.initializeLevel();
	}

	public void tick() {
		System.out.println("RUN");
	}

	public void render(Graphics render) {
		render.setColor(Color.BLACK);
		render.fillRect(0, 0, this.game.getGameWidth(), this.game.getGameHeight());
	}

}
