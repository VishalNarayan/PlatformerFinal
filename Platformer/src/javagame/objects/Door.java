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
 * The Door class represents a door, which 
 * is instantiated by each level class. 
 * Door extends Rectangle, which allows it 
 * to set boundaries and use various collision 
 * detection methods. 
 *
 */
public class Door extends Rectangle{
	
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 60;
	private static final int HEIGHT = 100;
	BufferedImage img = null;
	
	/**
	 * Constructs a door object and sets the image file to a door.
	 * @param x the x-coordinate of the Door
	 * @param y the y-coordinate of the Door
	 */
	public Door(int x, int y){
		setBounds(x, y, WIDTH, HEIGHT);
		try {
			img = ImageIO.read(new File("images/door.gif"));
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Draws the door.
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
