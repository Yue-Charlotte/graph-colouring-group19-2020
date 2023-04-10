package Phase2_Alpha;

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
	JFrame f;

	private long startTime;
	public JLabel a;
	public Timer timer;
	private double timeSpent;
	private long durationT;

		public static void main(String[] args) {
			new VisualTimer();
		}

	public VisualTimer() {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} 


				catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
					ex.printStackTrace();
				}

				JButton b=new JButton("SUBMIT");   


				JFrame f = new JFrame("Testing");

				f.add(new Testing());
				f.setSize(600,600);


				b.setBounds(20,20,20, 40);   
				f.pack();

				
				f.add(b);
				button = b;
				
				buttonClick();
				


				f.setLocationRelativeTo(null);
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
				
				ActionListener listener1 = new SubmitListener(); 
				button.addActionListener(listener1); 
				
				if(timeSpent < durationT) {
				submitPressCheck(timeSpent);
		//		printTimeMsg(timeSpent);
				}else {
					timeOutMethod(true);
				}
			}
		});

	}



	public class Testing extends JPanel {

		private long beginTime = -1;
		private long duration = 10000;
		private JLabel l;


		public Testing() {


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
						timer.stop();
					}
					SimpleDateFormat df = new SimpleDateFormat("mm:ss:SSS");
					l.setText(df.format(duration - clockTime));
				}
			});
			timer.setInitialDelay(0);
			addMouseListener(new MouseAdapter() {



				public void mouseClicked(MouseEvent e) {
					if (!timer.isRunning()) {
						beginTime = -1;
						timer.start();
					}
				}
			});



			l = new JLabel("VISUAL TIMER");
			add(l);


		}

		public Dimension Size() {
			return new Dimension(700,700);
		}

	}










}
