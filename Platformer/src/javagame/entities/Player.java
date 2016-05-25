package javagame.entities;



import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javagame.main.GamePanel;
import javagame.objects.*;

/**
 * 
 * @author Vishal Narayan and David Lung
 * Player class represents the player of the game.
 * It extends Rectangle, and contains an x and y 
 * coordinate, and also defines movement and block 
 * collision detections. 
 *
 */

public class Player extends Rectangle{
	
	private static final long serialVersionUID = 1L;
	
	//Player Lives
	public static int LIVES = 3;
	
	//Has Key
	public static boolean hasKey = false;
	
	//Movement
	private boolean right = false, left = false, jumping = false, falling = false;
	
	//Bounds
	private double x, y;
	private int width, height;
	
	//Velocity
	private double xvel = 2;
	private double yvel = 8;
	
	//Fall Speed
	private double maxFallSpeed = 2;
	private double currentFallSpeed = 0.1;
	
	
	private boolean bounce = false;
	private boolean moving = false;
	
	//Image to be displayed
	BufferedImage img = null;
	
	/**
	 * Constructs a Player and sets its position
	 * at the center of the screen. Sets the width and height
	 * of the player to 32, and sets the boolean hasKey to false.
	 */
	public Player(int xpos, int ypos){
		//Reads the image file to be drawn as the player.
		try {
			img = ImageIO.read(new File("images/character.png"));
		} catch (IOException e){
			e.printStackTrace();
		}
		
		x = xpos;
		y = ypos;
		this.width = 32;
		this.height = 32;
		setBounds((int)x, (int)y, width, height);
		hasKey = false;
	}

	/**
	 * This method is the game loop for Player,
	 * and repeats every frame.
	 */
	public void tick(){
		//check if moving right
		if (right){
			x += xvel;
		}
		//check if moving left
		if(left){
			x-=xvel;
		}
		
		//check if jumping
		if (jumping){
			
			y -= yvel;
			yvel -= 0.1;
			if (yvel <= 0){
				yvel = 8;
				jumping = false;
				falling = true;
			}
		}
		
		//check if falling
		if (falling){
			y+= currentFallSpeed;
			
			if(currentFallSpeed < maxFallSpeed){
				currentFallSpeed += 0.1;
			}
		}
		
		//set the Rectangle location
		this.setLocation((int)x, (int)y);
		
		checkBounds();
	}
	
	private void checkBounds(){
		//left-side
		if (x < 0){
			left = false;
			x = 0;
		}
		//right-side
		else if (x+width > GamePanel.WIDTH){
			right = false;
			x = GamePanel.WIDTH - width;
		}
		//top
		else if (y < 0){
			yvel = 0;
			jumping = false;
			falling = true;
			y = 0;
		}
		//bottom
		else if (y+height > GamePanel.HEIGHT){
			falling = false;
			y = GamePanel.HEIGHT - height;
			if (bounce) jumping = true;
		}
	}
	
	// These methods create Rectangles within the player to help with block collision detection
	private Rectangle getBoundsTop(){
		return new Rectangle((int)x+2, (int)y, width-4, height/2);
	}
	private Rectangle getBoundsBot(){
		return new Rectangle((int)x+2, (int)y+(height/2), width-4, height/2);
	}
	private Rectangle getBoundsLeft(){
		return new Rectangle((int)x, (int)y+2, 2, height-4);
	}
	private Rectangle getBoundsRight(){
		return new Rectangle((int)x+width-2, (int)y+2, 2, height-4);
	}
	
	
	/**
	 * Draws the Player using the image file.
	 * @param g the Graphics object used to draw
	 */
	public void draw(Graphics g){
		g.drawImage(img, (int)x, (int)y, new ImageObserver(){

			@Override
			public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
				return false;
			}
			
		});
	}
	
	/**
	 * Checks if a key is pressed, and sets the 
	 * movement boolean accordingly.
	 * @param k the integer passed
	 */
	public void keyPressed(int k){
		if(k == KeyEvent.VK_RIGHT){
			moving = true;
			right = true;
		}
		if(k == KeyEvent.VK_LEFT){
			moving = true;
			left = true;
		}
		if(k == KeyEvent.VK_UP){
			bounce  = true;
			if(!falling){
			    jumping = true;
			}
		}
	}
	
	/**
	 * Checks if a key is released, and sets the
	 * movement boolean accordingly.
	 * @param k
	 */
	public void keyReleased(int k){
		if(k == KeyEvent.VK_RIGHT){
			moving = false;
			right = false;
		}
		if(k == KeyEvent.VK_LEFT){
			moving = false;
			left = false;
		}
		if(k == KeyEvent.VK_UP) bounce = false;
	}
	
	
	/**
	 * Checks if the player is touching a Block
	 * object, and sets the respective instructions.
	 * @param b the Block object to check collision
	 */
	public void collision(Block b){
		double bbY = b.getY()+b.getHeight();
		falling = true;
		
		
		if (b.intersects(getBoundsTop())){
			yvel = 0;
			jumping = false;
			falling = true;
			y = bbY;
		}
		if (b.intersects(getBoundsBot())){
			falling = false;
			y = b.getY() - height;
			if (bounce) jumping = true;
		}
		if(b.intersects(getBoundsRight())){
			right = false;
			x = b.getX() - width;
			if(moving) right = true;
		}
		if (b.intersects(getBoundsLeft())){
			left = false;
			x = b.getX()+b.getWidth();
			if (moving) left = true;
		}
		
	}
	
	/**
	 * Check if the player is touching the door.
	 * @param d the Door object to be checked
	 * @return whether the player is touching the door.
	 */
	public boolean checkDoor(Door d){
		if (d.intersects(this)){
			return true;
		}else{return false;}
		
	}
	
	/**
	 * Check if the player is touching a Water object.
	 * @param f the Water object to be checked.
	 * @return whether the player is touching the fire.
	 */
	public boolean checkWater(Water f){
		if (f.intersects(this)){
			return true;
		}
		else{return false;}
	}
	
	/**
	 * Check if the player is touching the key.
	 * If the player is touching the key, boolean
	 * hasKey is set to true.
	 * @param k the Key object to be checked.
	 */
	public void checkKey(Key k){
		if (k.intersects(this)){
			hasKey = true;
			k.remove();
		}
	}
	


}
