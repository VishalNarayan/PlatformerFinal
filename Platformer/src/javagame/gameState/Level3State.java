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
 * Level3State, extending abstract class GameState, represents 
 * the third level of the platform game.
 */
public class Level3State extends GameState{
	
	//private instance variables
	private String lives = "Lives: " + Player.LIVES;
	private String level = "Level: 3";
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
	public Level3State(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		player = new Player(10, 600);
		door = new Door(340, 200);
		key = new Key(770, 570);
		
		blocks = new ArrayList<Block>();
		addBlocks();
		
		waters = new ArrayList<Water>();
		waters.add(new Water(320, 300, 120, 20));
		waters.add(new Water(60, 570, 620, 30));
	}
	
	//Method to add all the blocks in the level
	private void addBlocks(){

		blocks.add(new Block(80, 80, 40, 40));
		blocks.add(new Block(120, 120, 40, 40));
		blocks.add(new Block(160, 160, 40, 40));
		blocks.add(new Block(200, 200, 40, 40));
		blocks.add(new Block(240, 240, 40, 40));
		blocks.add(new Block(280, 280, 40, 40));
		blocks.add(new Block(320, 320, 120, 40));
		blocks.add(new Block(440, 280, 40, 40));
		blocks.add(new Block(480, 240, 40, 40));
		blocks.add(new Block(520, 200, 40, 40));
		blocks.add(new Block(560, 160, 40, 40));
		blocks.add(new Block(600, 120, 40, 40));
		blocks.add(new Block(640, 80, 40, 40));
		blocks.add(new Block(680, 80, 40, GamePanel.HEIGHT-80));
		blocks.add(new Block(40, GamePanel.HEIGHT - 80, 40, 40));
		blocks.add(new Block( 0, GamePanel.HEIGHT - 160, 40, 40));
		blocks.add(new Block(40, GamePanel.HEIGHT - 240, 40, 40));
		blocks.add(new Block(0, GamePanel.HEIGHT - 320, 40, 40));
		blocks.add(new Block( 40, GamePanel.HEIGHT - 400, 40, 40));
		blocks.add(new Block( GamePanel.WIDTH-80, GamePanel.HEIGHT - 80, 40, 40));
		blocks.add(new Block(GamePanel.WIDTH-40, GamePanel.HEIGHT - 160, 40, 40));
		blocks.add(new Block(GamePanel.WIDTH-80, GamePanel.HEIGHT - 240, 40, 40));
		blocks.add(new Block(GamePanel.WIDTH-40, GamePanel.HEIGHT - 320, 40, 40));
		blocks.add(new Block (GamePanel.WIDTH-80, GamePanel.HEIGHT - 400, 40, 40));
        
		
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
					gsm.states.push(new Level3State(gsm));
				}else{
					gsm.states.push(new EndState(gsm));
				}
			}
		}
		if (player.checkDoor(door)){
			if (player.hasKey){
				gsm.states.push(new Level4State(gsm));
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
