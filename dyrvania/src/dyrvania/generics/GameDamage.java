package dyrvania.generics;

public class GameDamage {

	private int damage;
	private final GameDamageType type;

	public enum GameDamageType {
		NORMAL, FIRE, POISON;
	}

	public GameDamage(int damage, GameDamageType type) {
		this.damage = damage;
		this.type = type;
	}

	public int getDamage() {
		return this.damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public GameDamageType getType() {
		return this.type;
	}

}
