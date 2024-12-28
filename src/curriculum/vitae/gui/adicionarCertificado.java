/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Dialog.java to edit this template
 */
package curriculum.vitae.gui;

import curriculum.vitae.core.Certificado;
import curriculum.vitae.core.Educacao;
import curriculum.vitae.core.Instituto;
import curriculum.vitae.core.Pessoa;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import p2p.NodeP2PGui;
import p2p.OremoteP2P;
import utils.Block;

/**
 *
 * @author joaob
 */
public class adicionarCertificado extends java.awt.Dialog {

    OremoteP2P rmtObject;
    int indexUser;
    String nomePessoa;
    String qualificacao;
    String areaEstudo;
    String instituicao;
    int mediaFinal;
    String nivelQEQ;
    String cidade;
    String pais;
    Date dataInic;
    Date dataFim;
    String descr;
    Educacao educacao;
    Pessoa user;
    Instituto inst;
    ArrayList<Pessoa> listaPessoas = new ArrayList<>();
    public static int MERKLE_TREE_SIZE = 1;

    /**
     * Creates new form adicionarEducacao
     *
     * @param parent
     * @param modal
     * @param inst
     * @param rmtObject
     */
    public adicionarCertificado(java.awt.Frame parent, boolean modal, Instituto inst, OremoteP2P rmtObject) {
        super(parent, modal);
        this.inst = inst;
        this.rmtObject = rmtObject;
        initComponents();
        this.setTitle("Adicionar Certficado");
        try {
            listaPessoas = new ArrayList<>(rmtObject.getPessoas());
        } catch (RemoteException ex) {
            Logger.getLogger(adicionarCertificado.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Verifica se as pessoas existentes no sistema, têm dados pessoais preenchidos e caso tenham, são adicionados à lista de seleção de pessoas
        for (Pessoa user : listaPessoas) {
            if (user.getDados() != null) {
                txtUtilizadores.addItem(user.getDados().getNome());
            }
        }
        txtInstituicao.setText(inst.getDadosInst().getNome());
        txtCidade.setText(inst.getDadosInst().getCidade());
        txtPais.setText(inst.getDadosInst().getPais());
        txtQualificacao.addActionListener((ActionEvent e) -> {
            int selectedIndex = txtQualificacao.getSelectedIndex();
            txtQEQ.setSelectedIndex(selectedIndex);
        });
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
        txtQualificacao = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtAreaEstudo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtInstituicao = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtQEQ = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        txtPais = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtDataInic = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        txtDataFim = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescr = new javax.swing.JTextArea();
        btnEducacao = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        txtMedia = new javax.swing.JSpinner();
        txtUtilizadores = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Adicionar Certificado");

        jLabel1.setText("Qualificação");

        txtQualificacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2º ciclo do Ensino Básico", "3º ciclo do Ensino Básico", "Ensino Secundário Regular", "Ensino Secundário Profissional", "Curso Técnico Superior Profissional", "Licenciatura", "Mestrado", "Doutoramento" }));

        jLabel3.setText("Área do Estudo");

        jLabel4.setText("Instituição");

        txtInstituicao.setEditable(false);

        jLabel5.setText("Média Final");

        jLabel6.setText("Nível QEQ");

        txtQEQ.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nível 1 QEQ", "Nível 2 QEQ", "Nível 3 QEQ", "Nível 4 QEQ", "Nível 5 QEQ", "Nível 6 QEQ", "Nível 7 QEQ", "Nível 8 QEQ" }));
        txtQEQ.setEnabled(false);

        jLabel8.setText("Cidade");

        txtCidade.setEditable(false);

        txtPais.setEditable(false);

        jLabel9.setText("País");

        jLabel10.setText("Data Início");

        jLabel11.setText("Data Fim");

        jLabel12.setText("Descrição");

        txtDescr.setColumns(20);
        txtDescr.setRows(5);
        jScrollPane1.setViewportView(txtDescr);

        btnEducacao.setText("Ver Educação");
        btnEducacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEducacaoActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        txtMedia.setModel(new javax.swing.SpinnerNumberModel(10, 10, 20, 1));

        jLabel7.setText("Nome do Graduado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMedia, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnGuardar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEducacao, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10)
                                .addComponent(txtDataInic, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(148, 148, 148)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11)
                                        .addComponent(txtDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel9)
                                        .addComponent(txtPais, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(106, 106, 106)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(93, 93, 93)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(txtQEQ, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtInstituicao)
                            .addComponent(txtAreaEstudo)
                            .addComponent(txtQualificacao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtUtilizadores, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUtilizadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
                .addComponent(txtInstituicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQEQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMedia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDataInic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnEducacao))
                .addContainerGap(21, Short.MAX_VALUE))
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

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        adicionarCertificado();
        btnGuardar.setEnabled(false);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEducacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEducacaoActionPerformed
        // TODO add your handling code here:
        dispose();
        new listaCertificados(null, true, inst, rmtObject).setVisible(true);
    }//GEN-LAST:event_btnEducacaoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEducacao;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtAreaEstudo;
    private javax.swing.JTextField txtCidade;
    private com.toedter.calendar.JDateChooser txtDataFim;
    private com.toedter.calendar.JDateChooser txtDataInic;
    private javax.swing.JTextArea txtDescr;
    private javax.swing.JTextField txtInstituicao;
    private javax.swing.JSpinner txtMedia;
    private javax.swing.JTextField txtPais;
    private javax.swing.JComboBox<String> txtQEQ;
    private javax.swing.JComboBox<String> txtQualificacao;
    private javax.swing.JComboBox<String> txtUtilizadores;
    // End of variables declaration//GEN-END:variables

    //Função que adiciona um certificado
    //O uso da Thread, é para prevenir que a interface seja bloqueada quando um novo bloco é gerado.
    private void adicionarCertificado() {
        new Thread(() -> {
            try {
                nomePessoa = (String) txtUtilizadores.getSelectedItem();
                for (int i = 0; i < listaPessoas.size(); i++) {
                     user = new Pessoa(listaPessoas.get(i));
                    if(listaPessoas.get(i).getDados() != null){
                        if(nomePessoa.equals(user.getDados().getNome())){
                            break;
                        }
                    }
                }
                String email = user.getEmail();
                qualificacao = (String) txtQualificacao.getSelectedItem();
                areaEstudo = txtAreaEstudo.getText();
                instituicao = txtInstituicao.getText();
                mediaFinal = (int) txtMedia.getValue();
                nivelQEQ = (String) txtQEQ.getSelectedItem();
                cidade = txtCidade.getText();
                pais = txtPais.getText();
                dataInic = txtDataInic.getDate();
                dataFim = txtDataFim.getDate();
                descr = txtDescr.getText();
                //Cria um objeto educação com base nas informações introduzidas pelo Instituto
                educacao = new Educacao(qualificacao, areaEstudo, instituicao, mediaFinal, nivelQEQ, cidade, pais, dataInic, dataFim, descr);
                user = new Pessoa(rmtObject.getPessoa(email));
                //Identificar o utilizador e o instituto que vão fazer parte do certificado
                Certificado c = new Certificado(inst, user, educacao);
                rmtObject.addMessage("Certificado " + c.toString() + " adicionado");
                //Adicionar o certificado à lista de certificados
                rmtObject.adicionarCertificado(c);
                new Thread(() -> {
                    try {
                        //fazer um bloco
                        List<Certificado> blockCertificados = rmtObject.getTemp();
                        if (blockCertificados.size() < 0) {
                            return;
                        }
                        Block b = new Block(rmtObject.getBlockchainLastHash(), blockCertificados);
                        //remover as transacoes
                        rmtObject.removeCertficados(rmtObject.getCertificados());
                        //minar o bloco
                        int nonce = rmtObject.mine(b.getMinerData(), 3);
                        //atualizar o nonce
                        b.setNonce(nonce, 3);
                        //adiconar o bloco
                        rmtObject.addBlock(b);
                    } catch (Exception ex) {
                        //onException(ex, "Start ming");
                        Logger.getLogger(NodeP2PGui.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }).start();
            } catch (RemoteException ex) {
                Logger.getLogger(adicionarCertificado.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(adicionarCertificado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ).start();
    }
}
