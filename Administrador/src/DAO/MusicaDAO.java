package DAO;

import Exception.InfoNula;
import java.sql.*;
import model.Musica;

public class MusicaDAO {
    private Connection conn;

    public MusicaDAO(Connection conn) {
        this.conn = conn;
    }

    public ResultSet top5MusicasCurtidas() throws SQLException {
        String sql = "SELECT nome_musica, curtidas, descurtidas, duracao " +
                     "FROM musica " +
                     "ORDER BY curtidas DESC " +
                     "LIMIT 5";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        return statement.getResultSet();
    }

    public ResultSet top5MusicasDescurtidas() throws SQLException {
        String sql = "SELECT nome_musica, curtidas, descurtidas, duracao " +
                     "FROM musica " +
                     "ORDER BY descurtidas DESC " +
                     "LIMIT 5";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        return statement.getResultSet();
    }

    public ResultSet totalMusicas() throws SQLException {
        String sql = "SELECT COUNT(*) AS total_musicas FROM musica";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        return statement.getResultSet();
    }

    public ResultSet exibirTodasMusicas() throws SQLException {
        String sql = "SELECT m.id_musica, m.nome_musica, m.curtidas, m.descurtidas, m.duracao, " +
                     "a.nome_artista, g.nome AS genero " +
                     "FROM musica m " +
                     "LEFT JOIN artista_musica am ON m.id_musica = am.id_musica " +
                     "LEFT JOIN artista a ON am.id_artista = a.id_artista " +
                     "LEFT JOIN musica_genero mg ON m.id_musica = mg.id_musica " +
                     "LEFT JOIN genero g ON mg.id_genero = g.id_genero";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        return statement.getResultSet();
    }
    
// jamais esquecer o ::interval, qualquer conversao deve ser feita 
public void adicionarMusica(Musica musc) throws SQLException{
      
    String insertMusica = "INSERT INTO musica (nome_musica, duracao, curtidas, descurtidas) VALUES (?, ?::interval, ?, ?) RETURNING id_musica";
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
        // Verifica se a musica ja existe
        String selectArtista = "SELECT id_artista FROM artista WHERE nome_artista = ?";
        PreparedStatement stmtSelect = conn.prepareStatement(selectArtista);
        stmtSelect.setString(1, musc.getArtista().getNome());
        ResultSet rsArtista = stmtSelect.executeQuery();
        
        int idArtista;
        if (rsArtista.next()) {
            idArtista = rsArtista.getInt("id_artista");
        } else {
            String insertArtista = "INSERT INTO artista (nome_artista) VALUES (?) RETURNING id_artista";
            PreparedStatement stmtInsert = conn.prepareStatement(insertArtista);
            stmtInsert.setString(1, musc.getArtista().getNome().trim());
            ResultSet rsInsert = stmtInsert.executeQuery();
            rsInsert.next();
            idArtista = rsInsert.getInt("id_artista");
        }
        
        // Insere relação musica-artisra
        String insertArtistaRel = "INSERT INTO artista_musica (id_musica, id_artista) VALUES (?, ?)";
        PreparedStatement stmtArtista = conn.prepareStatement(insertArtistaRel);
        stmtArtista.setInt(1, idMusica);
        stmtArtista.setInt(2, idArtista);
        stmtArtista.executeUpdate();
    }
    
    // Adiciona genero caso ele nao exista 
    if (musc.getGenero() != null && !musc.getGenero().isEmpty()) {
        // Verifica se o genero ja existe
        String selectGenero = "SELECT id_genero FROM genero WHERE nome = ?";
        PreparedStatement stmtSelectGenero = conn.prepareStatement(selectGenero);
        stmtSelectGenero.setString(1, musc.getGenero().trim());
        ResultSet rsGenero = stmtSelectGenero.executeQuery();

        int idGenero;
        if (rsGenero.next()) {
            idGenero = rsGenero.getInt("id_genero");
        } else {
            String insertGenero = "INSERT INTO genero (nome) VALUES (?) RETURNING id_genero";
            PreparedStatement stmtInsertGenero = conn.prepareStatement(insertGenero);
            stmtInsertGenero.setString(1, musc.getGenero());
            ResultSet rsInsertGenero = stmtInsertGenero.executeQuery();
            rsInsertGenero.next();
            idGenero = rsInsertGenero.getInt("id_genero");
        }

        // Insere relação musica-genero
        String insertGeneroRel = "INSERT INTO musica_genero (id_musica, id_genero) VALUES (?, ?)";
        PreparedStatement stmtGeneroRel = conn.prepareStatement(insertGeneroRel);
        stmtGeneroRel.setInt(1, idMusica);
        stmtGeneroRel.setInt(2, idGenero);
        stmtGeneroRel.executeUpdate();
    }
}

    public void excluirMusica(int idMusica) throws SQLException {
        String deleteGenero = "DELETE FROM musica_genero WHERE id_musica = ?";
        PreparedStatement stmt1 = conn.prepareStatement(deleteGenero);
        stmt1.setInt(1, idMusica);
        stmt1.executeUpdate();

        String deleteArtista = "DELETE FROM artista_musica WHERE id_musica = ?";
        PreparedStatement stmt2 = conn.prepareStatement(deleteArtista);
        stmt2.setInt(1, idMusica);
        stmt2.executeUpdate();

        String deleteMusica = "DELETE FROM musica WHERE id_musica = ?";
        PreparedStatement stmt3 = conn.prepareStatement(deleteMusica);
        stmt3.setInt(1, idMusica);
        stmt3.executeUpdate();
    }
}
