package bomberman;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MapGameState extends BasicGameState {

	public static final int ID = 2;
	private static GameContainer container;
	private static Map map = new Map();
	private static Player player = new Player(map, 48, 48, 1);
	private static Player player2 = new Player(map, 430, 370, 2);
	private static ArrayList<Bomb> listBomb = new ArrayList<Bomb>();
	public static int TIME = 0;
	private Hud hud = new Hud();
	public static boolean gameFinished = false;
	
	/*public static void main(String[] args) throws SlickException {
		new AppGameContainer(new MapGameState(), 640, 480, false).start();
	}
	
	public MapGameState() {
		super("Le bomberman");
	}*/
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		MapGameState.container = container;
		MapGameState.map.init();
		MapGameState.player.init();
		MapGameState.player2.init();
		MapGameState.container.setShowFPS(false);
		PlayerController controller = new PlayerController(MapGameState.player, 2);
		container.getInput().addKeyListener(controller);
		container.getInput().addControllerListener(controller);
		PlayerController controller2 = new PlayerController(MapGameState.player2, 3);
		container.getInput().addKeyListener(controller2);
		container.getInput().addControllerListener(controller2);
		MapGameState.container.setShowFPS(false);
        MapGameState.container.setFullscreen(false);
        this.hud.init();
		
		/*Music background = new Music("resources/music/music.wav");
	    background.loop();*/
	}
	
	public static void finishGame() {
		MapGameState.gameFinished = true;
	}

	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		MapGameState.map.render();
		MapGameState.player.render(g);
		MapGameState.player2.render(g);
		this.hud.render(g, this.player, this.player2, TIME);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		TIME += delta;
		//System.out.println(TIME/1000);
		MapGameState.player.update(delta);
		MapGameState.player2.update(delta);
		for (int i = 0; i < listBomb.size(); i++) {
			listBomb.get(i).update(TIME);
			if (listBomb.get(i).cleared == true) {
				listBomb.remove(i);
			}
		}
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE) || MapGameState.gameFinished || TIME >= 300000) {
            game.enterState(1, new FadeOutTransition(), new FadeInTransition());
            
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
	
	public static void giveOneMoreBomb(int playerNumber) {
		if (playerNumber == 1) {
			player.addMoreBomb();
		} else if (playerNumber == 2){
			player2.addMoreBomb();
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
	
	@Override
    public int getID() {
        // TODO Auto-generated method stub
        return ID;
    }

}
