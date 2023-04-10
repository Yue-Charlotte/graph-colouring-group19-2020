import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class hint1 {

    public static void main(String args[]) {

        JFrame frame = new JFrame("Colour Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        JMenuBar mb = new JMenuBar();

        JMenu m1 = new JMenu("Hint");
        mb.add(m1);

        JMenuItem m11 = new JMenuItem("Bound selection");
        JMenuItem m12 = new JMenuItem("Color choice");
        
        // It can open
        m11.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
                
                // we can put the bound selection information in there, user can read it.
                new MyFrame("<html>Hint information of bound selection：<br>1. Select any vertex to start coloring. <br>2. Color adjacent vertices, or color according to the connection between vertices.</html>");
			}
        });
        
        m12.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

                // information of colour choice
				new MyFrame("<html>Hint information of color choice： <br>1. Any color can be used. <br>2. A color can be reused, but cannot be used on two connected vertices. <br>3. Use as few colors as possible.</html>");
				
			}
        });
        
        m1.add(m11);
        m1.add(m12);

        JPanel panel = new JPanel(); 
        JLabel label = new JLabel("Bound selection can help you choose the next bound, Color choice can help you choose the next colour");
        panel.add(label); 

        JTextArea ta = new JTextArea();

        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);

    }
    
}

//new window of the bound and colour
class MyFrame extends JFrame{
	
	public MyFrame(String con){

		JLabel label=new JLabel(con);
		this.setLayout(new FlowLayout());
		this.add(label);
		this.setSize(500, 250);
        this.setVisible(true);
        
    }
    
}