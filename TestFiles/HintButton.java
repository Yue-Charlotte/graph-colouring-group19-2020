/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group19_Phase2;

/**
 *
 * @author hp
 */
public class HintButton extends javax.swing.JFrame {


    /**
     * Creates new form HintButton
     */
    public HintButton() {
        this.HintPopup = new HintPopupJFrame();
        initComponents();
    }
    HintPopupJFrame HintPopup; 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hint1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        hint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Group19_Phase2/hint1.png"))); // NOI18N
        hint1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hint1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hint1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hint1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hint1MouseExited(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(hint1)
                .addContainerGap(82, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(hint1)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        hint1.getAccessibleContext().setAccessibleName("hint1");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hint1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hint1MouseEntered

        hint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Group19_Phase2/hint2.png")));
    }//GEN-LAST:event_hint1MouseEntered

    private void hint1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hint1MouseExited

        hint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Group19_Phase2/hint1.png")));
    }//GEN-LAST:event_hint1MouseExited

    private void hint1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hint1MouseClicked

        HintPopup.setVisible(true);
        
    }//GEN-LAST:event_hint1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HintButton.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HintButton.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HintButton.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HintButton.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HintButton().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel hint1;
    // End of variables declaration//GEN-END:variables
}
