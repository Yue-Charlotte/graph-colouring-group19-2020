import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualTimer {

    public static void main(String[] args) {
        new VisualTimer();
    }

    public VisualTimer() {
        EventQueue.invokeLater(new Runnable() {
     
    public void run() {
        
        try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } 


            catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame f = new JFrame("Testing");
                f.add(new Testing());
                f.pack();
                f.setLocationRelativeTo(null);
                f.setVisible(true);
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }
        });
    }

    public class Testing extends JPanel {

        private Timer timer;
        private long beginTime = -1;
        private long duration = 10000;


private JLabel l;

        public Testing() {
            setLayout(new GridBagLayout());
            timer = new Timer(10, new ActionListener() {
                



                public void actionPerformed(ActionEvent e) {
                    if (beginTime < 0) {
                        beginTime = System.currentTimeMillis();
                    }
                    long now = System.currentTimeMillis();
                    long clockTime = now - beginTime;
                    if (clockTime >= duration) {
                        clockTime = duration;
                        timer.stop();
                    }
                    SimpleDateFormat df = new SimpleDateFormat("mm:ss:SSS");
                    l.setText(df.format(duration - clockTime));
                }
            });
            timer.setInitialDelay(0);
            addMouseListener(new MouseAdapter() {
                


                public void mouseClicked(MouseEvent e) {
                    if (!timer.isRunning()) {
                        beginTime = -1;
                        timer.start();
                    }
                }
            });
            l = new JLabel("VISUAL TIMER");
            add(l);
        }

        public Dimension Size() {
            return new Dimension(400, 500);
        }

    }

}