import javax.swing.*;

public class Button{
  
  public static void main(String [] args){

   JFrame f = new JFrame();
   f.setSize(400,500);

   JLabel la = new JLabel("BUTTON WINDOW");
   la.setBounds(130,50,250,20);

   JButton b = new JButton("CLICK");
   b.setBounds(130,100,100,40);
   
   f.add(b);
   f.add(la);

   f.setLayout(null);
   f.setVisible(true);

   f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


  }	
}