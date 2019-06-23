/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PreparacaoDados;

import Conversao.Conversor;
import OperacoesBd.Operacoes;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 *
 * @author bcnet
 */
public class PreparacaoDadosJogos {
    
    private final ArrayList<Double>rol = new ArrayList<>();
    private final ArrayList<Integer>Xi = new ArrayList<>();
    private final ArrayList<Double>Xi2 = new ArrayList<>();
    private final ArrayList<Integer>fi = new ArrayList<>();
    private final ArrayList<Integer>Fi = new ArrayList<>();
    private final ArrayList<Double>fr = new ArrayList<>();
    private final ArrayList<Double>Fr = new ArrayList<>();
    private final ArrayList<Double>Xifi = new ArrayList<>();
    private final ArrayList<Double>ultima = new ArrayList<>();
    private final ArrayList<Integer>moda = new ArrayList<>();
    private final double classes[][] = new double[linha()][2];
    private final ArrayList<String>classesFormatada = new ArrayList<>(); 
    private final StringBuilder sb = new StringBuilder();
    
    
    Operacoes con = new Operacoes();

    
   
    public void PreencherRol(String campo, int id_jogo)
    {
        
        for(Double q : con.buscarTempoJogos(campo, id_jogo))
        {
            BigDecimal valor = new BigDecimal(q).setScale(0, RoundingMode.HALF_DOWN);
            this.rol.add(valor.doubleValue());
            
                    
        }
        Collections.sort(this.rol);
        
        
         
    }
    
    
    public void PreencherXi()
    {
        
       double xi = 0;
       for(int i = 0; i < classes.length; i++)
        {
             
            for(int j = 0; j < classes[i].length; j++)
            {
                xi = xi + classes[i][j];
            }
            xi = xi/2;
            BigDecimal valor = new BigDecimal(xi).setScale(2, RoundingMode.HALF_DOWN);
            Xi2.add(valor.doubleValue());
           
        }
        
    }
    
    public void Preencherfi()
    {
        
        
        int contador = 0;
        for(int i = 0; i < classes.length; i++)
        {   
                
                for(double v : rol)
                {
                    if( v >= this.classes[i][0] && v < classes[i][1])
                    {
                        contador+=1;
                    }
                }
                
                this.fi.add(contador);
                contador = 0;
            
            
            
        }
        
        
    }
    
    public void PreencherFi()
    {
        int x = 0;
        for(int i = 0; i < this.fi.size(); i++)
        {
            int aux = this.fi.get(i);
            x += aux;
            
            this.Fi.add(x);
            
        }
        
    }
    
     public void Preencherfr()
    {
       for(double fi : this.fi )
       {
           double aux = (fi * 100) / somatoriofi();
           BigDecimal valor = new BigDecimal(aux).setScale(2, RoundingMode.HALF_DOWN);
           this.fr.add(valor.doubleValue());
       }
       
    }
    
    public void PreencherFr()
    {
        double x = 0;
        for(int i = 0; i < this.fr.size(); i++)
        {
            double aux = this.fr.get(i);
            x += aux;
            BigDecimal valor = new BigDecimal(x).setScale(2, RoundingMode.HALF_DOWN);
            this.Fr.add(valor.doubleValue());
            
        }
       
    }
    
    public void preencherXifi()
    {
        for(int i = 0; i <this.Xi2.size(); i++)
        {
            double xi = Xi2.get(i);
           
            
            int fi0 = fi.get(i);
            
            double xifi = xi*fi0;
            
            BigDecimal valor = new BigDecimal(xifi).setScale(2, RoundingMode.HALF_DOWN);
            
            this.Xifi.add(valor.doubleValue());
        }
        
    }
    
    public void preencherUltima()
    {
        for(int i = 0; i <this.Xi2.size(); i++)
        {
            double resultado = Math.pow((this.Xi2.get(i) - Media()),2)*this.fi.get(i);
            BigDecimal valor = new BigDecimal(resultado).setScale(2, RoundingMode.HALF_DOWN);
            this.ultima.add(valor.doubleValue());
        }
         
    }
        
    public int calcularN()
    {
        int n = 0;
        
        for(int valor : this.fi)
        {
            n += valor;
        }
        return n;
    }
    public int somatoriofi()
    {
        int soma = 0;
        for(int fi : this.fi)
        {
            soma+=fi;
        }
        return soma;
    }
    
    public double somatoriofr()
    {
        double soma = 0;
        for(double fr : this.fr)
        {
            soma+=fr;
        }
        BigDecimal valor = new BigDecimal(soma).setScale(2, RoundingMode.HALF_DOWN);
        return valor.doubleValue();
    }
    
    public double somatorioXifi()
    {
        double soma = 0;
        for(double xifi : this.Xifi)
        {
            soma += xifi;
        }
        
        BigDecimal valor = new BigDecimal(soma).setScale(2, RoundingMode.HALF_DOWN);
        return valor.doubleValue();
    }
    
    public double somatorioUltima()
    {
        double soma = 0;
        for(double ultima : this.ultima)
        {
            soma += ultima;
        }
        BigDecimal valor = new BigDecimal(soma).setScale(2, RoundingMode.HALF_DOWN);
        return valor.doubleValue();
    }
    
    public double Media()
    {
        double media = somatorioXifi() / somatoriofi();
        BigDecimal valor = new BigDecimal(media).setScale(2, RoundingMode.HALF_DOWN);
        
        return valor.doubleValue();
    }
    
    public String Mediana()
    {
        double mediana;
        StringBuilder med = new StringBuilder();
        if(calcularN() % 2 == 0)
        {
            int posicao1 = ((somatoriofi()-1) / 2);
            int posicao2 = ((somatoriofi()-1) / 2) + 1;
            
            double mediana2 = (this.rol.get(posicao1) + this.rol.get(posicao2));
            mediana = mediana2/2;
            
            
            
        }
        else 
        {
            int valor = (somatoriofi() + 1)/2;
            mediana = rol.get(valor - 1);
            
        }
        int contador = 0;
        for(int m = 0; m < classes.length;m++ )
        {
            if(mediana >= classes[m][0] && mediana < classes[m][1])
            {
                med.append(classes[m][0]);
                med.append("-");
                med.append(classes[m][1]);
            }
                
        }
        
        
        return med.toString();
    }
    
    public double Variancia()
    {
        double variancia = somatorioUltima()/(somatoriofi() - 1);
        BigDecimal valor = new BigDecimal(variancia).setScale(2, RoundingMode.HALF_DOWN);
        
        return valor.doubleValue();
    }
    
    public double desvioPadrao()
    {
        double desvio = Math.sqrt(Variancia());
        BigDecimal valor = new BigDecimal(desvio).setScale(2, RoundingMode.HALF_DOWN);
        
        return valor.doubleValue(); 
    }
    
    public void preencherModa()
    {
        int max = Collections.max(this.fi);
        int posicao = 0;
         for(int moda : this.fi)
         {
             if(moda == max)
             {
                 
                 
                 sb.append(classes[posicao][0]);
                 sb.append("-");
                 sb.append(classes[posicao][1]);
                                          
                 if((posicao + 1) < fi.size())
                 {
                    sb.append(", ");
                 }
                 
             }
             posicao += 1; 
         }
         
    }
    
    public double calcularfr()
    {
        double n = 0;
        
        for(int i = 0; i < this.fi.size(); i++)
        {
            double aux = (this.fi.get(i)*100)/calcularN();
        }
        return n;
    }
      
    public void ListarRol()
    {
        System.out.println("Rol");
        for (double rol : this.rol)
        {
            
            System.out.print(rol);
        }
    }
    
    public void ListarXi()
    {
        System.out.println("Xi");
        for (double xi : Xi)
        {
            
            System.out.print(xi);
        }
    }
    
    public void Listarfi()
    {
        System.out.println("fi");
        for (double fi : fi)
        {
            System.out.print(fi);
        }
    }
    
    public void ListarFi()
    {
        System.out.println("Fi");
        for (double Fi : Fi)
        {
            System.out.print(Fi);
        }
    }
    
    public void Listarfr()
    {
        System.out.println("fr");
        
        for(double fr : fr)
        {
            System.out.print(fr);
        }
    }
    
    public void ListarFr()
    {
        System.out.println("Fr");
        
        for(double Fr : Fr)
        {
            System.out.print(Fr);
        }
    }
    
    public void ListarXifi()
    {
        System.out.println("Xifi");
        
        for(double xifi : Xifi)
        {
            System.out.print(xifi);
        }
    }
    
    public void ListarUltima()
    {
        System.out.println("(Xi-x)²*fi");
        
        for(double ultima : ultima)
        {
            System.out.print(ultima);
        }
    }
    
    public void ListarModa()
    {
        for(int moda : this.moda)
       {
            
           System.out.println(moda);
            
       }
    }
       
    public void mostraN()
    {
        System.out.print("Valor de N:" + calcularN());
        
    }
    
    public ArrayList retornaRol()
    {
        return this.rol;
    }
    
    public ArrayList retornaXi()
    {
        
        return this.Xi2;
        
    }
    
    public ArrayList retornafi()
    {
        return this.fi;
    }
    
    public ArrayList retornaFi()
    {
        return this.Fi;
    }
    
    public ArrayList retornafr()
    {
        return this.fr;
    }
    
    public ArrayList retornaFr()
    {
        return this.Fr;
    }
    
    public ArrayList retornaXifi()
    {
        return this.Xifi;
    }
    
    public ArrayList retornaUltima()
    {
        return this.ultima;
    }
    
    public String retornaModa()
    {
        String x = sb.toString();
        return x;
    }
    public Double K()
    {
       double valor = 1 + 3.33 * Math.log10(rol.size());
       BigDecimal v = new BigDecimal(valor).setScale(2, RoundingMode.HALF_DOWN);
       
       return v.doubleValue();
       
    }
    
    public Double AT()
    {
        double min = Collections.min(rol);
        double max = Collections.max(rol);
        
        double vl = max - min; 
        
        
        return vl;
    }
    
    public Double AC()
    {
        
        
       double valor = AT()/K();
       double v = Math.round(valor);
       BigDecimal ac = new BigDecimal(valor).setScale(2, RoundingMode.HALF_DOWN);
        
        
        return ac.doubleValue();
    }
    
    public Integer linha()
    {
        double valor = 1+ 3.33*Math.log10(30);
        BigDecimal k = new BigDecimal(valor).setScale(0, RoundingMode.HALF_DOWN);
        
        
      
       return k.intValue();
    }
    
    public void preencherClasses()
    {
        double min = Collections.min(this.rol);
        double max = Collections.max(this.rol);
        
        
        
        double valor = min;
        int x = 0;
        
        
        for(int i = 0; i < classes.length; i++)
        {               
            for(int j = 0; j < classes[i].length; j++)
            {
                BigDecimal Valor = new BigDecimal(valor).setScale(2, RoundingMode.HALF_DOWN);
                classes[i][j] = Valor.doubleValue();
                
                valor = valor +  AC();
                x = j;   
            }
            
            valor = classes[i][x];
        }
        
    }
    public ArrayList<String> formataClasse()
    {
        for(int i = 0; i < classes.length; i++)
        {
            StringBuilder formatarClasses = new StringBuilder();
            formatarClasses.append(classes[i][0]);
            formatarClasses.append("-");
            formatarClasses.append(classes[i][1]);
            classesFormatada.add(formatarClasses.toString());
            
            
        }
        
        return classesFormatada;
        
    }
    
    public String[] arrayClasses()
    {
        String classes2[] = new String[formataClasse().size()];
        
        for(int i = 0; i < formataClasse().size(); i++)
        {
            classes2[i] = formataClasse().get(i);
        }
        return classes2;
    }
    
    public void listarClasses()
    {
        for(int i = 0; i < classes.length; i++)
        {
             System.out.println("");
            for(int j = 0; j < classes[i].length; j++)
            {
                System.out.print("  " + classes[i][j]);
            }
           
        }
    }
    
    public double[][] retornaClasses()
    {
        return classes;
    }
    
    
       
    public void LimparDados()
    {
       this.rol.clear();
       this.Xi2.clear();
       this.fi.clear();
       this.Fi.clear();
       this.fr.clear();
       this.Fr.clear();
       this.Xifi.clear();
       this.ultima.clear();
       this.sb.setLength(0);
       this.classesFormatada.clear();
    }

    

    
    
}