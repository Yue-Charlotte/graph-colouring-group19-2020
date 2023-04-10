import java.awt.event.*;    
import java.awt.*;    
import javax.swing.*;


public class ColorSelection extends JFrame implements ActionListener {

JButton b;    
Container c;    
ColorSelection(){    
    c=getContentPane();    
    c.setLayout(new FlowLayout());         
    b=new JButton("color");    
    b.addActionListener(this);         
    c.add(b);

    JLabel la = new JLabel("YOU CAN CHOOSE A COLOR HERE");
la.setBounds(130,50,250,20);
c.add(la);
    
}    
public void actionPerformed(ActionEvent e) {    
Color initialcolor=Color.RED;    
Color color=JColorChooser.showDialog(this,"Select a color",initialcolor);    
c.setBackground(color);    
}    
    
public static void main(String[] args) { 


    ColorSelection ch=new ColorSelection();    
    ch.setSize(400,500);    
    ch.setVisible(true);    
    ch.setDefaultCloseOperation(EXIT_ON_CLOSE);    
}    
}    