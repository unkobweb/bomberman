package bomberman;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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
		this.container.setShowFPS(false);
		this.map = new TiledMap("resources/map/map.tmx");
		
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
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		this.map.render(0, 0);
		/*g.setColor(new Color(0, 0, 0, .5f));
		g.fillOval((int) x - 16, (int) y - 8, 32, 16);*/
		g.drawAnimation(animations[direction + (moving ? 4 : 0)], x-10, y-25);
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		
		updateCharacter(delta);
		/*System.out.println(this.x);
		System.out.println(this.y);*/
		
		//Gestion de la Collision
		   if (this.moving) {
		        float futurX = getFuturX(delta);
		        float futurY = getFuturY(delta);
		        boolean collision = isCollision(futurX, futurY);
		        if (collision) {
		            this.moving = false;
		        } else {
		            this.x = futurX;
		            this.y = futurY;
		        }
		    }
	            
	}
	
	
	private void updateCharacter(int delta) {
		if (this.moving) {
			switch (this.direction) {
			case 0:
				this.y -= .1f * delta;
				break;
			case 1:
				this.x -= .1f * delta;
				break;
			case 2:
				this.y += .1f * delta;
				break;
			case 3:
				this.x += .1f * delta;
				break;
			}
		}
	}
	
	//Méthode pour déterminer si il y'a collision
	private boolean isCollision(float x, float y) {
	    int tileW = this.map.getTileWidth();
	    int tileH = this.map.getTileHeight();
	    int logicLayer = this.map.getLayerIndex("mur");
	    Image tile = this.map.getTileImage((int) x / tileW, (int) y / tileH, logicLayer);
	    boolean collision = tile != null;
	    if (collision) {
	        Color color = tile.getColor((int) x % tileW, (int) y % tileH);
	        collision = color.getAlpha() > 0;
	    }
	    return collision;
	}

	//Méthode pour trouver le déplacement X futur pour les collisions
	private float getFuturX(int delta) {
	    float futurX = this.x;
	    switch (this.direction) {
	    case 1: futurX = this.x - .1f * delta; break;
	    case 3: futurX = this.x + .1f * delta; break;
	    }
	    return futurX;
	}

	//Méthode pour trouver le déplacement futur pour les collisions
	private float getFuturY(int delta) {
	    float futurY = this.y;
	    switch (this.direction) {
	    case 0: futurY = this.y - .1f * delta; break;
	    case 2: futurY = this.y + .1f * delta; break;
	    }
	    return futurY;
	}
	
	@Override
	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_DOWN: 		this.direction = 2; this.moving = true; break;
		case Input.KEY_RIGHT: 	this.direction = 3; this.moving = true; break;
		case Input.KEY_UP: 	this.direction = 0; this.moving = true; break;
		case Input.KEY_LEFT: 	this.direction = 1; this.moving = true; break;
		}
	}
	
	@Override
	public void keyReleased(int key, char c) {
		if (Input.KEY_ESCAPE == key) {
			container.exit();
		}
		switch(key){
		  case Input.KEY_UP: 
		    if(direction == 0) this.moving = false; 
		      break;
		  case Input.KEY_LEFT: 
		    if(direction == 1) this.moving = false;
		      break;
		  case Input.KEY_DOWN: 
		    if(direction == 2) this.moving = false;
		      break;
		  case Input.KEY_RIGHT: 
		    if(direction == 3) this.moving = false;
		      break;
		    }
	}

}
