package dyrvania.saves;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class GameSaveManager {

	private static GameSave save;

	public static GameSave getSave() {
		return GameSaveManager.save;
	}

	public static void setSave(GameSave save) {
		GameSaveManager.save = save;
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
			GameSaveManager.saveData();
		}
	}

}
