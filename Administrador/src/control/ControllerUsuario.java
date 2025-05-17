/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import javax.swing.JTextField;
import view.*;
import model.*;
import DAO.*;
import java.sql.*;
import javax.swing.JOptionPane;


/**
 *
 * @author Arthur
 */
public class ControllerUsuario {
    private menuAdm view;
    
    public ControllerUsuario(menuAdm view) {
        this.view = view;
    }
    
    public void consultar(){
        String nome = view.getTxt_nome_usuario().getText();
        Conexao conexao = new Conexao();
        
        try{
            Connection conn = conexao.getConnection();
            UsuarioDAO usrDAO = new UsuarioDAO(conn);
            
            ResultSet res = usrDAO.consultar(new Usuario(nome));
            if(res.next()){
                Usuario usr2 = new Usuario(res.getString("nome_usuario"), res.getString("email"), res.getString("senha"));
                view.getTxt_info_usuario().setText(usr2.info());
            }else{
                JOptionPane.showMessageDialog(view, 
                                              "Usuário não encontrado", 
                                              "Aviso",
                                              JOptionPane.ERROR_MESSAGE);
            }
            
            
        } catch(SQLException e){    
            JOptionPane.showMessageDialog(view, 
                                              e, 
                                              "Aviso",
                                              JOptionPane.ERROR_MESSAGE);
        }          
    }
}
