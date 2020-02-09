package bomberman;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
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
	private boolean goesToScore = false;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.TIME = 0;
		this.gameFinished = false;
		this.goesToScore = false;
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
	
	public static int returnP1Score() {
		return player.getScore();
	}
	
	public static int returnP2Score() {
		return player2.getScore();
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
		if (this.gameFinished && this.goesToScore) {
			this.init(container, game);
		}
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
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            game.enterState(1, new FadeOutTransition(), new FadeInTransition());
            
		}
		if (MapGameState.gameFinished || TIME >= 300000) {
			int diffLife = player.getNbDeath() - player2.getNbDeath();
			if (diffLife < 0) {
				player.addScore((diffLife*(-1)*200));
			} else {
				player2.addScore(diffLife*200);
			}
			EndScreenGame.finishGame(player.getScore(), player2.getScore());
            game.enterState(5, new FadeOutTransition(), new FadeInTransition());
            this.goesToScore = true;
		}
	}
	
	public static void closeGame() {
		container.exit();
	}
	
	public static void addBomb(Bomb bomb) {
		listBomb.add(bomb);
	}
	
	public static void addScore(int score, int numero) {
		if (numero == 1) {
			player.addScore(score);
		} else if (numero == 2) {
			player2.addScore(score);
		}
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
