package bomberman;

import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
//import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class ObjectsGame extends BasicGame{
	
	private static GameContainer container;
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
		ObjectsGame.container = container;
		ObjectsGame.map.init();
		ObjectsGame.player.init();
		ObjectsGame.player2.init();
		ObjectsGame.container.setShowFPS(false);
		PlayerController controller = new PlayerController(ObjectsGame.player, 2);
		container.getInput().addKeyListener(controller);
		container.getInput().addControllerListener(controller);
		//PlayerController controller2 = new PlayerController(ObjectsGame.player2, 3);
		//container.getInput().addKeyListener(controller2);
		//container.getInput().addControllerListener(controller2);
		
		/*Music background = new Music("resources/music/music.wav");
	    background.loop();*/
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		ObjectsGame.map.render();
		ObjectsGame.player.render(g);
		ObjectsGame.player2.render(g);
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		TIME += delta;
		//System.out.println(TIME/1000);
		ObjectsGame.player.update(delta);
		ObjectsGame.player2.update(delta);
		for (int i = 0; i < listBomb.size(); i++) {
			System.out.println(listBomb.get(i).toString());
			listBomb.get(i).update(TIME);
			if (listBomb.get(i).cleared == true) {
				listBomb.remove(i);
			}
		}
	}
	
	public static void closeGame() {
		container.exit();
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
	
	public static void giveRange(int playerNumber) {
		if (playerNumber == 1) {
			player.addRange();
		} else if (playerNumber == 2){
			player2.addRange();
		}
	}
	
	public static void giveSpeed(int playerNumber) {
		if (playerNumber == 1) {
			player.addSpeed();
		} else if (playerNumber == 2){
			player2.addSpeed();
		}
	}
	
	
}