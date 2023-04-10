// Code by Payodh

import java.util.*;
import java.awt.Color;

public class ColorSet {
	private Color[] mColors;
	private int colorcount;
  
	// List of Colours
	public ColorSet() {
		mColors = new Color[13];
		
		mColors[0] = new Color(255, 0, 0);			// RED
		mColors[1] = new Color(255, 153, 0);		// ORANGE
		mColors[2] = new Color(255, 255, 0);		// YELLOW
		mColors[3] = new Color(102, 255, 51);		// GREEN
		mColors[4] = new Color(51, 204, 204);		// CYAN
		mColors[5] = new Color(0, 0, 255);			// BLUE
		mColors[6] = new Color(204, 51, 153);		// PURPLE
		mColors[7] = new Color(255, 0, 255);		// PINK
		mColors[8] = new Color(255, 204, 255);	// LIGHT PINK 
		mColors[9] = new Color(160, 160, 160);	// GRAY
		mColors[10] = new Color(100, 100, 100);	// DARK GRAY
		mColors[11] = new Color(255, 255, 255);	// WHITE		
		mColors[12] = new Color(0, 0, 0);				// BLACK
			
		colorcount = 13;	// Total amount of colours
	}
	
	// Method to get a random color
	public Color getRandomColor() {
		int c = (int)(Math.random()*mColors.length);
		return mColors[c];
	}
	
	/** 
	* Method to get specific color from the set.
	* @param c Colour index
	* @return Color object with the appropriate colour code.
	*/
	public Color getColor(int c){
		return mColors[c];
	}
	
	// Method to get total color count
	public int GetColorCount(){
		return colorcount;
	}
}