package Phase2_Alpha;

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


		JFrame submitFrame = new JFrame(); 
		submitFrame.setSize(300,200);           

		JButton b=new JButton("SUBMIT");    
		b.setBounds(80,80,140, 40);    
		
		this.button = b;

		JLabel label = new JLabel();
		label.setBounds(10, 110, 200, 100);


		submitFrame.add(label);
		submitFrame.add(b);        

		submitFrame.setLayout(null);    
		submitFrame.setVisible(true);    
		submitFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	}
	

	public void resultWindowShow(int player,int answer,double timeSeconds,boolean check) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(check == true) {
					resultWindow(player,answer,timeSeconds,check,new ImageIcon(SubmitButton.class.getResource("result.jpg")));
				}else {
					resultWindow(player,answer,timeSeconds,check,new ImageIcon(SubmitButton.class.getResource("continue.jpg")));
				}
				
			}
		});
	}
	
	
    public void popupWinShow(String title, String text,Icon img) {
        int confirm = JOptionPane.showConfirmDialog(submitFrame, text, title, JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,img);
        if (confirm == JOptionPane.YES_OPTION){
            new StartScreen();
        }
    }
    
    public void popupSurrenderShow(String title, String text,Icon img) {
        int confirm = JOptionPane.showConfirmDialog(submitFrame, text, title, JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,img);
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
    	if(check == false) {
    		JOptionPane.showMessageDialog(null," You have not finish it! You still have vertex uncolored! "," Tell me you wanna continue ", 0, img); 
			popupSurrenderShow("Make sure again","You did not finish it ! Do you really wanna surrender?",new ImageIcon(SubmitButton.class.getResource("surrender.jpg")));
		}else{
			if(time > 0) {
				JOptionPane.showMessageDialog(null,"Your answer is " + player + "\n" + "The chromatic number should be " + answer + "\n" 
					+ "It took you " + time + "s seconds to complete the graph ","Is this a title? Oh it is.", 0, img); 
			}else {
			JOptionPane.showMessageDialog(null,"Your answer is " + player + "\n" + "The chromatic number should be " + answer ,"Is this a title? Oh it is.", 0, img); 
			}
			popupWinShow("Congratulations, you made it!","Do you wanna try again?",new ImageIcon(SubmitButton.class.getResource("win.jpg")));
		}
    }
    


}