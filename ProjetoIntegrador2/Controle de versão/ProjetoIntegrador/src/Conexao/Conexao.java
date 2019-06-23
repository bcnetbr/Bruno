/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author bcnet
 */
public class Conexao {
    
    private static final String url = "jdbc:postgresql://localhost:5432/pesquisa";
    private static final String usuario = "postgres";
    private static final String senha = "Br801707#";
    private static final String driver = "org.postgresql.Driver";
    private static Connection con = null;
   
    //Método para conectar no banco de dados
    public static Connection Conectar()
    {  
       con = null;
       try
       {
           Class.forName(driver);
           con = DriverManager.getConnection(url,usuario,senha);
           
       }
       catch(SQLException e)
       {
           JOptionPane.showMessageDialog(null,"Erro de conexão" + e.getMessage());
       }
       catch(ClassNotFoundException e)
       {
           JOptionPane.showMessageDialog(null,"Erro de driver" + e.getMessage());
       }
          return con;  
    }
    
    //Método para desconectar no banco de dados
    public static void Desconectar() 
    {
        try
        {
            con.close();
            
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Erro ao desconectar" + e.getMessage());
        }
                
        
    }
    
    
}
