import javax.swing.*;    
import java.awt.event.*;  
import java.awt.*;    


class VisualOptionToAddMoreColours  extends JFrame implements ActionListener{    
  
 JFrame f; 
 JRadioButton r1; 
 JRadioButton r2;
 JButton b; 


VisualOptionToAddMoreColours(){  

JOptionPane.showMessageDialog(this,"DO WANT TO ADD MORE COLOURS ?");


f = new JFrame("ADD COLOURS");
f.setSize(400,500);    

r1=new JRadioButton("YES");    
r1.setBounds(100,50,100,50);      


r2=new JRadioButton("NO");    
r2.setBounds(100,100,100,50);    


ButtonGroup bgroup=new ButtonGroup();    

bgroup.add(r1);
bgroup.add(r2);    


b=new JButton("CLICK");    
b.setBounds(100,150,80,30);    
b.addActionListener(this);    

f.add(r1);
f.add(r2);
f.add(b);    

f.setLayout(null);    
f.setVisible(true);

f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    

}  



public void actionPerformed(ActionEvent ev){

if(r1.isSelected()){ 


JOptionPane.showMessageDialog(this,"Now you can add more colours.");    

}


if(r2.isSelected()){


JOptionPane.showMessageDialog(this,"You can continue.");    

}  
  }


public static void main(String args[]){    


new VisualOptionToAddMoreColours();    

}


  }   