package bomberman;


import org.newdawn.slick.GameContainer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class OptionScreen extends BasicGameState {


      public static final int ID = 3;
      private StateBasedGame game;
      private ArrayList<Integer> statBonus = new ArrayList<Integer>(Arrays.asList(3,2,0,1));
      private ArrayList<Integer> defaultStatBonus;
      private Image background;
      private Image health;
      private Image fire;
      private Image speed;
      private Image bomb;
      private Image back;
      private int choice = 0;
      private Connexion connect = Connexion.getInstance();
      private boolean quited = true; 
      


    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        // TODO Auto-generated method stub
        this.game = game;
        this.background = new Image("resources/optionScreen/baackground.jpg");
        this.health = new Image("resources/optionScreen/health.png");
        this.fire = new Image("resources/optionScreen/fireBoost.png");
        this.speed = new Image("resources/optionScreen/speed.png");
        this.bomb = new Image("resources/optionScreen/bomb.png");
        this.back = new Image("resources/optionScreen/return.png");
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        // TODO Auto-generated method stub
        background.draw(0, 0, container.getWidth(), container.getHeight());
        g.drawString("Settings ", 350, 10);
        g.drawImage(this.health, 350, 140);
        g.drawString(choice == 0 ? "    < " + this.statBonus.get(0) + " >" :     "    " + this.statBonus.get(0),400 , 140);
        g.drawImage(this.fire, 350, 240);
        g.drawString(choice == 1 ? "    < " + this.statBonus.get(1) + " >" :     "    " + this.statBonus.get(1),400, 240);
        g.drawImage(this.speed, 350, 340);
        g.drawString(choice == 2 ? "    < " + this.statBonus.get(2) + " >" :     "    " + this.statBonus.get(2),400, 340);
        g.drawImage(this.bomb, 350, 440);
        g.drawString(choice == 3 ? "    < " + this.statBonus.get(3) + " >" :     "    " + this.statBonus.get(3),400, 440);
        g.drawString(choice == 4 ? "< " + "Apply Changes" + " >" : "Apply changes", 540, 450);
        g.drawString(choice == 5 ? "< " + "Reset default settings" + " >" : "Reset default settings", 540, 550);
        g.drawImage(this.back, 10, 500);
        g.drawString("Press right button \n to return menu", 60, 535);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        // TODO Auto-generated method stub
        if (quited) {
        	quited = false;
        	ResultSet rs = connect.getSettings();
        	try {
				while(rs.next()) {
					statBonus.set(0, rs.getInt("life"));
					statBonus.set(1, rs.getInt("fire"));
					statBonus.set(2, rs.getInt("speed"));
					statBonus.set(3, rs.getInt("bomb"));
				    //System.out.println("Id : "+rs.getInt("id")+ "\n" +"Score : "+rs.getInt("score")+ "\n" + "Nom : "+rs.getString("name") + "\n");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

    @Override
    public void keyPressed(int key, char c) {
      switch(key) {
      case Input.KEY_ESCAPE:
    	  game.enterState(1, new FadeOutTransition(), new FadeInTransition());
    	  this.quited = true;
    	  break;
      case Input.KEY_DOWN:
    	  if(choice < 5) {
    		  choice++;
    	  }
    	  break;
      case Input.KEY_UP:
    	  if(choice > 0) {
    		  choice--;
    	  }
    	  break;
      case Input.KEY_RIGHT:
    	  if(choice == 0 && statBonus.get(0)<5) {
    		  statBonus.set(choice, statBonus.get(choice) + 1);
    	  } else if(choice == 1 && statBonus.get(1)<13) {
    		  statBonus.set(choice, statBonus.get(choice) + 1);
    	  } else if(choice == 2 && statBonus.get(2)<4) {
    		  statBonus.set(choice, statBonus.get(choice) + 1);
    	  } else if(choice == 3 && statBonus.get(3)<10) {
    		  statBonus.set(choice, statBonus.get(choice) + 1);
    	  } 
    	  break;
      case Input.KEY_LEFT:
    	  if(choice == 0 && statBonus.get(0)>1) {
    		  statBonus.set(choice, statBonus.get(choice) - 1);
    	  } else if(choice == 1 && statBonus.get(1)>1) {
    		  statBonus.set(choice, statBonus.get(choice) - 1);
    	  } else if(choice == 2 && statBonus.get(2)>0) {
    		  statBonus.set(choice, statBonus.get(choice) - 1);
    	  } else if(choice == 3 && statBonus.get(3)>1) {
    		  statBonus.set(choice, statBonus.get(choice) - 1);
    	  } 
    	  break;
      case Input.KEY_ENTER:
    	  if(choice == 5) {
    		  statBonus = new ArrayList<Integer>(Arrays.asList(3,2,0,1));
    		  System.out.println(defaultStatBonus);
    	  } else if(choice == 4) {
    		  connect.setSettings(statBonus.get(0), statBonus.get(1), statBonus.get(2), statBonus.get(3));
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
    
    
    @Override
	public void controllerUpPressed(int controller) {
    	if(choice > 0) {
  		  choice--;
  	  	}
	}
    
    @Override
	public void controllerDownPressed(int controller) {
    	if(choice < 5) {
  		  choice++;
  	  }
	}
    
    @Override
	public void controllerRightPressed(int controller) {
    	if(choice == 0 && statBonus.get(0)<5) {
  		  statBonus.set(choice, statBonus.get(choice) + 1);
  	  	} else if(choice == 1 && statBonus.get(1)<13) {
  		  statBonus.set(choice, statBonus.get(choice) + 1);
  	  	} else if(choice == 2 && statBonus.get(2)<4) {
  		  statBonus.set(choice, statBonus.get(choice) + 1);
  	  	} else if(choice == 3 && statBonus.get(3)<10) {
  		  statBonus.set(choice, statBonus.get(choice) + 1);
  	  	} 
	}
    
    @Override
	public void controllerLeftPressed(int controller) {
    	if(choice == 0 && statBonus.get(0)>1) {
  		  statBonus.set(choice, statBonus.get(choice) - 1);
  	  	} else if(choice == 1 && statBonus.get(1)>1) {
  		  statBonus.set(choice, statBonus.get(choice) - 1);
  	  	} else if(choice == 2 && statBonus.get(2)>0) {
  		  statBonus.set(choice, statBonus.get(choice) - 1);
  	  	} else if(choice == 3 && statBonus.get(3)>1) {
  		  statBonus.set(choice, statBonus.get(choice) - 1);
  	  	} 
	}
    
    public void controllerButtonPressed(int controller, int button) {
    	if (button == 1) {
    		if(choice == 5) {
      		  statBonus = new ArrayList<Integer>(Arrays.asList(3,2,0,1));
      		  System.out.println(defaultStatBonus);
      	  	} else if(choice == 4) {
      		  connect.setSettings(statBonus.get(0), statBonus.get(1), statBonus.get(2), statBonus.get(3));
      	  	}
    	}
    	if (button == 2) {
    		this.quited = true;
    		game.enterState(1, new FadeOutTransition(), new FadeInTransition());
    	}
    }

}