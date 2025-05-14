/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import DAO.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.*;
import view.*;

/**
 *
 * @author ferna
 */
public class ControllerLogin {
    private LoginUsu view;

    public ControllerLogin(LoginUsu view) {
        this.view = view;
    }
    public void loginUsuario(){
        Usuario usuario = new Usuario(null, view.getEmailUsu().getText(),view.getSenhaUsu().getText());
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
            UsuDAO dao = new UsuDAO(conn);
            ResultSet res = dao.consultar(usuario);
            if(res.next()){
                JOptionPane.showMessageDialog(view, "Login efetuado!", "Aviso",JOptionPane.INFORMATION_MESSAGE);
            } else{
                JOptionPane.showMessageDialog(view, "Login NÃO efetuado!","Aviso",JOptionPane.ERROR_MESSAGE);
            }
        } catch(SQLException e){    
            JOptionPane.showMessageDialog(view, "Erro de conexão!", "Aviso",JOptionPane.ERROR_MESSAGE);
        }
    }
}

