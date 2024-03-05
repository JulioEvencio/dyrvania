package dyrvania.scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import dyrvania.Game;
import dyrvania.generics.Camera;
import dyrvania.generics.GameRect;
import dyrvania.scenes.entities.player.Player;
import dyrvania.scenes.tiles.Floor;

public abstract class Scene {

	private final Game game;

	private final double gravity;

	private final int sizeBaseTiles;

	protected char[][] map;

	private final Player player;

	private final List<Floor> floors;

	public Scene(Game game) {
		this.game = game;

		this.gravity = 0.5;

		this.sizeBaseTiles = 32;

		this.buildGame();

		this.player = new Player(this);
		this.player.setPosition(250, 0);

		this.floors = new ArrayList<>();

		for (int i = 1; i < this.game.getGameWidth() / this.sizeBaseTiles - 1; i++) {
			this.floors.add(new Floor(this.sizeBaseTiles * i, 300, this.sizeBaseTiles, this.sizeBaseTiles));
		}
	}

	public double getGravity() {
		return this.gravity;
	}

	public int getSizeBaseTiles() {
		return this.sizeBaseTiles;
	}

	protected abstract void initializeLevel();

	private void buildGame() {
		this.initializeLevel();
	}

	public boolean isFree(GameRect rect) {
		for (Floor floor : this.floors) {
			if (floor.getRect().isColliding(rect)) {
				return false;
			}
		}

		return true;
	}

	private boolean canRender(GameRect rect) {
		GameRect areaCamera = new GameRect(Camera.x, Camera.y, this.game.getGameWidth(), this.game.getGameHeight());

		return areaCamera.isColliding(rect);
	}

	public void tick() {
		this.player.tick();
	}

	public void render(Graphics render) {
		render.setColor(Color.BLACK);
		render.fillRect(0, 0, this.game.getGameWidth(), this.game.getGameHeight());

		for (Floor floor : this.floors) {
			if (this.canRender(floor.getRect())) {
				floor.render(render);
			}
		}

		this.player.render(render);
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.player.moveRight();
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			this.player.moveLeft();
		}

		if (e.getKeyCode() == KeyEvent.VK_Z) {
			this.player.toJump();
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.player.stopRight();
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			this.player.stopLeft();
		}

		if (e.getKeyCode() == KeyEvent.VK_Z) {
			this.player.keyJumpReleased();
		}
	}

}
