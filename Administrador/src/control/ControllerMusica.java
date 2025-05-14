/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import DAO.Conexao;
import DAO.MusicaDAO;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Artista;
import model.Musica;
import view.*;
import Exception.*;

/**
 *
 * @author Arthur
 */
public class ControllerMusica {
    private menuAdm view;
    
    public ControllerMusica(menuAdm view){
        this.view = view;
    }
    
    
    public void detetarMusica(){
        Conexao conexao = new Conexao();
        
        try{
            Connection conn = conexao.getConnection();
            MusicaDAO muscDAO = new MusicaDAO(conn);
            muscDAO.excluirMusica(Integer.parseInt(view.getTxt_id_musica().getText()));
        }catch(SQLException e){
            JOptionPane.showMessageDialog(view, 
                                              e, 
                                              "Aviso",
                                              JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void adicionarMusica(){
        Conexao conexao = new Conexao();
        
        try{
            Connection conn = conexao.getConnection();
            MusicaDAO muscDAO = new MusicaDAO(conn);
            
            String nomeMusica = view.getTxt_nome_musica().getText();
            int curtidas = 0;
            int descurtidas = 0;
            String minutos = view.getTxt_duracao_minutos_musica().getText();
            String segundos = view.getTxt_duracao_segundos_musica().getText();
            String duracao = "0 hours " + minutos + " minutes " + segundos + " seconds";
            Artista artista = new Artista(view.getTxt_artista_musica().getText());
            String genero = view.getTxt_genero_musica().getText();
            
            Musica musc = new Musica(nomeMusica, curtidas, descurtidas, duracao, artista, genero);
            muscDAO.adicionarMusica(musc);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(view, 
                                              e, 
                                              "Aviso",
                                              JOptionPane.ERROR_MESSAGE);
        }catch(InfoNula e){
            JOptionPane.showMessageDialog(view, 
                                              e, 
                                              "Aviso",
                                              JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
