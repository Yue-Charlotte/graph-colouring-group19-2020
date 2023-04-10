package Phase2_Alpha;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Phase2_Alpha.Finish;
import Phase2_Alpha.VisualTimer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SubmitListener implements ActionListener {

	JFrame 	submitFrame;
	JButton button;
	private static int play;
	private static int ans;
	private static double ts;
	private static boolean chec;
	

	public static void passValue(int player,int answer,double timeSeconds,boolean check) {
		play = player;
		ans = answer;
		ts = timeSeconds;
		chec = check;
	}

	public void actionPerformed(ActionEvent e) {
		if(chec == true) {
			resultWindow(play,ans,ts,chec,new ImageIcon(SubmitButton.class.getResource("result.jpg")));
		}else {
			resultWindow(play,ans,ts,chec,new ImageIcon(SubmitButton.class.getResource("continue.jpg")));
		}

	}



	public void popupWinShow(String title, String text,Icon img) {
		int confirm = JOptionPane.showConfirmDialog(submitFrame, text, title, JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,img);
		if (confirm == JOptionPane.YES_OPTION){
			new StartScreen();
		}
	}

	public static void popupSurrenderShow(String title, String text,Icon img) {
		int confirm = JOptionPane.showConfirmDialog(null, text, title, JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,img);
		if (confirm == JOptionPane.YES_OPTION){
			System.exit(0);
		}
	}

	public void popupTimeOutShow(String title, String text,Icon img) {
		int confirm = JOptionPane.showConfirmDialog(submitFrame, text, title, JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,img);
		if (confirm == JOptionPane.YES_OPTION || confirm == JOptionPane.NO_OPTION){
			System.exit(0);
		}
	}


	public void resultWindow(int player,int answer,double time,boolean check,Icon img) {
		int timeint = (int) (time/1000);
		String timetest = timeStringFormat(timeint);

		if(check == false) {
			JOptionPane.showMessageDialog(null," You have not finish it! You still have vertex uncolored! "," Tell me you wanna continue ", 0, img); 
			popupSurrenderShow("Make sure again","You did not finish it ! Do you really wanna surrender?",new ImageIcon(SubmitButton.class.getResource("surrender.jpg")));
		}else{
			if(time > 0) {
				JOptionPane.showMessageDialog(null,"Your answer is " + player + "\n" + "The chromatic number should be " + answer + "\n" 
						+ "It took you " + timetest + " seconds to complete the graph ","Is this a title? Oh it is.", 0, img); 
			}else {
				JOptionPane.showMessageDialog(null,"Your answer is " + player + "\n" + "The chromatic number should be " + answer ,"Is this a title? Oh it is.", 0, img); 
			}
			popupWinShow("Congratulations, you made it!","Do you wanna try again?",new ImageIcon(SubmitButton.class.getResource("win.jpg")));
		}
	}




	public String timeStringFormat(int time) {
		double mins = time/60;
		time = time % 60;

		if (mins > 0) {
			return String.format("%d:%02d", mins, time);
		}

		else return time + " s";
	}

}
