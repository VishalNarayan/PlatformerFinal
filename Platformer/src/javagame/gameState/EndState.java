package javagame.gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javagame.main.GamePanel;

/**
 * 
 * @author Vishal Narayan and David Lung
 * EndState represents the screen that is displayed
 * when the player loses the game. It also gives the user
 * an option to either go back to the main menu or to 
 * quit the game.
 *
 */
public class EndState extends GameState {
	
	//private String variables
	private String end = "Game Over";
	private String enter = "Press [Enter] to go back to main menu";
	private String esc = "Press [Esc] to quit";

	/**
	 * Calls the super method of GameStateManager constructor
	 * @param gsm the GameStateManager constructor passed
	 */
	public EndState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {}

	@Override
	public void tick() {}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.ITALIC, 60));
		g.drawString(end, 250, 300);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString(enter, 200, 500);
		g.drawString(esc, 300, 550);
	}

	@Override
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ESCAPE){
			System.exit(0);
		}
		if (k == KeyEvent.VK_ENTER){
			gsm.states.push(new MenuState(gsm));
		}
	}

	@Override
	public void keyReleased(int k) {}
	

}
