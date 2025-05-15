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
import javax.swing.JTextField;

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
    
    public void adicionarMusica() throws SQLException{
        Conexao conexao = new Conexao();
        Connection conn = conexao.getConnection();
        MusicaDAO muscDAO = new MusicaDAO(conn);
        
        try{
        String nomeMusica;
        JTextField txtNomeMusica = view.getTxt_nome_musica();
        if(txtNomeMusica.getText().isEmpty()){
            throw new InfoNula("ERRO! Nome da música NÃO pode ser nulo");
        }else{
            nomeMusica = txtNomeMusica.getText();
        }


        int curtidas = 0;
        int descurtidas = 0;

        JTextField txtMinutos = view.getTxt_duracao_minutos_musica();
        JTextField txtSegundos = view.getTxt_duracao_segundos_musica();
        String minutos;
        String segundos;
        if(txtMinutos.getText().isEmpty() && txtSegundos.getText().isEmpty()){
            throw new InfoNula("ERRO! Duração da música inválida");
        }else if(txtMinutos.getText().isEmpty()){
            minutos = "0";
            segundos = txtMinutos.getText();
        }else if(txtSegundos.getText().isEmpty()){
            segundos = "0";
            minutos = txtMinutos.getText();
        }else{
            minutos = txtMinutos.getText();
            segundos = txtSegundos.getText();
        }
        String duracao = "0 hours " + minutos + " minutes " + segundos + " seconds";

        JTextField txtArtista = view.getTxt_artista_musica();
        Artista artistaMusica;
        if(txtArtista.getText().isEmpty()){
            throw new InfoNula("ERRO! Artista da música inválido, NÃO pode ser nulo");
        }else{
            artistaMusica = new Artista(txtArtista.getText());
        }

        String genero;
        JTextField txtGenero = view.getTxt_genero_musica();
        if(txtGenero.getText().isEmpty()){
            throw new InfoNula("ERRO! Gênero NÃO pode ser nulo");
        }else{
            genero = txtNomeMusica.getText();
        }

        Musica musc = new Musica(nomeMusica, curtidas, descurtidas, duracao, artistaMusica, genero);
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
