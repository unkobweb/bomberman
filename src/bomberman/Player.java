package bomberman;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Player {
	private float x, y;
	private float baseX, baseY;
	private int baseRange, baseNbBomb, baseSpeed;
	private int direction = 2;
	private boolean moving = false;
	private Animation[] animations = new Animation[8];
	private int nombreBombe = 1;
	private int nombreBombeMax = 1;
	private int numero;
	public boolean onBomb = false;
	private boolean dead = false;
	private int deadTime = 0;
	private int numberOfDeath = 0;
	
	private int timerBomb = 3;
	private int rangeBomb = 2;
	private int speed = 0;
	
	private int score = 0;
	
	private Map map;
	
	public Player(Map map, int x, int y, int numero) {
		this.map = map;
		this.x = x;
		this.y = y;
		this.baseX = x;
		this.baseY = y;
		this.baseNbBomb = 1;
		this.baseRange = 2;
		this.baseSpeed = 0;
		this.numero = numero;
	}
	
	public void addScore(int nbScore) {
		this.score += nbScore;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public int getNbDeath() {
		return this.numberOfDeath;
	}
	
	public int getNbBomb() {
		return this.nombreBombeMax;
	}
	
	public int getNbRange() {
		return this.rangeBomb;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public void addBomb() {
		this.nombreBombe++;
	}
	
	public void addMoreBomb() {
		this.nombreBombeMax++;
		this.nombreBombe++;
	}
	
	public void addRange() {
		this.rangeBomb++;
	}
	
	public void addSpeed() {
		if (this.speed < 4) {
			this.speed++;
		}
	}
	
	public void init() throws SlickException {
		this.numberOfDeath = 0;
		this.nombreBombe = this.baseNbBomb;
		this.rangeBomb = this.baseRange;
		this.speed = this.baseSpeed;
		this.x = this.baseX;
		this.y = this.baseY;
		this.score = 0;
		SpriteSheet deplacement = new SpriteSheet("resources/sprites/deplacement.png",21,33);
		SpriteSheet stay = new SpriteSheet("resources/sprites/stay.png",20,33);
		this.animations[2] = loadAnimation(stay,0,3,0);
		this.animations[3] = loadAnimation(stay,0,3,1);
		this.animations[0] = loadAnimation(stay,0,3,2);
		this.animations[1] = loadAnimation(stay,0,3,3);
		this.animations[6] = loadAnimation(deplacement,0,5,0);
		this.animations[7] = loadAnimation(deplacement,0,5,1);
		this.animations[4] = loadAnimation(deplacement,0,5,2);
		this.animations[5] = loadAnimation(deplacement,0,5,3);
	}
	
	private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
	    Animation animation = new Animation();
	    for (int x = startX; x < endX; x++) {
	        animation.addFrame(spriteSheet.getSprite(x, y), 100);
	    }
	    return animation;
	}
	
	public void render(Graphics g) throws SlickException {
		/*g.setColor(new Color(0, 0, 0, .5f));
		g.fillOval((int) x - 16, (int) y - 8, 32, 16);*/
		if (!this.dead) {
			g.drawAnimation(animations[direction + (moving ? 4 : 0)], x-10, y-25);
		}
	}
	
	public void update(int delta) {
		if (this.moving) {
	        float futurX = getFuturX(delta);
	        float futurY = getFuturY(delta);
	        boolean collision = this.map.isCollision(futurX, futurY, this.onBomb, this.numero);
	        if (collision) {
	            this.moving = false;
	        } else {
	            this.x = futurX;
	            this.y = futurY;
	        }
	        if (this.onBomb) {
	        	if (this.map.isOnBomb(this.x, this.y) == false) {
	        		this.onBomb = false;
	        	}
	        }
	    }
		if (!this.dead && this.map.isDead(this.x, this.y)) {
			this.dead = true;
			this.score = (this.score >= 200) ? this.score - 200 : 0;
			this.deadTime = MapGameState.TIME;
			this.numberOfDeath++;
			if (this.numberOfDeath == 3) {
				MapGameState.finishGame();
			}
			this.x = 1;
			this.y = 1;
		}
		if (this.dead && this.deadTime + 3000 <= MapGameState.TIME ) {
			this.dead = false;
			this.deadTime = 0;
			this.x = this.baseX;
			this.y = this.baseY;
			this.direction = 2;
		}
	}
	
	private float getFuturX(int delta) {
	    float futurX = this.x;
	    switch (this.direction) {
	    case 1: futurX = this.x - .1f * (delta+this.speed); break;
	    case 3: futurX = this.x + .1f * (delta+this.speed); break;
	    }
	    return futurX;
	}

	private float getFuturY(int delta) {
	    float futurY = this.y;
	    switch (this.direction) {
	    case 0: futurY = this.y - .1f * (delta+this.speed); break;
	    case 2: futurY = this.y + .1f * (delta+this.speed); break;
	    }
	    return futurY;
	}
	
	public void poserBombe() {
		if (this.nombreBombe > 0 && !this.dead && MapGameState.TIME > 200) {
			this.nombreBombe--;
			Bomb b = new Bomb(this.x, this.y, this.timerBomb, this.rangeBomb, map, this.numero);
			b.start();
			MapGameState.addBomb(b);
			if (this.map.isOnBomb(this.x, this.y)) {
				this.onBomb = true;
			} else {
				this.onBomb = false;
			}
		}
	}
	
	public float getX() { return x; }
	public void setX(float x) { this.x = x; }
	public float getY() { return y; }
	public void setY(float y) { this.y = y; }
	public int getDirection() { return direction; }
	public void setDirection(int direction) { this.direction = direction; }
	public boolean isMoving() { return moving; }
	public void setMoving(boolean moving) { this.moving = moving; }
}