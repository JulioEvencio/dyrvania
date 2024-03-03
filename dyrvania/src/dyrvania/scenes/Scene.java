package dyrvania.scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import dyrvania.Game;
import dyrvania.scenes.tiles.Floor;

public abstract class Scene {

	private final Game game;

	protected char[][] map;

	private final List<Floor> floors;

	public Scene(Game game) {
		this.game = game;

		this.buildGame();

		this.floors = new ArrayList<>();

		for (int i = 1; i < this.game.getGameWidth() / 32 - 1; i++) {
			this.floors.add(new Floor(32 * i, 300, 32, 32));
		}
	}

	protected abstract void initializeLevel();

	private void buildGame() {
		this.initializeLevel();
	}

	public void tick() {
		// Code
	}

	public void render(Graphics render) {
		render.setColor(Color.BLACK);
		render.fillRect(0, 0, this.game.getGameWidth(), this.game.getGameHeight());

		for (Floor floor : this.floors) {
			floor.render(render);
		}
	}

}
