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
public void loginUsuario() {
    Usuario usuario = new Usuario(null, view.getEmailUsu().getText(), view.getSenhaUsu().getText());
    Conexao conexao = new Conexao();
    Connection conn = null;
    try {
        conn = conexao.getConnection();
        UsuDAO dao = new UsuDAO(conn);
        ResultSet res = dao.consultar(usuario);

        if (res.next()) {
            int idUsuario = res.getInt("id_usuario"); // Supondo que sua coluna de ID se chame "id_do_usuario"

            try {
                // Cria e tenta exibir a tela MenuUsu
                MenuUsu telaMenu = new MenuUsu(idUsuario);
                telaMenu.setVisible(true);

                // Fecha a tela de login após abrir a MenuUsu (opcional)
                view.dispose();
            } catch (Exception ex) {
                // Se ocorrer algum erro ao abrir a MenuUsu
                JOptionPane.showMessageDialog(view, "Erro ao abrir a tela Menu!", "Erro", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace(); // Imprime o erro para depuração
                // A tela de login permanecerá aberta
            }

            JOptionPane.showMessageDialog(view, "Login efetuado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, "Login NÃO efetuado!", "Aviso", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(view, "Erro de conexão!", "Aviso", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
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
}

