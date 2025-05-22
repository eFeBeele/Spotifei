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
import java.sql.PreparedStatement;
/**
 *
 * @author ferna
 */
public class ControllerPesquisa {

    private int indiceAtualmus = 0;
    private int indiceAtualplay = 0;
    private MenuUsu view;
    private ArrayList<Musica> musicasEncontradas = new ArrayList<>();
    private ArrayList<Playlist> playlistsEncontradas = new ArrayList<>();    public ArrayList<Musica> criarListaDeMusicas(ResultSet resInfoMusicas) throws SQLException {
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
            indiceAtualmus = 0; 
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
        if (musicasEncontradas != null && !musicasEncontradas.isEmpty() && indiceAtualmus >= 0 && indiceAtualmus < musicasEncontradas.size()) {
            Musica musica = musicasEncontradas.get(indiceAtualmus);
            MusicaAtu.setText(musica.infoMusicasCompleta());
        } else if (MusicaAtu != null) {
            MusicaAtu.setText("");
        }
    }


    public ControllerPesquisa(MenuUsu view) {
        this.view = view;
    }



    public void proximaMusica() {
        if (musicasEncontradas != null && !musicasEncontradas.isEmpty() && indiceAtualmus < musicasEncontradas.size() - 1) {
            indiceAtualmus++;
            exibirMusicaAtual();
        }
    }

    public void musicaAnterior() {
        if (musicasEncontradas != null && !musicasEncontradas.isEmpty() && indiceAtualmus > 0) {
            indiceAtualmus--;
            exibirMusicaAtual();
        }
    }

    public void curtirMusica() throws SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.getConnection();
        MusicaDAO muscDAO = new MusicaDAO(conn);

        if (musicasEncontradas != null && !musicasEncontradas.isEmpty() && indiceAtualmus >= 0 && indiceAtualmus < musicasEncontradas.size()) {
            Musica musicaAtual = musicasEncontradas.get(indiceAtualmus);
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

        if (musicasEncontradas != null && !musicasEncontradas.isEmpty() && indiceAtualmus >= 0 && indiceAtualmus < musicasEncontradas.size()) {
            Musica musicaAtual = musicasEncontradas.get(indiceAtualmus);
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
 public void salvarPlaylist() {
        String nomePlaylist = view.getNomePlay().getText();
        String visibilidade = "privada";
        Playlist playlist = new Playlist(nomePlaylist, visibilidade,view.getIdUsu());


        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.getConnection();
            PlayDAO dao = new PlayDAO(conn);

            dao.insert(playlist);

            JOptionPane.showMessageDialog(null, "Playlist Salva com Sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar Playlist: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar a conexão: " + e.getMessage());
                }
            }
        }
    }
       public void excluirPlaylistAtual() {
        if (playlistsEncontradas == null || playlistsEncontradas.isEmpty()) {
            System.out.println("Nenhuma playlist para excluir."); 
            return;
        }
        
        Playlist playlistAtual = playlistsEncontradas.get(indiceAtualplay);
        int idPlaylistParaExcluir = playlistAtual.getIdPlaylist();

        Conexao conexao = new Conexao();
        Connection conn = null;
        try {
            conn = conexao.getConnection();
            PlayDAO dao = new PlayDAO(conn);

            boolean sucesso = dao.delete(idPlaylistParaExcluir);

            if (sucesso) {
                System.out.println("Playlist '" + playlistAtual.getNome() + "' Excluída com Sucesso!"); 
                playlistsEncontradas.remove(indiceAtualplay); 

                if (playlistsEncontradas.isEmpty()) {
                    indiceAtualplay = 0; 
                } else if (indiceAtualplay >= playlistsEncontradas.size()) {
                    indiceAtualplay = playlistsEncontradas.size() - 1; 
                }
                exibirPlaylistAtual();
                atualizarPlayGeral(); 
            } else {
                System.err.println("Erro: Playlist com ID " + idPlaylistParaExcluir + " não encontrada para exclusão.");
            }

        } catch (SQLException ex) {

            System.err.println("Erro ao Excluir Playlist: " + ex.getMessage()); 
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try { conn.close(); } catch (SQLException e) { System.err.println("Erro ao fechar a conexão: " + e.getMessage()); }
            }
        }
    }

    public void mostrarTodasPlaylistsDoUsuario() {
        Conexao conexao = new Conexao();
        Connection conn = null;
        ResultSet resInfoPlaylists = null;
        PreparedStatement ps = null; 

        try {
            conn = conexao.getConnection();
            PlayDAO dao = new PlayDAO(conn);

            int idUsuarioLogado = view.getIdUsu();

            String sql = "SELECT id_playlist, nome_playlist, visibilidade, id_usuariodono FROM playlist WHERE id_usuariodono = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idUsuarioLogado);
            resInfoPlaylists = ps.executeQuery();

            criarListaDePlaylists(resInfoPlaylists); 

            indiceAtualplay = 0;
            exibirPlaylistAtual(); 

            atualizarPlayGeral();
        } catch (SQLException e) {
            System.err.println("Erro ao carregar playlists: " + e.getMessage()); 
            e.printStackTrace();
        } finally {
            if (resInfoPlaylists != null) {
                try { resInfoPlaylists.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
            if (ps != null) { 
                try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
            if (conn != null) {
                try { conn.close(); } catch (SQLException e) { System.err.println("Erro ao fechar a conexão: " + e.getMessage()); }
            }
        }
    }


    private void criarListaDePlaylists(ResultSet resultSet) throws SQLException {
        playlistsEncontradas.clear(); 
        while (resultSet.next()) {
            int idPlaylist = resultSet.getInt("id_playlist");
            String nomePlaylist = resultSet.getString("nome_playlist");
            String visibilidade = resultSet.getString("visibilidade");
            int idUsuarioDono = resultSet.getInt("id_usuariodono");

            Playlist playlist = new Playlist(idPlaylist, nomePlaylist, visibilidade, idUsuarioDono);
            playlistsEncontradas.add(playlist);
        }
    }

    public void exibirPlaylistAtual() {
        JTextArea playInd = view.getPlayInd();

        if (playlistsEncontradas != null && !playlistsEncontradas.isEmpty() &&
            indiceAtualplay >= 0 && indiceAtualplay < playlistsEncontradas.size()) {
            Playlist playlistAtual = playlistsEncontradas.get(indiceAtualplay);
            playInd.setText(playlistAtual.infoPlaylistCompleta()); // Exibe no PlayInd
        } else {
            playInd.setText("Nenhuma playlist para exibir individualmente.");
        }
    }

    private void atualizarPlayGeral() {
        JTextArea playGeral = view.getPlayGeral(); 
        StringBuilder todasPlaylistsTexto = new StringBuilder();

        if (!playlistsEncontradas.isEmpty()) {
            todasPlaylistsTexto.append("Todas as Playlists do Usuário:\n\n");
            for (Playlist p : playlistsEncontradas) {
                todasPlaylistsTexto.append(p.infoPlaylistCompleta());
            }
        } else {
            todasPlaylistsTexto.append("Nenhuma playlist encontrada para este usuário.");
        }
        playGeral.setText(todasPlaylistsTexto.toString());
    }

    public void proximaPlaylist() {
        if (playlistsEncontradas != null && !playlistsEncontradas.isEmpty() &&
            indiceAtualplay < playlistsEncontradas.size() - 1) {
            indiceAtualplay++;
            exibirPlaylistAtual();
        } else if (playlistsEncontradas != null && !playlistsEncontradas.isEmpty()) { 
            System.out.println("Esta é a última playlist.");
        }
    }

    public void playlistAnterior() {
        if (playlistsEncontradas != null && !playlistsEncontradas.isEmpty() && indiceAtualplay > 0) {
            indiceAtualplay--;
            exibirPlaylistAtual();
        } else if (playlistsEncontradas != null && !playlistsEncontradas.isEmpty()) {
            System.out.println("Esta é a primeira playlist."); 
        }
    }
public void editarNomePlaylistAtual() { // Não recebe mais parâmetro direto, pega da View
        if (playlistsEncontradas == null || playlistsEncontradas.isEmpty()) {
            System.out.println("Nenhuma playlist selecionada para edição.");
            return;
        }

        // Pega o novo nome do JTextField da View
        String novoNome = view.getNovoPlayNome().getText();

        if (novoNome == null || novoNome.trim().isEmpty()) {
            System.out.println("Por favor, digite um novo nome para a playlist.");
            return;
        }

        Playlist playlistAtual = playlistsEncontradas.get(indiceAtualplay);
        int idPlaylistParaEditar = playlistAtual.getIdPlaylist();

        Conexao conexao = new Conexao();
        Connection conn = null;
        try {
            conn = conexao.getConnection();
            PlayDAO dao = new PlayDAO(conn);

            boolean sucesso = dao.update(idPlaylistParaEditar, novoNome);

            if (sucesso) {
                System.out.println("Nome da playlist atualizado para: '" + novoNome + "'");

                playlistAtual.setNome(novoNome);

                exibirPlaylistAtual();

                atualizarPlayGeral();
 
                view.getNovoPlayNome().setText("");
            } else {
                System.err.println("Erro: Playlist com ID " + idPlaylistParaEditar + " não encontrada para edição.");
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao Editar Playlist: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try { conn.close(); } catch (SQLException e) { System.err.println("Erro ao fechar a conexão: " + e.getMessage()); }
            }
        }
    }
public void mostrarTodasMusicasA(String x) {
        Conexao conexao = new Conexao();

        try {
            Connection conn = conexao.getConnection();
            MusicaDAO muscDAO = new MusicaDAO(conn);

            muscDAO.registrarHistoricoPesquisa(view.getIdUsu(),  x);
            ResultSet resInfoMusicas = muscDAO.exibirTodasMusicas(x);
            musicasEncontradas = criarListaDeMusicas(resInfoMusicas); 
            indiceAtualmus = 0; 
            exibirMusicaAtualA(); 

            String resultado3 = "";
            for (Musica m : musicasEncontradas) {
                resultado3 += m.infoMusicasCompleta();
            }
            view.getResultadoMusicaA().setText(resultado3); 
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view,
                    e,
                    "Aviso",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    public void exibirMusicaAtualA() {
        JTextArea MusicaAtuA = view.getMusicaAtuA();
        if (musicasEncontradas != null && !musicasEncontradas.isEmpty() && indiceAtualmus >= 0 && indiceAtualmus < musicasEncontradas.size()) {
            Musica musica = musicasEncontradas.get(indiceAtualmus);
            MusicaAtuA.setText(musica.infoMusicasCompleta());
        } else if (MusicaAtuA != null) {
            MusicaAtuA.setText("");
        }
    }
    public void proximaMusicaA() {
        if (musicasEncontradas != null && !musicasEncontradas.isEmpty() && indiceAtualmus < musicasEncontradas.size() - 1) {
            indiceAtualmus++;
            exibirMusicaAtualA();
        }
    }

    public void musicaAnteriorA() {
        if (musicasEncontradas != null && !musicasEncontradas.isEmpty() && indiceAtualmus > 0) {
            indiceAtualmus--;
            exibirMusicaAtualA();
        }
    }
public void mostrarTodasPlaylistsDoUsuarioA() {
        Conexao conexao = new Conexao();
        Connection conn = null;
        ResultSet resInfoPlaylists = null;
        PreparedStatement ps = null; 

        try {
            conn = conexao.getConnection();
            PlayDAO dao = new PlayDAO(conn);

            int idUsuarioLogado = view.getIdUsu();

            String sql = "SELECT id_playlist, nome_playlist, visibilidade, id_usuariodono FROM playlist WHERE id_usuariodono = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idUsuarioLogado);
            resInfoPlaylists = ps.executeQuery();

            criarListaDePlaylists(resInfoPlaylists); 

            indiceAtualplay = 0;
            exibirPlaylistAtualA(); 

            atualizarPlayGeralA();
        } catch (SQLException e) {
            System.err.println("Erro ao carregar playlists: " + e.getMessage()); 
            e.printStackTrace();
        } finally {
            if (resInfoPlaylists != null) {
                try { resInfoPlaylists.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
            if (ps != null) { 
                try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
            if (conn != null) {
                try { conn.close(); } catch (SQLException e) { System.err.println("Erro ao fechar a conexão: " + e.getMessage()); }
            }
        }
    }

    public void exibirPlaylistAtualA() {
        JTextArea playInd = view.getPlayIndA();

        if (playlistsEncontradas != null && !playlistsEncontradas.isEmpty() &&
            indiceAtualplay >= 0 && indiceAtualplay < playlistsEncontradas.size()) {
            Playlist playlistAtual = playlistsEncontradas.get(indiceAtualplay);
            playInd.setText(playlistAtual.infoPlaylistCompleta()); // Exibe no PlayInd
        } else {
            playInd.setText("Nenhuma playlist para exibir individualmente.");
        }
    }

    private void atualizarPlayGeralA() {
        JTextArea playGeral = view.getPlayGeralA(); 
        StringBuilder todasPlaylistsTexto = new StringBuilder();

        if (!playlistsEncontradas.isEmpty()) {
            todasPlaylistsTexto.append("Todas as Playlists do Usuário:\n\n");
            for (Playlist p : playlistsEncontradas) {
                todasPlaylistsTexto.append(p.infoPlaylistCompleta());
            }
        } else {
            todasPlaylistsTexto.append("Nenhuma playlist encontrada para este usuário.");
        }
        playGeral.setText(todasPlaylistsTexto.toString());
    }

    public void proximaPlaylistA() {
        if (playlistsEncontradas != null && !playlistsEncontradas.isEmpty() &&
            indiceAtualplay < playlistsEncontradas.size() - 1) {
            indiceAtualplay++;
            exibirPlaylistAtualA();
        } else if (playlistsEncontradas != null && !playlistsEncontradas.isEmpty()) { 
            System.out.println("Esta é a última playlist.");
        }
    }

    public void playlistAnteriorA() {
        if (playlistsEncontradas != null && !playlistsEncontradas.isEmpty() && indiceAtualplay > 0) {
            indiceAtualplay--;
            exibirPlaylistAtualA();
        } else if (playlistsEncontradas != null && !playlistsEncontradas.isEmpty()) {
            System.out.println("Esta é a primeira playlist."); 
        }
    }
public void adicionarMusicaNaPlaylist() {
    Playlist playlistAtual = playlistsEncontradas.get(indiceAtualplay);
    Musica musica = musicasEncontradas.get(indiceAtualmus);   
    int idPlaylist = playlistAtual.getIdPlaylist();
    int idMusica = musica.getIdMusica();


        if (idPlaylist <= 0 || idMusica <= 0) {

            JOptionPane.showMessageDialog(view, 
                                          "Os IDs da Playlist e da Música devem ser números positivos.",
                                          "IDs Inválidos",
                                          JOptionPane.WARNING_MESSAGE);
            return;
        }

 
        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.getConnection();
            PlayDAO playlistDAO = new PlayDAO(conn);

            playlistDAO.adicionarMusicaNaPlaylist(idPlaylist, idMusica);


            JOptionPane.showMessageDialog(view, // ou 'view', se for um JFrame/JDialog
                                          "Música adicionada à playlist com sucesso!",
                                          "Sucesso",
                                          JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar música na playlist (Controller): " + e.getMessage()); 
            JOptionPane.showMessageDialog(view, 
                                          "Erro ao adicionar música à playlist: " + e.getMessage(),
                                          "Erro de Banco de Dados",
                                          JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão no controller: " + e.getMessage());

            }
        }
    }
public void mostrarTodasPlaylistsDoUsuarioR() {
        Conexao conexao = new Conexao();
        Connection conn = null;
        ResultSet resInfoPlaylists = null;
        PreparedStatement ps = null; 

        try {
            conn = conexao.getConnection();
            PlayDAO dao = new PlayDAO(conn);

            int idUsuarioLogado = view.getIdUsu();

            String sql = "SELECT id_playlist, nome_playlist, visibilidade, id_usuariodono FROM playlist WHERE id_usuariodono = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idUsuarioLogado);
            resInfoPlaylists = ps.executeQuery();

            criarListaDePlaylists(resInfoPlaylists); 

            indiceAtualplay = 0;
            exibirPlaylistAtualR(); 

            atualizarPlayGeralR();
        } catch (SQLException e) {
            System.err.println("Erro ao carregar playlists: " + e.getMessage()); 
            e.printStackTrace();
        } finally {
            if (resInfoPlaylists != null) {
                try { resInfoPlaylists.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
            if (ps != null) { 
                try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
            if (conn != null) {
                try { conn.close(); } catch (SQLException e) { System.err.println("Erro ao fechar a conexão: " + e.getMessage()); }
            }
        }
    }

    public void exibirPlaylistAtualR() {
        JTextArea playInd = view.getPlayIndR();

        if (playlistsEncontradas != null && !playlistsEncontradas.isEmpty() &&
            indiceAtualplay >= 0 && indiceAtualplay < playlistsEncontradas.size()) {
            Playlist playlistAtual = playlistsEncontradas.get(indiceAtualplay);
            playInd.setText(playlistAtual.infoPlaylistCompleta()); // Exibe no PlayInd
        } else {
            playInd.setText("Nenhuma playlist para exibir individualmente.");
        }
    }

    private void atualizarPlayGeralR() {
        JTextArea playGeral = view.getPlayGeralR(); 
        StringBuilder todasPlaylistsTexto = new StringBuilder();

        if (!playlistsEncontradas.isEmpty()) {
            todasPlaylistsTexto.append("Todas as Playlists do Usuário:\n\n");
            for (Playlist p : playlistsEncontradas) {
                todasPlaylistsTexto.append(p.infoPlaylistCompleta());
            }
        } else {
            todasPlaylistsTexto.append("Nenhuma playlist encontrada para este usuário.");
        }
        playGeral.setText(todasPlaylistsTexto.toString());
    }

    public void proximaPlaylistR() {
        if (playlistsEncontradas != null && !playlistsEncontradas.isEmpty() &&
            indiceAtualplay < playlistsEncontradas.size() - 1) {
            indiceAtualplay++;
            exibirPlaylistAtualR();
        } else if (playlistsEncontradas != null && !playlistsEncontradas.isEmpty()) { 
            System.out.println("Esta é a última playlist.");
        }
    }

    public void playlistAnteriorR() {
        if (playlistsEncontradas != null && !playlistsEncontradas.isEmpty() && indiceAtualplay > 0) {
            indiceAtualplay--;
            exibirPlaylistAtualR();
        } else if (playlistsEncontradas != null && !playlistsEncontradas.isEmpty()) {
            System.out.println("Esta é a primeira playlist."); 
        }
    }


    public void mostrarTodasMusicasR(String x) {
        Conexao conexao = new Conexao();

        try {
            Connection conn = conexao.getConnection();
            MusicaDAO muscDAO = new MusicaDAO(conn);
            Playlist playlistAtual = playlistsEncontradas.get(indiceAtualplay);
            int idPlaylist = playlistAtual.getIdPlaylist();

            muscDAO.registrarHistoricoPesquisa(view.getIdUsu(),  x);
            ResultSet resInfoMusicas = muscDAO.exibirTodasMusicasR(x,idPlaylist);
            musicasEncontradas = criarListaDeMusicas(resInfoMusicas); 
            indiceAtualmus = 0; 
            exibirMusicaAtual(); 

            String resultado3 = "";
            for (Musica m : musicasEncontradas) {
                resultado3 += m.infoMusicasCompleta();
            }
            view.getResultadoMusicaR().setText(resultado3); 
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view,
                    e,
                    "Aviso",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void exibirMusicaAtualR() {
        JTextArea MusicaAtu = view.getMusicaAtuR();
        if (musicasEncontradas != null && !musicasEncontradas.isEmpty() && indiceAtualmus >= 0 && indiceAtualmus < musicasEncontradas.size()) {
            Musica musica = musicasEncontradas.get(indiceAtualmus);
            MusicaAtu.setText(musica.infoMusicasCompleta());
        } else if (MusicaAtu != null) {
            MusicaAtu.setText("");
        }
    }
public void removerMusicaNaPlaylist() {
    Playlist playlistAtual = playlistsEncontradas.get(indiceAtualplay);
    Musica musica = musicasEncontradas.get(indiceAtualmus);   
    int idPlaylist = playlistAtual.getIdPlaylist();
    int idMusica = musica.getIdMusica();


        if (idPlaylist <= 0 || idMusica <= 0) {
            JOptionPane.showMessageDialog(view, 
                                          "Os IDs da Playlist e da Música devem ser números positivos.",
                                          "IDs Inválidos",
                                          JOptionPane.WARNING_MESSAGE);
            return;
        }


        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.getConnection();
            PlayDAO playlistDAO = new PlayDAO(conn);

            playlistDAO.removerMusicaDaPlaylist(idPlaylist, idMusica);


            JOptionPane.showMessageDialog(view, 
                                          "Música removida da playlist com sucesso!",
                                          "Sucesso",
                                          JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar música na playlist (Controller): " + e.getMessage());
  
            JOptionPane.showMessageDialog(view, 
                                          "Erro ao remover música da playlist: " + e.getMessage(),
                                          "Erro de Banco de Dados",
                                          JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão no controller: " + e.getMessage());

            }
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

    public void proximaMusicaR() {
        if (musicasEncontradas != null && !musicasEncontradas.isEmpty() && indiceAtualmus < musicasEncontradas.size() - 1) {
            indiceAtualmus++;
            exibirMusicaAtualR();
        }
    }

    public void musicaAnteriorR() {
        if (musicasEncontradas != null && !musicasEncontradas.isEmpty() && indiceAtualmus > 0) {
            indiceAtualmus--;
            exibirMusicaAtualR();
        }
    }
}