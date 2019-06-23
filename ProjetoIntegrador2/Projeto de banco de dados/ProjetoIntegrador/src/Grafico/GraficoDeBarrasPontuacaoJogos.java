/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafico;

/**
 *
 * @author Aluno
 */

    
import OperacoesBd.Operacoes;
import PreparacaoDados.PreparacaoDadosPesq;
import PreparacaoDados.PreparacaoDadosPontuacao;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

public class GraficoDeBarrasPontuacaoJogos  {
    
    PreparacaoDadosPontuacao prep3 = new PreparacaoDadosPontuacao();
    Operacoes op = new Operacoes();
    ChartPanel chartPanel;
   
   //Método construtor para gerar o gráfico de barras
   public GraficoDeBarrasPontuacaoJogos(String chartTitle,int id_jogo ) {
      //super( applicationTitle );  
       System.out.println(id_jogo);   
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "Avaliação",            
         "Pontuação",            
         createDataset(id_jogo),          
         PlotOrientation.VERTICAL,           
         true, true, false);
         
      chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );
      
      
      
   }
   
   //Método para adicionar os dados aos eixos do gráfico
   private CategoryDataset createDataset(int id_jogo) {
      
      prep3.PreencherRol(id_jogo);
      prep3.PreencherXi();
      prep3.Preencherfi();  
      
      final String [] pontuacao = {"1-40","41-80","81-120","121-160"};
      final int[][] classe = {{1,40},{41,80},{81,120},{121,160}};
      
      final DefaultCategoryDataset dataset = 
      new DefaultCategoryDataset( );  
      
      
      int freq = 0;
      for(int i = 0; i < classe.length; i++)
      {
          freq = op.Frequencia(id_jogo,classe[i][0],classe[i][1]);
          if(freq > 0)
          {
            dataset.addValue(freq ,"", pontuacao[i]);
          }
           
      }  
      return dataset; 
   }
  
    //Método que retorna o panel onde será exibido o gráfico
    public ChartPanel getChartPanel() {
        return chartPanel;
    }
   
}

    
    

