/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Dialog.java to edit this template
 */
package curriculum.vitae.gui;

import curriculum.vitae.core.RegistoCertificado;
import java.net.MalformedURLException;
import rmi.RemoteInterface;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author joaob
 */
public class Blockchain extends java.awt.Dialog {
    RemoteInterface rmtInterface;
    RegistoCertificado registoCerti;
    /**
     * Creates new form Blockchain
     * @param parent
     * @param modal
     * @param rmtObject
     * @throws java.rmi.RemoteException
     */
    public Blockchain(java.awt.Frame parent, boolean modal, String rmtObject) throws RemoteException {
        super(parent, modal);
        initComponents();
        setTitle("Blockchain");
        try {
            this.rmtInterface = (RemoteInterface) Naming.lookup(rmtObject);
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(Blockchain.class.getName()).log(Level.SEVERE, null, ex);
        }
        registoCerti = rmtInterface.getBlockchain();
        jTextPane1.setText(registoCerti.getBc().toString());
        txtRegisto.setText(registoCerti.toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBlock = new javax.swing.JTabbedPane();
        panelBlockchain = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtRegisto = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        panelBlockchain.setPreferredSize(new java.awt.Dimension(1350, 365));

        txtRegisto.setColumns(20);
        txtRegisto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtRegisto.setRows(5);
        jScrollPane1.setViewportView(txtRegisto);

        jScrollPane2.setViewportView(jTextPane1);

        javax.swing.GroupLayout panelBlockchainLayout = new javax.swing.GroupLayout(panelBlockchain);
        panelBlockchain.setLayout(panelBlockchainLayout);
        panelBlockchainLayout.setHorizontalGroup(
            panelBlockchainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBlockchainLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1011, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelBlockchainLayout.setVerticalGroup(
            panelBlockchainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
        );

        panelBlock.addTab("Blockchain", new javax.swing.ImageIcon(getClass().getResource("/curriculum/vitae/images/blockchain.png")), panelBlockchain); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBlock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBlock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTabbedPane panelBlock;
    private javax.swing.JPanel panelBlockchain;
    private javax.swing.JTextArea txtRegisto;
    // End of variables declaration//GEN-END:variables
}
