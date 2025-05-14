package controller;

import DAO.UsuDao;
import DAO.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Usuario;
import view.CadastroUsu;

public class ControllerCadastro {
    private CadastroUsu view;

    public ControllerCadastro(CadastroUsu view){
        this.view = view;
    }

    public void salvarUsuario(){
        String nome = view.getTxt_nome_cadastro().getText();
        String email = view.getTxt_usuario_cadastro().getText();
        String senha = view.getTxt_senha_cadastro().getText();
        Usuario usuario = new Usuario(nome, email, senha);

        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            UsuDao dao = new UsuDao(conn);
            dao.inserir(usuario);
            JOptionPane.showMessageDialog(view, "Usuário Cadastrado!","Aviso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Usuário não cadastrado!","Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
