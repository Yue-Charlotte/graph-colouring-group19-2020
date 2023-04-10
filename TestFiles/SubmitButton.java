import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class SubmitButton {
    SubmitButton(){    
        JFrame f = new JFrame(); 
        f.setSize(400,500);           

        JButton b=new JButton("SUBMIT");    
        b.setBounds(100,100,140, 40);    
                    

        JLabel la1 = new JLabel();        
        la1.setText("ENTER :");
        la1.setBounds(10, 10, 100, 100);


        JLabel la2 = new JLabel();
        la2.setBounds(10, 110, 200, 100);


        JTextField tf= new JTextField();
        tf.setBounds(110, 50, 130, 30);



        f.add(la2);
        f.add(tf);
        f.add(la1);
        f.add(b);        
        f.setLayout(null);    
        f.setVisible(true);    
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        


        b.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent arg0) {
                    la2.setText("IT HAS BEEN SUBMITTED.");                
            }          
          });
        }         

        public static void main(String[] args) {    
            new SubmitButton();    
        }

         }