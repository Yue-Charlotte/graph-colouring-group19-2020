// Code by Endri

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;


public class VisualClickableNode extends JButton implements ActionListener {
	
	private boolean mouseOver = false;
	private boolean mousePressed = false;

	private int x;		// Where am I?
	private int y;		
	private int id;		// Which vertex am I?
	
	public static int dia = 30;

	public VisualClickableNode(String text, int x, int y, int id){
		
		super(text);
		setOpaque(false);
		setFocusPainted(false);
		setBorderPainted(false);
		//setBounds(x + getWidth()/2 - getDiameter()/2, y + getHeight()/2 - getDiameter()/2, getDiameter(), getDiameter());
		
		this.x = x;
		this.y = y;
		this.id = id;
		
		setBounds(x, y, getDiameter(), getDiameter());
		
		addActionListener(this);
		
		MouseAdapter mouseListener = new MouseAdapter(){
			
 			public void mousePressed(MouseEvent me){
				if(contains(me.getX(), me.getY())){
					mousePressed = true;
					repaint();
				}
			}
			
			
			
			public void mouseReleased(MouseEvent me){
				mousePressed = false;
				repaint();
			}
			
 			

 			public void mouseExited(MouseEvent me){
				mouseOver = false;
				mousePressed = false;
				repaint();
			}
			
 			

 			public void mouseMoved(MouseEvent me){
				mouseOver = contains(me.getX(), me.getY());
				repaint();
			}	
		};
		
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);	
			
		System.out.println("New clickable at " + this.x + " " + this.y);
	}
	
	


	private int getDiameter(){
		int diameter = Math.min(getWidth(), getHeight());
		return diameter;
	}
	
 	

 	public boolean contains(int x, int y){
		int rad = getDiameter()/2;
		return Point2D.distance(x, y, getWidth()/2, getHeight()/2) < rad;
	}
	
	public void actionPerformed(ActionEvent e){
		Group19Phase2.gameManager.mode.SetCurrentVertex(id);
		GUI.baseFrame.repaint();
	}
		


 	public void paintComponent(Graphics g){
		
		int diameter = getDiameter();
		int rad = diameter/2;

		g.setColor(Color.BLACK);	
		g.fillOval(getWidth()/2 - rad, getHeight()/2 - rad, diameter, diameter);

		if(mousePressed){
			g.setColor(Color.YELLOW);
		}
		

		else{
			Color c = Color.WHITE;
			
			if (Group19Phase2.G.GetVertexColour(id) != -1){
					c = Game.colorset.getColor(Group19Phase2.G.GetVertexColour(id));
					g.setColor(c);
			}
			
			g.setColor(c);
		}
		
		

		g.fillOval(getWidth()/2 - rad + 1, getHeight()/2 - rad + 1, diameter - 2, diameter - 2);
		
		

		if(mouseOver){
			g.setColor(Color.RED);
			g.drawOval(getWidth()/2 - rad, getHeight()/2 - rad, diameter, diameter);
		}
		
		if (Group19Phase2.G.GetVertexColour(id) == -1){
			g.setColor(Color.RED);
			g.setFont(getFont());
			
			FontMetrics metrics = g.getFontMetrics(getFont());
			
			int stringWidth = metrics.stringWidth(getText());
			
			int stringHeight = metrics.getHeight();
			
			g.drawString(getText(), getWidth()/2 - stringWidth/2, getHeight()/2 + stringHeight/4);
		}
	}
}

