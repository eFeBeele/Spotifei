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
    private int id;

    public ControllerLogin(LoginUsu view) {
        this.view = view;
    }
  public void login() {
        String email = view.getEmailUsu().getText();
        String senhaDigitada = view.getSenhaUsu().getText();
        Conexao conexao = new Conexao();

        try (Connection conn = conexao.getConnection()) {
            UsuDAO usrDAO = new UsuDAO(conn);
            Usuario usuario = new Usuario(email,senhaDigitada);
            ResultSet res = usrDAO.loginUsu(usuario);

            if (res.next()) {
                Autenticacao usuCorreto = new Usuario(
                    res.getString("nome_usuario"),
                    res.getString("email"),
                    res.getString("senha")
                );

                if (usuCorreto.login(senhaDigitada)) {
                    ResultSet rest = usrDAO.consultar(usuario);
                    if(rest.next()){
                    id =rest.getInt("id_usuario");
                    }
                    MenuUsu mADM = new MenuUsu(id);
                    mADM.setVisible(true);
                    view.setVisible(false);
                    JOptionPane.showMessageDialog(view, 
                                                  "Login efetuado com sucesso", 
                                                  "Aviso",
                                                  JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(view, 
                                                  "Senha incorreta", 
                                                  "Erro",
                                                  JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(view, 
                                              "Usuario não encontrado", 
                                              "Erro",
                                              JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, 
                                          e.getMessage(), 
                                          "Erro",
                                          JOptionPane.ERROR_MESSAGE);
        }
    }
}

