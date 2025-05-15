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
                 "FROM musica m " + // .prod removido aqui
                 "LEFT JOIN artista_musica am ON m.id_musica = am.id_musica " + // .prod removido aqui
                 "LEFT JOIN artista a ON am.id_artista = a.id_artista " + // .prod removido aqui
                 "LEFT JOIN musica_genero mg ON m.id_musica = mg.id_musica " + // .prod removido aqui
                 "LEFT JOIN genero g ON mg.id_genero = g.id_genero " + // .prod removido aqui
                 "WHERE m.nome_musica LIKE ? OR a.nome_artista LIKE ? OR g.nome LIKE ?";

    PreparedStatement statement = conn.prepareStatement(sql);

    // O mesmo 'termoPesquisa' é usado para todos os campos
    statement.setString(1, "%" + termoPesquisa + "%"); // Para nome_musica
    statement.setString(2, "%" + termoPesquisa + "%"); // Para nome_artista
    statement.setString(3, "%" + termoPesquisa + "%"); // Para nome do gênero

    statement.execute();
    return statement.getResultSet();
}
}
