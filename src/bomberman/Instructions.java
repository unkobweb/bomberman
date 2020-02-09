package bomberman;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import org.newdawn.slick.ControllerListener;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

public class Instructions extends BasicGameState {

    public static final int ID = 4;
    private Image background;
    private StateBasedGame game;
    private Image health;
    private Image fire;
    private Image speed;
    private Image bomb;
    private Image button;
    private Image joystick;
    private Image back;

  @Override
  public void init(GameContainer container, StateBasedGame game) throws SlickException {
      // TODO Auto-generated method stub
      this.game = game;
      this.background = new Image("resources/instructionScreen/baackground.jpg");
      this.health = new Image("resources/instructionScreen/health.png");
      this.fire = new Image("resources/instructionScreen/fireBoost.png");
      this.speed = new Image("resources/instructionScreen/speed.png");
      this.bomb = new Image("resources/instructionScreen/bomb.png");
      this.button = new Image("resources/instructionScreen/button.png");
      this.joystick = new Image("resources/instructionScreen/joystick.png");
      this.back = new Image("resources/instructionScreen/return.png");
  }

  @Override
  public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
      // TODO Auto-generated method stub
      background.draw(0, 0, container.getWidth(), container.getHeight());
      g.drawString("Bonus : ", 10, 10);
      g.drawImage(this.health, 10, 70);
      g.drawString(":    This bonus represents the number \n     of lives you have. When it's 0, you \n     loose the game. ",40 , 70);
      g.drawImage(this.fire, 10, 170);
      g.drawString(":    This bonus increases the range \n     of your bomb explosion. Be carrefull \n     fire burns.", 40, 170);
      g.drawImage(this.speed, 10, 270);
      g.drawString(":    This bonus increases the speed \n     of your character. Don't take too much \n     of them, you will fly.", 40, 270);
      g.drawImage(this.bomb, 10, 370);
      g.drawString(":    This bonus increases the number \n     of bomb that you can put in the same \n     time. Don't trap yourself.", 40, 370);
      g.drawString("Rules : ", 480, 10);
      g.drawString("This game is easy too understand. \nThe objectif is to kill the \nennemy. To do that, \nyou need to put some bombs to \ndestroy the walls and reach your \nopponent. The first with \n0 life loose. May the odds be \never in your favor !", 480, 70);
      g.drawString("How to play : ", 480, 270);
      g.drawImage(this.button, 480, 340);
      g.drawString(":  Use left one to put \n   your bombs.", 540, 350);
      g.drawImage(this.joystick, 480 , 420);
      g.drawString(":  Use it to move your \n   character in the map.", 540, 450);
      g.drawImage(this.back, 10, 500);
      g.drawString("Press right button \n to return menu", 60, 535);
      
  }

  @Override
  public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
      // TODO Auto-generated method stub
      if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
          game.enterState(1, new FadeOutTransition(), new FadeInTransition());
      }

  }

    @Override
    public void keyReleased(int key, char c) {
      game.enterState(OptionScreen.ID);
    }
    
    public void controllerButtonPressed(int controller, int button) {
    	if (button == 2) {
    		game.enterState(1, new FadeOutTransition(), new FadeInTransition());
    	}
    }


  @Override
  public int getID() {
      // TODO Auto-generated method stub
      return ID;
  }

  public static void main(String[] args) {
      // TODO Auto-generated method stub

  }

}
