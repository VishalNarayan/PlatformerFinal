package javagame.objects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author Vishal Narayan and David Lung
 * The Block class defines a standard platform
 * block, which is used by the level classes to 
 * construct each level. Block extends Rectangle, 
 * which allows it to set boundaries and use various
 * collision detection methods. 
 *
 */

public class Block extends Rectangle{
	private static final long serialVersionUID = 1L;
	
	private static final int blockSize = 60;
	
	BufferedImage img = null;
	
	/**
	 * Constructs Block object with default block size
	 * and puts it at the passed position.
	 * @param x the x-coordinate of the block
	 * @param y the y-coordinate of the block
	 */
	public Block(int x, int y){
		try {
			img = ImageIO.read(new File("images/block.png"));
		} catch (IOException e){
			e.printStackTrace();
		}
		setBounds(x, y, blockSize, blockSize);
		
		
	}
	
	/**
	 * Constructs Block object with the passed block size
	 * and puts it at the passed position. 
	 * @param x the x-coordinate of the block
	 * @param y the y-coordinate of the block
	 * @param sizeX the width of block
	 * @param sizeY the height of block
	 */
	public Block(int x, int y, int sizeX, int sizeY){
		try {
			img = ImageIO.read(new File("images/block.png"));
		} catch (IOException e){
			e.printStackTrace();
		}
		setBounds(x, y, sizeX, sizeY);
	}

	
	
	/**
	 * Draws the block
	 * @param g the Graphics object passed
	 */
	public void draw(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		TexturePaint tp = new TexturePaint(img, new Rectangle(0,0,16,16));
		g2d.setPaint(tp);
		g2d.fillRect(x, y, width, height);
		
	}
	
	
	
	
	

}
