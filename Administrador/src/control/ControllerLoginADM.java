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
public class ControllerLoginADM {
    private loginAdm view;
    
    public ControllerLoginADM(loginAdm view) {
        this.view = view;
    }
    
    public void login() {
        String email = view.getTxt_email_login_adm().getText();
        String senhaDigitada = view.getTxt_senha_login_adm().getText();
        Conexao conexao = new Conexao();

        try (Connection conn = conexao.getConnection()) {
            UsuarioDAO usrDAO = new UsuarioDAO(conn);
            ResultSet res = usrDAO.loginADM(new Admin(email));

            if (res.next()) {
                Autenticacao admCorreto = new Admin(
                    res.getString("nome_usuario"),
                    res.getString("email"),
                    res.getString("senha")
                );

                if (admCorreto.login(senhaDigitada)) {
                    menuAdm mADM = new menuAdm();
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
                                              "Administrador não encontrado", 
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