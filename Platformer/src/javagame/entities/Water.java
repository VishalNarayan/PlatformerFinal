package javagame.entities;

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
 * The Water class defines a fire object which reduces the Player's life
 * if touched. It extends Rectangle, and displays a water image using a 
 * Texture.
 *
 */
public class Water extends Rectangle {
	
	private static final long serialVersionUID = 1L;
	private int width, height;
	
	BufferedImage img = null;
	
	 /**
	  * Constructs a Water object
	  * @param x the x-value of the Water object
	  * @param y the y-value of the Water object
	  * @param w the width of the Water object
	  * @param h the height of the Water object
	  */
	public Water(int x, int y, int w, int h){
		//reads the water image file to create texture
		try {
			img = ImageIO.read(new File("images/water.gif"));
		} catch (IOException e){
			e.printStackTrace();
		}
		
		setBounds(x, y, w, h);
		width = w;
		height = h;
	}
	
	/**
	 * Draws the Water object, using a texture to make it look like water
	 * @param g the Graphics object passed
	 */
	public void draw(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		TexturePaint tp = new TexturePaint(img, new Rectangle(0,0,16,16));
		g2d.setPaint(tp);
		g2d.fillRect(x, y, width, height);
	}

}
