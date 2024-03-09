package dyrvania.generics;

public class GameDamage {

	private final int damage;
	private final GameDamageType type;

	public enum GameDamageType {
		NORMAL, FIRE, POISON;
	}

	public GameDamage(int damage, GameDamageType type) {
		this.damage = damage;
		this.type = type;
	}

	public int getDamage() {
		return damage;
	}

	public GameDamageType getType() {
		return type;
	}

}
