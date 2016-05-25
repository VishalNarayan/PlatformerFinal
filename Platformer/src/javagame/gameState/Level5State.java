package javagame.gameState;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javagame.entities.Water;
import javagame.entities.Player;
import javagame.main.GamePanel;
import javagame.objects.Block;
import javagame.objects.Door;
import javagame.objects.Key;

/**
 * 
 * @author Vishal Narayan and David Lung
 * 
 * Level5State, extending abstract class GameState, represents 
 * the fifth level of the platform game.
 */
public class Level5State extends GameState {
	
	//private instance variables
	private String lives = "Lives: " + Player.LIVES;
	private String level = "Level: 5";
	private String StringKey = "";
	private Player player;
	private Door door;
	private Key key;
	private List<Block> blocks;
	private List<Water> waters;

	/**
	 * Calls the super method of GameStateManager constructor
	 * @param gsm the GameStateManager constructor passed
	 */
	public Level5State(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		player = new Player(20, 600);
		door = new Door(550, 500);
		key = new Key(450, 450);
		
		blocks = new ArrayList<Block>();
		waters = new ArrayList<Water>();
		addBlocks();
		addWaters();
	}
	
	//Method to add all the blocks in the level
	private void addBlocks(){
		
		blocks.add(new Block(0, 100, 30, 30));
		blocks.add(new Block(0, 375, 30, 30));
		blocks.add(new Block(50, 550, 30, 30));
		blocks.add(new Block(50, 200, 30, 30));
		
		blocks.add(new Block(80, 170, 50, 430));
		blocks.add(new Block(130, 170, 330, 50));
		
		blocks.add(new Block(500, 170, 50, 430));
		blocks.add(new Block(600, 170, 200, 60));
		blocks.add(new Block(550, 480, 200, 20));
		
		blocks.add(new Block(130, 350, 70, 30));
		blocks.add(new Block(300, 290, 200, 30));
		blocks.add(new Block(300, 420, 200, 20));
		blocks.add(new Block(130, 550, 300, 50));
	}
	
	//Method to add all the water blocks in the level
	private void addWaters(){
		waters.add(new Water(200, 350, 50, 30));
		waters.add(new Water(430, 575, 70, 25));
		waters.add(new Water(130, 380, 70, 170));
		
		waters.add(new Water(650, 230, 150, 30));
		waters.add(new Water(550, 320, 50, 30));
		waters.add(new Water(700, 350, 100, 30));
		waters.add(new Water(600, 450, 100, 30));
		waters.add(new Water(700, 580, 20, 20));
	}

	@Override
	public void tick() {
		player.tick();
		player.checkKey(key);
		if (player.hasKey){
			StringKey = "Key: Yes";
		}
		else{
			StringKey = "Key: No";
		}
		for (Block b : blocks){
			player.collision(b);
		}
		for (Water f: waters){
			if(player.checkWater(f)){
				if (player.LIVES > 1){
					player.LIVES -= 1;
					gsm.states.push(new Level5State(gsm));
				}else{
					gsm.states.push(new EndState(gsm));
				}
			}
		}
		if (player.checkDoor(door)){
			if (player.hasKey){
				gsm.states.push(new WinnerState(gsm));
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		g.setColor(Color.WHITE);
		g.drawString(lives, 10, 15);
		g.drawString(level, 375, 15);
		g.drawString(StringKey, 700, 15);
		for (Block b: blocks){
			b.draw(g);
		}
		for (Water f: waters){
			f.draw(g);
		}
		door.draw(g);
		key.draw(g);
		player.draw(g);
	}

	@Override
	public void keyPressed(int k) {
		player.keyPressed(k);
	}

	@Override
	public void keyReleased(int k) {
		player.keyReleased(k);
	}

}
