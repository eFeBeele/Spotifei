/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Usuario;

/**
 *
 * @author ferna
 */
public class MusicaDAO {
        private Connection conn;

    public MusicaDAO(Connection conn) {
        this.conn = conn;
    }
    
public ResultSet pesquisarMusicas(String termoPesquisa) throws SQLException {
    String sql = "SELECT nome_musica, artista, genero FROM musicas " +
                 "WHERE nome_musica LIKE ? OR artista LIKE ? OR genero LIKE ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, "%" + termoPesquisa + "%");
    pstmt.setString(2, "%" + termoPesquisa + "%");
    pstmt.setString(3, "%" + termoPesquisa + "%");
    ResultSet rs = pstmt.executeQuery();
    return rs;
}
}
