/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Dialog.java to edit this template
 */
package curriculum.vitae.gui;

import curriculum.vitae.core.Instituto;
import curriculum.vitae.core.Pessoa;
import java.io.File;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import rmi.RemoteInterface;
import utils.Recursos;

/**
 *
 * @author joaob
 */
public class Registo extends java.awt.Dialog {

    CurriculumVitae cv;
    String email;
    String password;
    String confPassword;
    String codNome;
    Pessoa user;
    Instituto instituto;
    String rmtObject;
    RemoteInterface rmtInterface;

    /**
     * Creates new form Registo
     *
     * @param parent
     * @param modal
     * @param rmtObject
     */
    public Registo(java.awt.Frame parent, boolean modal, String rmtObject) {
        super(parent, modal);
        this.setTitle("Registo");
        initComponents();
        this.rmtObject = rmtObject;
        try {
            this.rmtInterface = (RemoteInterface) Naming.lookup(rmtObject);
        } catch (Exception ex) {
            Logger.getLogger(Registo.class.getName()).log(Level.SEVERE, null, ex);
        } 
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelLogin = new javax.swing.JTabbedPane();
        painelRegistoUsers = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtEmailUser = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtPasswordUser = new javax.swing.JPasswordField();
        jLabel10 = new javax.swing.JLabel();
        txtConfPasswordSuer = new javax.swing.JPasswordField();
        btnRegistoUser = new javax.swing.JButton();
        btnLoginUser = new javax.swing.JButton();
        painelRegistoInstituto = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCodNomeInst = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtPasswordInst = new javax.swing.JPasswordField();
        jLabel14 = new javax.swing.JLabel();
        txtConfPasswordInst = new javax.swing.JPasswordField();
        btnRegistoInst = new javax.swing.JButton();
        btnLoginInst = new javax.swing.JButton();

        setName(""); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        painelRegistoUsers.setToolTipText("Utilizador");
        painelRegistoUsers.setName(""); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/curriculum/vitae/images/registoImage.jpg"))); // NOI18N
        jLabel6.setMaximumSize(new java.awt.Dimension(150, 125));
        jLabel6.setMinimumSize(new java.awt.Dimension(150, 125));
        jLabel6.setPreferredSize(new java.awt.Dimension(150, 150));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(86, 137, 171));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("REGISTO");

        jLabel8.setText("Email");

        jLabel9.setText("Password");

        jLabel10.setText("Confirmar Password");

        btnRegistoUser.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRegistoUser.setForeground(new java.awt.Color(86, 137, 171));
        btnRegistoUser.setText("Registar-me");
        btnRegistoUser.setPreferredSize(new java.awt.Dimension(75, 30));
        btnRegistoUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistoUserActionPerformed(evt);
            }
        });

        btnLoginUser.setBackground(new java.awt.Color(86, 137, 171));
        btnLoginUser.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLoginUser.setForeground(new java.awt.Color(255, 255, 255));
        btnLoginUser.setText("Login");
        btnLoginUser.setPreferredSize(new java.awt.Dimension(75, 30));
        btnLoginUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelRegistoUsersLayout = new javax.swing.GroupLayout(painelRegistoUsers);
        painelRegistoUsers.setLayout(painelRegistoUsersLayout);
        painelRegistoUsersLayout.setHorizontalGroup(
            painelRegistoUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelRegistoUsersLayout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(painelRegistoUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmailUser)
                    .addComponent(txtPasswordUser)
                    .addComponent(txtConfPasswordSuer)
                    .addGroup(painelRegistoUsersLayout.createSequentialGroup()
                        .addGroup(painelRegistoUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addGap(0, 14, Short.MAX_VALUE))
                    .addGroup(painelRegistoUsersLayout.createSequentialGroup()
                        .addComponent(btnRegistoUser, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLoginUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        painelRegistoUsersLayout.setVerticalGroup(
            painelRegistoUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelRegistoUsersLayout.createSequentialGroup()
                .addGroup(painelRegistoUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(painelRegistoUsersLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmailUser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPasswordUser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtConfPasswordSuer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(painelRegistoUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRegistoUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLoginUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        painelLogin.addTab("<html>&nbsp; Pessoa</html>", new javax.swing.ImageIcon(getClass().getResource("/curriculum/vitae/images/user.png")), painelRegistoUsers); // NOI18N

        painelRegistoInstituto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/curriculum/vitae/images/registoInsituto.jpg"))); // NOI18N

        jLabel11.setText("Código do Instituto");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 153, 51));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("REGISTO");

        jLabel13.setText("Password");

        jLabel14.setText("Confirmar Password");

        btnRegistoInst.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRegistoInst.setForeground(new java.awt.Color(0, 153, 51));
        btnRegistoInst.setText("Registar-me");
        btnRegistoInst.setPreferredSize(new java.awt.Dimension(75, 30));
        btnRegistoInst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistoInstActionPerformed(evt);
            }
        });

        btnLoginInst.setBackground(new java.awt.Color(0, 153, 51));
        btnLoginInst.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLoginInst.setForeground(new java.awt.Color(255, 255, 255));
        btnLoginInst.setText("Login");
        btnLoginInst.setPreferredSize(new java.awt.Dimension(75, 30));
        btnLoginInst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginInstActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelRegistoInstitutoLayout = new javax.swing.GroupLayout(painelRegistoInstituto);
        painelRegistoInstituto.setLayout(painelRegistoInstitutoLayout);
        painelRegistoInstitutoLayout.setHorizontalGroup(
            painelRegistoInstitutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelRegistoInstitutoLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(painelRegistoInstitutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPasswordInst)
                    .addComponent(txtConfPasswordInst)
                    .addGroup(painelRegistoInstitutoLayout.createSequentialGroup()
                        .addComponent(btnRegistoInst, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLoginInst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCodNomeInst)
                    .addGroup(painelRegistoInstitutoLayout.createSequentialGroup()
                        .addGroup(painelRegistoInstitutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel11)
                            .addComponent(jLabel14))
                        .addGap(0, 13, Short.MAX_VALUE)))
                .addGap(19, 19, 19))
        );
        painelRegistoInstitutoLayout.setVerticalGroup(
            painelRegistoInstitutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(painelRegistoInstitutoLayout.createSequentialGroup()
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodNomeInst, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPasswordInst, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConfPasswordInst, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(painelRegistoInstitutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistoInst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoginInst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        painelLogin.addTab("<html>&nbsp;Instituto</html>", new javax.swing.ImageIcon(getClass().getResource("/curriculum/vitae/images/Instituto.png")), painelRegistoInstituto); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelLogin)
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

    private void btnLoginInstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginInstActionPerformed
        // TODO add your handling code here:
        dispose();
        new Login(null, true, rmtObject).setVisible(true);
    }//GEN-LAST:event_btnLoginInstActionPerformed

    private void btnRegistoInstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistoInstActionPerformed
        try {
            // TODO add your handling code here:
            adicionarInstituto();
        } catch (Exception ex) {
            Logger.getLogger(Registo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRegistoInstActionPerformed

    private void btnLoginUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginUserActionPerformed
        // TODO add your handling code here:
        dispose();
        new Login(null, true, rmtObject).setVisible(true);
    }//GEN-LAST:event_btnLoginUserActionPerformed

    private void btnRegistoUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistoUserActionPerformed
        try {
            // TODO add your handling code here:
            adicionarUtilizador();
        } catch (Exception ex) {
            Logger.getLogger(Registo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRegistoUserActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoginInst;
    private javax.swing.JButton btnLoginUser;
    private javax.swing.JButton btnRegistoInst;
    private javax.swing.JButton btnRegistoUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTabbedPane painelLogin;
    private javax.swing.JPanel painelRegistoInstituto;
    private javax.swing.JPanel painelRegistoUsers;
    private javax.swing.JTextField txtCodNomeInst;
    private javax.swing.JPasswordField txtConfPasswordInst;
    private javax.swing.JPasswordField txtConfPasswordSuer;
    private javax.swing.JTextField txtEmailUser;
    private javax.swing.JPasswordField txtPasswordInst;
    private javax.swing.JPasswordField txtPasswordUser;
    // End of variables declaration//GEN-END:variables

    //Função que adiciona um Pessoa à Lista de Pessoas (ArrayList)
    private void adicionarUtilizador() throws Exception {
        email = txtEmailUser.getText().trim();
        password = new String(txtPasswordUser.getPassword());
        confPassword = new String(txtConfPasswordSuer.getPassword());
        //Faz a validação dos campos
        if (rmtInterface.verificaRegistoUser(email, password, confPassword) == true && rmtInterface.verificaEmailRegisto(email) == false) {
            //Estando a validação correta, o utilizador Pessoa é adicionado à lista de Pessoas
            user = new Pessoa(rmtInterface.registerUser(email, password));
            dispose();
            new Login(null, true, rmtObject).setVisible(true);
        }
    }
    
    //Função que adiciona uma Instituição à Lista de Instituições (ArrayList)
    private void adicionarInstituto() throws Exception {
        codNome = txtCodNomeInst.getText().trim();
        password = new String(txtPasswordInst.getPassword());
        confPassword = new String(txtConfPasswordInst.getPassword());
        //Faz a validação dos campos
        if (rmtInterface.verificaRegistoInst(codNome, password, confPassword)== true && rmtInterface.verificaCodNome(codNome) == false) {
             
            dispose();
            new Login(null, true, rmtObject).setVisible(true);
        }
    }
}
