/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package curriculum.vitae.gui;

import curriculum.vitae.core.Utilizador;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;
import utils.Recursos;

/**
 *
 * @author joaob
 */
public class CurriculumVitae extends javax.swing.JFrame {
    ArrayList<Utilizador> listUsers = new ArrayList<>();
    File f;

    /**
     * Creates new form PaginaInicial
     */
    public CurriculumVitae() {
        initComponents();
        f = new File("../Curriculum Vitae/utilizadores/users.user");
        listUsers = (ArrayList<Utilizador>) Recursos.readObject(f.getAbsolutePath());
        //Fecha a janela principal da aplicação e encerra a aplicação
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Vai guardar a lista de todos os utilizadores durante a atividade no sistema.
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                try {
                    Recursos.writeObject(listUsers, f.getAbsolutePath());
                    System.out.println("Lista de utilizadores gravada com sucesso");
                } catch (Exception ex) {
                    System.err.println("Erro ao gravar a lista de utilizadores = " + ex.getMessage());
                }
            }
        });
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCriarCurriculum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAboutUs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCriarCurriculum, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAboutUs, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCriarCurriculumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriarCurriculumActionPerformed
        // TODO add your handling code here:
        new Login(this, true).setVisible(true);
    }//GEN-LAST:event_btnCriarCurriculumActionPerformed

    private void btnAboutUsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAboutUsActionPerformed
        // TODO add your handling code here:
        new aboutUs(this, true).setVisible(true);
    }//GEN-LAST:event_btnAboutUsActionPerformed

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
            java.util.logging.Logger.getLogger(CurriculumVitae.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CurriculumVitae.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CurriculumVitae.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CurriculumVitae.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CurriculumVitae().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAboutUs;
    private javax.swing.JButton btnCriarCurriculum;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
