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
import PreparacaoDados.PreparacaoDadosJogos;
import PreparacaoDados.PreparacaoDadosPesq;
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

public class GraficoDeBarrasTempoJogo  {
    
    PreparacaoDadosJogos prep = new PreparacaoDadosJogos();
    Operacoes op = new Operacoes();
    ChartPanel chartPanel;
   
   //Método construtor para gerar o gráfico de barras
   public GraficoDeBarrasTempoJogo( String chartTitle,int id_jogo ) {
        
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "Avaliação",            
         "Notas",            
         createDataset(id_jogo),          
         PlotOrientation.VERTICAL,           
         true, true, false);
         
      chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );
      
      
      
   }
   
   //Método para adicionar os dados aos eixos do gráfico
   private CategoryDataset createDataset(int id_jogo) {
      
      prep.PreencherRol("tempo_jogo",id_jogo);
      prep.preencherClasses();
       
      
      final DefaultCategoryDataset dataset = 
      new DefaultCategoryDataset( );  
      
      int freq = 0;
      for(int i = 0; i < prep.retornaClasses().length; i++)
      {
          freq = op.FrequenciaTempo(id_jogo,prep.retornaClasses()[i][0],prep.retornaClasses()[i][1]);
          if(freq > 0)
          {
              
            dataset.addValue(freq ,"", prep.formataClasse().get(i));
            
          }
           
      } 
      
      return dataset; 
   }
   
    //Método que retorna o panel onde será exibido o gráfico
    public ChartPanel getChartPanel() {
        return chartPanel;
    }
   
}

    
    

