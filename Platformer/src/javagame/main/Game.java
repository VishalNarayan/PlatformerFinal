package javagame.main;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * 
 * @author Vishal Narayan and David Lung
 * Class Game contains the main method of the
 * whole game. It creates a JFrame object, which
 * represents the screen of the game.
 *
 */

public class Game {
	
	public static void main (String[] args){
		JFrame frame = new JFrame("Platformer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.add(new GamePanel(), BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}

}
