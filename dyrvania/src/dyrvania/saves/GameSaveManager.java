package dyrvania.saves;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import dyrvania.Game;
import dyrvania.scenes.Scene;
import dyrvania.scenes.levels.Level02;
import dyrvania.scenes.levels.Level03;
import dyrvania.scenes.levels.Level06;
import dyrvania.scenes.levels.Tutorial;
import dyrvania.scenes.objects.Teleport;

public class GameSaveManager {

	private static GameSave save;

	public static boolean saveIsEmpty() {
		return GameSaveManager.save == null || GameSaveManager.save.getLastScene() == null;
	}

	public static GameSave getSave() {
		return GameSaveManager.save;
	}

	public static void setSave(GameSave save) {
		GameSaveManager.save = save;
	}

	public static Scene getScene(Game game) {
		Scene scene;

		switch (GameSaveManager.save.getLastScene()) {
		case "level-02":
			scene = new Level02(game, new Teleport(0, 0, 0xFFFF006C, false));
			break;
		case "level-03":
			scene = new Level03(game, new Teleport(0, 0, 0xFF0000FF, false));
			break;
		case "level-06":
			scene = new Level06(game, new Teleport(0, 0, 0xFF0000FF, false));
			break;
		default:
			scene = new Tutorial(game, new Teleport(0, 0, 0xFF0000FF, false));
		}

		return scene;
	}

	public static void saveData() {
		try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream("data/save.obj"))) {
			out.writeObject(GameSaveManager.save);
		} catch (Exception e) {
			// Code
		}
	}

	public static void loadData() {
		try (ObjectInput in = new ObjectInputStream(new FileInputStream("data/save.obj"))) {
			GameSaveManager.save = (GameSave) in.readObject();
		} catch (Exception e) {
			// Code
		}
	}

}
