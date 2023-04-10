package Phase2_Alpha;

import java.awt.event.*;    
import java.awt.*;    
import javax.swing.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Finish extends StartScreen implements GameMode{
	
	private int gameModeSelect;
	private boolean completedOrNot;
	private int generatedVertex;
	private int playResult; // the number of colors player used 
	private int correctNumber; // the correct chromatic number
	private double timeSeconds;  // time player spent only under mode 2
	

	Finish(){
		
		// To test different result window below
		//finishPress(2);
		//timeOutCheck();
		
		/**
		 * The timer is only relevant for the upper bound game mode
		 * To judge if the window will show a time spent or not
		 * is "timeSeconds" > 0 or not, which means,if you click the submitButton before time out, the timeSeconds will not be 0
		 * Then the result window will automatically show how much time you spent. Otherwise it will not show it. 
		 */
		
	}

	public void finishPress(int gamemode) {

		gameModeSelect = gamemode;
		
		if(gameModeSelect == 2) {
			
//			timeSeconds = timeExtract;   Here needs a variable from the mode2 
			
//			timeSeconds = 5;  Test
			
		}
		
		finishCheck();


	}
	
	/** Method timeOutCheck
	 * Combination with mode 2, if the time is out in mode 2, directly call this method
	 */
	
	public void timeOutCheck() {
		
		SubmitButton timeOutAction = new SubmitButton();
		timeOutAction.popupSurrenderShow("Time Out!", "Time Out! Too slow!", new ImageIcon(SubmitButton.class.getResource("timeout.jpg")));
		
	}
	
	/** Method finishCheck
	 * 
	 * to check if the player colored all the vertexes 
	 * otherwise when you click the submit button, it will reminds you " you have not finished it! "
	 * if the player colored all the vertexes, and then click the submitButton, it will call the result window
	 * 
	 */
	
	
	public void finishCheck() {
		completedOrNot = true;
		
		SubmitButton action = new SubmitButton();
		
		if(current_vertex < generatedVertex) {  //don't know if this current_vertex is the same as "colored_vertex"
			completedOrNot = false;
		}
		
		action.resultWindowShow(playResult,correctNumber,timeSeconds,completedOrNot);
		
	}
	
	/**
	 * 
	 * To Test this uncommented below 

	public static void main(String[] args) {
		new Finish();

	}
	
	*/

	public int GetCurrentVertex() {
		return current_vertex;
	}

	public void SetCurrentVertex(int v) {
		
	}



}
