// Visual nodes by Endri, edges by Christopher

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import java.awt.event.*;
import java.util.*;

public class VisualGraph extends JPanel {
	private int x;
	private int y;
	public double r = 30;
	public int bigradius = 240;
	public boolean ready = false;
	private ColorSet myColorSet;
	
	private int tick = 0;
	
	public VisualGraph(){
		JPanel nodes_panel = new JPanel();
		nodes_panel.setBounds(100, 100, 600, 600);
		nodes_panel.setLayout(null);
		nodes_panel.setOpaque(false);
		
		setLayout(null);
		setOpaque(false);
		
		//nodes = new Object[Group19Phase2.G.GetVertexCount()];
		
		int dia = VisualClickableNode.dia;
		
		for(int i = 0; i < Group19Phase2.G.GetVertexCount(); i++){
			// Create new clickable node
			VisualClickableNode new_node = new VisualClickableNode("?", convert_x(i), convert_y(i), i);
			
			// Set position & click bounds of clickable node
			new_node.setBounds(convert_x(i) - dia/2, convert_y(i) - dia/2, dia, dia);
			
			// Add new clickable node to the panel
			nodes_panel.add(new_node);
		}
		
		GUI.baseFrame.add(nodes_panel);
		GUI.baseFrame.revalidate();
		GUI.baseFrame.repaint();
		
		ready = true;
	}

	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		
		if (ready){
			//g2.setColor(new Color(220, 220, 220));
			//g2.fillRect(0, 30, 800, 800);
			
			for(int i = 0; i < Group19Phase2.G.GetVertexCount(); i++){
				boolean thisvertexselected = false;
				if (i == Game.selectedvertex) {
						thisvertexselected = true;
				}
				
				double n = ((double)i/Group19Phase2.G.GetVertexCount());
				
				List<Integer> L = Group19Phase2.G.Connected(i);
				
				for(int j = 0; j < L.size(); j++){
					int vv = L.get(j);
					
					if (thisvertexselected){
						g2.setStroke(new BasicStroke(3));
					}else{
						g2.setStroke(new BasicStroke(1));
					}

					if (Group19Phase2.G.CheckEdgeColouring(i, vv)){
						g2.setColor(Color.RED);
					}else{
						g2.setColor(Color.BLACK);
					}
					g2.drawLine(convert_x(i), convert_y(i), convert_x(vv), convert_y(vv));
				}
			}
			
			/*
			for(int i = 0; i < Group19Phase2.G.GetVertexCount(); i++){	
				int xx = convert_x(i) - (int)r/2;
				int yy = convert_y(i) - (int)r/2;
				int rr = (int)r;
				
				//g.drawOval(xx, yy, rr, rr);
				Color c = new Color(255, 255, 255);
				if (Group19Phase2.G.GetVertexColour(i) != -1){
					c = Game.colorset.getColor(Group19Phase2.G.GetVertexColour(i));
					g2.setColor(c);
					g2.fillOval(xx, yy, rr, rr);
				}else{
					c = Color.WHITE;
					g2.setColor(c);
					g2.fillOval(xx, yy, rr, rr);
					
					g2.setColor(Color.RED);
					g2.setFont(new Font("Arial", Font.PLAIN, 24)); 
					g2.drawString("?", xx + 8, yy + 24); 
				}
				
				if (i == Game.selectedvertex) {
					g2.setStroke(new BasicStroke(3));
					g2.setColor(new Color(255, 0, 0));
					g2.drawOval(xx - 4, yy - 4, rr + 8, rr + 8);
				}
			}*/
			
			int xx = convert_x(Game.selectedvertex) - (int)r/2;
			int yy = convert_y(Game.selectedvertex) - (int)r/2;
			int rr = (int)r;
			
			g2.setStroke(new BasicStroke(3));
			g2.setColor(new Color(255, 0, 0));
			g2.drawOval(xx - 4, yy - 4, rr + 8, rr + 8);
		}
	}
	
	public int convert_x(int v){
		double n = ((double)v/Group19Phase2.G.GetVertexCount());
		return 400 - (int)(r/2) + (int)(bigradius*-Math.cos(Math.PI * 2 * n));
	}
	
	public int convert_y(int v){
		double n = ((double)v/Group19Phase2.G.GetVertexCount());
		return 350 - (int)(r/2) + (int)(bigradius*-Math.sin(Math.PI * 2 * n));
	}
}