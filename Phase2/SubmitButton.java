// Code by Yuewei

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class SubmitButton {
	JFrame 	submitFrame;
	JButton button;
	
	/**
	 * To be able to run, remember to put win.jpg/surrender.jpg/timeout.jpg/continue.jpg/result.jpg
	 * with the same position of SubmitButton Class
	 * 
	 */

	public SubmitButton(){    

	}
	

	public void resultWindowShow(int player,int answer,double timeSeconds,boolean check) {
		//button.addActionListener(new ActionListener() {
		//	public void actionPerformed(ActionEvent e) {
				if(check == true) {
					resultWindow(player,answer,timeSeconds,check);
				}else {
					resultWindow(player,answer,timeSeconds,check);
				}
				
		//	}
		//});
	}
	
	
    public void popupWinShow(String title, String text) {
        int confirm = JOptionPane.showConfirmDialog(submitFrame, text, title, JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION){
					Group19Phase2.RestartGame();
        }else{
					System.exit(0);
				}
    }
    
    public void popupSurrenderShow(String title, String text) {
        int confirm = JOptionPane.showConfirmDialog(submitFrame, text, title, JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION){
        	Group19Phase2.RestartGame();
        }else{
					System.exit(0);
				}
    }
    
    public void popupTimeOutShow(String title, String text) {
        int confirm = JOptionPane.showConfirmDialog(submitFrame, text, title, JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION || confirm == JOptionPane.NO_OPTION){
        	Group19Phase2.RestartGame();
        }else{
					System.exit(0);
				}
    }
    
    
    public void resultWindow(int player, int answer, double time, boolean check) {
		int timeint = (int)(time/1000);
		String timestring = timeStringFormat(timeint);
		
			if(check == false) {
				String result = "";
				if (Group19Phase2.G.HasUncolouredVertices()){
					result += "Some vertices are still uncoloured.\n";
				}
				if (Group19Phase2.G.AmountOfBadEdges() > 1) {
					result += "Some edges have an invalid connection.\n";
				}else if (Group19Phase2.G.AmountOfBadEdges() > 0) {
					result += "One edge has an invalid connection.\n";
				}
				
				JOptionPane.showMessageDialog(null, result + "You lost.", "Unfortunate...", JOptionPane.PLAIN_MESSAGE); 
				popupSurrenderShow(":(", "Would you like to try again?");
			}else{
				
				String result = "You have used " + player + " colours.\n" + "The graph's chromatic number is " + answer + ".";
				if (player == answer) result += "\nYou won!";
				else result += "\nBetter luck next time...";
				
				if(time > 0) {
					JOptionPane.showMessageDialog(null, result  + "\nIt took you " + timestring + " to complete the graph.", "Results!", JOptionPane.PLAIN_MESSAGE); 
				}else {
					JOptionPane.showMessageDialog(null, result, "Results!", JOptionPane.PLAIN_MESSAGE); 
				}
				popupWinShow(null, "Do you want to try again?");
			}
	}
    
	public String timeStringFormat(int time) {
		double mins = time/60;
		time = time % 60;

		if (mins > 0) {
			return String.format("%f:%2f", (double)mins, (double)time);
		}

		else return time + " seconds";
	}

}