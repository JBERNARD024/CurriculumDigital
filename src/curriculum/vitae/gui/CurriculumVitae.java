/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package curriculum.vitae.gui;

import curriculum.vitae.core.RegistoCertificado;
import curriculum.vitae.core.Instituto;
import curriculum.vitae.core.Pessoa;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.security.Security;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 *
 * @author joaob
 */
public class CurriculumVitae extends javax.swing.JDialog {

    ArrayList<Pessoa> listUsers = new ArrayList<>();
    ArrayList<Instituto> listInst = new ArrayList<>();
    RegistoCertificado registoCerti = new RegistoCertificado();
    String basePath = new File("").getAbsolutePath();
    String pathUsers = basePath + "/resources/pessoas/users.user";
    String pathInst = basePath + "/resources/institutos/institutos.inst";
    String pathBlockchain = basePath + "/resources/blockchain/blockchain.bc";
    File fichUsers;
    File fichInst;
    File fichRegisto;
    String rmtObject;

    /**
     * Creates new form PaginaInicial
     *
     * @param parent
     * @param rmtObject
     * @throws java.lang.ClassNotFoundException
     * @throws java.io.IOException
     */
    public CurriculumVitae(java.awt.Frame parent, String rmtObject) throws ClassNotFoundException, IOException, Exception {
        super(parent, true);
        initComponents();
        this.rmtObject = rmtObject;
        //Adiciona o Bouncy Castle à lista providers de segurança
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnCriarCurriculum = new javax.swing.JButton();
        btnAboutUs = new javax.swing.JButton();
        btnBlockChain = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Página Inicial");
        setResizable(false);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/curriculum/vitae/images/CV.jpeg"))); // NOI18N

        btnCriarCurriculum.setBackground(new java.awt.Color(86, 137, 171));
        btnCriarCurriculum.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCriarCurriculum.setForeground(new java.awt.Color(255, 255, 255));
        btnCriarCurriculum.setText("Cria o teu Curriculum Vitae aqui!!");
        btnCriarCurriculum.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        btnCriarCurriculum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriarCurriculumActionPerformed(evt);
            }
        });

        btnAboutUs.setBackground(new java.awt.Color(86, 137, 171));
        btnAboutUs.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAboutUs.setForeground(new java.awt.Color(255, 255, 255));
        btnAboutUs.setText("About Us");
        btnAboutUs.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        btnAboutUs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAboutUsActionPerformed(evt);
            }
        });

        btnBlockChain.setBackground(new java.awt.Color(86, 137, 171));
        btnBlockChain.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBlockChain.setForeground(new java.awt.Color(255, 255, 255));
        btnBlockChain.setText("Blockchain");
        btnBlockChain.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        btnBlockChain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBlockChainActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBlockChain, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnCriarCurriculum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAboutUs, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(btnCriarCurriculum, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAboutUs, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBlockChain, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCriarCurriculumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriarCurriculumActionPerformed
        // TODO add your handling code here:
        //Redireciona o utilizador para a página de Login
        new Login(null, true, rmtObject).setVisible(true);
    }//GEN-LAST:event_btnCriarCurriculumActionPerformed

    private void btnAboutUsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAboutUsActionPerformed
        // TODO add your handling code here:
        //Redireciona o utilizador para a página de aboutUs
        new aboutUs(null, true).setVisible(true);
    }//GEN-LAST:event_btnAboutUsActionPerformed

    private void btnBlockChainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBlockChainActionPerformed
        try {
            // TODO add your handling code here:
            //Redireciona o utilizador para a página da Blockchains
            new Blockchain(null, true, rmtObject).setVisible(true);
        } catch (RemoteException ex) {
            Logger.getLogger(CurriculumVitae.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBlockChainActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAboutUs;
    private javax.swing.JButton btnBlockChain;
    private javax.swing.JButton btnCriarCurriculum;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
