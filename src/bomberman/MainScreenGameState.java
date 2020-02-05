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
 
public class MainScreenGameState extends BasicGameState {
   
      public static final int ID = 1;
      private Image background;
      private StateBasedGame game;
      private int choice = 0;
     
     
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        // TODO Auto-generated method stub
        this.game = game;
        this.background = new Image("resources/homeScreen/HomeScreen.png");
       
    }
 
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        // TODO Auto-generated method stub
        background.draw(0, 0, container.getWidth(), container.getHeight());
        g.drawString(choice == 0 ? "> " + "Press F1 to play" : "" + "Press F1 to play", 300, 380);
        g.drawString(choice == 1 ? "> " + "Press F2 for options" : "" +"Press F2 for options", 300, 410);
    }
 
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        // TODO Auto-generated method stub
        if(container.getInput().isKeyPressed(Input.KEY_F1)) {
            game.enterState(2, new FadeOutTransition(), new FadeInTransition());
        }
        if(container.getInput().isKeyPressed(Input.KEY_F2)) {
            game.enterState(3, new FadeOutTransition(), new FadeInTransition());
        }
        if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            container.exit();
        }
    }
   
      @Override
      public void keyReleased(int key, char c) {
        game.enterState(MapGameState.ID);
      }
     
 
    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return ID;
    }
 
    public static void main(String[] args) {
        // TODO Auto-generated method stub
 
    }
    
    @Override
	public void controllerDownPressed(int controller) {
    	if (choice > 0) {
			choice--;
		}
	}
    
    @Override
	public void controllerUpPressed(int controller) {
		if (choice < 1) {
			choice++;
		}
	}
}