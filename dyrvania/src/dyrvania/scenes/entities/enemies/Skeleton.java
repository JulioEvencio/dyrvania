package dyrvania.scenes.entities.enemies;

import dyrvania.generics.GameDamage;
import dyrvania.generics.GameDamage.GameDamageType;
import dyrvania.generics.GameRect;
import dyrvania.generics.GameSpriteAnimation;
import dyrvania.managers.GameManagerSpriteDeath;
import dyrvania.managers.entities.enemies.GameManagerSpriteSkeleton;
import dyrvania.scenes.Scene;

public class Skeleton extends Enemy {

	private GameSpriteAnimation spriteSpanwRight;
	private GameSpriteAnimation spriteSpanwLeft;

	public Skeleton(Scene scene, int x, int y) {
		super(scene, x, y, 10, 40, 3, new GameDamage(1, GameDamageType.NORMAL), 0.5f);
	}

	@Override
	public void loadSprites() {
		GameRect spriteRect = new GameRect(0, 0, 44, 52);

		super.spriteRunRight = GameManagerSpriteSkeleton.createSpriteRunRight(spriteRect);
		super.spriteRunLeft = GameManagerSpriteSkeleton.createSpriteRunLeft(spriteRect);

		super.spriteDeath = GameManagerSpriteDeath.createSpriteDeath(spriteRect);

		this.spriteSpanwRight = GameManagerSpriteSkeleton.createSpriteSpawnRight(spriteRect);
		this.spriteSpanwLeft = GameManagerSpriteSkeleton.createSpriteSpawnLeft(spriteRect);

		if (super.isDirRight) {
			this.currentSprite = this.spriteSpanwRight;
		} else {
			this.currentSprite = this.spriteSpanwLeft;
		}
	}

	@Override
	protected void setSpritePosition() {
		if (super.isDirRight) {
			super.currentSprite.setPosition(super.rect.getRect().getX() - 14, super.rect.getRect().getY() - 12);
		} else {
			super.currentSprite.setPosition(super.rect.getRect().getX() - 20, super.rect.getRect().getY() - 12);
		}
	}

	@Override
	public void tick() {
		if (this.currentSprite == this.spriteSpanwRight || this.currentSprite == this.spriteSpanwLeft) {
			this.currentSprite.tick();

			if (this.currentSprite.finishedAnimation()) {
				this.currentSprite.reset();

				if (super.isDirRight) {
					this.currentSprite = this.spriteRunRight;
				} else {
					this.currentSprite = this.spriteRunLeft;
				}
			}

			return;
		}

		super.tick();
	}

}
