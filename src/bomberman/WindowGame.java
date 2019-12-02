package bomberman;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

public class WindowGame extends BasicGame {
	private GameContainer container;
	private TiledMap map;
	private float x = 300, y = 300;
	private int direction = 0;
	private boolean moving = false;
	private Animation[] animations = new Animation[8];
	
	public static void main(String[] args) throws SlickException {
		new AppGameContainer(new WindowGame(), 640, 480, false).start();
	}
	
	public WindowGame() {
		super("Le bomberman");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		this.container = container;
		this.map = new TiledMap("resources/map/map.tmx");
		SpriteSheet deplacement = new SpriteSheet("resources/sprites/deplacement.png",20,33);
		SpriteSheet stay = new SpriteSheet("resources/sprites/stay.png",20,33);
		this.animations[0] = loadAnimation(stay,0,3,0);
		this.animations[1] = loadAnimation(stay,0,3,1);
		this.animations[2] = loadAnimation(stay,0,3,2);
		this.animations[3] = loadAnimation(stay,0,3,3);
		this.animations[4] = loadAnimation(deplacement,0,5,0);
		this.animations[5] = loadAnimation(deplacement,0,5,1);
		this.animations[6] = loadAnimation(deplacement,0,5,2);
		this.animations[7] = loadAnimation(deplacement,0,5,3);
	}
	
	private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
	    Animation animation = new Animation();
	    for (int x = startX; x < endX; x++) {
	        animation.addFrame(spriteSheet.getSprite(x, y), 100);
	    }
	    return animation;
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		this.map.render(0, 0);
		g.drawAnimation(animations[direction + (moving ? 4 : 0)], x, y);
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if (this.moving) {
	        switch (this.direction) {
	            case 2: this.y -= .1f * delta; break;
	            case 3: this.x -= .1f * delta; break;
	            case 0: this.y += .1f * delta; break;
	            case 1: this.x += .1f * delta; break;
	        }
	    }
	}
	
	@Override
	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_DOWN: 		this.direction = 0; this.moving = true; break;
		case Input.KEY_RIGHT: 	this.direction = 1; this.moving = true; break;
		case Input.KEY_UP: 	this.direction = 2; this.moving = true; break;
		case Input.KEY_LEFT: 	this.direction = 3; this.moving = true; break;
		}
	}
	
	@Override
	public void keyReleased(int key, char c) {
		if (Input.KEY_ESCAPE == key) {
			container.exit();
		}
		this.moving = false;
	}

}
