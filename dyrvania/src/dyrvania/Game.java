package dyrvania;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import dyrvania.generics.GameColors;
import dyrvania.generics.GameStatus;
import dyrvania.managers.GameManagerAudio;
import dyrvania.resources.GameAudio;
import dyrvania.resources.GameFont;
import dyrvania.saves.GameSaveManager;
import dyrvania.scenes.Scene;
import dyrvania.scenes.levels.Tutorial;
import dyrvania.screens.ConfirmMainMenu;
import dyrvania.screens.ConfirmNewGame;
import dyrvania.screens.Credits;
import dyrvania.screens.Exit;
import dyrvania.screens.GameOver;
import dyrvania.screens.Lore;
import dyrvania.screens.MainMenu;
import dyrvania.screens.NoData;
import dyrvania.screens.OpeningScreen;
import dyrvania.screens.Pause;
import dyrvania.screens.Screen;
import dyrvania.screens.SelectLanguage;
import dyrvania.screens.Transition;
import dyrvania.strings.StringError;
import dyrvania.strings.StringGame;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener {

	private static final long serialVersionUID = 1L;

	private final String VERSION;

	private final JFrame frame;

	private final int WIDTH;
	private final int HEIGHT;

	private final float SCREEN_RATIO;

	private int windowWidth;
	private int windowHeight;

	private int rendererX;
	private int rendererY;
	private int rendererWidth;
	private int rendererHeight;

	private final BufferedImage renderer;

	private boolean isFullscreen;
	private boolean updateFullscreen;

	private int fps;
	private boolean showFPS;

	private GameStatus gameStatus;
	private GameStatus lastGameStatus;

	private final Transition transition;
	private final OpeningScreen openingScreen;
	private final GameOver gameOver;

	private Lore lore;
	private final List<Screen> screens;

	private Scene scene;

	private boolean enableAudio;
	private GameAudio audioNow;

	public Game() {
		GameSaveManager.loadData();

		this.addKeyListener(this);
		this.addMouseListener(this);

		this.VERSION = "1.5";

		this.WIDTH = 800;
		this.HEIGHT = 450;

		this.SCREEN_RATIO = (float) this.WIDTH / (float) this.HEIGHT;

		this.windowWidth = this.WIDTH;
		this.windowHeight = this.HEIGHT;

		this.rendererX = 0;
		this.rendererY = 0;
		this.rendererWidth = WIDTH;
		this.rendererHeight = HEIGHT;

		this.setPreferredSize(new Dimension(this.windowWidth, this.windowHeight));

		this.frame = new JFrame();

		this.frame.setTitle(StringGame.TITLE.getValue());
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.add(this);
		this.frame.setResizable(false);
		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);

		try {
			this.frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ImageIO.read(getClass().getResource("/sprites/cursor.png")), new Point(0, 0), "cursor"));
			this.frame.setIconImage(ImageIO.read(Game.class.getResource("/sprites/icon.png")));
		} catch (Exception e) {
			Main.exitWithError(StringError.ERROR_LOADING_FILES.getValue());
		}

		this.renderer = new BufferedImage(this.WIDTH, this.HEIGHT, BufferedImage.TYPE_INT_RGB);

		this.isFullscreen = false;
		this.updateFullscreen = false;

		this.fps = 0;
		this.showFPS = false;

		this.transition = new Transition(this, GameStatus.OPENING_SCREEN);
		this.openingScreen = new OpeningScreen(this);
		this.gameOver = new GameOver(this);

		this.lore = null;
		this.scene = null;
		this.screens = new ArrayList<>();

		this.enableAudio = true;

		this.setAudio(GameManagerAudio.getAudioMenu());
		this.initializeScreen();
		this.initializeScene(new Tutorial(this, null));
		this.setGameStatus(GameStatus.OPENING_SCREEN);
	}

	public String getVersion() {
		return this.VERSION;
	}

	public int getGameWidth() {
		return this.WIDTH;
	}

	public int getGameHeight() {
		return this.HEIGHT;
	}

	public int getRendererX() {
		return this.rendererX;
	}

	public int getRendererY() {
		return this.rendererY;
	}

	public int getRendererWidth() {
		return this.rendererWidth;
	}

	public int getRendererHeight() {
		return this.rendererHeight;
	}

	public Graphics getRender() {
		return this.renderer.getGraphics();
	}

	public boolean isFullscreen() {
		return this.isFullscreen;
	}

	public GameStatus getLastGameStatus() {
		return this.lastGameStatus;
	}

	public void setTransition(GameStatus gameStatus) {
		this.transition.setNextGameStatus(gameStatus);
		this.setGameStatus(GameStatus.TRANSITION);
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.lastGameStatus = this.gameStatus;
		this.gameStatus = gameStatus;

		if (this.gameStatus == GameStatus.RUN || this.gameStatus == GameStatus.PAUSE || (this.lastGameStatus == GameStatus.PAUSE && this.gameStatus == GameStatus.EXIT) || this.gameStatus == GameStatus.TRANSITION || this.gameStatus == GameStatus.CONFIRM_MAIN_MENU) {
			if (!GameSaveManager.getSave().isBossDefeated() && this.scene.currentLevelString().equals("boss-01")) {
				this.setAudio(GameManagerAudio.getAudioBoss());
			} else {
				this.setAudio(GameManagerAudio.getAudioGame());
			}
		} else if (this.gameStatus == GameStatus.LORE) {
			this.setAudio(GameManagerAudio.getAudioLore());
		} else if (this.gameStatus == GameStatus.GAME_OVER) {
			this.audioNow.stop();
			this.setAudio(null);

			GameManagerAudio.getAudioGameOver().play();
		} else {
			this.setAudio(GameManagerAudio.getAudioMenu());
		}
	}

	public void initializeScreen() {
		this.screens.clear();

		this.lore = new Lore(this);

		this.screens.add(new Pause(this));
		this.screens.add(new MainMenu(this));
		this.screens.add(new ConfirmMainMenu(this));
		this.screens.add(new dyrvania.screens.Options(this));
		this.screens.add(new ConfirmNewGame(this));
		this.screens.add(new NoData(this));
		this.screens.add(new Credits(this));
		this.screens.add(new Exit(this));
		this.screens.add(new SelectLanguage(this));
	}

	public void initializeScene(Scene scene) {
		this.scene = scene;
	}

	private void setAudio(GameAudio audio) {
		if (this.audioNow != audio) {
			if (this.audioNow != null) {
				this.audioNow.stop();
			}

			this.audioNow = audio;
		}
	}

	private void toggleFullscreen() {
		if (this.updateFullscreen) {
			this.frame.dispose();

			if (this.isFullscreen) {
				this.windowWidth = this.WIDTH;
				this.windowHeight = this.HEIGHT;

				this.frame.setUndecorated(false);
			} else {
				this.windowWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
				this.windowHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

				this.frame.setUndecorated(true);
			}

			this.setPreferredSize(new Dimension(this.windowWidth, this.windowHeight));
			this.setScreenRatio();

			this.frame.pack();
			this.frame.setLocationRelativeTo(null);
			this.frame.setVisible(true);

			this.requestFocus();

			this.isFullscreen = !this.isFullscreen;
			this.updateFullscreen = false;
		}
	}

	private void setScreenRatio() {
		this.rendererWidth = (int) (this.windowHeight * this.SCREEN_RATIO);

		if (this.rendererWidth > this.windowWidth) {
			this.rendererWidth = this.windowWidth;
			this.rendererHeight = (int) (this.windowWidth * this.SCREEN_RATIO);

			this.rendererX = 0;
			this.rendererY = (this.windowHeight - this.rendererHeight) / 2;
		} else {
			this.rendererHeight = this.windowHeight;

			this.rendererX = (this.windowWidth - this.rendererWidth) / 2;
			this.rendererY = 0;
		}
	}

	private void tick() {
		if (this.audioNow != null) {
			if (this.enableAudio) {
				this.audioNow.loop();
			} else {
				this.audioNow.stop();
			}
		}

		this.toggleFullscreen();

		if (this.gameStatus == GameStatus.RUN) {
			this.scene.tick();
		} else if (this.gameStatus == GameStatus.GAME_OVER) {
			this.gameOver.tick();
		} else if (this.gameStatus == GameStatus.TRANSITION) {
			this.transition.tick();
		} else if (this.gameStatus == GameStatus.LORE) {
			this.lore.tick();
		} else if (this.gameStatus == GameStatus.OPENING_SCREEN) {
			this.openingScreen.tick();
		} else {
			for (Screen screen : this.screens) {
				if (screen.getGameStatus() == this.gameStatus) {
					screen.tick();
					break;
				}
			}
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics render = this.renderer.getGraphics();

		if (this.gameStatus == GameStatus.RUN) {
			this.scene.render(render);
		} else if (this.gameStatus == GameStatus.GAME_OVER) {
			this.gameOver.render(render);
		} else if (this.gameStatus == GameStatus.TRANSITION) {
			this.transition.render(render);
		} else if (this.gameStatus == GameStatus.LORE) {
			this.lore.render(render);
		} else if (this.gameStatus == GameStatus.OPENING_SCREEN) {
			this.openingScreen.render(render);
		} else {
			for (Screen screen : this.screens) {
				if (screen.getGameStatus() == this.gameStatus) {
					screen.render(render);
					break;
				}
			}
		}

		if (this.showFPS) {
			render.setColor(GameColors.BLACK);
			render.fillRect(this.WIDTH - 200, 10, 180, 30);

			render.setColor(GameColors.WHITE);
			render.setFont(GameFont.getSmall());
			render.drawString(String.format("FPS: %d", this.fps), this.WIDTH - 180, 32);

			render.drawRect(this.WIDTH - 200, 10, 180, 30);
		}

		render.dispose();

		Graphics graphics = bs.getDrawGraphics();

		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, this.windowWidth, this.windowHeight);

		graphics.drawImage(this.renderer, this.rendererX, this.rendererY, this.rendererWidth, this.rendererHeight, null);

		bs.show();
	}

	@Override
	public void run() {
		this.requestFocus();

		long lastTime = System.nanoTime();

		double amountOfTicks = 60.0;
		double ns = 1000000000.0 / amountOfTicks;
		double delta = 0.0;

		double timer = System.currentTimeMillis();

		int frames = 0;

		while (true) {
			long now = System.nanoTime();

			delta += (now - lastTime) / ns;
			lastTime = now;

			if (delta >= 1) {
				this.tick();
				this.render();

				frames++;
				delta--;
			}

			if (System.currentTimeMillis() - timer >= 1000) {
				timer = System.currentTimeMillis();

				this.fps = frames;
				frames = 0;
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (this.gameStatus == GameStatus.RUN) {
			this.scene.keyPressed(e);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.scene.keyReleased(e);

		if (this.gameStatus == GameStatus.RUN) {
			if (e.getKeyCode() == KeyEvent.VK_P || e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				this.setGameStatus(GameStatus.PAUSE);
			}
		} else if (this.gameStatus == GameStatus.PAUSE) {
			if (e.getKeyCode() == KeyEvent.VK_P || e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				this.setGameStatus(GameStatus.RUN);
			}
		} else if (this.gameStatus == GameStatus.LORE) {
			this.lore.keyReleased(e);
		}

		if (e.getKeyCode() == KeyEvent.VK_F2) {
			this.updateFullscreen = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_F3) {
			this.showFPS = !this.showFPS;
		}

		if (e.getKeyCode() == KeyEvent.VK_F4) {
			this.enableAudio = !this.enableAudio;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Code
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Code
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Code
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Code
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (this.gameStatus == GameStatus.RUN) {
			this.scene.mousePressed(e);
		} else {
			for (Screen screen : this.screens) {
				if (screen.getGameStatus() == this.gameStatus) {
					screen.mousePressed(e);
					break;
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (this.gameStatus == GameStatus.RUN) {
			this.scene.mouseReleased(e);
		} else {
			for (Screen screen : this.screens) {
				if (screen.getGameStatus() == this.gameStatus) {
					screen.mouseReleased(e);
					break;
				}
			}
		}
	}

}
