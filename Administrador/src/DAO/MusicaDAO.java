package DAO;

import java.sql.*;
import model.Musica;

public class MusicaDAO {
    private Connection conn;

    public MusicaDAO(Connection conn) {
        this.conn = conn;
    }

    public ResultSet top5MusicasCurtidas() throws SQLException {
        String sql = "SELECT nome_musica, curtidas, descurtidas, duracao " +
                     "FROM prod.musica " +
                     "ORDER BY curtidas DESC " +
                     "LIMIT 5";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        return statement.getResultSet();
    }

    public ResultSet top5MusicasDescurtidas() throws SQLException {
        String sql = "SELECT nome_musica, curtidas, descurtidas, duracao " +
                     "FROM prod.musica " +
                     "ORDER BY descurtidas DESC " +
                     "LIMIT 5";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        return statement.getResultSet();
    }

    public ResultSet totalMusicas() throws SQLException {
        String sql = "SELECT COUNT(*) AS total_musicas FROM prod.musica";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        return statement.getResultSet();
    }

    public ResultSet exibirTodasMusicas() throws SQLException {
        String sql = "SELECT m.id_musica, m.nome_musica, m.curtidas, m.descurtidas, m.duracao, " +
                     "a.nome_artista, g.nome AS genero " +
                     "FROM prod.musica m " +
                     "LEFT JOIN prod.artista_musica am ON m.id_musica = am.id_musica " +
                     "LEFT JOIN prod.artista a ON am.id_artista = a.id_artista " +
                     "LEFT JOIN prod.musica_genero mg ON m.id_musica = mg.id_musica " +
                     "LEFT JOIN prod.genero g ON mg.id_genero = g.id_genero";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        return statement.getResultSet();
    }

    public void adicionarMusica(Musica musc) throws SQLException {
        String insertMusica = "INSERT INTO prod.musica (nome_musica, duracao, curtidas, descurtidas) VALUES (?, ?, ?, ?) RETURNING id_musica";
        PreparedStatement stmt = conn.prepareStatement(insertMusica);
        stmt.setString(1, musc.getNomeMusica());
        stmt.setString(2, musc.getDuracao());
        stmt.setInt(3, musc.getCurtidas());
        stmt.setInt(4, musc.getDescurtidas());

        ResultSet rs = stmt.executeQuery();
        int idMusica = -1;
        if (rs.next()) {
            idMusica = rs.getInt("id_musica");
        }
        
        // Adiciona no nome do art caso nao tenha nas tabelas
        if (musc.getArtista().getNome() != null && !musc.getArtista().getNome().isEmpty()) {
            String insertArtistaRel = "INSERT INTO prod.artista_musica (id_musica, id_artista) " +
                                      "VALUES (?, (SELECT id_artista FROM prod.artista WHERE nome_artista = ?))";
            PreparedStatement stmtArtista = conn.prepareStatement(insertArtistaRel);
            stmtArtista.setInt(1, idMusica);
            stmtArtista.setString(2, musc.getArtista().getNome());
            stmtArtista.executeUpdate();
        }
        
        // Adiciona genero caso ele nao exista 
        if (musc.getGenero() != null && !musc.getGenero().isEmpty()) {
            String insertGeneroRel = "INSERT INTO prod.musica_genero (id_musica, id_genero) " +
                                     "VALUES (?, (SELECT id_genero FROM prod.genero WHERE nome = ?))";
            PreparedStatement stmtGenero = conn.prepareStatement(insertGeneroRel);
            stmtGenero.setInt(1, idMusica);
            stmtGenero.setString(2, musc.getGenero());
            stmtGenero.executeUpdate();
        }
    }

    public void excluirMusica(int idMusica) throws SQLException {
        String deleteGenero = "DELETE FROM prod.musica_genero WHERE id_musica = ?";
        PreparedStatement stmt1 = conn.prepareStatement(deleteGenero);
        stmt1.setInt(1, idMusica);
        stmt1.executeUpdate();

        String deleteArtista = "DELETE FROM prod.artista_musica WHERE id_musica = ?";
        PreparedStatement stmt2 = conn.prepareStatement(deleteArtista);
        stmt2.setInt(1, idMusica);
        stmt2.executeUpdate();

        String deleteMusica = "DELETE FROM prod.musica WHERE id_musica = ?";
        PreparedStatement stmt3 = conn.prepareStatement(deleteMusica);
        stmt3.setInt(1, idMusica);
        stmt3.executeUpdate();
    }
}
