package dyrvania.scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import dyrvania.Game;
import dyrvania.generics.Camera;
import dyrvania.generics.GameRect;
import dyrvania.generics.GameStatus;
import dyrvania.managers.GameManagerAudio;
import dyrvania.saves.GameSave;
import dyrvania.saves.GameSaveManager;
import dyrvania.scenes.entities.Player;
import dyrvania.scenes.entities.enemies.Enemy;
import dyrvania.scenes.entities.enemies.Skeleton;
import dyrvania.scenes.entities.enemies.Skull;
import dyrvania.scenes.entities.enemies.Thing;
import dyrvania.scenes.objects.Life;
import dyrvania.scenes.objects.Sword;
import dyrvania.scenes.objects.Teleport;
import dyrvania.scenes.tiles.Floor;
import dyrvania.scenes.tiles.Wall;

public abstract class Scene {

	private final Game game;

	private final float gravity;

	private final int sizeBaseTiles;

	private int width;
	private int height;

	private Teleport teleportCurrent;
	private final List<Teleport> teleports;

	private Life life;
	private Sword sword;

	private final Player player;

	private final List<Enemy> enemies;

	private final List<Floor> floors;
	private final List<Wall> walls;

	public Scene(Game game, Teleport teleport) {
		this.game = game;

		this.gravity = 0.5f;

		this.sizeBaseTiles = 32;

		if (teleport == null) {
			this.teleportCurrent = new Teleport(this, 0, 0, 0xFF0000FF, false);
		} else {
			this.teleportCurrent = teleport;
		}

		this.teleports = new ArrayList<>();

		if (GameSaveManager.getSave() == null) {
			GameSaveManager.setSave(new GameSave(10, 10, 1, false, true, null, false));
		}

		this.player = new Player(this);

		this.player.setHp(GameSaveManager.getSave().getHp());
		this.player.setHpMax(GameSaveManager.getSave().getHpMax());
		this.player.setDamage(GameSaveManager.getSave().getDamage());
		this.player.setPoisoning(GameSaveManager.getSave().isPoisoning());
		this.player.setDir(GameSaveManager.getSave().isDirRight());

		this.enemies = new ArrayList<>();
		this.floors = new ArrayList<>();
		this.walls = new ArrayList<>();

		this.buildGame();
	}

	public Game getGame() {
		return this.game;
	}

	protected Teleport getTeleportCurrent() {
		return this.teleportCurrent;
	}

	public double getGravity() {
		return this.gravity;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public int getSizeBaseTiles() {
		return this.sizeBaseTiles;
	}

	protected abstract String currentLevelString();

	protected abstract BufferedImage loadLevel();

	protected abstract Scene nextScene();

	private void buildGame() {
		BufferedImage map = this.loadLevel();

		int[] pixels = new int[map.getWidth() * map.getHeight()];

		this.width = map.getWidth();
		this.height = map.getHeight();

		map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());

		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				int currentPixel = pixels[x + (y * map.getWidth())];

				switch (currentPixel) {
					case 0xFFFFFFFF:
						this.walls.add(new Wall(x * this.sizeBaseTiles, y * this.sizeBaseTiles, this.sizeBaseTiles, this.sizeBaseTiles));
						break;
					case 0xFFFF00FF:
						this.floors.add(new Floor(x * this.sizeBaseTiles, y * this.sizeBaseTiles, this.sizeBaseTiles, this.sizeBaseTiles));
						break;
					case 0xFFFF6400:
						this.enemies.add(new Skeleton(this, x * this.sizeBaseTiles, y * this.sizeBaseTiles - 8));
						break;
					case 0xFFFFFF00:
						this.enemies.add(new Skull(this, x * this.sizeBaseTiles, y * this.sizeBaseTiles));
						break;
					case 0xFF00FF00:
						this.enemies.add(new Thing(this, x * this.sizeBaseTiles, y * this.sizeBaseTiles - 3));
						break;
					case 0xFF070732:
						this.teleports.add(new Teleport(this, x * this.sizeBaseTiles, y * this.sizeBaseTiles, 0xFF0000FF, false));
						break;
					case 0xFFFA81B5:
						this.teleports.add(new Teleport(this, x * this.sizeBaseTiles, y * this.sizeBaseTiles, 0xFFFF006c, false));
						break;
					case 0xFF7D4DD9:
						this.teleports.add(new Teleport(this, x * this.sizeBaseTiles, y * this.sizeBaseTiles, 0xFF5800FF, false));
						break;
					case 0xFF358A66:
						this.teleports.add(new Teleport(this, x * this.sizeBaseTiles, y * this.sizeBaseTiles, 0xFF00FF93, false));
						break;
					case 0xFF518B1D:
						this.teleports.add(new Teleport(this, x * this.sizeBaseTiles, y * this.sizeBaseTiles, 0xFF7AFF00, false));
						break;
					case 0xFFC7B04F:
						this.teleports.add(new Teleport(this, x * this.sizeBaseTiles, y * this.sizeBaseTiles, 0xFF8F7300, false));
						break;
					case 0xFF7B4131:
						this.teleports.add(new Teleport(this, x * this.sizeBaseTiles, y * this.sizeBaseTiles, 0xFFFF3900, false));
						break;
					case 0xFF0C252C:
						this.teleports.add(new Teleport(this, x * this.sizeBaseTiles, y * this.sizeBaseTiles, 0xFF02495D, false));
						break;
					case 0xFF00FFFF:
						if (!GameSaveManager.getSave().getSwords().contains(this.currentLevelString())) {
							this.sword = new Sword();
							this.sword.setPosition(x * this.sizeBaseTiles, y * this.sizeBaseTiles);
						}
						break;
					case 0xFFFF0000:
						if (!GameSaveManager.getSave().getLifes().contains(this.currentLevelString())) {
							this.life = new Life();
							this.life.setPosition(x * this.sizeBaseTiles, y * this.sizeBaseTiles);
						}
						break;
					default:
						if (currentPixel == this.teleportCurrent.getColor()) {
							this.player.setPosition(x * this.sizeBaseTiles, y * this.sizeBaseTiles - 12);
						}

						if (this.teleportCurrent.getColor() == 0xFFFF3900) {
							this.player.toJumpSpecial();
						}
				}
			}
		}
	}

	public boolean isFree(GameRect rect) {
		for (Floor floor : this.floors) {
			if (floor.getRect().isColliding(rect)) {
				return false;
			}
		}

		for (Wall wall : this.walls) {
			if (wall.getRect().isColliding(rect)) {
				return false;
			}
		}

		return true;
	}

	private boolean canRender(GameRect rect) {
		return new GameRect(Camera.x, Camera.y, this.game.getGameWidth(), this.game.getGameHeight()).isColliding(rect);
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

		if (this.life != null && this.player.getRect().isColliding(this.life.getRect())) {
			GameManagerAudio.getAudioObject().play();

			this.player.increaseHp();
			this.player.toHeal();
			this.life = null;

			GameSaveManager.getSave().setHpMax(this.player.getHpMax());
			GameSaveManager.getSave().getLifes().add(this.currentLevelString());
		}

		if (this.sword != null && this.player.getRect().isColliding(this.sword.getRect())) {
			GameManagerAudio.getAudioObject().play();

			this.player.increaseAttack();
			this.sword = null;

			GameSaveManager.getSave().setDamage(this.player.getDamage());
			GameSaveManager.getSave().getSwords().add(this.currentLevelString());
		}

		for (Teleport teleport : this.teleports) {
			if (teleport.getRect().isColliding(this.player.getRect())) {
				this.teleportCurrent = teleport;
				break;
			}
		}

		GameSaveManager.getSave().setHp(this.player.getHp());
		GameSaveManager.getSave().setPoisoning(this.player.isPoisoning());
		GameSaveManager.getSave().setIsDirRight(this.player.isDirRight());

		if (!this.canRender(this.player.getRect())) {
			this.game.initializeScene(this.nextScene());
			this.game.setTransition(GameStatus.RUN);
		}
	}

	public void render(Graphics render) {
		render.setColor(Color.BLACK);
		render.fillRect(0, 0, this.game.getGameWidth(), this.game.getGameHeight());

		for (Wall wall : this.walls) {
			if (this.canRender(wall.getRect())) {
				wall.render(render);
			}
		}

		for (Floor floor : this.floors) {
			if (this.canRender(floor.getRect())) {
				floor.render(render);
			}
		}

		if (this.life != null && this.canRender(this.life.getRect())) {
			this.life.render(render);
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
			this.player.setDir(false);
			this.player.toAttack();
		}

		if (e.getButton() == MouseEvent.BUTTON3) {
			this.player.setDir(true);
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
