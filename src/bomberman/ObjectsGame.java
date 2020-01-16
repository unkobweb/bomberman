package bomberman;

import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class ObjectsGame extends BasicGame{
	
	private GameContainer container;
	private static Map map = new Map();
	private static Player player = new Player(map, 48, 48, 1);
	private static Player player2 = new Player(map, 430, 370, 2);
	private static ArrayList<Bomb> listBomb = new ArrayList<Bomb>();
	public static int TIME = 0;
	
	public static void main(String[] args) throws SlickException {
		new AppGameContainer(new ObjectsGame(), 640, 480, false).start();
	}
	
	public ObjectsGame() {
		super("Le bomberman");
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
	
	
}