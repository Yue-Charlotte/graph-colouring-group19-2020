// importing the swing package and all classes
import javax.swing.*;
//importing the util package and Scanner class
import java.util.Scanner;

public class ResultMessage {

public static void main(String[] args){

JFrame f = new JFrame("ResultMessage");	

//Showing a Message to user to enter the name

JOptionPane.showMessageDialog(f,"Please enter your name");
System.out.println("Type your name here");

//Creating the "in" object and  inputing the name of the user

Scanner in = new Scanner(System.in);

String name = in.nextLine();

//Printing the messages

JOptionPane.showMessageDialog(f,"The game is over");

JOptionPane.showMessageDialog(f,"the score of _"+ name+"_is :");
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

f.setLayout(null);
f.setVisible(true);

}	 
  }