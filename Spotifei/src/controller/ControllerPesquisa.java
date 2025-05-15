package controller;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import view.*;
import DAO.*;
import model.Musica;

public class ControllerPesquisa {

    private MenuUsu view;
    private MusicaDAO dao;
    private Connection conexao;

    public ControllerPesquisa(MenuUsu view, Connection conexao) {
        this.view = view;
        this.conexao = conexao;
        this.dao = new MusicaDAO(this.conexao);
    }

    public void pesquisarMusicas() {
        String termo = view.getBuscaMus().getText();
        ArrayList<Musica> musicas = new ArrayList<Musica>();
        StringBuilder resultadosConcatenados = new StringBuilder();

        if (termo.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Por favor, digite algo para pesquisar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            ResultSet rs = dao.pesquisarMusicas(termo);

            // Enquanto houver resultados no ResultSet
            while (rs.next()) {
                // Crie uma nova instância de Musica
                Musica musica = new Musica();

                // Preencha os atributos da música com os dados do ResultSet
                musica // Ajuste o nome da coluna se necessário
//                musica.setNomeMusica(rs.getString("nome_musica")); // Ajuste o nome da coluna se necessário
//                musica.setGenero(rs.getString("genero")); // Ajuste o nome da coluna se necessário
//                musica.setCurtidas(rs.getInt("curtidas")); // Ajuste o nome da coluna se necessário
//                musica.setDescurtidas(rs.getInt("descurtidas")); // Ajuste o nome da coluna se necessário
//                musica.setDuracao(rs.getString("duracao")); // Ajuste o nome da coluna se necessário

                // Para o artista, precisamos buscar o nome (assumindo uma relação com outra tabela)
                int artistaId = rs.getInt("artista_id"); // Ajuste o nome da coluna se necessário
                String nomeArtista = buscarNomeArtista(artistaId);
                Artista artista = new Artista(artistaId, nomeArtista);
                musica.setArtista(artista);

                // Adicione a música à lista
                musicas.add(musica);
            }

            // Após processar todos os resultados, itere pela lista e exiba na TextArea
            int i = 0;
            while (i < musicas.size()) {
                resultadosConcatenados.append(musicas.get(i).infoMusicasCompleta());
                i++;
            }

            view.exibirResultados(resultadosConcatenados.toString());

            if (listaDeMusicas.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Nenhum resultado encontrado para: " + termo, "Informação", JOptionPane.INFORMATION_MESSAGE);
                view.exibirResultados("");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Erro ao executar a pesquisa: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private String buscarNomeArtista(int artistaId) throws SQLException {
        String sql = "SELECT nome FROM artistas WHERE id_artista = ?";
        try (java.sql.PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, artistaId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("nome");
            }
        }
        return "Artista Desconhecido";
    }
}