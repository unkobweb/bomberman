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

public class Instructions extends BasicGameState {

    public static final int ID = 4;
    private Image background;
    private StateBasedGame game;


  @Override
  public void init(GameContainer container, StateBasedGame game) throws SlickException {
      // TODO Auto-generated method stub
      this.game = game;
      this.background = new Image("resources/homeScreen/Super-Bomberman-R-21-front-2.jpg");
  }

  @Override
  public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
      // TODO Auto-generated method stub
      background.draw(0, 0, container.getWidth(), container.getHeight());
      g.drawString("Appuyer sur F1 pour jouer", 300, 300);
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


  @Override
  public int getID() {
      // TODO Auto-generated method stub
      return ID;
  }

  public static void main(String[] args) {
      // TODO Auto-generated method stub

  }

}
