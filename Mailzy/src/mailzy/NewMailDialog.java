/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailzy;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenuBar;
import net.atlanticbb.tantlinger.shef.HTMLEditorPane;

/**
 *
 * @author lalexandrov
 */
public class NewMailDialog extends javax.swing.JDialog {

    /**
     * Creates new form NewMailDialog
     */
    public NewMailDialog(java.awt.Frame parent, boolean modal, Mail blankMail) {
        super(parent, modal);
        initComponents();
        menuBar.add(editor.getEditMenu());
        menuBar.add(editor.getFormatMenu());
        menuBar.add(editor.getInsertMenu());

        this.getContentPane().setLayout(null);
        button.setBounds(680,580,100,20);
        this.getContentPane().add(button);
        editor.setBounds(0, 80, 780, 600);
        this.getContentPane().add(editor);
        this.setVisible(true);
    }
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();

        setTitle("New Mail");
        setName("New Mail"); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 770));
        setResizable(false);

        button.setText("Send");
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });
        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(291, Short.MAX_VALUE)
                .addComponent(button)
                .addGap(66, 66, 66))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(button)
                .addContainerGap(145, Short.MAX_VALUE))
        );

        button.getAccessibleContext().setAccessibleName("button");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActionPerformed
        //TODO: Check if all fields are valid
        this.setVisible(false);
        //this.getContentPane().setBackground(Color.red); //Test
    }//GEN-LAST:event_buttonActionPerformed
    
    
    private HTMLEditorPane editor = new HTMLEditorPane();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables
}
