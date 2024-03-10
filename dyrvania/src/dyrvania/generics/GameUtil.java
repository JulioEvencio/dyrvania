package dyrvania.generics;

import java.util.Random;

public class GameUtil {

	private final static Random random;

	static {
		random = new Random();
	}

	public static int generateRandomNumber(int x, int y) {
		return GameUtil.random.nextInt((y - x) + 1) + x;
	}

}
