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
 * Level2State, extending abstract class GameState, represents 
 * the second level of the platform game.
 */
public class Level2State extends GameState {
	
	//private instance variables
	private String lives = "Lives: " + Player.LIVES;
	private String level = "Level: 2";
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
	public Level2State(GameStateManager gsm) {
		super(gsm);
		
	}

	@Override
	public void init() {
		player = new Player(20, 300);
		door = new Door(0, 60);
		key = new Key(600, 200);
		
		blocks = new ArrayList<Block>();
		blocks.add(new Block(60, GamePanel.HEIGHT - 60, GamePanel.WIDTH-60, 60));
		blocks.add(new Block(120, GamePanel.HEIGHT - 120, GamePanel.WIDTH-120, 60));
		blocks.add(new Block(180, GamePanel.HEIGHT - 180, GamePanel.WIDTH-180, 60));
		blocks.add(new Block(240, GamePanel.HEIGHT - 240, GamePanel.WIDTH-240, 60));
		blocks.add(new Block(300, GamePanel.HEIGHT - 300, GamePanel.WIDTH-300, 60));
		blocks.add(new Block(0, 160, 250, 40));
		
		waters = new ArrayList<Water>();
		waters.add(new Water(400, 270, 100, 30));
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
					gsm.states.push(new Level2State(gsm));
				}else{
					gsm.states.push(new EndState(gsm));
				}
			}
		}
		if (player.checkDoor(door)){
			if (player.hasKey){
				gsm.states.push(new Level3State(gsm));
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

	
	public void keyReleased(int k) {
		player.keyReleased(k);
		
	}
	
	

}
