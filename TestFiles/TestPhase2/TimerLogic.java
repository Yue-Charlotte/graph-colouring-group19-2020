package Phase2_Alpha;

import java.awt.event.ActionListener;

import javax.swing.*;
import Phase2_Alpha.Finish;


//import Phase2_Alpha.ChromaticNumber.ChromaticTimer;

public class TimerLogic {

//	private long timeSpent;

//	public static void main(String[] args) {
//		new TimerLogic();
//	}

	public TimerLogic() {
		
	}
	/**
	//Test use; when merged it will pass the time value to Finish Class
	public void printTimeMsg(long timeSpent) {
		
		System.out.println("The time you spent is " + timeStringFormat(timeSpent/1000));
	}

	public String timeStringFormat(long seconds) {
		long mins = seconds/60;
		seconds = seconds % 60;

		if (mins > 0) {
			return String.format("%d:%02d", mins, seconds);
		}

		else return seconds + " s";
	}
*/
	//press SubmitButton
	
	/**
	 * A draft idea: cuz mode 2 using util Timer now not swing Timer
	 * plan to add stop/start timer into mode 2 class
	 * or when start mode 2 automatically run the class visual Timer
	 */
	
	public void submitPressCheck(double timeSpent) {
		Finish.finishCheck2(timeSpent);
		// call the finish Check
		// the timer will not stop until player decided to give up the game (when player didn't finish that) or time out 
		// which means if player once finished (colored the vertex) and clicked finished 
		// it will automatically gave the resultWindow(No chance to let player decide to submit or not)
	}
	
	public void timeOutMethod(boolean check) {
		Finish.timeOutCheck();
	}

}