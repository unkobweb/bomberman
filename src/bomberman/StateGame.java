package bomberman;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class StateGame extends StateBasedGame {

    public static void main(String[] args) throws SlickException {
        // TODO Auto-generated method stub
         new AppGameContainer(new StateGame(), 800, 600, false).start();
    }

    public StateGame() {
         super("Le Bomberman");
     }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        // TODO Auto-generated method stub
        addState(new MainScreenGameState());
        addState(new MapGameState());
        addState(new OptionScreen());
        addState(new Instructions());
        addState(new EndScreenGame());
    }
}