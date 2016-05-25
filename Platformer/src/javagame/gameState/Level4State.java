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
 * Level4State, extending abstract class GameState, represents 
 * the fourth level of the platform game.
 */
public class Level4State extends GameState {
	
	//private instance variables
	private String lives = "Lives: " + Player.LIVES;
	private String level = "Level: 4";
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
	public Level4State(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		player = new Player(20, 600);
		door = new Door(0, 50);
		key = new Key(770, 570);
		
		blocks = new ArrayList<Block>();
		waters = new ArrayList<Water>();
		addBlocks();
		addWaters();
	}
	
	//Method to add all the blocks in the level
	private void addBlocks(){
		
		blocks.add(new Block(0, 150, 80, 35));
		blocks.add(new Block(0, GamePanel.HEIGHT/2, 160, 35));
		
		blocks.add(new Block(80, 580, 60, 20));
		blocks.add(new Block(180, 540, 60, 60));
		blocks.add(new Block(280, 500, 60, 100));
		
		blocks.add(new Block(350, 0, 60, 260));
		blocks.add(new Block(350, 340, 60, 260));
		
		blocks.add(new Block(470, 460, 40, 140));
		blocks.add(new Block(570, 450, 80, 150));
	}
	
	//Method to add all the water blocks in the level
	private void addWaters(){
		waters.add(new Water(140, 540, 40, 60));
		waters.add(new Water(240, 500, 40, 100));
		waters.add(new Water(340, 480, 10, 120));
		
		waters.add(new Water(410, 460, 60, 140));
		waters.add(new Water(510, 450, 60, 150));
		waters.add(new Water(650, 525, 110, 75));
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
					gsm.states.push(new Level4State(gsm));
				}else{
					gsm.states.push(new EndState(gsm));
				}
			}
		}
		if (player.checkDoor(door)){
			if (player.hasKey){
				gsm.states.push(new Level5State(gsm));
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		g.setColor(Color.WHITE);
		g.drawString(lives, 10, 15);
		g.drawString(level, 410, 15);
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
