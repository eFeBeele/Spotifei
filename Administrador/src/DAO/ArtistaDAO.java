/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.*;

/**
 *
 * @author Arthur
 */
public class ArtistaDAO {
    private Connection conn;
    
    public ArtistaDAO(Connection conn){
        this.conn = conn;
    }
    
    public void inserir(String nome_digitado) throws SQLException{
        String sql = "INSERT into artista (nome_artista) VALUES ('"
                      + nome_digitado + "')";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        conn.close();
    }
    
    public void excluir(String nome_digitado) throws SQLException{
        String sql = "Delete from artista where nome_artista = ?";      
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, nome_digitado);        
        statement.execute();
        conn.close();
    }
    
    public ResultSet consultar() throws SQLException{
        String sql = "SELECT * FROM artista";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        
        return resultado;
    }
}
