
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class colortest extends JFrame {

	private JPanel contentPane;
	JButton button_4 = new JButton("Color hint");
	Color have;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					colortest frame = new colortest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public colortest() {
		
		ColorSet cs = new ColorSet();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 923, 606);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		button_4.setBounds(626, 200, 116, 82);
		button_4.setBackground(cs.getRandomColor());
		button_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Color cl = cs.getRandomColor();
				while(have==cl)
				{
					cl = cs.getRandomColor();
				}
				button_4.setBackground(cl);
			}
		});
		contentPane.add(button_4);
		
		JButton button_1 = new JButton("");
		button_1.setBounds(37, 73, 132, 49);
		button_1.setBackground(cs.getColor(0));
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.setBounds(37, 134, 132, 49);
		button_2.setBackground(cs.getColor(1));
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("");
		button_3.setBounds(37, 194, 132, 49);
		button_3.setBackground(cs.getColor(2));
		contentPane.add(button_3);
		
		JButton button_5 = new JButton("");
		button_5.setBounds(37, 256, 132, 49);
		button_5.setBackground(cs.getColor(3));
		contentPane.add(button_5);
		
		JButton button_6 = new JButton("");
		button_6.setBounds(37, 315, 132, 49);
		button_6.setBackground(cs.getColor(4));
		contentPane.add(button_6);
		
		JButton button_7 = new JButton("");
		button_7.setBounds(37, 374, 132, 49);
		button_7.setBackground(cs.getColor(5));
		contentPane.add(button_7);
		
		JButton button_8 = new JButton("");
		button_8.setBounds(37, 433, 132, 49);
		button_8.setBackground(cs.getColor(6));
		contentPane.add(button_8);
		
		JButton button = new JButton("");
		button.setBackground(cs.getColor(7));
		button.setBounds(186, 73, 132, 49);
		contentPane.add(button);
		
		JButton button_9 = new JButton("");
		button_9.setBackground(cs.getColor(8));
		button_9.setBounds(186, 134, 132, 49);
		contentPane.add(button_9);
		
		JButton button_10 = new JButton("");
		button_10.setBackground(cs.getColor(9));
		button_10.setBounds(186, 194, 132, 49);
		contentPane.add(button_10);
		
		JButton button_11 = new JButton("");
		button_11.setBackground(cs.getColor(10));
		button_11.setBounds(186, 256, 132, 49);
		contentPane.add(button_11);
		
		JButton button_12 = new JButton("");
		button_12.setBackground(cs.getColor(11));
		button_12.setBounds(186, 315, 132, 49);
		contentPane.add(button_12);
		
		JButton button_13 = new JButton("");
		button_13.setBackground(cs.getColor(12));
		button_13.setBounds(186, 374, 132, 49);
		contentPane.add(button_13);
		
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				button_4.setBackground(button_1.getBackground());
				have=button_1.getBackground();
			}
		});
		button_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				button_4.setBackground(button_2.getBackground());
				have=button_2.getBackground();
			}
		});
		button_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				button_4.setBackground(button_3.getBackground());
				have=button_3.getBackground();
			}
		});
		button_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				button_4.setBackground(button_5.getBackground());
				have=button_5.getBackground();
			}
		});
		button_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				button_4.setBackground(button_6.getBackground());
				have=button_6.getBackground();
			}
		});
		button_7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				button_4.setBackground(button_7.getBackground());
				have=button_7.getBackground();
			}
		});
		button_8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				button_4.setBackground(button_8.getBackground());
				have=button_8.getBackground();
			}
		});
		button_9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				button_4.setBackground(button_9.getBackground());
				have=button_9.getBackground();
			}
		});
		button_10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				button_4.setBackground(button_10.getBackground());
				have=button_10.getBackground();
			}
		});
		button_11.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				button_4.setBackground(button_11.getBackground());
				have=button_11.getBackground();
			}
		});
		button_12.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				button_4.setBackground(button_12.getBackground());
				have=button_12.getBackground();
			}
		});
		button_13.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				button_4.setBackground(button_13.getBackground());
				have=button_13.getBackground();
			}
		});
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				button_4.setBackground(button.getBackground());
				have=button.getBackground();
			}
		});
		
		
	}
}