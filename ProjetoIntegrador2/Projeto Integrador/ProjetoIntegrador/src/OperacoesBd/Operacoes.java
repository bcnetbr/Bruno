/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OperacoesBd;

import Conexao.Conexao;
import entidades.QuestionarioRespostas;
import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author bcnet
 */
public class Operacoes {
    
    private Connection con;
    
    
    public Operacoes() 
    {
        this.con = Conexao.Conectar();
    }
    
    public boolean inserirRespostaQuestionario(QuestionarioRespostas quest) 
    {
        this.con = Conexao.Conectar();
        String query = "INSERT INTO perguntas(pergunta1,pergunta2,pergunta3,pergunta4,pergunta5,id_jogo)VALUES(?,?,?,?,?,?)";
        this.con = Conexao.Conectar();
        
        try 
        {
            PreparedStatement stmt = con.prepareStatement(query);
            
            stmt.setInt(1, quest.getNota1());
            stmt.setInt(2, quest.getNota2());
            stmt.setInt(3, quest.getNota3());
            stmt.setInt(4, quest.getNota4());
            stmt.setInt(5, quest.getNota5());
            stmt.setInt(6, quest.getId_jogo());
            
            stmt.executeUpdate();
            stmt.close();
            
            return true;
        } 
        catch (SQLException ex) 
        {
            return false;
        }
        finally
        {
            
            Conexao.Desconectar();
        }
        
        
    }
    public ArrayList<Integer> buscarRespostasQuestionario(String coluna, int id_jogo)
    {
        this.con = Conexao.Conectar();
        ResultSet rs = null;
        ArrayList<Integer>quest = new ArrayList<>();
        
        String query = "select * from perguntas where id_jogo = ?";
        try 
        {
            PreparedStatement stmt = con.prepareStatement(query);
            
            stmt.setInt(1, id_jogo);
            
            rs = stmt.executeQuery();
            
            
            
            while(rs.next())
            {
               quest.add(rs.getInt(coluna));
            }
           
            rs.close();
            stmt.close();
            
            return quest;
            
            
        } 
        catch (SQLException ex) 
        {
            
            return quest;
        }
        
        finally
        {
            Conexao.Desconectar();
        }
        
        
    }
    
    public ArrayList<Double> buscarTempoJogos(String coluna, int id_jogo)
    {
        this.con = Conexao.Conectar();
        ResultSet rs = null;
        ArrayList<Double>quest = new ArrayList<>();
        
        String query = "select * from dados_jogos where id_jogo = ?";
        try 
        {
            PreparedStatement stmt = con.prepareStatement(query);
            
            stmt.setInt(1, id_jogo);
            
            rs = stmt.executeQuery();
            
            
            
            while(rs.next())
            {
               
               quest.add(rs.getDouble(coluna));
            }
           
            rs.close();
            stmt.close();
            
            return quest;
            
            
        } 
        catch (SQLException ex) 
        {
            System.out.println("Erro: " + ex.getMessage());

            return quest;
        }
        finally
        {
            Conexao.Desconectar();
        }
        
        
    }
    
    
    public ArrayList<String> buscarNomeJogos()
    {
        this.con = Conexao.Conectar();
        ResultSet rs = null;
        ArrayList<String>jogos = new ArrayList<>();
        String query = "select * from jogos" ;
        
        try 
        {
            PreparedStatement stmt = con.prepareStatement(query);
           
            
            rs = stmt.executeQuery();
            while(rs.next())
            {
               jogos.add(rs.getString("nome"));
            }
            
            stmt.close();
            
            return jogos;            
        } 
        catch (SQLException ex) 
        {
            System.out.println("Erro: " + ex.getMessage());

            return jogos;
        }
        finally
        {
            Conexao.Desconectar();
        }
    }
    
    public int buscarIdJogos(String nome)
    {   
        this.con = Conexao.Conectar();
        ResultSet rs = null;
       
        String query = "select id_jogo from jogos where nome = ?" ;
        int id = 0;
        try 
        {   PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, nome);
            
            rs = stmt.executeQuery();
            while(rs.next())
            {
               id = rs.getInt("id_jogo");
            }
            
            
            
            stmt.close();
            
            return id;            
        } 
        catch (SQLException ex) 
        {
            System.out.println("Erro: " + ex.getMessage());
            return id;
        }
        finally
        {
            Conexao.Desconectar();
        }
    }
    
    public ArrayList<Integer> buscarPontuacaoJogos(int id_jogo)
    {
        this.con = Conexao.Conectar();
        ResultSet rs = null;
        ArrayList<Integer>quest = new ArrayList<>();
       
        String query = "select ponto_jogo from dados_jogos where id_jogo = ?";
        try 
        {
            PreparedStatement stmt = con.prepareStatement(query);
            
            stmt.setInt(1, id_jogo);
            
            rs = stmt.executeQuery();
            
            
            
            while(rs.next())
            {
               quest.add(rs.getInt("ponto_jogo"));
            }
           
            rs.close();
            stmt.close();
            
            return quest;
            
            
        } 
        catch (SQLException ex) 
        {
            System.out.println("Erro: " + ex.getMessage());
            return quest;
        }
        finally
        {
            Conexao.Desconectar();
        }
        
        
    }
    //Método que retorna a frequencia da pontuação  
    public Integer Frequencia(int id_jogo,int min, int max)
    {
        this.con = Conexao.Conectar();
        ResultSet rs = null;
        int frequencia = 0;
        
        String query = "select count(ponto_jogo)as frequencia from dados_jogos where id_jogo = ? AND ponto_jogo BETWEEN ? AND ?";        
        try 
        {
            PreparedStatement stmt = con.prepareStatement(query);
            
            stmt.setInt(1, id_jogo);
            stmt.setInt(2, min);
            stmt.setInt(3, max);
           
            
            rs = stmt.executeQuery();
            
            
            
            while(rs.next())
            {
               frequencia = rs.getInt("frequencia");
            }
           
            rs.close();
            stmt.close();
            
            return frequencia;
           
            
        } 
        catch (SQLException ex) 
        {
            System.out.println("Erro: " + ex.getMessage());
            return frequencia;
        }
        finally
        {
            Conexao.Desconectar();
        }     
    }
    
    //Método que retorna a frequencia do tempo de jogo  
    public Integer FrequenciaTempo(int id_jogo,double min,double max)
    {
        this.con = Conexao.Conectar();
        ResultSet rs = null;
        int frequenciaTemp = 0;
       
        String query = "select count(tempo_jogo)as frequencia from dados_jogos where id_jogo = ? AND tempo_jogo >= ? AND tempo_jogo < ?";        
        try 
        {
            PreparedStatement stmt = con.prepareStatement(query);
            
            stmt.setInt(1, id_jogo);
            stmt.setDouble(2, min);
            stmt.setDouble(3, max);
            
            rs = stmt.executeQuery();
            
            
            
            while(rs.next())
            {
               frequenciaTemp = rs.getInt("frequencia");
            }
           
            rs.close();
            stmt.close();
            
            return frequenciaTemp;
            
            
        } 
        catch (SQLException ex) 
        {
            System.out.println("Erro: " + ex.getMessage());
            return frequenciaTemp;
        }
        finally
        {
            Conexao.Desconectar();
        }     
    } 
}
