// Visual timer code by Endri, logic implemented by Yuewei

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualTimer extends TimerLogic {

	JButton button;

	JPanel timerpanel;
	JLabel timerlabel;

	private long startTime;
	public JLabel a;
	public Timer timer;
	private double timeSpent;
	private long durationT;
	
	private long duration;

	public VisualTimer(long duration) {
		this.duration = duration;
		
		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} 


				catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
					ex.printStackTrace();
				}

				JButton b = new JButton("SUBMIT");  
				b.setBounds(610, 560, 170, 60); 	
				
				timerpanel = new JPanel();
				
				timerlabel = new JLabel("VISUAL TIMER");
				
				timerpanel.setBounds(0, 0, 800, 800);
				//timerpanel.setBounds(0, 0, 800, 40);
				
				Timing t = new Timing();
				
				timerpanel.add(timerlabel);
				timerpanel.add(b);
				
				GUI.baseFrame.add(timerpanel);
				GUI.baseFrame.repaint();
 
				
				button = b;
				buttonClick();

			}
		});
	}


	public void buttonClick() {

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				long stopTime = System.currentTimeMillis();
				long reactionTime = stopTime- startTime;

				timeSpent = reactionTime;
				
				// ActionListener listener1 = new SubmitListener(); 
				// button.addActionListener(listener1); 
				
				if(timeSpent < durationT) {
					submitPressCheck(timeSpent);
		//		printTimeMsg(timeSpent);
				}else {
					timeOutMethod(true);
				}
			}
		});

	}
	
	class Timing extends JPanel {
		private long beginTime = -1;

		public Timing() {

			setLayout(new GridBagLayout());
			timer = new Timer(10, new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					if (beginTime < 0) {
						beginTime = System.currentTimeMillis();
					}
					long now = System.currentTimeMillis();

					startTime = beginTime;
					durationT = duration;

					long clockTime = now - beginTime;
					

					if (clockTime >= duration) {
						clockTime = duration;
						System.out.println("Timer has ended");
						TimerLogic.timeOutMethod(true);
						
						timer.stop();
					}
					SimpleDateFormat df = new SimpleDateFormat("mm:ss:SSS");
					timerlabel.setText(df.format(duration - clockTime));
				}
			});
			
			timer.setInitialDelay(0);
			timer.start();
			
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (!timer.isRunning()) {
						beginTime = -1;
						timer.start();
					}
				}
			});
		}

	}
}
