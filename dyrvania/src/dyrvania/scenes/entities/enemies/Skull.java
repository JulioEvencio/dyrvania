package dyrvania.scenes.entities.enemies;

import dyrvania.generics.GameDamage;
import dyrvania.generics.GameDamage.GameDamageType;
import dyrvania.generics.GameRect;
import dyrvania.generics.GameRectEntity;
import dyrvania.generics.GameUtil;
import dyrvania.managers.GameManagerSpriteDeath;
import dyrvania.managers.entities.enemies.GameManagerSpriteSkull;
import dyrvania.scenes.Scene;

public class Skull extends Enemy {

	public Skull(Scene scene, int x, int y) {
		super(scene, x, y, 16, 18, 1, new GameDamage(3, GameDamageType.FIRE), 1f);

		super.speedY = (GameUtil.generateRandomNumber(0, 1) == 1) ? 1 : -1;
	}

	@Override
	public void loadSprites() {
		GameRect spriteRect = new GameRect(0, 0, 96 / 3, 112 / 3);

		super.spriteRunRight = GameManagerSpriteSkull.createSpriteRunRight(spriteRect);
		super.spriteRunLeft = GameManagerSpriteSkull.createSpriteRunLeft(spriteRect);

		super.spriteDeath = GameManagerSpriteDeath.createSpriteDeath(spriteRect);
	}

	@Override
	protected void applyGravity() {
		if (super.scene.isFree(new GameRectEntity(super.rect.getX(), super.rect.getY() + super.speedY, super.rect.getWidth(), super.rect.getHeight()).getRect())) {
			super.rect.setY(super.rect.getY() + super.speedY);
		} else {
			super.speedY *= -1;
		}
	}

	@Override
	protected void toMove() {
		float velX;

		if (super.isDirRight) {
			velX = 0.5f;
		} else {
			velX = -0.5f;
		}

		for (float i = 0f; i <= super.speedX; i += 0.5f) {
			if (super.scene.isFree(new GameRectEntity(super.rect.getX() + velX, super.rect.getY(), super.rect.getWidth(), super.rect.getHeight()).getRect())) {
				super.rect.setX(super.rect.getX() + velX);
			} else {
				super.isDirRight = !super.isDirRight;
				break;
			}
		}
	}

	@Override
	protected void setSpritePosition() {
		if (super.isDirRight) {
			super.currentSprite.setPosition(super.rect.getRect().getX() - 8, super.rect.getRect().getY() - 15);
		} else {
			super.currentSprite.setPosition(super.rect.getRect().getX() - 8, super.rect.getRect().getY() - 15);
		}
	}

}
