package controller;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import view.*;
import DAO.*;
import model.*;

public class ControllerPesquisa {

    private MenuUsu view;

    public ControllerPesquisa(MenuUsu view) {
        this.view = view;
    }

    public void mostrarTodasMusicas(String x){
        Conexao conexao = new Conexao();
        
        try{
            Connection conn = conexao.getConnection();
            MusicaDAO muscDAO = new MusicaDAO(conn);
            
            ResultSet resInfoMusicas = muscDAO.exibirTodasMusicas(x);
            ArrayList<Musica> musicas = new ArrayList <>();
            
            while(resInfoMusicas.next()){
                int idMusica = resInfoMusicas.getInt("id_musica");
                String nomeMusica = resInfoMusicas.getString("nome_musica");
                int curtidas = resInfoMusicas.getInt("curtidas");
                int descurtidas = resInfoMusicas.getInt("descurtidas");
                String duracao = resInfoMusicas.getString("duracao");
                String nomeArtista = resInfoMusicas.getString("nome_artista");
                String genero = resInfoMusicas.getString("genero");
                
                musicas.add(new Musica(idMusica, nomeMusica, curtidas, descurtidas, duracao, new Artista(nomeArtista), genero));
            }
            
            String resultado3 = "";
            for(Musica m : musicas){
                resultado3 += m.infoMusicasCompleta();
            }
            
            view.getResultadoMusica().setText(resultado3);
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(view, 
                                              e, 
                                              "Aviso",
                                              JOptionPane.ERROR_MESSAGE);
        }
    }
}