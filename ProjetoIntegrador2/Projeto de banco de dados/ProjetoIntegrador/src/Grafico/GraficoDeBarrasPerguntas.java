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

public class GraficoDeBarrasPerguntas  {
    
    PreparacaoDadosPesq prep = new PreparacaoDadosPesq();
    ChartPanel chartPanel;
   
   //Método construtor para gerar o gráfico de barras
   public GraficoDeBarrasPerguntas(String chartTitle, String campo,int id_jogo ) {
        
      prep.PreencherRol(campo,id_jogo);
      prep.PreencherXi();
      prep.Preencherfi();      
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "Avaliação",            
         "Notas",            
         createDataset(),          
         PlotOrientation.VERTICAL,           
         true, true, false);
         
      chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );
     
   }
   
   //Método para adicionar os dados aos eixos do gráfico
   private CategoryDataset createDataset( ) {
      
      
      final String [] notas = {"0","1","2","3","4","5","6","7","8","9","10"};
      
      final DefaultCategoryDataset dataset = 
      new DefaultCategoryDataset( );  
      
      
      for(int i = 0; i < prep.retornaXi().size(); i++)
      {
      
            if((Integer)prep.retornaXi().get(i) == 0)
            {
                dataset.addValue((Integer)prep.retornafi().get(i) ,"", notas[0]); 
            }
            if((Integer)prep.retornaXi().get(i) == 1)
            {
                dataset.addValue((Integer)prep.retornafi().get(i) ,"", notas[1]); 
            }

            if((Integer)prep.retornaXi().get(i) == 2)
            {
                dataset.addValue((Integer)prep.retornafi().get(i) ,"", notas[2]); 
            }
            
            if((Integer)prep.retornaXi().get(i) == 3)
            {
                dataset.addValue((Integer)prep.retornafi().get(i) , "", notas[3]); 
            }
            if((Integer)prep.retornaXi().get(i) == 4)
            {
                dataset.addValue((Integer)prep.retornafi().get(i) , "", notas[4]); 
            }
            
            if((Integer)prep.retornaXi().get(i) == 5)
            {
                dataset.addValue((Integer)prep.retornafi().get(i) , "", notas[5]); 
            }
            
            if((Integer)prep.retornaXi().get(i) == 6)
            {
                dataset.addValue((Integer)prep.retornafi().get(i) , "", notas[6]); 
            }
            
            if((Integer)prep.retornaXi().get(i) == 7)
            {
                dataset.addValue((Integer)prep.retornafi().get(i) , "", notas[7]); 
            }
            
            if((Integer)prep.retornaXi().get(i) == 8)
            {
                dataset.addValue((Integer)prep.retornafi().get(i) , "", notas[8]); 
            }
            
            if((Integer)prep.retornaXi().get(i) == 9)
            {
                dataset.addValue((Integer)prep.retornafi().get(i) , "", notas[9]); 
            }
            
            if((Integer)prep.retornaXi().get(i) == 10)
            {
                dataset.addValue((Integer)prep.retornafi().get(i) , "", notas[10]); 
            }
            
            
            
           
      }
      
     

      return dataset; 
   }
   
   
   //Método que retorna o panel onde será exibido o gráfico
   public ChartPanel getChartPanel() {
       return chartPanel;
   }
   
}

    
    

