package controller;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import view.*;
import DAO.*;
import model.*;
import javax.swing.JTextArea;

public class ControllerPesquisa {

    private MenuUsu view;
    private ArrayList<Musica> musicasEncontradas = new ArrayList<>();
    private int indiceAtual = 0;

    public ControllerPesquisa(MenuUsu view) {
        this.view = view;
    }

    public ArrayList<Musica> criarListaDeMusicas(ResultSet resInfoMusicas) throws SQLException {
        ArrayList<Musica> musicas = new ArrayList<>();
        while (resInfoMusicas.next()) {
            int idMusica = resInfoMusicas.getInt("id_musica");
            String nomeMusica = resInfoMusicas.getString("nome_musica");
            int curtidas = resInfoMusicas.getInt("curtidas");
            int descurtidas = resInfoMusicas.getInt("descurtidas");
            String duracao = resInfoMusicas.getString("duracao");
            String nomeArtista = resInfoMusicas.getString("nome_artista");
            String genero = resInfoMusicas.getString("genero");

            musicas.add(new Musica(idMusica, nomeMusica, curtidas, descurtidas, duracao, new Artista(nomeArtista), genero));
        }
        return musicas;
    }

    public void mostrarTodasMusicas(String x) {
        Conexao conexao = new Conexao();

        try {
            Connection conn = conexao.getConnection();
            MusicaDAO muscDAO = new MusicaDAO(conn);

            ResultSet resInfoMusicas = muscDAO.exibirTodasMusicas(x);
            musicasEncontradas = criarListaDeMusicas(resInfoMusicas); 
            indiceAtual = 0; 
            exibirMusicaAtual(); 

            String resultado3 = "";
            for (Musica m : musicasEncontradas) {
                resultado3 += m.infoMusicasCompleta();
            }
            view.getResultadoMusica().setText(resultado3); 
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view,
                    e,
                    "Aviso",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void exibirMusicaAtual() {
        JTextArea MusicaAtu = view.getMusicaAtu();
        if (musicasEncontradas != null && !musicasEncontradas.isEmpty() && indiceAtual >= 0 && indiceAtual < musicasEncontradas.size()) {
            Musica musica = musicasEncontradas.get(indiceAtual);
            MusicaAtu.setText(musica.infoMusicasCompleta());
        } else if (MusicaAtu != null) {
            MusicaAtu.setText("");
        }
    }

    public void proximaMusica() {
        if (musicasEncontradas != null && !musicasEncontradas.isEmpty() && indiceAtual < musicasEncontradas.size() - 1) {
            indiceAtual++;
            exibirMusicaAtual();
        }
    }

    public void musicaAnterior() {
        if (musicasEncontradas != null && !musicasEncontradas.isEmpty() && indiceAtual > 0) {
            indiceAtual--;
            exibirMusicaAtual();
        }
    }

    public void curtirMusica() {
        if (musicasEncontradas != null && !musicasEncontradas.isEmpty() && indiceAtual >= 0 && indiceAtual < musicasEncontradas.size()) {
            Musica musicaAtual = musicasEncontradas.get(indiceAtual);
            atualizarCurtidaDescurtida(musicaAtual.getIdMusica(), musicaAtual.getCurtidas() + 1, musicaAtual.getDescurtidas());
            musicaAtual.setCurtidas(musicaAtual.getCurtidas() + 1);
            exibirMusicaAtual();
        }
    }

    public void descurtirMusica() {
        if (musicasEncontradas != null && !musicasEncontradas.isEmpty() && indiceAtual >= 0 && indiceAtual < musicasEncontradas.size()) {
            Musica musicaAtual = musicasEncontradas.get(indiceAtual);
            atualizarCurtidaDescurtida(musicaAtual.getIdMusica(), musicaAtual.getCurtidas(), musicaAtual.getDescurtidas() + 1);
            musicaAtual.setDescurtidas(musicaAtual.getDescurtidas() + 1);
            exibirMusicaAtual();
        }
    }

    private void atualizarCurtidaDescurtida(int idMusica, int novasCurtidas, int novasDescurtidas) {
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            MusicaDAO muscDAO = new MusicaDAO(conn);
            muscDAO.atualizarCurtidaDescurtida(idMusica, novasCurtidas, novasDescurtidas);
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view,
                    "Erro ao curtir/descurtir a música: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}