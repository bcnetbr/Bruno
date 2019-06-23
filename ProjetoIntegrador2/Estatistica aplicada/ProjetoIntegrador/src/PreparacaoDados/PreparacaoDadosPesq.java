/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PreparacaoDados;

import OperacoesBd.Operacoes;
import entidades.QuestionarioRespostas;
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
public class PreparacaoDadosPesq {
    
    private final ArrayList<Integer>rol = new ArrayList<>();
    private final ArrayList<Integer>Xi = new ArrayList<>();
    private final ArrayList<Integer>fi = new ArrayList<>();
    private final ArrayList<Integer>Fi = new ArrayList<>();
    private final ArrayList<Double>fr = new ArrayList<>();
    private final ArrayList<Double>Fr = new ArrayList<>();
    private final ArrayList<Integer>Xifi = new ArrayList<>();
    private final ArrayList<Double>ultima = new ArrayList<>();
    private final ArrayList<Integer>moda = new ArrayList<>();
    private final StringBuilder sb = new StringBuilder(); 
    Operacoes con = new Operacoes();
    
    
    public void PreencherRol(String campo, int id_jogo)
    {
        int i = 0;
        for(int q : con.buscarRespostasQuestionario(campo, id_jogo))
        {
            this.rol.add(q);
            
            i = i+1;
                    
        }
        Collections.sort(this.rol);
        
    }
    public void PreencherXi()
    {
        ArrayList<Integer> aux = new ArrayList<>(new HashSet(this.rol));
        Collections.sort(aux);
        
        for(int xi : aux)
        {
            this.Xi.add(xi);
        }
       
    }
    
    public void Preencherfi()
    {
        for(int i = 0; i <= 10; i++)
        { 
           int frequencia = Collections.frequency(this.rol, i);
            if(frequencia > 0)
            {
                this.fi.add(frequencia);
            }    
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
       int cont = -1; 
       for(double fi : this.fi )
       {
           double aux = (fi * 100) / somatoriofi();
           BigDecimal valor = new BigDecimal(aux).setScale(2, RoundingMode.HALF_DOWN);
           this.fr.add(valor.doubleValue());
           cont += 1;
       }
       if(somatoriofr()>= 99 && somatoriofr() < 100)
       {
           
           double diferenca = 100 - somatoriofr();
           double valor = fr.get(cont) + diferenca;
           BigDecimal v = new BigDecimal(valor).setScale(2, RoundingMode.HALF_DOWN);
           this.fr.set(cont,v.doubleValue()); 
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
        for(int i = 0; i <this.Xi.size(); i++)
        {
            int xi = Xi.get(i);
            int fi0 = fi.get(i);
            int xifi = xi*fi0;
            this.Xifi.add(xifi);
        }
         
    }
    
    public void preencherUltima()
    {
        for(int i = 0; i <this.Xi.size(); i++)
        {
            double resultado = Math.pow((this.Xi.get(i) - Media()),2)*this.fi.get(i);
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
        return soma;
    }
    
    public double somatorioXifi()
    {
        double soma = 0;
        for(int xifi : this.Xifi)
        {
            soma += xifi;
        }
        return soma;
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
        //System.out.print(media);
        BigDecimal valor = new BigDecimal(media).setScale(2, RoundingMode.HALF_DOWN);
        return valor.doubleValue();
    }
    
    public double Mediana()
    {
        if(calcularN() % 2 == 0)
        {
            int posicao1 = ((somatoriofi()-1) / 2);
            int posicao2 = ((somatoriofi()-1) / 2) + 1;
            
            double mediana = (this.rol.get(posicao1) + this.rol.get(posicao2));
            double mediana2 = mediana/2;
            //System.out.print(mediana2);
            return mediana2;
            
            
        }
        else 
        {
            double mediana = (somatoriofi() + 1)/2;
            
            return mediana;
        }
    }
    
    public double Variancia()
    {
        double variancia = somatorioUltima()/(somatoriofi() - 1);
        //System.out.print(variancia);
        return variancia;
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
                 
                 sb.append(Integer.toString(this.Xi.get(posicao)));
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
        
        for(int xifi : Xifi)
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
        return this.Xi;
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
    public double calcularClasses()
    {
       double k = 1 + (3.33 * Math.log(retornaRol().size()));
       return k;
    }
    
    public double AT()
    {
        double min = Collections.min(rol);
        double max = Collections.max(rol);
        double At = max - min; 
        return At;
    }
    
    public void LimparDados()
    {
       this.rol.clear();
       this.Xi.clear();
       this.fi.clear();
       this.Fi.clear();
       this.fr.clear();
       this.Fr.clear();
       this.Xifi.clear();
       this.ultima.clear();
       this.sb.setLength(0);
    }
    
}