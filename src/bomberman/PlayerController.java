package bomberman;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

public class PlayerController implements KeyListener {
	private Player player;
	
	public PlayerController(Player player) {
	    this.player = player;
	}
	
	@Override
	public void setInput(Input input) { }

	@Override
	public boolean isAcceptingInput() { return true; }
	 
	@Override
	public void inputEnded() { }

	@Override
	public void inputStarted() { }

	@Override
	public void keyPressed(int key, char c) {
		System.out.println(key);
		switch (key) {
		case Input.KEY_UP: 			this.player.setDirection(0); this.player.setMoving(true); break;
		case Input.KEY_LEFT: 		this.player.setDirection(1); this.player.setMoving(true); break;
		case Input.KEY_DOWN: 		this.player.setDirection(2); this.player.setMoving(true); break;
		case Input.KEY_RIGHT: 		this.player.setDirection(3); this.player.setMoving(true); break;
		case Input.KEY_NUMPAD0:		this.player.poserBombe(); break;
		}
	}

	@Override
	public void keyReleased(int key, char c) {
		this.player.setMoving(false);
	}
}
