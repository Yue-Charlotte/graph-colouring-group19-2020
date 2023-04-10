import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class TestHint1 extends JFrame{

	JLabel msg,hint;
	JButton mark;
	
	public TestHint1(){

		msg=new JLabel("");
		msg.setBounds(50, 50, 100, 100);

		hint=new JLabel("");
		hint.setBounds(220, 50, 100, 50);

		mark=new JButton("?");
		mark.setBounds(160, 50, 50, 50);
		mark.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				//fill in the information. Click the ? mark to display the message, click again to disappear
				String msg2=msg.getText();
				if(msg2.equals("")){
					// I just put the information, the information can be changed here.
					msg.setText("<html>information<br>information<br>information<br></html>");
				}else {
					msg.setText("");
				}
			}
		});

		mark.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {

				//The hint is displayed when the mouse moves to the ? mark
				String h=hint.getText();
				if(h.equals("")){
					hint.setText("Hint");
				}
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				
				
			}
		});

		mark.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {

				//Hini disappears when the mouse leaves the ? mark
				String h=hint.getText();
				if(!h.equals("")){
					hint.setText("");
				}
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		add(hint);
		add(msg);
		add(mark);

		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 500);

	}

	public static void main(String[] args) {

		new TestHint1();
	}
}
