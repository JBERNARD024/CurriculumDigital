/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Dialog.java to edit this template
 */
package curriculum.vitae.gui;

import curriculum.vitae.core.Educacao;
import curriculum.vitae.core.Utilizador;
import java.awt.Color;
import java.awt.Image;
import java.time.Instant;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

/**
 *
 * @author joaob
 */
public class listaEducacao extends java.awt.Dialog {
    CurriculumVitae cv;
    int index;
    Utilizador user;
    ImageIcon icon;
    Image imagem;
    DefaultListModel educaList;
    int indexEducacao;
    /**
     * Creates new form listaEducacao
     */
    public listaEducacao(CurriculumVitae cv, boolean modal, int index) {
        super(cv, modal);
        this.cv = cv;
        this.index = index;
        initComponents();
        user = new Utilizador(cv.listUsers.get(index));
        icon = new ImageIcon(user.getImagem());
        imagem = icon.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_SMOOTH);
        lblFoto.setBackground(Color.white);
        lblFoto.setIcon(new ImageIcon(imagem));
        educaList = new DefaultListModel<>();
        for (int i = 0; i < cv.listUsers.get(index).getDados().getEducacao().size(); i++) {
            educaList.addElement(cv.listUsers.get(index).getDados().getEducacao().get(i));
        }
        indexEducacao = 0;
        educacaoList.setModel(educaList);
        educacaoList.setSelectedIndex(indexEducacao);
        btnEliminar.setEnabled(educaList.size() > 0);
        btnEditar.setEnabled(educaList.size() > 0);
        panelBtns.setVisible(educaList.size() > 0);
        btnPrimeiro.setEnabled(indexEducacao > 1);
        btnAnterior.setEnabled(indexEducacao > 1);
        btnAvancar.setEnabled(indexEducacao < educaList.getSize() - 1);
        btnUltimo.setEnabled(indexEducacao < educaList.getSize() - 1);
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
        txtInstituicao = new javax.swing.JTextField();
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
        txtEmCurso = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescr = new javax.swing.JTextArea();
        painelPerfil = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        btnDadosPessoais = new javax.swing.JButton();
        btnExpProfissional = new javax.swing.JButton();
        btnLingua = new javax.swing.JButton();
        btnLogout2 = new javax.swing.JButton();
        lblFoto = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        educacaoList = new javax.swing.JList<>();
        panelBtns = new javax.swing.JPanel();
        btnPrimeiro = new javax.swing.JButton();
        btnUltimo = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();
        btnAvancar = new javax.swing.JButton();
        painelGestao = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtMedia = new javax.swing.JSpinner();
        txtQEQ = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Lista de Educação");

        jLabel1.setText("Qualificação");

        txtQualificacao.setEditable(false);

        jLabel3.setText("Área de Estudo");

        txtAreaEstudo.setEditable(false);
        txtAreaEstudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAreaEstudoActionPerformed(evt);
            }
        });

        jLabel4.setText("Instituição");

        txtInstituicao.setEditable(false);

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

        txtEmCurso.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtEmCurso.setForeground(new java.awt.Color(0, 255, 51));
        txtEmCurso.setText("Em Curso");

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

        btnExpProfissional.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnExpProfissional.setText("Experiência Profissional");
        btnExpProfissional.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnExpProfissional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExpProfissionalActionPerformed(evt);
            }
        });

        btnLingua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLingua.setText("Competências Linguísticas");
        btnLingua.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnLingua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLinguaActionPerformed(evt);
            }
        });

        btnLogout2.setText("Terminar Sessão");
        btnLogout2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogout2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelPerfilLayout = new javax.swing.GroupLayout(painelPerfil);
        painelPerfil.setLayout(painelPerfilLayout);
        painelPerfilLayout.setHorizontalGroup(
            painelPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPerfilLayout.createSequentialGroup()
                .addGroup(painelPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelPerfilLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(painelPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblFoto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(btnExpProfissional, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDadosPessoais, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLingua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(painelPerfilLayout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jLabel16)))
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelPerfilLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnLogout2)
                .addGap(56, 56, 56))
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
                .addGap(18, 18, 18)
                .addComponent(btnExpProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLingua, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addComponent(btnLogout2)
                .addGap(19, 19, 19))
        );

        educacaoList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                educacaoListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(educacaoList);

        panelBtns.setBackground(null);

        btnPrimeiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/curriculum/vitae/images/Primeiro.png"))); // NOI18N
        btnPrimeiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrimeiroActionPerformed(evt);
            }
        });

        btnUltimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/curriculum/vitae/images/Ultimo.png"))); // NOI18N
        btnUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUltimoActionPerformed(evt);
            }
        });

        btnAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/curriculum/vitae/images/Anterior.png"))); // NOI18N
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        btnAvancar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/curriculum/vitae/images/Proximo.png"))); // NOI18N
        btnAvancar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvancarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBtnsLayout = new javax.swing.GroupLayout(panelBtns);
        panelBtns.setLayout(panelBtnsLayout);
        panelBtnsLayout.setHorizontalGroup(
            panelBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPrimeiro, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(btnAvancar, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelBtnsLayout.setVerticalGroup(
            panelBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAvancar, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrimeiro, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        painelGestao.setBackground(null);

        btnNovo.setText("Adicionar Educação");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar Educação");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar Educação");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelGestaoLayout = new javax.swing.GroupLayout(painelGestao);
        painelGestao.setLayout(painelGestaoLayout);
        painelGestaoLayout.setHorizontalGroup(
            painelGestaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelGestaoLayout.createSequentialGroup()
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelGestaoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86))
        );
        painelGestaoLayout.setVerticalGroup(
            painelGestaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelGestaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelGestaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setText("Média Final");

        txtMedia.setEnabled(false);

        txtQEQ.setEditable(false);
        txtQEQ.setToolTipText("");

        jLabel17.setText("Nível QEQ");

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
                            .addComponent(jLabel1)
                            .addComponent(txtQualificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txtMedia, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(287, 287, 287))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGap(2, 2, 2)
                                            .addComponent(jLabel5)
                                            .addGap(306, 306, 306)))
                                    .addComponent(txtWeb, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtInstituicao, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAreaEstudo, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelBtns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(painelGestao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(txtDataInic, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtEmCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel11)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel17)
                                    .addComponent(txtQEQ)
                                    .addComponent(txtPais, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtQualificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAreaEstudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtInstituicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel11))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtDataFim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtDataInic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(3, 3, 3))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtEmCurso))))
                            .addComponent(jScrollPane1))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(panelBtns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                                .addComponent(painelGestao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        new perfil(cv, true, index).setVisible(true);
    }//GEN-LAST:event_btnDadosPessoaisActionPerformed

    private void btnExpProfissionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExpProfissionalActionPerformed
        // TODO add your handling code here:
        dispose();
        new listaExperienciaProfissional(cv, true, index).setVisible(true);
    }//GEN-LAST:event_btnExpProfissionalActionPerformed

    private void btnLinguaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLinguaActionPerformed
        // TODO add your handling code here:
        //new listaLinguas
    }//GEN-LAST:event_btnLinguaActionPerformed

    private void btnLogout2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogout2ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnLogout2ActionPerformed

    private void btnPrimeiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrimeiroActionPerformed
        // TODO add your handling code here:
//        index = 0;
//        listEleitores.setSelectedIndex(index);
//        btnPrimeiro.setEnabled(index > 0);
//        btnAnterior.setEnabled(index > 0);
//        btnAvancar.setEnabled(index < dList.getSize() - 1);
//        btnUltimo.setEnabled(index < dList.getSize() - 1);
    }//GEN-LAST:event_btnPrimeiroActionPerformed

    private void btnUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUltimoActionPerformed
        // TODO add your handling code here:
//        index = dList.size() - 1;
//        listEleitores.setSelectedIndex(index);
//        btnPrimeiro.setEnabled(index > 0);
//        btnAnterior.setEnabled(index > 0);
//        btnAvancar.setEnabled(index < dList.getSize() - 1);
//        btnUltimo.setEnabled(index < dList.getSize() - 1);
    }//GEN-LAST:event_btnUltimoActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        // TODO add your handling code here:
//        tamanho = dList.size();
//        index -= 1;
//        if (tamanho > index) {
//            listEleitores.setSelectedIndex(index);
//            btnPrimeiro.setEnabled(index > 0);
//            btnAnterior.setEnabled(index > 0);
//            btnAvancar.setEnabled(index < tamanho - 1);
//            btnUltimo.setEnabled(index < tamanho - 1);
//        }
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnAvancarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvancarActionPerformed
        // TODO add your handling code here:
//        tamanho = dList.size();
//        index += 1;
//        if (tamanho > index) {
//            listEleitores.setSelectedIndex(index);
//            btnPrimeiro.setEnabled(index > 0);
//            btnAnterior.setEnabled(index > 0);
//            btnAvancar.setEnabled(index < tamanho - 1);
//            btnUltimo.setEnabled(index < tamanho - 1);
//        }
    }//GEN-LAST:event_btnAvancarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        this.dispose();
        new adicionarEducacao(cv, true, index).setVisible(true);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void educacaoListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_educacaoListValueChanged
        // TODO add your handling code here:
        indexEducacao = educacaoList.getSelectedIndex();
        if (indexEducacao >= 0) {
            //mostraPanelEleitor();
            Educacao e = (Educacao) educacaoList.getSelectedValues()[0];
            txtQualificacao.setText(e.getQualificacao());
            txtQEQ.setText(e.getNivelQEQ());
            txtAreaEstudo.setText(e.getAreaEstudo());
            txtMedia.setValue(e.getMediaFinal());
            txtInstituicao.setText(e.getInstituicao());
            txtWeb.setText(e.getSitioWeb());
            txtCidade.setText(e.getCidade());
            txtPais.setText(e.getPais());
            txtDataInic.setDate(e.getDataInic());
            if(e.getDataFim().equals(Date.from(Instant.now()))){
                txtEmCurso.setVisible(true);
            }else{
                txtEmCurso.setVisible(false);
                txtDataFim.setDate(e.getDataFim());
            }
            txtDescr.setText(e.getDescr());
        }
        btnPrimeiro.setEnabled(indexEducacao > 1);
        btnAnterior.setEnabled(indexEducacao > 1);
        btnAvancar.setEnabled(indexEducacao < educaList.getSize() - 1);
        btnUltimo.setEnabled(indexEducacao < educaList.getSize() - 1);
    }//GEN-LAST:event_educacaoListValueChanged

    private void txtAreaEstudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAreaEstudoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAreaEstudoActionPerformed

    /*public void mostraPanelEleitor() {
        panelFoto.setVisible(true);
        labelFoto.setVisible(true);
        panelEleitor.setVisible(true);
        txtNome.setVisible(true);
        txtCC.setVisible(true);
        txtDataNasc.setVisible(true);
        txtIdade.setVisible(true);
        txtPassword.setVisible(true);
    }*/
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnAvancar;
    private javax.swing.JButton btnDadosPessoais;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEduc;
    private javax.swing.JButton btnEduc1;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnExpProf;
    private javax.swing.JButton btnExpProf1;
    private javax.swing.JButton btnExpProfissional;
    private javax.swing.JButton btnLing;
    private javax.swing.JButton btnLing1;
    private javax.swing.JButton btnLingua;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnLogout1;
    private javax.swing.JButton btnLogout2;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPrimeiro;
    private javax.swing.JButton btnUltimo;
    private javax.swing.JList<String> educacaoList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelFoto;
    private javax.swing.JLabel labelFoto1;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JPanel painelGestao;
    private javax.swing.JPanel painelPerfil;
    private javax.swing.JPanel panelBtns;
    private javax.swing.JTextField txtAreaEstudo;
    private javax.swing.JTextField txtCidade;
    private com.toedter.calendar.JDateChooser txtDataFim;
    private com.toedter.calendar.JDateChooser txtDataInic;
    private javax.swing.JTextArea txtDescr;
    private javax.swing.JLabel txtEmCurso;
    private javax.swing.JTextField txtInstituicao;
    private javax.swing.JSpinner txtMedia;
    private javax.swing.JTextField txtPais;
    private javax.swing.JTextField txtQEQ;
    private javax.swing.JTextField txtQualificacao;
    private javax.swing.JTextField txtWeb;
    // End of variables declaration//GEN-END:variables
}