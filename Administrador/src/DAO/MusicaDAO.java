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
public class MusicaDAO {
    private Connection conn;
    
    public MusicaDAO(Connection conn){
        this.conn = conn;
    }
    
    public ResultSet top5MusicasCurtidas() throws SQLException{
        String sql = "SELECT nome_musica, curtidas\n" +
                     "FROM prod.musica\n" +
                     "ORDER BY curtidas DESC\n" +
                     "LIMIT 5;";
        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        
        return resultado;
    }
    
    public ResultSet top5MusicasDescurtidas() throws SQLException{
        String sql = "SELECT nome_musica, descurtidas\n" +
                     "FROM prod.musica\n" +
                     "ORDER BY descurtidas DESC\n" +
                     "LIMIT 5;";
        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        
        return resultado;
    }
    
    public ResultSet totalMusicas() throws SQLException{
        String sql = "SELECT COUNT(*) AS total_musicas FROM prod.musica";
        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        
        return resultado;
    }
}
