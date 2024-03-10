package dyrvania.scenes.entities.enemies;

import dyrvania.generics.GameDamage;
import dyrvania.generics.GameDamage.GameDamageType;
import dyrvania.generics.GameRect;
import dyrvania.managers.GameManagerSpriteDeath;
import dyrvania.managers.entities.enemies.GameManagerSpriteThing;
import dyrvania.scenes.Scene;

public class Thing extends Enemy {

	public Thing(Scene scene, int x, int y) {
		super(scene, x, y, 15, 35, 10, new GameDamage(1, GameDamageType.POISON), 0.1f);
	}

	@Override
	public void loadSprites() {
		GameRect spriteRect = new GameRect(0, 0, 33, 45);

		super.spriteRunRight = GameManagerSpriteThing.createSpriteRunRight(spriteRect);
		super.spriteRunLeft = GameManagerSpriteThing.createSpriteRunLeft(spriteRect);

		super.spriteDeath = GameManagerSpriteDeath.createSpriteDeath(spriteRect);
	}

	@Override
	protected void setSpritePosition() {
		if (super.isDirRight) {
			super.currentSprite.setPosition(super.rect.getRect().getX() - 8, super.rect.getRect().getY() - 10);
		} else {
			super.currentSprite.setPosition(super.rect.getRect().getX() - 9, super.rect.getRect().getY() - 10);
		}
	}

}
