// Code by Yuewei

import java.awt.event.ActionListener;
import javax.swing.*;


public class TimerLogic {
	public TimerLogic() {
		
	}
	
	public void submitPressCheck(double timeSpent) {
		Finish.finishCheck(timeSpent);
		// call the finish Check
		// the timer will not stop until player decided to give up the game (when player didn't finish that) or time out 
		// which means if player once finished (colored the vertex) and clicked finished 
		// it will automatically gave the resultWindow(No chance to let player decide to submit or not)
	}
	
	public static void timeOutMethod(boolean check) {
		Finish.timeOutCheck();
	}

}