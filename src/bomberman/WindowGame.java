package bomberman;

import java.util.ArrayList;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

public class WindowGame extends BasicGame {
	private GameContainer container;
	private static Map map = new Map();
	private static Player player = new Player(map, 48, 48, 1);
	private static Player player2 = new Player(map, 430, 370, 2);
	private static ArrayList<Bomb> listBomb = new ArrayList<Bomb>();
	public static int TIME = 0;
	
	public static void main(String[] args) throws SlickException {
		new AppGameContainer(new WindowGame(), 640, 480, false).start();
	}
	
	public WindowGame() {
		super("Le bomberman");
	}
	
	public static void addBomb(Bomb bomb) {
		listBomb.add(bomb);
	}
	
	public static void giveOneBomb(int playerNumber) {
		if (playerNumber == 1) {
			player.addBomb();
		} else if (playerNumber == 2){
			player2.addBomb();
		}
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		this.container = container;
		this.map.init();
		this.player.init();
		this.player2.init();
		this.container.setShowFPS(false);
		
		/*Music background = new Music("resources/music/music.wav");
	    background.loop();*/
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		this.map.render();
		this.player.render(g);
		this.player2.render(g);
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		TIME += delta;
		//System.out.println(TIME/1000);
		this.player.update(delta);
		this.player2.update(delta);
		for (int i = 0; i < listBomb.size(); i++) {
			listBomb.get(i).update(TIME);
			if (listBomb.get(i).cleared == true) {
				listBomb.remove(i);
			}
		}
	}
	
	@Override
	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_DOWN: 		this.player.setDirection(2); this.player.setMoving(true); break;
		case Input.KEY_RIGHT: 		this.player.setDirection(3); this.player.setMoving(true); break;
		case Input.KEY_UP: 			this.player.setDirection(0); this.player.setMoving(true); break;
		case Input.KEY_LEFT: 		this.player.setDirection(1); this.player.setMoving(true); break;
		case Input.KEY_NUMPAD0:		this.player.poserBombe(); break;
		
		case Input.KEY_Z : 			this.player2.setDirection(0); this.player2.setMoving(true); break;
		case Input.KEY_D : 			this.player2.setDirection(3); this.player2.setMoving(true); break;
		case Input.KEY_Q : 			this.player2.setDirection(1); this.player2.setMoving(true); break;
		case Input.KEY_S : 			this.player2.setDirection(2); this.player2.setMoving(true); break;
		case Input.KEY_SPACE :	this.player2.poserBombe(); break;
		}
	}
	
	@Override
	public void keyReleased(int key, char c) {
		if (Input.KEY_ESCAPE == key) {
			container.exit();
		}
		switch(key){
		  case Input.KEY_UP: 
		    if(this.player.getDirection() == 0) this.player.setMoving(false); 
		      break;
		  case Input.KEY_LEFT: 
		    if(this.player.getDirection() == 1) this.player.setMoving(false);
		      break;
		  case Input.KEY_DOWN: 
		    if(this.player.getDirection() == 2) this.player.setMoving(false);
		      break;
		  case Input.KEY_RIGHT: 
		    if(this.player.getDirection() == 3) this.player.setMoving(false);
		      break;
		  case Input.KEY_Z: 
			if(this.player2.getDirection() == 0) this.player2.setMoving(false);
			break;
		  case Input.KEY_D: 
			if(this.player2.getDirection() == 3) this.player2.setMoving(false);
			break;
		  case Input.KEY_Q: 
			if(this.player2.getDirection() == 1) this.player2.setMoving(false);
			break;
		  case Input.KEY_S: 
			if(this.player2.getDirection() == 2) this.player2.setMoving(false);
			break;
		}
	}

}
