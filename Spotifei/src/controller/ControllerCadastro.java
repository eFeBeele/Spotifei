/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.*;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import view.*;
import model.*;

/**
 *
 * @author ferna
 */
public class ControllerCadastro {
    private CadastroUsu view;
    
    public ControllerCadastro(CadastroUsu view){
        this.view = view;
    }
    
public void salvarUsuario() {
    String nome = view.getNomeUsu().getText();
    String email = view.getEmailUsu().getText();
    String senha = view.getSenhaUsu().getText();

    if (nome == null || nome.trim().isEmpty() ||
        email == null || email.trim().isEmpty() ||
        senha == null || senha.trim().isEmpty()) {

        JOptionPane.showMessageDialog(view, "Todos os campos são obrigatórios!", "Erro de Validação", JOptionPane.WARNING_MESSAGE);
        return;
    }

    Usuario usuario = new Usuario(nome, email, senha);
    Conexao conexao = new Conexao();

    try {
        Connection conn = conexao.getConnection();
        UsuDAO dao = new UsuDAO(conn);
        dao.inserir(usuario);
        JOptionPane.showMessageDialog(view, "Usuário Cadastrado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(view, "Usuário não cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace(); 
    }
    }
}
