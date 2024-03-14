package dyrvania.scenes.entities;

import java.awt.Graphics;
import java.time.LocalDateTime;

import dyrvania.generics.Camera;
import dyrvania.generics.GameColors;
import dyrvania.generics.GameDamage;
import dyrvania.generics.GameDamage.GameDamageType;
import dyrvania.generics.GameRect;
import dyrvania.generics.GameRectEntity;
import dyrvania.generics.GameSpriteAnimation;
import dyrvania.managers.GameManagerAudio;
import dyrvania.managers.entities.GameManagerSpritePlayer;
import dyrvania.resources.GameFont;
import dyrvania.scenes.Scene;

public class Player {

	private Scene scene;

	private final GameRectEntity rect;
	private final GameRect rectAttack;

	private int hp;
	private int hpMax;

	private int poisonControl;
	private boolean isPoisoning;

	protected long shieldTime;
	protected boolean shieldActive;
	protected LocalDateTime shieldDamage;

	private GameDamage damage;

	private boolean isAttacking;
	private boolean canDealDamage;

	private float speedX;
	private float speedY;

	private boolean keyRight;
	private boolean keyLeft;
	private boolean keyJump;
	private boolean keyAttack;

	private boolean isJump;
	private final float jumpHeight;
	private float jumpFrames;

	private boolean isDirRight;
	private GameSpriteAnimation currentSprite;

	private final GameSpriteAnimation spriteIdleRight;
	private final GameSpriteAnimation spriteIdleLeft;

	private final GameSpriteAnimation spriteJumpRight;
	private final GameSpriteAnimation spriteJumpLeft;

	private final GameSpriteAnimation spriteRunRight;
	private final GameSpriteAnimation spriteRunLeft;

	private final GameSpriteAnimation spriteAttackRight;
	private final GameSpriteAnimation spriteAttackLeft;

	public Player(Scene scene) {
		this.scene = scene;

		this.rect = new GameRectEntity(0, 0, 20, 44);
		this.rectAttack = new GameRect(0, 0, 31, 12);

		this.hpMax = 9;
		this.hp = this.hpMax;

		this.poisonControl = 0;
		this.isPoisoning = false;

		this.shieldTime = 2;
		this.shieldActive = false;
		this.shieldDamage = LocalDateTime.now().minusSeconds(this.shieldTime);

		this.damage = new GameDamage(0, GameDamageType.FIRE);
		this.isAttacking = false;
		this.canDealDamage = false;

		this.speedX = 3f;
		this.speedY = 0f;

		this.keyRight = false;
		this.keyLeft = false;
		this.keyJump = false;
		this.keyAttack = false;

		this.isJump = false;
		this.jumpHeight = 100f;
		this.jumpFrames = 0f;

		this.isDirRight = true;

		GameRect spriteRect = new GameRect(0, 0, 100, 59);

		this.spriteIdleRight = GameManagerSpritePlayer.createSpriteIdleRight(spriteRect);
		this.spriteIdleLeft = GameManagerSpritePlayer.createSpriteIdleLeft(spriteRect);

		this.spriteJumpRight = GameManagerSpritePlayer.createSpriteJumpRight(spriteRect);
		this.spriteJumpLeft = GameManagerSpritePlayer.createSpriteJumpLeft(spriteRect);

		this.spriteRunRight = GameManagerSpritePlayer.createSpriteRunRight(spriteRect);
		this.spriteRunLeft = GameManagerSpritePlayer.createSpriteRunLeft(spriteRect);

		this.spriteAttackRight = GameManagerSpritePlayer.createSpriteAttackRight(spriteRect);
		this.spriteAttackLeft = GameManagerSpritePlayer.createSpriteAttackLeft(spriteRect);

		this.setCurrentSprite(this.spriteIdleRight);
		this.setSpritePosition();
	}

	public int getHp() {
		return this.hp;
	}

	public int getHpMax() {
		return this.hpMax;
	}

	public int getDamage() {
		return this.damage.getDamage();
	}

	public boolean isPoisoning() {
		return this.isPoisoning;
	}

	public void setPoisoning(boolean isPoisoning) {
		this.isPoisoning = isPoisoning;
	}

	public GameRect getRect() {
		return this.rect.getRect();
	}

	public boolean isDead() {
		return this.hp <= 0;
	}

	public boolean isAttacking() {
		return this.isAttacking && this.canDealDamage;
	}

	public GameRect getAreaAttack() {
		return this.rectAttack;
	}

	public GameDamage dealDamage() {
		return this.damage;
	}

	public boolean finishedAnimation() {
		return this.currentSprite.getIndex() == 0;
	}

	public void takeDamage(GameDamage damage) {
		if (!this.shieldActive) {
			GameManagerAudio.getAudioPlayerHit().play();

			this.hp -= damage.getDamage();
			this.shieldDamage = LocalDateTime.now().plusSeconds(this.shieldTime);

			if (this.hp < 0) {
				this.hp = 0;
			}

			if (damage.getType() == GameDamageType.POISON) {
				this.isPoisoning = true;
			}
		}
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setHpMax(int hpMax) {
		this.hpMax = hpMax;
	}

	public void setDamage(int damage) {
		this.damage.setDamage(damage);
	}

	public void setPosition(int x, int y) {
		this.rect.setX(x);
		this.rect.setY(y);

		this.setSpritePosition();
	}

	public void moveRight() {
		this.keyRight = true;
	}

	public void stopRight() {
		this.keyRight = false;
	}

	public void moveLeft() {
		this.keyLeft = true;
	}

	public void stopLeft() {
		this.keyLeft = false;
	}

	public void toJump() {
		if (!this.isJump && !this.keyJump && this.isOnTheFloor()) {
			GameManagerAudio.getAudioPlayerJump().play();

			this.isJump = true;
			this.keyJump = true;
		}
	}

	public void toJumpSpecial() {
		this.isJump = true;
	}

	public void keyJumpReleased() {
		this.keyJump = false;
	}

	public void toAttack() {
		if (!this.keyAttack && !this.isAttacking && this.isOnTheFloor()) {
			GameManagerAudio.getAudioPlayerAttack().play();

			this.keyAttack = true;
			this.isAttacking = true;
		}
	}

	public void keyAttackReleased() {
		this.keyAttack = false;
	}

	public void toHeal() {
		this.isPoisoning = false;
		this.hp = this.hpMax;
	}

	public void increaseHp() {
		if (this.hpMax < 999) {
			this.hpMax++;
		}
	}

	public void increaseAttack() {
		if (this.damage.getDamage() < 99) {
			this.damage.setDamage(this.damage.getDamage() + 1);
		}
	}

	public boolean isDirRight() {
		return this.isDirRight;
	}

	public void setDir(boolean dir) {
		this.isDirRight = dir;
	}

	private void applyGravity() {
		this.speedY += this.scene.getGravity();

		if (this.speedY > 7f) {
			this.speedY = 7f;
		}

		for (float i = 0f; i <= this.speedY; i += 0.5f) {
			if (!this.isOnTheFloor()) {
				this.rect.setY(this.rect.getY() + 0.5f);
			} else {
				this.speedY = 0f;
				break;
			}
		}
	}

	private void toMove() {
		if (this.keyRight && !this.isAttacking) {
			this.isDirRight = true;

			for (float i = 0f; i <= this.speedX; i += 0.5f) {
				if (this.scene.isFree(new GameRectEntity(this.rect.getX() + 0.5f, this.rect.getY(), this.rect.getWidth(), this.rect.getHeight()).getRect())) {
					this.rect.setX(this.rect.getX() + 0.5f);

					if (!this.isJump && !this.isOnTheFloor()) {
						this.rect.setY(this.rect.getY() + 0.5f);
					}
				} else {
					break;
				}
			}
		}

		if (this.keyLeft && !this.isAttacking) {
			this.isDirRight = false;

			for (float i = 0f; i <= this.speedX; i += 0.5f) {
				if (this.scene.isFree(new GameRectEntity(this.rect.getX() - 0.5f, this.rect.getY(), this.rect.getWidth(), this.rect.getHeight()).getRect())) {
					this.rect.setX(this.rect.getX() - 0.5f);

					if (!this.isJump && !this.isOnTheFloor()) {
						this.rect.setY(this.rect.getY() + 0.5f);
					}
				} else {
					break;
				}
			}
		}
	}

	private void jump() {
		if (this.jumpFrames < 10f) {
			this.speedY = 8f;
		} else if (this.jumpFrames < 20f) {
			this.speedY = 7f;
		} else if (this.jumpFrames < 30f) {
			this.speedY = 6f;
		} else if (this.jumpFrames < 40f) {
			this.speedY = 5f;
		} else if (this.jumpFrames < 50f) {
			this.speedY = 4f;
		} else {
			this.speedY = 3f;
		}

		for (float i = 0f; i <= this.speedY; i += 0.5f) {
			if (this.jumpFrames < this.jumpHeight && this.scene.isFree(new GameRectEntity(this.rect.getX(), this.rect.getY() - 0.5f, this.rect.getWidth(), this.rect.getHeight()).getRect())) {
				this.rect.setY(this.rect.getY() - 0.5f);
				this.jumpFrames += 0.5f;
			} else {
				this.speedY = 0f;
				this.jumpFrames = 0f;
				this.isJump = false;
				break;
			}
		}
	}

	private boolean isOnTheFloor() {
		return !this.scene.isFree(new GameRectEntity(this.rect.getX(), this.rect.getY() + 0.5f, this.rect.getWidth(), this.rect.getHeight()).getRect());
	}

	private void setSpritePosition() {
		if (this.isDirRight) {
			this.currentSprite.setPosition(this.rect.getRect().getX() - 45, this.rect.getRect().getY() - 15);
		} else {
			this.currentSprite.setPosition(this.rect.getRect().getX() - 35, this.rect.getRect().getY() - 15);
		}
	}

	private void setCurrentSprite(GameSpriteAnimation newSprite) {
		if (this.currentSprite != newSprite) {
			if (this.currentSprite != null) {
				this.currentSprite.reset();
			}

			this.currentSprite = newSprite;
		}
	}

	private void setCamera() {
		Camera.x = Camera.clamp(this.rect.getRect().getX() - (this.scene.getGame().getGameWidth() / 2), 0, this.scene.getWidth() * this.scene.getSizeBaseTiles() - this.scene.getGame().getGameWidth());
		Camera.y = Camera.clamp(this.rect.getRect().getY() - (this.scene.getGame().getGameHeight() / 2), 0, this.scene.getHeight() * this.scene.getSizeBaseTiles() - this.scene.getGame().getGameHeight());
	}

	public void tick() {
		if (this.isPoisoning && this.hp > 1) {
			this.poisonControl++;

			if (this.poisonControl == 300) {
				GameManagerAudio.getAudioPlayerHit().play();

				this.hp--;
				this.poisonControl = 0;
			}
		} else {
			this.poisonControl = 0;
			this.isPoisoning = false;
		}

		if (this.shieldDamage.isBefore(LocalDateTime.now())) {
			this.shieldActive = false;
			this.currentSprite.setAlpha(1f);
		} else {
			this.shieldActive = true;
			this.currentSprite.setAlpha(0.5f);
		}

		if (this.isJump) {
			this.jump();
		} else {
			this.applyGravity();
		}

		this.toMove();

		if (!this.isOnTheFloor()) {
			if (this.isDirRight) {
				this.setCurrentSprite(this.spriteJumpRight);
			} else {
				this.setCurrentSprite(this.spriteJumpLeft);
			}
		} else if (this.isAttacking) {
			if (this.isDirRight) {
				this.setCurrentSprite(this.spriteAttackRight);
			} else {
				this.setCurrentSprite(this.spriteAttackLeft);
			}
		} else if (this.keyRight || this.keyLeft) {
			if (this.isDirRight) {
				this.setCurrentSprite(this.spriteRunRight);
			} else {
				this.setCurrentSprite(this.spriteRunLeft);
			}
		} else {
			if (this.isDirRight) {
				this.setCurrentSprite(this.spriteIdleRight);
			} else {
				this.setCurrentSprite(this.spriteIdleLeft);
			}
		}

		if (this.isDirRight) {
			this.rectAttack.setX((int) this.rect.getX() + 20);
		} else {
			this.rectAttack.setX((int) this.rect.getX() - 31);
		}

		this.rectAttack.setY((int) this.rect.getY() + 11);

		this.setSpritePosition();

		this.currentSprite.tick();

		if (this.isAttacking) {
			if (this.currentSprite.getIndex() == 2 || this.currentSprite.getIndex() == 3) {
				this.canDealDamage = true;
			} else {
				this.canDealDamage = false;
			}

			if (this.currentSprite.finishedAnimation()) {
				this.isAttacking = false;
				this.currentSprite.reset();
				this.canDealDamage = false;
			}
		}

		this.setCamera();
	}

	public void render(Graphics render) {
		if (this.isPoisoning) {
			this.currentSprite.renderSpritesSecondary(render);
		} else {
			this.currentSprite.render(render);
		}

		this.renderHp(render);
		this.renderAttack(render);
	}

	private void renderHp(Graphics render) {
		render.setColor(GameColors.BLACK);
		render.fillRect(5, 5, 150, 20);

		render.setColor(GameColors.WHITE);
		render.setFont(GameFont.getTinyFont());
		render.drawString(String.format("HP: %03d | %03d", this.hp, this.hpMax), 15, 20);

		render.drawRect(5, 5, 150, 20);
	}

	private void renderAttack(Graphics render) {
		render.setColor(GameColors.BLACK);
		render.fillRect(165, 5, 90, 20);

		render.setColor(GameColors.WHITE);
		render.setFont(GameFont.getTinyFont());
		render.drawString(String.format("ATK: %02d", this.damage.getDamage()), 175, 20);

		render.drawRect(165, 5, 90, 20);
	}

}
