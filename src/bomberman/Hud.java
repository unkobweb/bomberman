package bomberman;
 
import org.newdawn.slick.*;
 
public class Hud {
 
    private static final int P1_NAME_X = 600;
    private static final int P1_NAME_Y = 10;
   
    private static final int P2_NAME_X = 600;
    private static final int P2_NAME_Y = 300;
   
    private static final int B1_X = 500;
    private static final int B1_Y = 120;
   
    private static final int B2_X = 500;
    private static final int B2_Y = 400;
   
    private static final int F1_X = 500;
    private static final int F1_Y = 170;
   
    private static final int F2_X = 500;
    private static final int F2_Y = 450;
   
    private static final int H1_X = 500;
    private static final int H1_Y = 70;
   
    private static final int H2_X = 500;
    private static final int H2_Y = 350;
   
    private static final int S1_X = 500;
    private static final int S1_Y = 220;
   
    private static final int S2_X = 500;
    private static final int S2_Y = 500;
   
    private Image playerOne;
    private Image playerTwo;
    private Image bombOne;
    private Image bombTwo;
    private Image fireOne;
    private Image fireTwo;
    private Image healOne;
    private Image healTwo;
    private Image speedOne;
    private Image speedTwo;
   
    public void init() throws SlickException {
        this.playerOne = new Image("resources/hud/player1.png");
        this.playerTwo = new Image("resources/hud/player2.png");
        this.bombOne = new Image("resources/hud/bomb.png");
        this.bombTwo = new Image("resources/hud/bomb.png");
        this.fireOne = new Image("resources/hud/fireBoost.png");
        this.fireTwo = new Image("resources/hud/fireBoost.png");
        this.healOne = new Image("resources/hud/health.png");
        this.healTwo = new Image("resources/hud/health.png");
        this.speedOne = new Image("resources/hud/speed.png");
        this.speedTwo = new Image("resources/hud/speed.png");
    }
 
 
    public void render(Graphics g) {
      g.resetTransform();
      g.drawImage(this.playerOne, P1_NAME_X, P1_NAME_Y);
      g.drawImage(this.playerTwo, P2_NAME_X, P2_NAME_Y);
      g.drawImage(this.bombOne, B1_X, B1_Y);
      g.drawImage(this.bombTwo, B2_X, B2_Y);
      g.drawImage(this.fireOne, F1_X, F1_Y);
      g.drawImage(this.fireTwo, F2_X, F2_Y);
      g.drawImage(this.healOne, H1_X, H1_Y);
      g.drawImage(this.healTwo, H2_X, H2_Y);
      g.drawImage(this.speedOne, S1_X, S1_Y);
      g.drawImage(this.speedTwo, S2_X, S2_Y);
    }
   
   
    public static void main(String[] args) {
        // TODO Auto-generated method stub
 
    }
 
}