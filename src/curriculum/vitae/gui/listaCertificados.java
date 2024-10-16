/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Dialog.java to edit this template
 */
package curriculum.vitae.gui;

import curriculum.vitae.core.Certificado;
import curriculum.vitae.core.Instituto;
import curriculum.vitae.core.Utilizador;
import java.awt.Color;
import java.awt.Image;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import utils.Block;
import utils.Converter;
import utils.MerkleTree;
import utils.Recursos;

/**
 *
 * @author joaob
 */
public class listaCertificados extends java.awt.Dialog {

    CurriculumVitae cv;
    int index;
    Instituto inst;
    ImageIcon icon;
    Image imagem;
    DefaultListModel myCertificados;
    int indexEducacao;
    MerkleTree tree;

    /**
     * Creates new form listaEducacao
     * @param cv
     * @param modal
     * @param index
     */
    public listaCertificados(CurriculumVitae cv, boolean modal, int index) {
        super(cv, modal);
        this.cv = cv;
        this.index = index;
        initComponents();
        this.setTitle("Lista de Certficados");
        inst = new Instituto(cv.listInst.get(index));
        icon = new ImageIcon(inst.getImagem());
        imagem = icon.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_SMOOTH);
        lblFoto.setBackground(Color.white);
        lblFoto.setIcon(new ImageIcon(imagem));
        myCertificados = new DefaultListModel<>();
        indexEducacao = 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtQualificacao = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtAreaEstudo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtGraduado = new javax.swing.JTextField();
        txtCidade = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtPais = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtDataFim = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtWeb = new javax.swing.JTextField();
        txtDataInic = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescr = new javax.swing.JTextArea();
        painelPerfil = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        btnDadosPessoais = new javax.swing.JButton();
        lblFoto = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        btnObterCertificados = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        certificadosList = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        txtMedia = new javax.swing.JSpinner();
        txtQEQ = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtAssinatura = new javax.swing.JTextField();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Lista de Certificados");

        jLabel1.setText("Qualificação");

        txtQualificacao.setEditable(false);

        jLabel3.setText("Área de Estudo");

        txtAreaEstudo.setEditable(false);
        txtAreaEstudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAreaEstudoActionPerformed(evt);
            }
        });

        jLabel4.setText("Nome do Graduado");

        txtGraduado.setEditable(false);

        txtCidade.setEditable(false);
        txtCidade.setToolTipText("");

        jLabel8.setText("Cidade");

        jLabel9.setText("País");

        txtPais.setEditable(false);
        txtPais.setToolTipText("");

        jLabel10.setText("Data Início");

        txtDataFim.setEnabled(false);

        jLabel11.setText("Data Fim");

        jLabel12.setText("Sítio Web");

        txtWeb.setEditable(false);

        txtDataInic.setEnabled(false);

        jLabel13.setText("Descrição");
        jLabel13.setToolTipText("");

        txtDescr.setEditable(false);
        txtDescr.setColumns(20);
        txtDescr.setRows(5);
        jScrollPane2.setViewportView(txtDescr);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel16.setText("PERFIL");

        btnDadosPessoais.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDadosPessoais.setText("Dados Pessoais");
        btnDadosPessoais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDadosPessoaisActionPerformed(evt);
            }
        });

        btnLogout.setBackground(new java.awt.Color(255, 0, 0));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("Terminar Sessão");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnObterCertificados.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnObterCertificados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/curriculum/vitae/images/Educacao.png"))); // NOI18N
        btnObterCertificados.setText("Obter Certificados");
        btnObterCertificados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObterCertificadosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelPerfilLayout = new javax.swing.GroupLayout(painelPerfil);
        painelPerfil.setLayout(painelPerfilLayout);
        painelPerfilLayout.setHorizontalGroup(
            painelPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPerfilLayout.createSequentialGroup()
                .addGroup(painelPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelPerfilLayout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jLabel16))
                    .addGroup(painelPerfilLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(btnLogout))
                    .addGroup(painelPerfilLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(painelPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnObterCertificados, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(painelPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblFoto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addComponent(btnDadosPessoais, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        painelPerfilLayout.setVerticalGroup(
            painelPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPerfilLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addGap(59, 59, 59)
                .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                .addComponent(btnDadosPessoais, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnObterCertificados, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        certificadosList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                certificadosListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(certificadosList);

        jLabel5.setText("Média Final");

        txtMedia.setEnabled(false);

        txtQEQ.setEditable(false);
        txtQEQ.setToolTipText("");

        jLabel17.setText("Nível QEQ");

        jLabel6.setText("Validade Assinatura");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(painelPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtWeb, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGraduado, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAreaEstudo, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel12)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtCidade, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                            .addComponent(jLabel5)
                                            .addComponent(txtMedia, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel10)
                                            .addComponent(txtDataInic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel9)
                                                .addComponent(jLabel17)
                                                .addComponent(txtQEQ)
                                                .addComponent(txtPais, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(24, 24, 24))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(txtQualificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(342, 342, 342)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtAssinatura, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 202, Short.MAX_VALUE)))
                .addGap(0, 30, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(482, 482, 482)
                .addComponent(jLabel2)
                .addGap(9, 9, 9))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtQualificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAreaEstudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGraduado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtWeb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel17))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMedia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtQEQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel11))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDataInic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3))
                                    .addComponent(txtDataFim, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtAssinatura, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(9, Short.MAX_VALUE)
                        .addComponent(painelPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void btnDadosPessoaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDadosPessoaisActionPerformed
        // TODO add your handling code here:
        dispose();
        new perfilInstituto(cv, true, index).setVisible(true);
    }//GEN-LAST:event_btnDadosPessoaisActionPerformed

    private void certificadosListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_certificadosListValueChanged
        // TODO add your handling code here:
        indexEducacao = certificadosList.getSelectedIndex();
        if (indexEducacao >= 0) {
            //mostraPanelEleitor();
            Certificado c = (Certificado) certificadosList.getSelectedValues()[0];
            txtQualificacao.setText(c.getExperiencia().getQualificacao());
            txtAreaEstudo.setText(c.getExperiencia().getAreaEstudo());
            txtGraduado.setText(c.getGraduado().getDados().getNome());
            txtWeb.setText(c.getInstituto().getDadosInst().getSitioWeb());
            txtMedia.setValue(c.getExperiencia().getMediaFinal());
            txtQEQ.setText(c.getExperiencia().getNivelQEQ());
            txtCidade.setText(c.getExperiencia().getCidade());
            txtPais.setText(c.getExperiencia().getCidade());
            txtDataInic.setDate(c.getExperiencia().getDataInic());
            txtDataFim.setDate(c.getExperiencia().getDataFim());
            txtDescr.setText(c.getExperiencia().getDescr());
            if(c.isValid()){
                txtAssinatura.setText("Válida");
            }else{
                txtAssinatura.setText("Inválida");
            }
        }
    }//GEN-LAST:event_certificadosListValueChanged

    private void txtAreaEstudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAreaEstudoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAreaEstudoActionPerformed

    private void btnObterCertificadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObterCertificadosActionPerformed
        getCertificados();
    }//GEN-LAST:event_btnObterCertificadosActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        dispose();
        new Login(cv, true).setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDadosPessoais;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnObterCertificados;
    private javax.swing.JList<String> certificadosList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JPanel painelPerfil;
    private javax.swing.JTextField txtAreaEstudo;
    private javax.swing.JTextField txtAssinatura;
    private javax.swing.JTextField txtCidade;
    private com.toedter.calendar.JDateChooser txtDataFim;
    private com.toedter.calendar.JDateChooser txtDataInic;
    private javax.swing.JTextArea txtDescr;
    private javax.swing.JTextField txtGraduado;
    private javax.swing.JSpinner txtMedia;
    private javax.swing.JTextField txtPais;
    private javax.swing.JTextField txtQEQ;
    private javax.swing.JTextField txtQualificacao;
    private javax.swing.JTextField txtWeb;
    // End of variables declaration//GEN-END:variables

private void getCertificados() {
        try {
            // TODO add your handling code here:
            for (Block b : cv.registoCerti.getBc().getChain()) {
                tree = (MerkleTree) Recursos.readObject("../CurriculumDigital/merkleTree/" + b.getCurrentHash() + ".mk");
                for (int i = 0; i < cv.registoCerti.getRegisto().size(); i++) {
                    String cert = cv.registoCerti.getRegisto().get(i);
                    List<String> proof = tree.getProof(cert);
                    boolean isProofValid = tree.isProofValid(cert, proof);
                    if(isProofValid){
                        Certificado c = (Certificado) Converter.hexToObject(cv.registoCerti.getRegisto().get(i));
                        if(c.getInstituto().getCodNome().equals(inst.getCodNome())){
                            myCertificados.addElement(c);
                        }
                    }
                }
            }
            certificadosList.setModel(myCertificados);
            certificadosList.setSelectedIndex(indexEducacao);
        } catch (Exception ex) {
            Logger.getLogger(listaEducacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
