/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import view.MenuUsu;
import model.*;

/**
 *
 * @author ferna
 */
import model.Playlist;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlayDAO {
    private Connection conn;
    private MenuUsu v;

    public PlayDAO(Connection conn) {
        this.conn = conn;
    }

    public void insert(Playlist playlist) throws SQLException {

        String sql = "INSERT INTO playlist (nome_playlist, visibilidade, id_usuariodono) VALUES (?,?,?)";

        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, playlist.getNome());
            statement.setString(2, playlist.getVisibilidade());
            statement.setInt(3, playlist.getIdUsuarioDono()); // Continua sendo passado no DAO

            statement.execute();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
    public boolean delete(int idPlaylist) throws SQLException {
        String sql = "DELETE FROM playlist WHERE id_playlist = ?";
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, idPlaylist);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public ResultSet exibirTodasPlaylistsPorUsuario(int idUsuarioDono) throws SQLException {
        String sql = "SELECT id_playlist, nome_playlist, visibilidade, id_usuariodono " +
                     "FROM playlist " +
                     "WHERE id_usuariodono = ?"; // Filtra pelo ID do dono


        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, idUsuarioDono); 

        return statement.executeQuery(); 
    }
    public boolean update(int idPlaylist, String novoNome) throws SQLException {
        String sql = "UPDATE playlist SET nome_playlist = ? WHERE id_playlist = ?";
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, novoNome);
            statement.setInt(2, idPlaylist);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
 public void adicionarMusicaNaPlaylist(int idPlaylist, int idMusica) throws SQLException {

        String sql = "INSERT INTO playlist_musica (id_playlist, id_musica) VALUES (?, ?)";


        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPlaylist);
            pstmt.setInt(2, idMusica);

            int linhasAfetadas = pstmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Música com ID " + idMusica + " adicionada à playlist com ID " + idPlaylist + " com sucesso!");
            } else {
                System.out.println("Não foi possível adicionar a música à playlist. Verifique os IDs ou se a combinação já existe (se a PK for composta).");
            }

        } catch (SQLException e) {
            System.err.println("Erro de SQL ao adicionar música na playlist (PlaylistDAO): " + e.getMessage());

            throw e;
        }

    }
public int removerMusicaDaPlaylist(int idPlaylist, int idMusica) throws SQLException {

    String sql = "DELETE FROM playlist_musica WHERE id_playlist = ? AND id_musica = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, idPlaylist);
        pstmt.setInt(2, idMusica);

        int linhasAfetadas = pstmt.executeUpdate(); 

        if (linhasAfetadas > 0) {
            System.out.println("Música com ID " + idMusica + " removida da playlist com ID " + idPlaylist + " com sucesso!");
        } else {
            System.out.println("Não foi possível remover a música da playlist. Verifique os IDs ou se a combinação não existe.");
        }
        return linhasAfetadas;
    }
}
}
