package dyrvania.generics;

import java.util.Random;

public class GameUtil {

	public static int generateRandomNumber(int x, int y) {
		return new Random().nextInt((y - x) + 1) + x;
	}

}
