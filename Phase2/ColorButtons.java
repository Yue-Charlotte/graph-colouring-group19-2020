// Code by Payodh

import javax.swing.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.FlowLayout;


public class ColorButtons extends JPanel {
	// Variables declaration 
	private javax.swing.JLabel[] buttons;	// Button array
	private javax.swing.JLabel bextra;		// Extra button 
	JPanel buttpanel;	// Buttons panel
	
	// Total amount of colours at user's disposal
	int amountofbuttons = 13;
	
	public ColorButtons() {
		buttpanel = new JPanel();
		
		// Initialize the components of the button panel
		initComponents();
	}
	
	/** 
	* Get the button array index of the button we pressed. This is useful because we initialize the button in an array, and this lets us identify the button's index later on when it's pressed (because you can't use local variables when initializing the event methods in an array).
	* @param butt The button object that we clicked on.
	* @return Index of the pressed button
	*/
	private int GetArrayIndex(Object butt) {
		for(int i = 0; i < buttons.length; i++) {
			if (butt == buttons[i]){
				return i;
			}
		}
		
		return -1;
	}

	/** 
	* Initialize the components to go onto the visual button panel.
	*/
	private void initComponents() {
			// Initialize every button into an array
			buttons = new JLabel[13];	
			buttons[0] = new javax.swing.JLabel();
			buttons[1] = new javax.swing.JLabel();
			buttons[2] = new javax.swing.JLabel();
			buttons[3] = new javax.swing.JLabel();
			buttons[4] = new javax.swing.JLabel();
			buttons[5] = new javax.swing.JLabel();
			buttons[6] = new javax.swing.JLabel();
			buttons[7] = new javax.swing.JLabel();
			buttons[8] = new javax.swing.JLabel();
			buttons[9] = new javax.swing.JLabel();
			buttons[10] = new javax.swing.JLabel();
			buttons[11] = new javax.swing.JLabel();
			buttons[12] = new javax.swing.JLabel();
			
			JLabel bextra = new javax.swing.JLabel();
			
			// Initialize every button one by one
			for(int i = 0; i < 13; i++){
				
				buttons[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/button (" + i + ").png")));
				buttons[i].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
				buttons[i].addMouseListener(new java.awt.event.MouseAdapter() {
						// What happens when we click one of the buttons?
						public void mouseClicked(java.awt.event.MouseEvent evt) {
							int i = GetArrayIndex(evt.getSource());
							buttonMouseClicked(evt, i);
						}
						
						// Set the button image to indicate selection
						public void mouseEntered(java.awt.event.MouseEvent evt) {
							int i = GetArrayIndex(evt.getSource());
							buttons[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/button (i"+i+").png")));
						}
						
						// Reset the button image once it's no longer selected
						public void mouseExited(java.awt.event.MouseEvent evt) {
							int i = GetArrayIndex(evt.getSource());
							buttons[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/button (" + i + ").png")));
						}
				});
			}
			
			// Set up the "extra colours" button
			bextra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Add button.png"))); // NOI18N
			bextra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
			bextra.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent evt) {
							bextraMouseClicked(evt);
					}
					public void mouseEntered(java.awt.event.MouseEvent evt) {
						bextra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Button add.png")));
					}
					public void mouseExited(java.awt.event.MouseEvent evt) {
						bextra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Add button.png")));
					}
			});
			
			// Where on the screen do we display the button panel?
			final int buttpanel_y = 620;
			
			// Set up the button panel
			buttpanel.setBounds(0, buttpanel_y, 800, 800-buttpanel_y);
			buttpanel.setLayout(new FlowLayout());
			
			// Add all 13 colours to the panel
			for(int i = 0; i < amountofbuttons; i++){
				buttpanel.add(buttons[i]);
			}
			//buttpanel.add(bextra);
			
			GUI.baseFrame.add(buttpanel);
			
			GUI.baseFrame.repaint();
	}

	/**
	* Mouse Click event to select colour. When the user clicks a button, it changes the colour of the currently-selected vertex.
	* @param evt Click event source
	* @param b Index of button that was pressed (so, the index of the colour in the colorset class)
	* @return Nothing.
	*/
	private void buttonMouseClicked(java.awt.event.MouseEvent evt, int b) {
		Group19Phase2.gameManager.mode.ColourCurrentVertex(Group19Phase2.gameManager.selectedvertex, b);
	}
	
	/**
	* Mouse Click event for the "add new colour" button. Will probably be removed.
	* @param evt Click event source
	* @return Nothing.
	*/
	private void bextraMouseClicked(java.awt.event.MouseEvent evt) {
		/*amountofbuttons = Math.min(amountofbuttons + 1, 12);
		
		buttpanel.add(buttons[amountofbuttons]);
		GUI.baseFrame.revalidate();
		GUI.baseFrame.repaint();*/
	}
}
