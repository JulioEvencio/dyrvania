package dyrvania.saves;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameSave implements Serializable {

	private static final long serialVersionUID = 1L;

	private int hpMax;
	private int damage;
	private boolean isPoisoning;
	private boolean isDirRight;

	private boolean bossDefeated;

	private String lastScene;

	private boolean sceneSaveRight;

	private List<String> swords;
	private List<String> lifes;

	public GameSave(int hpMax, int damage, boolean isPoisoning, boolean isDirRight, String lastScene, boolean sceneSaveRight) {
		this.hpMax = hpMax;
		this.damage = damage;
		this.isPoisoning = isPoisoning;
		this.isDirRight = isDirRight;

		this.bossDefeated = false;

		this.lastScene = lastScene;

		this.sceneSaveRight = sceneSaveRight;

		this.swords = new ArrayList<>();
		this.lifes = new ArrayList<>();
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

	public boolean isDirRight() {
		return this.isDirRight;
	}

	public void setIsDirRight(boolean isDirRight) {
		this.isDirRight = isDirRight;
	}

	public boolean isBossDefeated() {
		return this.bossDefeated;
	}

	public void setBossDefeated(boolean bossDefeated) {
		this.bossDefeated = bossDefeated;
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
