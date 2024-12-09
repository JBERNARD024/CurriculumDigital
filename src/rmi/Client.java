/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package rmi;

import curriculum.vitae.gui.CurriculumVitae;
import java.net.InetAddress;
import java.rmi.Naming;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.GuiUtils;

/**
 *
 * @author joaob
 */
public class Client extends javax.swing.JFrame {

    String host;
    int remotePort;
    String remoteName;

    /**
     * Creates new form Client
     */
    public Client() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        txtClientLog = new javax.swing.JTextPane();
        imgClientRunning = new javax.swing.JLabel();
        btStartClient = new javax.swing.JButton();
        txtServerAdress = new javax.swing.JTextField();
        txtServerObjectName = new javax.swing.JTextField();
        txtServerPort = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Client");

        txtClientLog.setBorder(javax.swing.BorderFactory.createTitledBorder("Log Client"));
        txtClientLog.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jScrollPane2.setViewportView(txtClientLog);

        imgClientRunning.setIcon(new javax.swing.ImageIcon(getClass().getResource("/curriculum/vitae/images/loading_blue.gif"))); // NOI18N
        imgClientRunning.setEnabled(false);

        btStartClient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/curriculum/vitae/images/startClient.jpg"))); // NOI18N
        btStartClient.setText("Connect");
        btStartClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStartClientActionPerformed(evt);
            }
        });

        txtServerAdress.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtServerAdress.setText("127.0.0.1");
        txtServerAdress.setBorder(javax.swing.BorderFactory.createTitledBorder("Server Adress"));
        txtServerAdress.setPreferredSize(new java.awt.Dimension(200, 36));

        txtServerObjectName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtServerObjectName.setText("remoteObject");
        txtServerObjectName.setBorder(javax.swing.BorderFactory.createTitledBorder("Object Name"));

        txtServerPort.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtServerPort.setText("10010");
        txtServerPort.setBorder(javax.swing.BorderFactory.createTitledBorder("Port Number"));
        txtServerPort.setPreferredSize(new java.awt.Dimension(200, 36));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btStartClient, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtServerAdress, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtServerPort, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtServerObjectName, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imgClientRunning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btStartClient, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtServerAdress, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtServerPort, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtServerObjectName, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(imgClientRunning, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btStartClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStartClientActionPerformed
        new Thread(() -> {
            try {
                imgClientRunning.setEnabled(true);
                host = txtServerAdress.getText();
                remotePort = Integer.parseInt(txtServerPort.getText());
                remoteName = txtServerObjectName.getText();
                String rmtObject = String.format("//%s:%d/%s", host, remotePort, remoteName);
                // get reference to the remote object
                RemoteInterface rmtInterface = (RemoteInterface) Naming.lookup(rmtObject);
                //execute remote method
                GuiUtils.addText(txtClientLog, host, rmtInterface.getMessage());
                rmtInterface.publish("Ligou-se ao servidor!");
                new CurriculumVitae(null, rmtObject).setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }//GEN-LAST:event_btStartClientActionPerformed

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
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Client().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btStartClient;
    private javax.swing.JLabel imgClientRunning;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane txtClientLog;
    private javax.swing.JTextField txtServerAdress;
    private javax.swing.JTextField txtServerObjectName;
    private javax.swing.JTextField txtServerPort;
    // End of variables declaration//GEN-END:variables
}
