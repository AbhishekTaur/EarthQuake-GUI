package guimodule;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import processing.core.PApplet;
import processing.core.PImage;

/** 
 * A class to illustrate some use of the PApplet class
 * Used in module 3 of the UC San Diego MOOC Object Oriented Programming in Java
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 * 
 *
 */
public class MyPApplet extends PApplet{
	PImage img;
	
	public void setup() {
		//Add setup code for MyPApplet
		size(400,400);				//set canvas size
		background(255);			//set canvas color
		stroke(0);				//set pen color
		img = loadImage("palmTrees.jpg", "jpg");
	}
	
	public void draw() {
		//Add drawing code for MyPApplet
		img.resize(0, height);			//resize loaded image to full height of canvas
		image(img, 0, 0);		//display image
		DateFormat df = new SimpleDateFormat("HH");
	    Date dateobj = new Date();
	    String s = df.format(dateobj).toString();
	    int time = Integer.parseInt(s);
		
		int[] color = sunColorSec(Math.abs(time));		//calculate color code for sun
		fill(color[0],color[1],color[2]);	//set sun color
		ellipse(width/4,height/5,width/4,height/5);	//draw sun
	}
	
	/** Return the RGB color of the sun at this number of seconds in the minute */
	public int[] sunColorSec(int time)
	{
		int[] rgb = new int[3];
		// Scale the brightness of the yellow based on the seconds.  0 seconds 
		// is bright yellow.  30 seconds is black.
		float diffFrom24 = Math.abs(24-time);
		
		float ratio = diffFrom24/24;
		rgb[0] = (int)(255*ratio);
		rgb[1] = (int)(255*ratio);
		rgb[2] = 0;
		
		//System.out.println("R" + rgb[0] + " G" + rgb[1] + " B" + rgb[2]);
		return rgb;
	}	
	
	public static void main (String[] args) {
		//Add main method for running as application
		PApplet.main(new String[] {"--present", "MyPApplet"});
	}
}


