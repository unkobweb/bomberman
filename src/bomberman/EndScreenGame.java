package bomberman;

import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class EndScreenGame extends BasicGameState {

    public static final int ID = 5;
    private Image sadBomberman;
    private Animation crown;
    private StateBasedGame game;
    private static String winner = null;
    private static String looser = null;
    private static int winnerScore = 0;
    private static int looserScore = 0;
    private static boolean equality = false;
    private ArrayList<String> alphabet = new ArrayList<String>(Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"));
    private static int firstLetter = 0;
    private static int secondLetter = 0;
    private static int thirdLetter = 0;
    private int letterChoiced = 0;
    private Connexion connect = Connexion.getInstance();

  public static void finishGame(int P1Score, int P2Score) {
	  firstLetter = 0;
	  secondLetter = 0;
	  thirdLetter = 0;
	  equality = false;
	  if (P1Score == P2Score) {
    	  equality = true;
    	  winnerScore = P1Score;
      } else if (P1Score > P2Score) {
    	  winner = "P1";
    	  winnerScore = P1Score;
    	  looser = "P2";
    	  looserScore = P2Score;
      } else {
    	  winner = "P2";
    	  winnerScore = P2Score;
    	  looser = "P1";
    	  looserScore = P1Score;
      }
  }

  @Override
  public void init(GameContainer container, StateBasedGame game) throws SlickException {
      // TODO Auto-generated method stub
      this.game = game;
      this.sadBomberman = new Image("resources/img/sad_bomberman2.png");
      SpriteSheet flying_crown = new SpriteSheet("resources/img/crown_spritesheet.png",450,168);
      this.crown = new Animation(flying_crown, 120);
  }

  @Override
  public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
      // TODO Auto-generated method stub
      if (equality) {
    	  g.drawString("You're not even capable of winning..", 200, 50);
    	  g.drawString("Both of you, you disappoint me...", 200, 75);
    	  g.drawString("Only winners can go into the leaderboard.", 200, 125);
    	  g.drawString("Please go away..", 200, 175);
    	  g.drawString("Shame on you.", 200, 200);
    	  g.drawString("Press left button to go to main menu..", 200, 250);
    	  g.drawImage(this.sadBomberman, 300, 300);
      } else {
    	  g.drawAnimation(this.crown, 175, 5);
    	  g.drawString("WINNER", 370, 180);
    	  g.drawString(winner+" : "+winnerScore+" points", 330, 200);
    	  g.drawString("LOOSER --> "+looser+" : "+looserScore+" points", 280, 350);
    	  g.drawString(winner+" choose your name :", 290, 400);
    	  g.drawString(this.alphabet.get(this.firstLetter), 350, 440);
    	  g.drawString(this.alphabet.get(this.secondLetter), 380, 440);
    	  g.drawString(this.alphabet.get(this.thirdLetter), 410, 440);
    	  g.drawString("/\\", 345+(this.letterChoiced*30), 420);
    	  g.drawString("\\/", 345+(this.letterChoiced*30), 460);
    	  g.drawString("Press left button to valid your name", 240, 500);
      }
  }

  @Override
  public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
      // TODO Auto-generated method stub
      if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
          game.enterState(1, new FadeOutTransition(), new FadeInTransition());
      }

  }
    
    @Override
    public void keyPressed(int key, char c) {
      switch (key) {
      	case Input.KEY_ENTER:
      		if (!equality) {
      			connect.putScore(this.alphabet.get(this.firstLetter)+this.alphabet.get(this.secondLetter)+this.alphabet.get(this.thirdLetter), this.winnerScore);
      	      	MainScreenGameState.updateLeaderboard();
      		}
    	  game.enterState(MainScreenGameState.ID, new FadeOutTransition(), new FadeInTransition());
    	  break;
      	case Input.KEY_LEFT:
      		if (this.letterChoiced > 0) {
      			this.letterChoiced--;
      		}
      		break;
      	case Input.KEY_RIGHT:
      		if (this.letterChoiced < 2) {
      			this.letterChoiced++;
      		}
      		break;
      	case Input.KEY_UP:
      		switch (this.letterChoiced) {
      			case 0:
      				System.out.println("oui");
      				if (this.firstLetter < 25) {
      					this.firstLetter++;
      				} else {
      					this.firstLetter = 0;
      				}
      				break;
      			case 1:
      				if (this.secondLetter < 25) {
      					this.secondLetter++;
      				} else {
      					this.secondLetter = 0;
      				}
      				break;
      			case 2:
      				if (this.thirdLetter < 25) {
      					this.thirdLetter++;
      				} else {
      					this.thirdLetter = 0;
      				}
      				break;
      		}
      		break;
      	case Input.KEY_DOWN:
      		switch (this.letterChoiced) {
  			case 0:
  				if (this.firstLetter > 0) {
  					this.firstLetter--;
  				} else {
  					this.firstLetter = 25;
  				}
  				break;
  			case 1:
  				if (this.secondLetter > 0) {
  					this.secondLetter--;
  				} else {
  					this.secondLetter = 25;
  				}
  				break;
  			case 2:
  				if (this.thirdLetter > 0) {
  					this.thirdLetter--;
  				} else {
  					this.thirdLetter = 25;
  				}
  				break;
      		}
      		break;
      }
    	
    }
    
    public void controllerButtonPressed(int controller, int button) {
    	if (button == 1) {
    		if (!equality) {
      			connect.putScore(this.alphabet.get(this.firstLetter)+this.alphabet.get(this.secondLetter)+this.alphabet.get(this.thirdLetter), this.winnerScore);
      	      	MainScreenGameState.updateLeaderboard();
      		}
    		game.enterState(MainScreenGameState.ID, new FadeOutTransition(), new FadeInTransition());
    	}
    }
    
    @Override
	public void controllerLeftPressed(int controller) {
    	if (this.letterChoiced > 0) {
  			this.letterChoiced--;
  		}
	}
    
    @Override
	public void controllerRightPressed(int controller) {
    	if (this.letterChoiced < 2) {
  			this.letterChoiced++;
  		}
	}
    
    @Override
	public void controllerUpPressed(int controller) {
    	switch (this.letterChoiced) {
			case 0:
				System.out.println("oui");
				if (this.firstLetter < 25) {
					this.firstLetter++;
				} else {
					this.firstLetter = 0;
				}
				break;
			case 1:
				if (this.secondLetter < 25) {
					this.secondLetter++;
				} else {
					this.secondLetter = 0;
				}
				break;
			case 2:
				if (this.thirdLetter < 25) {
					this.thirdLetter++;
				} else {
					this.thirdLetter = 0;
				}
				break;
		}
	}
    
    @Override
	public void controllerDownPressed(int controller) {
    	switch (this.letterChoiced) {
			case 0:
				if (this.firstLetter > 0) {
					this.firstLetter--;
				} else {
					this.firstLetter = 25;
				}
				break;
			case 1:
				if (this.secondLetter > 0) {
					this.secondLetter--;
				} else {
					this.secondLetter = 25;
				}
				break;
			case 2:
				if (this.thirdLetter > 0) {
					this.thirdLetter--;
				} else {
					this.thirdLetter = 25;
				}
				break;
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
