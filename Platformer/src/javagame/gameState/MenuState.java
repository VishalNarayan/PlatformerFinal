package javagame.gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javagame.entities.Player;
import javagame.main.GamePanel;

/**
 * 
 * @author Vishal Narayan and David Lung
 * Class MenuState represents the opening menu screen. 
 * It has three options, "Start", "Help", and "Quit", 
 * represented by the array of Strings. MenuState extends
 * GameState, which is an abstract class. 
 *
 */

public class MenuState extends GameState{
	
	//options
	private String[] options = {"Start", "Help", "Quit"};
	
	//subscript at bottom
	private String help = "Use arrow keys and [Enter] to select an option";
	
	private int currentSelection = 0;

	/**
	 * Calls the super method of GameStateManager constructor
	 * @param gsm the GameStateManager constructor passed
	 */
	public MenuState(GameStateManager gsm) {
		super(gsm);
		
	}

	@Override
	public void init() {
		Player.LIVES = 3;
	}

	@Override
	public void tick() {}

	@Override
	public void draw(Graphics g) {
		
		g.setColor(new Color(50, 150, 200));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		for (int i = 0; i < options.length; i++){
			if (i == currentSelection) {
				g.setColor(Color.GREEN);
			}
			else{
				g.setColor(Color.WHITE);
			}
			
			g.setFont(new Font("Arial", Font.PLAIN, 72));
			g.drawString(options[i], GamePanel.WIDTH/2 - 75, 150 + i*150);
		}
		g.setColor(Color.DARK_GRAY);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(help, 100, 550);
	}

	@Override
	public void keyPressed(int k) {
		//highlight the selected option
		if (k == KeyEvent.VK_DOWN){
			currentSelection++;
			if(currentSelection >= options.length){
				currentSelection = 0;
			}
		}else if(k == KeyEvent.VK_UP){
			currentSelection--;
			if(currentSelection < 0){
				currentSelection = options.length - 1;
			}
		}
		
		//check which option is chosen and push the corresponding GameState to the Stack.
		if (k == KeyEvent.VK_ENTER){
			if(currentSelection == 0){
				gsm.states.push(new Level1State(gsm));
			}else if(currentSelection == 1){
				// display instructions
				gsm.states.push(new HelpState(gsm));
			}else if(currentSelection == 2){
				System.exit(0);
			}
		}
	}

	@Override
	public void keyReleased(int k) {}

}
