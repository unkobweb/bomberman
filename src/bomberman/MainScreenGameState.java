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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.newdawn.slick.ControllerListener;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
 
public class MainScreenGameState extends BasicGameState {
   
      public static final int ID = 1;
      private Image logo;
      private StateBasedGame game;
      private int choice = 0;
      private ResultSet tenBest = null;
      private Connexion connect = Connexion.getInstance();
      private ArrayList<String> nom = new ArrayList<String>();
      private ArrayList<Integer> score = new ArrayList<Integer>();
      private static boolean newScore = false;
     
     
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        // TODO Auto-generated method stub
        this.game = game;
        this.logo = new Image("resources/img/Super_Bomberman_R_Logo.png");
        try {
			connect.init();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        this.tenBest = connect.getTenBest();
        this.nom.clear();
        this.score.clear();
        try {
        	while(tenBest.next()) {
            	this.nom.add(tenBest.getString("name"));
            	this.score.add(tenBest.getInt("score"));
            	System.out.println(tenBest.getString("name"));
            	System.out.println(tenBest.getInt("score"));
            	System.out.println("---");
                //System.out.println("Id : "+rs.getInt("id")+ "\n" +"Score : "+rs.getInt("score")+ "\n" + "Nom : "+rs.getString("name") + "\n");
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    }
    
    public static void updateLeaderboard() {
    	newScore = true;
    }
    
    private String fiveNumberScore(int score) {
    	String answer = "";
    	if ((score / 10000) > 0) {
    		answer = ""+score;
    	} else if ((score / 1000) > 0) {
    		answer = "0"+score;
    	} else if ((score / 100) > 0) {
    		answer = "00"+score;
    	} else if ((score / 10) > 0) {
    		answer = "000"+score;
    	} else {
    		answer = "0000"+score;
    	}
    	return answer;
    }
 
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        // TODO Auto-generated method stub
    	int place = 0;
    	int high = 340;
    	g.drawImage(this.logo, 100, 15);
        g.drawString(choice == 0 ? "> " + "Play" : "" + "Play", 255, 360);
        g.drawString(choice == 1 ? "> " + "Options" : "" +"Options", 255, 390);
        g.drawString(choice == 2 ? "> " + "Instructions" : "" +"Instructions", 255, 420);
        g.drawString("LEADERBOARD", 455, 320);
        g.drawString(this.nom.size() >= 1 ? "1| "+this.nom.get(0) : "1| ---", 455, high);
    	g.drawString(this.score.size() >= 1 ? ""+this.fiveNumberScore(this.score.get(0)) : "-----", 520, high);
    	g.drawString(this.nom.size() >= 2 ? "2| "+this.nom.get(1) : "2| ---", 455, high+20);
    	g.drawString(this.score.size() >= 2 ? ""+this.fiveNumberScore(this.score.get(1)) : "-----", 520, high+20);
    	g.drawString(this.nom.size() >= 3 ? "3| "+this.nom.get(2) : "3| ---", 455, high+40);
    	g.drawString(this.score.size() >= 3 ? ""+this.fiveNumberScore(this.score.get(2)) : "-----", 520, high+40);
    	g.drawString(this.nom.size() >= 4 ? "4| "+this.nom.get(3) : "4| ---", 455, high+60);
    	g.drawString(this.score.size() >= 4 ? ""+this.fiveNumberScore(this.score.get(3)) : "-----", 520, high+60);
    	g.drawString(this.nom.size() >= 5 ? "5| "+this.nom.get(4) : "5| ---", 455, high+80);
    	g.drawString(this.score.size() >= 5 ? ""+this.fiveNumberScore(this.score.get(4)) : "-----", 520, high+80);
    	g.drawString(this.nom.size() >= 6 ? "6| "+this.nom.get(5) : "6| ---", 455, high+100);
    	g.drawString(this.score.size() >= 6 ? ""+this.fiveNumberScore(this.score.get(5)) : "-----", 520, high+100);
    	g.drawString(this.nom.size() >= 7 ? "7| "+this.nom.get(6) : "7| ---", 455, high+120);
    	g.drawString(this.score.size() >= 7 ? ""+this.fiveNumberScore(this.score.get(6)) : "-----", 520, high+120);
    	g.drawString(this.nom.size() >= 8 ? "8| "+this.nom.get(7) : "8| ---", 455, high+140);
    	g.drawString(this.score.size() >= 8 ? ""+this.fiveNumberScore(this.score.get(7)) : "-----", 520, high+140);
    	g.drawString(this.nom.size() >= 9 ? "9| "+this.nom.get(8) : "9| ---", 455, high+160);
    	g.drawString(this.score.size() >= 9 ? ""+this.fiveNumberScore(this.score.get(8)) : "-----", 520, high+160);
    	g.drawString(this.nom.size() >= 10 ? "10| "+this.nom.get(9) : "10| ---", 446, high+180);
    	g.drawString(this.score.size() >= 10 ? ""+this.fiveNumberScore(this.score.get(9)) : "-----", 520, high+180);
        
    }
 
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
    	
    	if (newScore) {
    		this.init(container, game);
    		newScore = false;
    	}
    }
      
      @Override
      public void keyPressed(int key, char c) {
    	  switch (key) {
    	  	case Input.KEY_DOWN:
    		  if (this.choice < 2) {
    			  this.choice++;
    		  }
    		  break;
    	  	case Input.KEY_UP:
    	  		if (this.choice > 0) {
    	  			this.choice--;
    	  		}
    	  		break;
    	  	case Input.KEY_ENTER:
    	  		switch (this.choice) {
    	  		case 0:
    	  			game.enterState(2, new FadeOutTransition(), new FadeInTransition());
    	  			break;
    	  		case 1:
    	  			game.enterState(3, new FadeOutTransition(), new FadeInTransition());
    	  			break;
    	  		case 2:
    	  			game.enterState(4, new FadeOutTransition(), new FadeInTransition());
    	  			break;
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
    	if (choice > 0) {
			choice--;
		}
	}
    
    @Override
	public void controllerDownPressed(int controller) {
		if (choice < 2) {
			choice++;
		}
	}
    
    public void controllerButtonPressed(int controller, int button) {
    	if (button == 1) {
    		switch (choice) {
    		case 0:
    			game.enterState(2, new FadeOutTransition(), new FadeInTransition());
    			break;
    		case 1:
    			game.enterState(3, new FadeOutTransition(), new FadeInTransition());
    			break;
    		case 2:
    			game.enterState(4, new FadeOutTransition(), new FadeInTransition());
    			break;
    		} 
    	}
    }
}