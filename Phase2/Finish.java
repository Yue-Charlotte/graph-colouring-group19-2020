// Code by Yuewei

import java.awt.event.*;    
import java.awt.*;    
import javax.swing.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Finish extends GUI{
	
	private static int gameModeSelect;
	private static boolean completedOrNot;
	private static int generatedVertex;
	private static int playResult; // the number of colors player used 
	private static int correctNumber; // the correct chromatic number
	private static double timeSeconds;  // time player spent only under mode 2
	

	Finish(){
		/**
		 * The timer is only relevant for the upper bound game mode
		 * To judge if the window will show a time spent or not
		 * is "timeSeconds" > 0 or not, which means,if you click the submitButton before time out, the timeSeconds will not be 0
		 * Then the result window will automatically show how much time you spent. Otherwise it will not show it. 
		 */
		
	}
	
	/** Method timeOutCheck
	 * Combination with mode 2, if the time is out in mode 2, directly call this method
	 */
	public static void timeOutCheck() {
		
		SubmitButton timeOutAction = new SubmitButton();
		timeOutAction.popupSurrenderShow("Out of time!", "Too slow... Do you wish to try again?");
		
	}
	
	/** Method finishCheck
	 * 
	 * to check if the player colored all the vertexes 
	 * otherwise when you click the submit button, it will reminds you " you have not finished it! "
	 * if the player colored all the vertexes, and then click the submitButton, it will call the result window
	 * 
	 */
	public static void finishCheck(double timeSpent) {
		completedOrNot = true;
		
		SubmitButton action = new SubmitButton();
		
		if (Group19Phase2.G.HasUncolouredVertices() | Group19Phase2.G.AmountOfBadEdges() > 0) {  //don't know if this current_vertex is the same as "colored_vertex"
			System.out.println("FFFF");
			completedOrNot = false;
		}
		
		playResult = Group19Phase2.G.AmountOfColoursUsed();
		correctNumber = Game.chromaticnumber;
		
		action.resultWindowShow(playResult, correctNumber, timeSpent, completedOrNot);
	}
}
