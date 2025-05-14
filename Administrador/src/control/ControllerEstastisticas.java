/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import model.*;
import view.*;
import DAO.*;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Arthur
 */
import javax.swing.JOptionPane;
public class ControllerEstastisticas {
    private menuAdm view;

    public ControllerEstastisticas(menuAdm mAdm) {
        this.view = mAdm;
    }
   
    
    public void consultar(){
        Conexao conexao = new Conexao();
        
        try{
            Connection conn = conexao.getConnection();
            UsuarioDAO usrDAO = new UsuarioDAO(conn);
            MusicaDAO muscDAO = new MusicaDAO(conn);
            
            ResultSet resTotalUsr = usrDAO.totalUsuarios();
            ResultSet resTotalMusc = muscDAO.totalMusicas();
            ResultSet resTop5Curtidas = muscDAO.top5MusicasCurtidas();
            ResultSet resTop5Descurtidas = muscDAO.top5MusicasDescurtidas();
            
            // Total de Users
            if (resTotalUsr.next()) {
                view.getLbl_usuarios().setText(resTotalUsr.getString("total_usuarios"));
            }
            
            // Total de musicas
            if (resTotalMusc.next()) {
                view.getLbl_musicas().setText(resTotalMusc.getString("total_musicas"));
            }
            
            // Top 5 musicas curtidas
            ArrayList<Musica> musicasCurtidas = new ArrayList();
            while(resTop5Curtidas.next()){
                String nome = resTop5Curtidas.getString("nome_musica");
                int curtidas = resTop5Curtidas.getInt("curtidas");
                int descurtidas = resTop5Curtidas.getInt("descurtidas");
                String duracao = resTop5Curtidas.getString("duracao");
                
                Musica musc = new Musica(nome, curtidas, descurtidas, duracao);
                musicasCurtidas.add(musc);
            }
            String resultado = "";
            for(int i=0; i<musicasCurtidas.size(); i++){
                resultado += musicasCurtidas.get(i).infoMusicas();
            }
            view.getTxt_musicas_curtidas().setText(resultado);
            
            
            
            // Top 5 musicas descurtidas
            ArrayList<Musica> musicasDescurtidas = new ArrayList();
            while(resTop5Descurtidas.next()){
                String nome = resTop5Descurtidas.getString("nome_musica");
                int curtidas = resTop5Descurtidas.getInt("curtidas");
                int descurtidas = resTop5Descurtidas.getInt("descurtidas");
                String duracao = resTop5Descurtidas.getString("duracao");
                
                Musica musc = new Musica(nome, curtidas, descurtidas, duracao);
                musicasDescurtidas.add(musc);
            }
            String resultado2 = "";
            for(int i=0; i<musicasDescurtidas.size(); i++){
                resultado2 += musicasDescurtidas.get(i).infoMusicas();
            }
            view.getTxt_musicas_descurtidas().setText(resultado2);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(view, 
                                              e, 
                                              "Aviso",
                                              JOptionPane.ERROR_MESSAGE);
        }      
    }
    
    public void mostrarTodasMusicas(){
        Conexao conexao = new Conexao();
        
        try{
            Connection conn = conexao.getConnection();
            MusicaDAO muscDAO = new MusicaDAO(conn);
            
            ResultSet resInfoMusicas = muscDAO.exibirTodasMusicas();
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
            
            view.getTxt_musicas().setText(resultado3);
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(view, 
                                              e, 
                                              "Aviso",
                                              JOptionPane.ERROR_MESSAGE);
        }
    }
}
