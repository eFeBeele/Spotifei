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

public class ControllerPesquisa {

    private MenuUsu view;
    private ArrayList<Musica> musicasEncontradas = new ArrayList<>();
    private ArrayList<Playlist> playlistsEncontradas = new ArrayList<>();
    private int indiceAtualmus = 0;
    private int indiceAtualplay = 0;

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
    try {
        MusicaDAO muscDAO = new MusicaDAO(conn);

        // Garante que temos uma música atual e que o índice é válido
        if (musicasEncontradas != null && !musicasEncontradas.isEmpty() && indiceAtualmus >= 0 && indiceAtualmus < musicasEncontradas.size()) {
            Musica MusicaAtual = musicasEncontradas.get(indiceAtualmus);

            // Obter o ID do usuário logado (view.getIdUsuario() é um exemplo)
            int idUsuarioLogado = view.getIdUsu(); // <--- Assumindo que você tem um método para pegar o ID do usuário logado

            // Verificar o status atual da curtida/descurtida para este usuário e esta música
            Boolean statusCurtida = muscDAO.verificarStatusCurtida(idUsuarioLogado, MusicaAtual.getIdMusica());

            if (statusCurtida == null || statusCurtida == false) { // Se não curtiu ou descurtiu
                try {
                    // 1. Atualiza o histórico para "curtida"
                    // registrarHistorico(idUsuario, idMusica, true) já faz o delete/insert
                    muscDAO.registrarHistoricoCurtida(idUsuarioLogado, MusicaAtual.getIdMusica());

                    // 2. Atualiza as contagens na tabela 'musica'
                    // Verifica se estava descurtido antes, para subtrair das descurtidas
                    int novasDescurtidas = MusicaAtual.getDescurtidas();
                    if (statusCurtida != null && statusCurtida == false) { // Se estava descurtido antes
                        novasDescurtidas = Math.max(0, MusicaAtual.getDescurtidas() - 1); // Garante que não fica negativo
                    }

                    muscDAO.atualizarCurtidaDescurtida(
                        MusicaAtual.getIdMusica(),
                        MusicaAtual.getCurtidas() + 1,
                        novasDescurtidas
                    );

                    // 3. Atualiza o objeto MusicaAtual na memória e a exibição
                    MusicaAtual.setCurtidas(MusicaAtual.getCurtidas() + 1);
                    MusicaAtual.setDescurtidas(novasDescurtidas); // Atualiza também o contador de descurtidas
                    exibirMusicaAtual();

                    JOptionPane.showMessageDialog(null, "Música curtida com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                } catch (SQLException e) {
                    System.err.println("Erro ao curtir música: " + e.getMessage());
                    JOptionPane.showMessageDialog(null, "Erro ao curtir a música: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    throw e; // Relança a exceção para o catch mais externo, se houver
                }
            } else {
                // Usuário já curtiu esta música, não faz nada e avisa
                JOptionPane.showMessageDialog(null, "Você já curtiu esta música!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma música para curtir.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    } catch (SQLException e) {
        System.err.println("Erro de conexão/SQL ao curtir música: " + e.getMessage());
        JOptionPane.showMessageDialog(null, "Erro de conexão ao curtir a música: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


public void descurtirMusica() throws SQLException {
    Conexao conexao = new Conexao();
    Connection conn = conexao.getConnection();
    try {
        MusicaDAO muscDAO = new MusicaDAO(conn);

        // Garante que temos uma música atual e que o índice é válido
        if (musicasEncontradas != null && !musicasEncontradas.isEmpty() && indiceAtualmus >= 0 && indiceAtualmus < musicasEncontradas.size()) {
            Musica MusicaAtual = musicasEncontradas.get(indiceAtualmus);

            // Obter o ID do usuário logado
            int idUsuarioLogado = view.getIdUsu(); // <--- Assumindo que você tem um método para pegar o ID do usuário logado

            // Verificar o status atual da curtida/descurtida para este usuário e esta música
            Boolean statusCurtida = muscDAO.verificarStatusCurtida(idUsuarioLogado, MusicaAtual.getIdMusica());

            if (statusCurtida == null || statusCurtida == true) { // Se não descurtiu ou curtiu
                try {
                    // 1. Atualiza o histórico para "descurtida"
                    // registrarHistorico(idUsuario, idMusica, false) já faz o delete/insert
                    muscDAO.registrarHistoricoDescurtida(idUsuarioLogado, MusicaAtual.getIdMusica());

                    // 2. Atualiza as contagens na tabela 'musica'
                    // Verifica se estava curtido antes, para subtrair das curtidas
                    int novasCurtidas = MusicaAtual.getCurtidas();
                    if (statusCurtida != null && statusCurtida == true) { // Se estava curtido antes
                        novasCurtidas = Math.max(0, MusicaAtual.getCurtidas() - 1); // Garante que não fica negativo
                    }

                    muscDAO.atualizarCurtidaDescurtida(
                        MusicaAtual.getIdMusica(),
                        novasCurtidas,
                        MusicaAtual.getDescurtidas() + 1
                    );

                    // 3. Atualiza o objeto MusicaAtual na memória e a exibição
                    MusicaAtual.setCurtidas(novasCurtidas); // Atualiza também o contador de curtidas
                    MusicaAtual.setDescurtidas(MusicaAtual.getDescurtidas() + 1);
                    exibirMusicaAtual();

                    JOptionPane.showMessageDialog(null, "Música descurtida com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                } catch (SQLException e) {
                    System.err.println("Erro ao descurtir música: " + e.getMessage());
                    JOptionPane.showMessageDialog(null, "Erro ao descurtir a música: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    throw e; // Relança a exceção para o catch mais externo, se houver
                }
            } else {
                // Usuário já descurtiu esta música, não faz nada e avisa
                JOptionPane.showMessageDialog(null, "Você já descurtiu esta música!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma música para descurtir.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    } catch (SQLException e) {
        System.err.println("Erro de conexão/SQL ao descurtir música: " + e.getMessage());
        JOptionPane.showMessageDialog(null, "Erro de conexão ao descurtir a música: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                // Atualiza o objeto Playlist na lista local para refletir a mudança no DB
                playlistAtual.setNome(novoNome);
                // Reexibe a playlist atual para mostrar o nome atualizado no PlayInd
                exibirPlaylistAtual();
                // Atualiza a lista geral no PlayGeral para que o nome também mude lá
                atualizarPlayGeral();
                // Opcional: Limpar o campo de texto após a edição
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
            // Mensagem de erro para IDs não positivos
            JOptionPane.showMessageDialog(view, // ou 'view', se for um JFrame/JDialog
                                          "Os IDs da Playlist e da Música devem ser números positivos.",
                                          "IDs Inválidos",
                                          JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Gerenciar a conexão e chamar o DAO
        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.getConnection();
            PlayDAO playlistDAO = new PlayDAO(conn);

            playlistDAO.adicionarMusicaNaPlaylist(idPlaylist, idMusica);

            // Mensagem de sucesso
            JOptionPane.showMessageDialog(view, // ou 'view', se for um JFrame/JDialog
                                          "Música adicionada à playlist com sucesso!",
                                          "Sucesso",
                                          JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar música na playlist (Controller): " + e.getMessage()); // Ainda bom para logar no console para depuração
            // Mensagem de erro de banco de dados
            // Você pode tornar a mensagem mais amigável, talvez removendo o 'e.getMessage()' se for muito técnico
            JOptionPane.showMessageDialog(view, // ou 'view', se for um JFrame/JDialog
                                          "Erro ao adicionar música à playlist: " + e.getMessage(),
                                          "Erro de Banco de Dados",
                                          JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão no controller: " + e.getMessage()); // Ainda bom para logar
                // Opcional: Mensagem de erro ao fechar conexão (geralmente não visível ao usuário)
                // JOptionPane.showMessageDialog(view.getComponent(),
                //                               "Erro interno ao finalizar a operação.",
                //                               "Erro Crítico",
                //                               JOptionPane.ERROR_MESSAGE);
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
}