package dyrvania.scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import dyrvania.Game;
import dyrvania.generics.Camera;
import dyrvania.generics.GameRect;
import dyrvania.resources.GameAudio;
import dyrvania.scenes.entities.Player;
import dyrvania.scenes.entities.enemies.Enemy;
import dyrvania.scenes.entities.enemies.Skeleton;
import dyrvania.scenes.entities.enemies.Skull;
import dyrvania.scenes.entities.enemies.Thing;
import dyrvania.scenes.objects.Sword;
import dyrvania.scenes.tiles.Floor;

public abstract class Scene {

	private final Game game;

	private final double gravity;

	private final int sizeBaseTiles;

	protected char[][] map;

	private Sword sword;

	private final Player player;

	private final List<Enemy> enemies;

	private final List<Floor> floors;

	private final GameAudio audioObject;

	public Scene(Game game) {
		this.game = game;

		this.gravity = 0.5;

		this.sizeBaseTiles = 32;

		this.buildGame();

		this.sword = new Sword();
		this.sword.setPosition(600, 270);

		this.player = new Player(this);
		this.player.setPosition(250, 50);

		this.enemies = new ArrayList<>();

		this.enemies.add(new Skull(this, 200, 50));
		this.enemies.add(new Thing(this, 400, 50));
		this.enemies.add(new Skeleton(this, 700, 50));

		this.floors = new ArrayList<>();

		for (int i = 1; i < this.game.getGameWidth() / this.sizeBaseTiles - 1; i++) {
			this.floors.add(new Floor(this.sizeBaseTiles * i, 300, this.sizeBaseTiles, this.sizeBaseTiles));
			this.floors.add(new Floor(this.sizeBaseTiles * i, 0, this.sizeBaseTiles, this.sizeBaseTiles));
		}

		for (int i = 1; i < this.game.getGameHeight() / this.sizeBaseTiles - 1; i++) {
			this.floors.add(new Floor(0, this.sizeBaseTiles * i, this.sizeBaseTiles, this.sizeBaseTiles));
			this.floors.add(new Floor(this.game.getGameHeight() + 300, this.sizeBaseTiles * i, this.sizeBaseTiles, this.sizeBaseTiles));
		}

		this.floors.add(new Floor(300, 300 - this.sizeBaseTiles, this.sizeBaseTiles, this.sizeBaseTiles));
		this.floors.add(new Floor(300, 300 - this.sizeBaseTiles * 2, this.sizeBaseTiles, this.sizeBaseTiles));
		this.floors.add(new Floor(400, 300 - this.sizeBaseTiles, this.sizeBaseTiles, this.sizeBaseTiles));
		this.floors.add(new Floor(500, 300 - this.sizeBaseTiles, this.sizeBaseTiles, this.sizeBaseTiles));
		this.floors.add(new Floor(200, 200 - this.sizeBaseTiles, this.sizeBaseTiles, this.sizeBaseTiles));
		this.floors.add(new Floor(100, 300 - this.sizeBaseTiles, this.sizeBaseTiles, this.sizeBaseTiles));
		this.floors.add(new Floor(100, 300 - this.sizeBaseTiles * 2, this.sizeBaseTiles, this.sizeBaseTiles));
		this.floors.add(new Floor(100, 300 - this.sizeBaseTiles * 3, this.sizeBaseTiles, this.sizeBaseTiles));

		this.floors.add(new Floor(300 + 32 * 2, 200 - this.sizeBaseTiles, this.sizeBaseTiles, this.sizeBaseTiles));
		this.floors.add(new Floor(300 + 32 * 3, 200 - this.sizeBaseTiles, this.sizeBaseTiles, this.sizeBaseTiles));
		this.floors.add(new Floor(300 + 32 * 4, 200 - this.sizeBaseTiles, this.sizeBaseTiles, this.sizeBaseTiles));
		this.floors.add(new Floor(300 + 32 * 5, 200 - this.sizeBaseTiles, this.sizeBaseTiles, this.sizeBaseTiles));
		this.floors.add(new Floor(300 + 32 * 6, 200 - this.sizeBaseTiles, this.sizeBaseTiles, this.sizeBaseTiles));
		this.floors.add(new Floor(300 + 32 * 7, 200 - this.sizeBaseTiles, this.sizeBaseTiles, this.sizeBaseTiles));

		this.audioObject = new GameAudio("/audios/objects.wav");
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
		List<Enemy> enemiesRemove = new ArrayList<>();

		this.player.tick();

		for (Enemy enemy : this.enemies) {
			if (this.player.isAttacking() && this.player.getAreaAttack().isColliding(enemy.getRect())) {
				enemy.takeDamage(this.player.dealDamage());
			}

			enemy.tick();

			if (enemy.isDead()) {
				enemiesRemove.add(enemy);
			}

			if (this.player.getRect().isColliding(enemy.getRect())) {
				this.player.takeDamage(enemy.dealDamage());
			}

			if (this.player.finishedAnimation()) {
				enemy.resetShield();
			}
		}

		this.enemies.removeAll(enemiesRemove);

		if (this.sword != null && this.player.getRect().isColliding(this.sword.getRect())) {
			this.audioObject.stop();
			this.audioObject.play();

			this.player.increaseAttack();
			this.sword = null;
		}
	}

	public void render(Graphics render) {
		render.setColor(Color.BLACK);
		render.fillRect(0, 0, this.game.getGameWidth(), this.game.getGameHeight());

		for (Floor floor : this.floors) {
			if (this.canRender(floor.getRect())) {
				floor.render(render);
			}
		}

		if (this.sword != null && this.canRender(this.sword.getRect())) {
			this.sword.render(render);
		}

		for (Enemy enemy : this.enemies) {
			if (this.canRender(enemy.getRect())) {
				enemy.render(render);
			}
		}

		this.player.render(render);
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			this.player.moveRight();
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			this.player.moveLeft();
		}

		if (e.getKeyCode() == KeyEvent.VK_Z || e.getKeyCode() == KeyEvent.VK_J || e.getKeyCode() == KeyEvent.VK_SPACE) {
			this.player.toJump();
		}

		if (e.getKeyCode() == KeyEvent.VK_X || e.getKeyCode() == KeyEvent.VK_K) {
			this.player.toAttack();
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			this.player.stopRight();
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			this.player.stopLeft();
		}

		if (e.getKeyCode() == KeyEvent.VK_Z || e.getKeyCode() == KeyEvent.VK_J || e.getKeyCode() == KeyEvent.VK_SPACE) {
			this.player.keyJumpReleased();
		}

		if (e.getKeyCode() == KeyEvent.VK_X || e.getKeyCode() == KeyEvent.VK_K) {
			this.player.keyAttackReleased();
		}
	}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			this.player.updateDir(false);
			this.player.toAttack();
		}

		if (e.getButton() == MouseEvent.BUTTON3) {
			this.player.updateDir(true);
			this.player.toAttack();
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			this.player.keyAttackReleased();
		}

		if (e.getButton() == MouseEvent.BUTTON3) {
			this.player.keyAttackReleased();
		}
	}

}
