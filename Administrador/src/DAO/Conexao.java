/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Arthur
 */
public class Conexao {
    public Connection getConnection() throws SQLException{
        String url = "jdbc:postgresql://db.kmajufuzbkxhrnennvqj.supabase.co:5432/postgres";
        String user = "postgres";
        String senha = "foda123";
        
        Connection conn = DriverManager.getConnection(url, user, senha);
        return conn;
    }
}
