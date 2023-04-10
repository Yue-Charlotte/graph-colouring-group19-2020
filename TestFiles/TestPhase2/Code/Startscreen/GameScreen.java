import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameScreen extends Start implements ActionListener{
	GameScreen(){
		int v = 6; //v will need to be the amount of vectors

		JButton[] vectors = new JButton[v];

		for(int i = 0; i<v; i+=4){
		if(i<v){
			vectors[i] = new JButton();
			vectors[i].setBounds(300 + (10*i), 50 - (10*i), 10, 10);
		}
		if(i+1<v){
			vectors[i] = new JButton();
			vectors[i].setBounds(550 - (10*i), 300 - (10*i), 10, 10);
		}
		if(i+2<v){
			vectors[i] = new JButton();
			vectors[i].setBounds(300 - (10*i), 550 + (10*i), 10, 10);
		}
		if(i+3<v){
			vectors[i] = new JButton();
			vectors[i].setBounds(50 + (10*i), 550 + (10*i), 10, 10);
		}

		}

		this.setTitle("GameScreen");
		this.setSize(600,600);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}


		public void actionPerformed(ActionEvent e){
			//if(e.getSource()==button){}

		}
	}