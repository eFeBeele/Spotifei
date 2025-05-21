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
            
            muscDAO.registrarHistoricoPesquisa(view.getIdUsu(),  x);
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

    public void curtirMusica() throws SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.getConnection();
        MusicaDAO muscDAO = new MusicaDAO(conn);

        if (musicasEncontradas != null && !musicasEncontradas.isEmpty() && indiceAtual >= 0 && indiceAtual < musicasEncontradas.size()) {
            Musica musicaAtual = musicasEncontradas.get(indiceAtual);
            int idMusica = musicaAtual.getIdMusica();
            int idUsuario = view.getIdUsu();

            Boolean status = muscDAO.buscarStatusCurtida(idUsuario, idMusica);

            if (status == null || !status) {
                muscDAO.atualizarCurtidaDescurtida(idMusica, musicaAtual.getCurtidas() + 1, musicaAtual.getDescurtidas() - (status == null ? 0 : 1));
                muscDAO.registrarHistoricoCurtida(idUsuario, idMusica);
                musicaAtual.setCurtidas(musicaAtual.getCurtidas() + 1);
                if (status != null && !status) {
                    musicaAtual.setDescurtidas(musicaAtual.getDescurtidas() - 1);
                }
                exibirMusicaAtual();
            }
        }
        conn.close();
    }


    public void descurtirMusica() throws SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.getConnection();
        MusicaDAO muscDAO = new MusicaDAO(conn);

        if (musicasEncontradas != null && !musicasEncontradas.isEmpty() && indiceAtual >= 0 && indiceAtual < musicasEncontradas.size()) {
            Musica musicaAtual = musicasEncontradas.get(indiceAtual);
            int idMusica = musicaAtual.getIdMusica();
            int idUsuario = view.getIdUsu();

            Boolean status = muscDAO.buscarStatusCurtida(idUsuario, idMusica);

            if (status == null || status) {
                muscDAO.atualizarCurtidaDescurtida(idMusica, musicaAtual.getCurtidas() - (status == null ? 0 : 1), musicaAtual.getDescurtidas() + 1);
                muscDAO.registrarHistoricoDescurtida(idUsuario, idMusica);
                musicaAtual.setDescurtidas(musicaAtual.getDescurtidas() + 1);
                if (status != null && status) {
                    musicaAtual.setCurtidas(musicaAtual.getCurtidas() - 1);
                }
                exibirMusicaAtual();
            }
        }
        conn.close();
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
    
    public void mostraHistorico(){
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            MusicaDAO muscDAO = new MusicaDAO(conn);
            ResultSet histPesq = muscDAO.exibirHistoricoPesq(view.getIdUsu());
            ResultSet histCurt = muscDAO.exibirHistoricoCurt(view.getIdUsu());      
            
            // Historico Pesq
            ArrayList<String> pesquisas = new ArrayList();
            
            while(histPesq.next()){
                String mostraPesq = "";
                mostraPesq += histPesq.getString("pesquisa");
                mostraPesq += "\n--------------------\n";
                pesquisas.add(mostraPesq);
            }
            String finalPesq = "";

            int index = pesquisas.size() - 1;
            int vezes = 10;
            while (vezes > 0 && index >= 0) {
                finalPesq += pesquisas.get(index);
                index--;
                vezes--;
            }
            
            view.getTxt_mostra_historico_pesq().setText(finalPesq);
            
            // Historico Curti
            ArrayList<String> interacoe = new ArrayList();
            while (histCurt.next()) {
                String mostraCurt = "";
                String nome = histCurt.getString("nome_musica");
                boolean curtiu = histCurt.getBoolean("curtida");

                mostraCurt += nome + " ";
                mostraCurt += (curtiu ? " CURTIU" : " DESCURTIU");
                mostraCurt += "\n--------------------\n";
                interacoe.add(mostraCurt);
            }
            
            String finalCurt = "";
            int vezes2 = 10;
            int index2 = interacoe.size() - 1;

            while (vezes2 > 0 && index2 >= 0) {
                finalCurt += interacoe.get(index2);
                index2--;
                vezes2--;
            }

            view.getTxt_mostra_historico_curt().setText(finalCurt);
            
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view,
                    e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
}