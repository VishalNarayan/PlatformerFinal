package javagame.objects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * 
 * @author Vishal Narayan and David Lung
 * The Key class represents a key, which 
 * is instantiated by each level class. 
 * Key extends Rectangle, which allows it 
 * to set boundaries and use various collision 
 * detection methods. 
 *
 */
public class Key extends Rectangle{
	
	private static final long serialVersionUID = 1L;
	
	//Dimensions
	private static final int WIDTH = 20;
	private static final int HEIGHT = 20;
	
	BufferedImage img = null;
	
	/**
	 * Constructs a Key object and sets its image file to a key
	 * @param x the x-coordinate of the Key
	 * @param y the y-coordinate of the Key
	 */
	public Key(int x, int y){
		setBounds(x, y, WIDTH, HEIGHT);
		try {
			img = ImageIO.read(new File("images/key.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Removes the key by setting its location to outside the screen
	 */
	public void remove(){
		setBounds(800, 600, WIDTH, HEIGHT);
	}
	
	/**
	 * Draws a key.
	 * @param g the Graphics object passed
	 */
	public void draw(Graphics g){
		g.drawImage(img, x, y, new ImageObserver(){

			@Override
			public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
				return false;
			}
			
		});
	}

}
