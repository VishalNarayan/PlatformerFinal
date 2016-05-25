package javagame.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import javagame.gameState.GameStateManager;

/**
 * 
 * @author Vishal Narayan and David Lung
 * GamePanel defines the attributes of the screen, 
 * by extending JPanel. It also implements Runnable 
 * and KeyListener, which are used to run the program
 * and detect when keys are pressed.
 *
 */

public class GamePanel extends JPanel implements Runnable, KeyListener{
	private static final long serialVersionUID = 1L;
	
	//dimensions of the screen
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	private Thread thread;
	private boolean isRunning = false;
	
	//Frames Per Second
	private int FPS = 15;
	private long targetTime = 100 / FPS;
	
	private GameStateManager gsm;
	
	/**
	 * Constructs the screen and sets its size to
	 * WIDTH AND HEIGHT. It calls the start method, 
	 * and adds itself to the KeyListener.
	 */
	public GamePanel(){
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		addKeyListener(this);
		setFocusable(true);
		
		start();
	}
	
	/**
	 * Runs the thread that starts the game.
	 */
	private void start(){
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * Starts the game and keeps it running.
	 */
	public void run() {
		long start, elapsed, wait;
		
		gsm = new GameStateManager();
		while(isRunning){
			start = System.nanoTime();
			
			
			tick();
			repaint();
			
			elapsed = System.nanoTime() - start;
			wait = targetTime - elapsed / 1000000;
			
			if (wait < 0){
				wait = 5;
			}
			
			try{
				Thread.sleep(wait);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * Runs every frame per second
	 */
	public void tick(){
		gsm.tick();
	}
	
	/**
	 * Sets the method for drawing each object
	 * that extends GameState
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.clearRect(0, 0, WIDTH, HEIGHT);
		
		gsm.draw(g);
	}
	
	/**
	 * Performs actions when a key is pressed
	 * @param k the integer passed
	 */
	public void keyPressed(KeyEvent e){
		gsm.keyPressed(e.getKeyCode());
	}
	
	/**
	 * Performs actions when a key is released
	 * @param k the integer passed
	 */
	public void keyReleased(KeyEvent e){
		gsm.keyReleased(e.getKeyCode());
	}
	
	/**
	 * Performs actions when a key is typed
	 * @param k the integer passed
	 */
	public void keyTyped(KeyEvent e){}

}
