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
import view.*;

/**
 *
 * @author ferna
 */
public class MusicaDAO {
        private Connection conn;
        private MenuUsu v;

    public MusicaDAO(Connection conn) {
        this.conn = conn;
    }
    
public ResultSet exibirTodasMusicas(String termoPesquisa) throws SQLException {
    String sql = "SELECT m.id_musica, m.nome_musica, m.curtidas, m.descurtidas, m.duracao, " +
                 "a.nome_artista, g.nome AS genero " +
                 "FROM musica m " + 
                 "LEFT JOIN artista_musica am ON m.id_musica = am.id_musica " + 
                 "LEFT JOIN artista a ON am.id_artista = a.id_artista " + 
                 "LEFT JOIN musica_genero mg ON m.id_musica = mg.id_musica " + 
                 "LEFT JOIN genero g ON mg.id_genero = g.id_genero " + 
                 "WHERE m.nome_musica LIKE ? OR a.nome_artista LIKE ? OR g.nome LIKE ?";

    PreparedStatement statement = conn.prepareStatement(sql);

    statement.setString(1, "%" + termoPesquisa + "%"); 
    statement.setString(2, "%" + termoPesquisa + "%"); 
    statement.setString(3, "%" + termoPesquisa + "%"); 

    statement.execute();
    return statement.getResultSet();
}
    public void atualizarCurtidaDescurtida(int idMusica, int novasCurtidas, int novasDescurtidas) throws SQLException {
        String sql = "UPDATE musica SET curtidas = ?, descurtidas = ? WHERE id_musica = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, novasCurtidas);
            pstmt.setInt(2, novasDescurtidas);
            pstmt.setInt(3, idMusica);
            pstmt.executeUpdate();
        }
    }
    public void registrarHistoricoCurtida(int idUsuario, int idMusica) throws SQLException {
        registrarHistorico(idUsuario, idMusica, true);
    }

    public void registrarHistoricoDescurtida(int idUsuario, int idMusica) throws SQLException {
        registrarHistorico(idUsuario, idMusica, false);
    }

    private void registrarHistorico(int idUsuario, int idMusica, boolean curtida) throws SQLException {
        String sqlVerificar = "SELECT COUNT(*) FROM historico WHERE id_usuario = ? AND id_musica = ?";
        String sqlDeletar = "DELETE FROM historico WHERE id_usuario = ? AND id_musica = ?";
        String sqlInserir = "INSERT INTO historico (id_usuario, id_musica, curtida) VALUES (?, ?, ?)";

        try (PreparedStatement pstmtVerificar = conn.prepareStatement(sqlVerificar);
             PreparedStatement pstmtDeletar = conn.prepareStatement(sqlDeletar);
             PreparedStatement pstmtInserir = conn.prepareStatement(sqlInserir)) {

            // Verificar se já existe um registro
            pstmtVerificar.setInt(1, idUsuario);
            pstmtVerificar.setInt(2, idMusica);
            ResultSet rs = pstmtVerificar.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (count > 0) {
                // Deletar o registro existente
                pstmtDeletar.setInt(1, idUsuario);
                pstmtDeletar.setInt(2, idMusica);
                pstmtDeletar.executeUpdate();
            }

            // Criar um novo registro
            pstmtInserir.setInt(1, idUsuario);
            pstmtInserir.setInt(2, idMusica);
            pstmtInserir.setBoolean(3, curtida);
            pstmtInserir.executeUpdate();
        }
    }
public Boolean verificarStatusCurtida(int idUsuario, int idMusica) throws SQLException {
    String sql = "SELECT curtida FROM historico WHERE id_usuario = ? AND id_musica = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, idUsuario);
        pstmt.setInt(2, idMusica);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getBoolean("curtida"); // Retorna true se curtiu, false se descurtiu
            }
        }
    }
    return null; // Retorna null se não houver registro (nem curtiu, nem descurtiu)
}
}
