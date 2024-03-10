package dyrvania.scenes.entities.enemies;

import dyrvania.generics.GameDamage;
import dyrvania.generics.GameDamage.GameDamageType;
import dyrvania.generics.GameRect;
import dyrvania.managers.GameManagerSpriteDeath;
import dyrvania.managers.entities.enemies.GameManagerSpriteSkeleton;
import dyrvania.scenes.Scene;

public class Skeleton extends Enemy {

	public Skeleton(Scene scene, int x, int y) {
		super(scene, x, y, 10, 40, 3, new GameDamage(1, GameDamageType.NORMAL), 0.5f);
	}

	@Override
	public void loadSprites() {
		GameRect spriteRect = new GameRect(0, 0, 44, 52);

		super.spriteRunRight = GameManagerSpriteSkeleton.createSpriteRunRight(spriteRect);
		super.spriteRunLeft = GameManagerSpriteSkeleton.createSpriteRunLeft(spriteRect);

		super.spriteDeath = GameManagerSpriteDeath.createSpriteDeath(spriteRect);
	}

	@Override
	protected void setSpritePosition() {
		if (super.isDirRight) {
			super.currentSprite.setPosition(super.rect.getRect().getX() - 14, super.rect.getRect().getY() - 12);
		} else {
			super.currentSprite.setPosition(super.rect.getRect().getX() - 20, super.rect.getRect().getY() - 12);
		}
	}

}
