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
   
    
    public void login(){
        Conexao conexao = new Conexao();
        
        try{
            Connection conn = conexao.getConnection();
            UsuarioDAO usrDAO = new UsuarioDAO(conn);
            MusicaDAO muscDAO = new MusicaDAO(conn);
            
            ResultSet resTotalUsr = usrDAO.totalUsuarios();
            ResultSet resTotalMusc = muscDAO.totalMusicas();
            ResultSet resTop5Curtidas = muscDAO.top5MusicasCurtidas();
            ResultSet resTop5Descurtidas = muscDAO.top5MusicasDescurtidas();
            
            view.getLbl_musicas().setText(resTotalMusc.toString());
            view.getLbl_usuarios().setText(resTotalUsr.toString());
            
            
            ArrayList<Musica> musicasCurtidas = new ArrayList();
            
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(view, 
                                              e, 
                                              "Aviso",
                                              JOptionPane.ERROR_MESSAGE);
        }      
    }
}
