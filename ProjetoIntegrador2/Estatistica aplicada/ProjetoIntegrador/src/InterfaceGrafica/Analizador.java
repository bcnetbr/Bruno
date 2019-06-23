/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceGrafica;

import Grafico.GraficoDeBarrasPerguntas;
import Grafico.GraficoDeBarrasPontuacaoJogos;
import Grafico.GraficoDeBarrasTempoJogo;
import OperacoesBd.Operacoes;
import PreparacaoDados.PreparacaoDadosJogos;
import PreparacaoDados.PreparacaoDadosPesq;
import PreparacaoDados.PreparacaoDadosPontuacao;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author Aluno
 */
public class Analizador extends javax.swing.JFrame {

    /**
     * Creates new form Analizador
     */
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modelo2 = new DefaultTableModel();
    DefaultTableModel modelo3 = new DefaultTableModel();
    PreparacaoDadosPesq prep = new PreparacaoDadosPesq();
    PreparacaoDadosJogos prep2 = new PreparacaoDadosJogos();
    PreparacaoDadosPontuacao prep3 = new PreparacaoDadosPontuacao(); 
    Operacoes op = new Operacoes();
    String escolha = "";
    String nome = "";
    URL caminhoImagem;
    Image iconeTitulo;
    int id = 0;

    public Analizador() {
        initComponents();
        caminhoImagem = this.getClass().getClassLoader().getResource("104705.png");
        iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        setIconImage(iconeTitulo);
        carregarOpoes();
        
        modelo3.addColumn("Xi");
        modelo3.addColumn("fi");
        modelo3.addColumn("Fi");
        modelo3.addColumn("fr");
        modelo3.addColumn("Fr");
        modelo3.addColumn("Xi*fi");
        modelo3.addColumn("(Xi-x)²*fi");
        
        modelo2.addColumn("Classes");
        modelo2.addColumn("Xi");
        modelo2.addColumn("fi");
        modelo2.addColumn("Fi");
        modelo2.addColumn("fr");
        modelo2.addColumn("Fr");
        modelo2.addColumn("Xi*fi");
        modelo2.addColumn("(Xi-x)²*fi");

        modelo.addColumn("Xi");
        modelo.addColumn("fi");
        modelo.addColumn("Fi");
        modelo.addColumn("fr");
        modelo.addColumn("Fr");
        modelo.addColumn("Xi*fi");
        modelo.addColumn("(Xi-x)²*fi");
        

    }
    //Método para preeencher a tabela com os dados da pesquisa
    public void PreencherTabela(String campo, int id_jogo) {
        prep.PreencherRol(campo, id_jogo);
        prep.PreencherXi();
        prep.Preencherfi();
        prep.PreencherFi();
        prep.Preencherfr();
        prep.PreencherFr();
        prep.preencherXifi();
        prep.preencherUltima();
        prep.preencherModa();

        for (int i = 0; i < prep.retornaXi().size(); i++) {
            modelo.addRow(new Object[]{prep.retornaXi().get(i), prep.retornafi().get(i),
                prep.retornaFi().get(i), prep.retornafr().get(i), prep.retornaFr().get(i),
                prep.retornaXifi().get(i), prep.retornaUltima().get(i)});
        }
        modelo.addRow(new Object[]{null, prep.somatoriofi(), null, prep.somatoriofr(), null, prep.somatorioXifi(), prep.somatorioUltima()});
    }
    
    //Método para preeencher a tabela com os dados de tempo do jogo
     public void PreencherTabela2(int id_jogo) {
        prep2.PreencherRol("tempo_jogo", id_jogo);
        prep2.preencherClasses();
        prep2.PreencherXi();
        prep2.Preencherfi();
        prep2.PreencherFi();
        prep2.Preencherfr();
        prep2.PreencherFr();
        prep2.preencherXifi();
        prep2.preencherUltima();
        prep2.preencherModa();

        for (int i = 0; i < prep2.retornaXi().size(); i++) {
            modelo2.addRow(new Object[]{prep2.formataClasse().get(i),prep2.retornaXi().get(i), prep2.retornafi().get(i),
                prep2.retornaFi().get(i), prep2.retornafr().get(i), prep2.retornaFr().get(i),
                prep2.retornaXifi().get(i), prep2.retornaUltima().get(i)});
        }
        modelo2.addRow(new Object[]{null,null, prep2.somatoriofi(), null, prep2.somatoriofr(), null, prep2.somatorioXifi(), prep2.somatorioUltima()});
         
    }
    
    //Método para preeencher a tabela com os dados de pontuação do jogo
    public void PreencherTabela3(int id_jogo) {
        prep3.PreencherRol(id_jogo);
        prep3.PreencherXi();
        prep3.Preencherfi();
        prep3.PreencherFi();
        prep3.Preencherfr();
        prep3.PreencherFr();
        prep3.preencherXifi();
        prep3.preencherUltima();
        prep3.preencherModa();

        for (int i = 0; i < prep3.retornaXi().size(); i++) {
            modelo3.addRow(new Object[]{prep3.retornaXi().get(i), prep3.retornafi().get(i),
                prep3.retornaFi().get(i), prep3.retornafr().get(i), prep3.retornaFr().get(i),
                prep3.retornaXifi().get(i), prep3.retornaUltima().get(i)});
        }
        modelo3.addRow(new Object[]{null, prep3.somatoriofi(), null, prep3.somatoriofr(), null, prep3.somatorioXifi(), prep3.somatorioUltima()});
    }

    
    //Método para carregar o gráfico com os dados da pesquisa do jogo
    public void carregarGrafico(String nome, String escolha,int id) {
        //System.out.println("Criando grafico!");
        GraficoDeBarrasPerguntas gdb = new GraficoDeBarrasPerguntas(nome, escolha, id);
        jpGrafico.setLayout((new java.awt.BorderLayout()));
        jpGrafico.add(gdb.getChartPanel(), BorderLayout.CENTER);
        jpGrafico.validate();
    }
    
     //Método para carregar o gráfico com os dados de pontuação do jogo
    public void carregarGrafico2(int id) {
        System.out.println(id);
        GraficoDeBarrasPontuacaoJogos gdb = new GraficoDeBarrasPontuacaoJogos("Pontuação",id);
        jpGrafico.setLayout((new java.awt.BorderLayout()));
        jpGrafico.add(gdb.getChartPanel(), BorderLayout.CENTER);
        jpGrafico.validate();
    }
    
     //Método para carregar gráfico com os dados de tempo do jogo
     public void carregarGrafico3(int id) {
        //System.out.println("Criando grafico!");
        GraficoDeBarrasTempoJogo gdb = new GraficoDeBarrasTempoJogo("Tempo de jogo",id);
        jpGrafico.setLayout((new java.awt.BorderLayout()));
        jpGrafico.add(gdb.getChartPanel(), BorderLayout.CENTER);
        jpGrafico.validate();
    }
    //Método para carregar o combox com o nome dos jogos salvos
    public void carregarOpoes() {

        
        cbId_jogo.removeAllItems();
        
        for (String x : op.buscarNomeJogos()) {
            cbId_jogo.addItem(x);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgGrupoBotoes = new javax.swing.ButtonGroup();
        Pergunta1 = new javax.swing.JRadioButton();
        Pergunta2 = new javax.swing.JRadioButton();
        Pergunta3 = new javax.swing.JRadioButton();
        Pergunta4 = new javax.swing.JRadioButton();
        Pergunta5 = new javax.swing.JRadioButton();
        jlMedia = new javax.swing.JLabel();
        txtMedia = new javax.swing.JTextField();
        jlModa = new javax.swing.JLabel();
        txtModa = new javax.swing.JTextField();
        jlMediana = new javax.swing.JLabel();
        txtMediana = new javax.swing.JTextField();
        jlVariancia = new javax.swing.JLabel();
        txtVariancia = new javax.swing.JTextField();
        jlDesvioPadrao = new javax.swing.JLabel();
        txtDesvioPadrao = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtDistribuicao = new javax.swing.JTable();
        jpGrafico = new javax.swing.JPanel();
        Pontos_jogo = new javax.swing.JRadioButton();
        cbId_jogo = new javax.swing.JComboBox<>();
        Tempo_jogo = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SOFTWARE ANALIZADOR");
        setBackground(new java.awt.Color(0, 0, 0));
        setName("SOFTWARE ANALIZAR ESTATÍSTICO"); // NOI18N
        setResizable(false);

        bgGrupoBotoes.add(Pergunta1);
        Pergunta1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Pergunta1.setText("Jogaria?");
        Pergunta1.setToolTipText("");
        Pergunta1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Pergunta1ItemStateChanged(evt);
            }
        });

        bgGrupoBotoes.add(Pergunta2);
        Pergunta2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Pergunta2.setText("Recomendaria?");
        Pergunta2.setToolTipText("");
        Pergunta2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Pergunta2ItemStateChanged(evt);
            }
        });

        bgGrupoBotoes.add(Pergunta3);
        Pergunta3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Pergunta3.setText("Sonorização");
        Pergunta3.setToolTipText("");
        Pergunta3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Pergunta3ItemStateChanged(evt);
            }
        });

        bgGrupoBotoes.add(Pergunta4);
        Pergunta4.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Pergunta4.setText("Jogabilidade");
        Pergunta4.setToolTipText("");
        Pergunta4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Pergunta4ItemStateChanged(evt);
            }
        });

        bgGrupoBotoes.add(Pergunta5);
        Pergunta5.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Pergunta5.setText("Visual");
        Pergunta5.setToolTipText("");
        Pergunta5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Pergunta5ItemStateChanged(evt);
            }
        });

        jlMedia.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jlMedia.setText("Media");
        jlMedia.setToolTipText("");

        txtMedia.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        txtMedia.setToolTipText("");
        txtMedia.setEnabled(false);

        jlModa.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jlModa.setText("Moda");
        jlModa.setToolTipText("");

        txtModa.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        txtModa.setToolTipText("");
        txtModa.setEnabled(false);

        jlMediana.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jlMediana.setText("Mediana");
        jlMediana.setToolTipText("");

        txtMediana.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        txtMediana.setToolTipText("");
        txtMediana.setEnabled(false);

        jlVariancia.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jlVariancia.setText("Variância");
        jlVariancia.setToolTipText("");

        txtVariancia.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        txtVariancia.setToolTipText("");
        txtVariancia.setEnabled(false);

        jlDesvioPadrao.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jlDesvioPadrao.setText("Desvio  Padrão");
        jlDesvioPadrao.setToolTipText("");

        txtDesvioPadrao.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        txtDesvioPadrao.setToolTipText("");
        txtDesvioPadrao.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TABELA DE DISTRIBUIÇÃO DE FREQUÊNCIA");
        jLabel1.setToolTipText("");

        jScrollPane2.setToolTipText("");

        jtDistribuicao.setBackground(new java.awt.Color(210, 209, 224));
        jtDistribuicao.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jtDistribuicao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Xi", "fi", "Fi", "fr", "Fr", "Xi*fi", "(Xi-x)²*fi"
            }
        ));
        jtDistribuicao.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jtDistribuicao.setGridColor(new java.awt.Color(240, 240, 245));
        jtDistribuicao.setInheritsPopupMenu(true);
        jtDistribuicao.setName("ANALIZADOR"); // NOI18N
        jScrollPane2.setViewportView(jtDistribuicao);

        jpGrafico.setToolTipText("");

        javax.swing.GroupLayout jpGraficoLayout = new javax.swing.GroupLayout(jpGrafico);
        jpGrafico.setLayout(jpGraficoLayout);
        jpGraficoLayout.setHorizontalGroup(
            jpGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 792, Short.MAX_VALUE)
        );
        jpGraficoLayout.setVerticalGroup(
            jpGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        bgGrupoBotoes.add(Pontos_jogo);
        Pontos_jogo.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Pontos_jogo.setText("Pontuação");
        Pontos_jogo.setToolTipText("");
        Pontos_jogo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Pontos_jogoItemStateChanged(evt);
            }
        });

        cbId_jogo.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        cbId_jogo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Selecione o nome do jogo>", "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbId_jogo.setToolTipText("");
        cbId_jogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbId_jogoActionPerformed(evt);
            }
        });

        bgGrupoBotoes.add(Tempo_jogo);
        Tempo_jogo.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Tempo_jogo.setText("Tempo de jogo");
        Tempo_jogo.setToolTipText("");
        Tempo_jogo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Tempo_jogoItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtVariancia, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMediana, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtModa, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlMedia, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlModa, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlMediana, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlVariancia, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlDesvioPadrao, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Pergunta2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Pergunta4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Pergunta5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Pergunta3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Pontos_jogo, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Pergunta1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbId_jogo, javax.swing.GroupLayout.Alignment.LEADING, 0, 181, Short.MAX_VALUE)
                            .addComponent(Tempo_jogo, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMedia, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDesvioPadrao))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbId_jogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Pergunta1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Pergunta2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Pergunta3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Pergunta4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Pergunta5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Pontos_jogo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Tempo_jogo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlMedia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMedia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlModa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtModa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlMediana)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMediana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlVariancia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVariancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlDesvioPadrao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDesvioPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jpGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Pergunta1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Pergunta1ItemStateChanged
        // TODO add your handling code here:
        try {
            if (Pergunta1.isSelected()) {
                PreencherTabela("pergunta1", id);
                
                jtDistribuicao.setModel(modelo);
                txtMedia.setText(String.valueOf(prep.Media()));
                txtModa.setText(String.valueOf(prep.retornaModa()));
                txtMediana.setText(String.valueOf(prep.Mediana()));
                txtVariancia.setText(String.valueOf(prep.Variancia()));
                txtDesvioPadrao.setText(String.valueOf(prep.desvioPadrao()));
                escolha = "pergunta1";
                String nome = Pergunta1.getText();
                carregarGrafico(nome,escolha,id);
                

            } else if (!Pergunta1.isSelected()) {
                ((DefaultTableModel) jtDistribuicao.getModel()).setRowCount(0);
                prep.LimparDados();

            }
        } 
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Não existem dados no banco de dados!");
        }
        
     
        
    }//GEN-LAST:event_Pergunta1ItemStateChanged

    private void Pergunta2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Pergunta2ItemStateChanged
        // TODO add your handling code here:
        try {
            if (Pergunta2.isSelected()) {
                PreencherTabela("pergunta2", id);
                
                jtDistribuicao.setModel(modelo);
                txtMedia.setText(String.valueOf(prep.Media()));
                txtModa.setText(String.valueOf(prep.retornaModa()));
                txtMediana.setText(String.valueOf(prep.Mediana()));
                txtVariancia.setText(String.valueOf(prep.Variancia()));
                txtDesvioPadrao.setText(String.valueOf(prep.desvioPadrao()));
                escolha = "pergunta2";
                String nome = Pergunta2.getText();
                carregarGrafico(nome,escolha,id);
            } else if (!Pergunta2.isSelected()) {
                ((DefaultTableModel) jtDistribuicao.getModel()).setRowCount(0);
                prep.LimparDados();
                
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Não existem dados no banco de dados!");
        }
    }//GEN-LAST:event_Pergunta2ItemStateChanged

    private void Pergunta3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Pergunta3ItemStateChanged
        // TODO add your handling code here:
        try {
                if (Pergunta3.isSelected()) {
                    PreencherTabela("pergunta3", id);
                    
                    jtDistribuicao.setModel(modelo);
                    txtMedia.setText(String.valueOf(prep.Media()));
                    txtModa.setText(String.valueOf(prep.retornaModa()));
                    txtMediana.setText(String.valueOf(prep.Mediana()));
                    txtVariancia.setText(String.valueOf(prep.Variancia()));
                    txtDesvioPadrao.setText(String.valueOf(prep.desvioPadrao()));
                    escolha = "pergunta3";
                    String nome = Pergunta3.getText();
                    carregarGrafico(nome,escolha,id);
                } else if (!Pergunta3.isSelected()) {
                    ((DefaultTableModel) jtDistribuicao.getModel()).setRowCount(0);
                    prep.LimparDados();
                    
                }
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null, "Não existem dados no banco de dados!");
            }

    }//GEN-LAST:event_Pergunta3ItemStateChanged

    private void Pergunta4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Pergunta4ItemStateChanged
        // TODO add your handling code here:
        try {
            if (Pergunta4.isSelected()) {
                PreencherTabela("pergunta4", id);
                
                jtDistribuicao.setModel(modelo);
                txtMedia.setText(String.valueOf(prep.Media()));
                txtModa.setText(String.valueOf(prep.retornaModa()));
                txtMediana.setText(String.valueOf(prep.Mediana()));
                txtVariancia.setText(String.valueOf(prep.Variancia()));
                txtDesvioPadrao.setText(String.valueOf(prep.desvioPadrao()));
                escolha = "pergunta4";
                String nome = Pergunta4.getText();
                carregarGrafico(nome,escolha,id);
            } else if (!Pergunta4.isSelected()) {
                ((DefaultTableModel) jtDistribuicao.getModel()).setRowCount(0);
                prep.LimparDados();
                
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Não existem dados no banco de dados!");
        }
    }//GEN-LAST:event_Pergunta4ItemStateChanged

    private void Pergunta5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Pergunta5ItemStateChanged
        // TODO add your handling code here:
        try {
           
            if (Pergunta5.isSelected()) {
                PreencherTabela("pergunta5", id);
                
                jtDistribuicao.setModel(modelo);
                txtMedia.setText(String.valueOf(prep.Media()));
                txtModa.setText(String.valueOf(prep.retornaModa()));
                txtMediana.setText(String.valueOf(prep.Mediana()));
                txtVariancia.setText(String.valueOf(prep.Variancia()));
                txtDesvioPadrao.setText(String.valueOf(prep.desvioPadrao()));
                escolha = "pergunta5";
                String nome = Pergunta5.getText();
                carregarGrafico(nome,escolha,id);
            } else if (!Pergunta5.isSelected()) {
                ((DefaultTableModel) jtDistribuicao.getModel()).setRowCount(0);
                prep.LimparDados();
                
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Não existem dados no banco de dados!");
        }
    }//GEN-LAST:event_Pergunta5ItemStateChanged

    private void Pontos_jogoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Pontos_jogoItemStateChanged
        // TODO add your handling code here:
        try {
            if (Pontos_jogo.isSelected()) {
                PreencherTabela3(id);
               
                jtDistribuicao.setModel(modelo3);
                txtMedia.setText(String.valueOf(prep3.Media()));
                txtModa.setText(String.valueOf(prep3.retornaModa()));
                txtMediana.setText(String.valueOf(prep3.Mediana()));
                txtVariancia.setText(String.valueOf(prep3.Variancia()));
                txtDesvioPadrao.setText(String.valueOf(prep3.desvioPadrao()));
                
                carregarGrafico2(id);
            } else if (!Pontos_jogo.isSelected()) {
                ((DefaultTableModel) jtDistribuicao.getModel()).setRowCount(0);
                prep3.LimparDados();
                
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Não existem dados no banco de dados!"+e.getMessage());
        }
    }//GEN-LAST:event_Pontos_jogoItemStateChanged

    private void cbId_jogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbId_jogoActionPerformed
        
        if (cbId_jogo.getSelectedItem()!= null) {
            nome = (cbId_jogo.getItemAt(cbId_jogo.getSelectedIndex()));
             id = op.buscarIdJogos(nome);
            
        }
        


    }//GEN-LAST:event_cbId_jogoActionPerformed

    private void Tempo_jogoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Tempo_jogoItemStateChanged
        // TODO add your handling code here:
        try {
            if (Tempo_jogo.isSelected()) {
                PreencherTabela2(id);
                
                jtDistribuicao.setModel(modelo2);
                txtMedia.setText(String.valueOf(prep2.Media()));
                txtModa.setText(String.valueOf(prep2.retornaModa()));
                txtMediana.setText(String.valueOf(prep2.Mediana()));
                txtVariancia.setText(String.valueOf(prep2.Variancia()));
                txtDesvioPadrao.setText(String.valueOf(prep2.desvioPadrao()));
                
                carregarGrafico3(id);
            } else if (!Tempo_jogo.isSelected()) {
                ((DefaultTableModel) jtDistribuicao.getModel()).setRowCount(0);

                prep2.LimparDados();
                
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Não existem dados no banco de dados!");
        }
    }//GEN-LAST:event_Tempo_jogoItemStateChanged

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
            java.util.logging.Logger.getLogger(Analizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Analizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Analizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Analizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Analizador().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Pergunta1;
    private javax.swing.JRadioButton Pergunta2;
    private javax.swing.JRadioButton Pergunta3;
    private javax.swing.JRadioButton Pergunta4;
    private javax.swing.JRadioButton Pergunta5;
    private javax.swing.JRadioButton Pontos_jogo;
    private javax.swing.JRadioButton Tempo_jogo;
    private javax.swing.ButtonGroup bgGrupoBotoes;
    private javax.swing.JComboBox<String> cbId_jogo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jlDesvioPadrao;
    private javax.swing.JLabel jlMedia;
    private javax.swing.JLabel jlMediana;
    private javax.swing.JLabel jlModa;
    private javax.swing.JLabel jlVariancia;
    private javax.swing.JPanel jpGrafico;
    private javax.swing.JTable jtDistribuicao;
    private javax.swing.JTextField txtDesvioPadrao;
    private javax.swing.JTextField txtMedia;
    private javax.swing.JTextField txtMediana;
    private javax.swing.JTextField txtModa;
    private javax.swing.JTextField txtVariancia;
    // End of variables declaration//GEN-END:variables
}
