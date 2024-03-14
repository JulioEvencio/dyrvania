package dyrvania.saves;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameSave implements Serializable {

	private static final long serialVersionUID = 1L;

	private int hp;
	private int hpMax;
	private int damage;
	private boolean isPoisoning;

	private String lastScene;

	private boolean sceneSaveRight;

	private List<String> swords;
	private List<String> lifes;

	public GameSave(int hp, int hpMax, int damage, boolean isPoisoning, String lastScene, boolean sceneSaveRight) {
		this.hp = hp;
		this.hpMax = hpMax;
		this.damage = damage;
		this.isPoisoning = isPoisoning;

		this.lastScene = lastScene;

		this.sceneSaveRight = sceneSaveRight;

		this.swords = new ArrayList<>();
		this.lifes = new ArrayList<>();
	}

	public int getHp() {
		return this.hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getHpMax() {
		return this.hpMax;
	}

	public void setHpMax(int hpMax) {
		this.hpMax = hpMax;
	}

	public int getDamage() {
		return this.damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public boolean isPoisoning() {
		return this.isPoisoning;
	}

	public void setPoisoning(boolean isPoisoning) {
		this.isPoisoning = isPoisoning;
	}

	public String getLastScene() {
		return this.lastScene;
	}

	public void setLastScene(String lastScene) {
		this.lastScene = lastScene;
	}

	public boolean isSceneSaveRight() {
		return this.sceneSaveRight;
	}

	public void setSceneSaveRight(boolean sceneSaveRight) {
		this.sceneSaveRight = sceneSaveRight;
	}

	public List<String> getSwords() {
		return this.swords;
	}

	public void setSwords(List<String> swords) {
		this.swords = swords;
	}

	public List<String> getLifes() {
		return this.lifes;
	}

	public void setLifes(List<String> lifes) {
		this.lifes = lifes;
	}

}
