package javagame.gameState;

import java.awt.Graphics;
import java.util.Stack;

/**
 * 
 * @author Vishal Narayan and David Lung
 * 
 * GameStateManager is a class that uses a Stack of 
 * GameState objects to control whether the opening menu, 
 * help, or the gameplay is being shown on the screen. 
 *
 */

public class GameStateManager {
	
	public Stack<GameState> states;
	
	/**
	 * Constructs a Stack object which contains 
	 * all the GameState objects.
	 */
	public GameStateManager(){
		states = new Stack<GameState>();
		states.push(new MenuState(this));
	}
	
	/**
	 * Calls the tick method of the Stack object.
	 */
	public void tick(){
		states.peek().tick();
	}
	
	/**
	 * Calls the draw method of the Stack object.
	 * @param g the Graphics object passed
	 */
	public void draw(Graphics g){
		states.peek().draw(g);
	}
	
	/**
	 * Calls the keyPressed method of the Stack object.
	 * @param k the integer passed
	 */
	public void keyPressed(int k){
		states.peek().keyPressed(k);
	}
	
	/**
	 * Calls the keyReleased method of the Stack object.
	 * @param k the integer passed
	 */
	public void keyReleased(int k){
		states.peek().keyReleased(k);
	}

}
