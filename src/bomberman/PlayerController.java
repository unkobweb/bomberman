package bomberman;

import org.newdawn.slick.ControllerListener;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

public class PlayerController implements KeyListener, ControllerListener {
	private Player player;
	private int numJoystick;
	
	public PlayerController(Player player, int numJoystick) {
	    this.player = player;
	    this.numJoystick = numJoystick;
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
		}
	}
	
	@Override
	public void controllerLeftPressed(int controller) {
		if (controller == this.numJoystick) {
			this.player.setDirection(1);
			this.player.setMoving(true);
		}
	}

	@Override
	public void controllerLeftReleased(int controller) {
		if (controller == this.numJoystick) {
			if (this.player.getDirection() == 1) {
				this.player.setMoving(false);
			}
		}
	}

	@Override
	public void controllerRightPressed(int controller) {
		if (controller == this.numJoystick) {
			this.player.setDirection(3);
			this.player.setMoving(true);
		}
	}

	@Override
	public void controllerRightReleased(int controller) {
		if (controller == this.numJoystick) {
			if (this.player.getDirection() == 3) {
				this.player.setMoving(false);
			}
		}
	}

	@Override
	public void controllerUpPressed(int controller) {
		if (controller == this.numJoystick) {
			this.player.setDirection(0);
			this.player.setMoving(true);
		}
	}

	@Override
	public void controllerUpReleased(int controller) {
		if (controller == this.numJoystick) {
			if (this.player.getDirection() == 0) {
				this.player.setMoving(false);
			}
		}
	}

	@Override
	public void controllerDownPressed(int controller) {
		if (controller == this.numJoystick) {
			this.player.setDirection(2);
			this.player.setMoving(true);
		}
	}

	@Override
	public void controllerDownReleased(int controller) {
		if (controller == this.numJoystick) {
			if (this.player.getDirection() == 2) {
				this.player.setMoving(false);
			}
		}
	}

	@Override
	public void controllerButtonPressed(int controller, int button) {
		if (controller == this.numJoystick) {
			if (button == 1) {
				this.player.poserBombe();
			}
			System.out.println(controller);
			System.out.println(button);
		}
	}

	@Override
	public void controllerButtonReleased(int controller, int button) {
	}
}
