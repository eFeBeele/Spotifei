package controller;

import DAO.UsuDao;
import DAO.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Usuario;
import view.AltExcFrame;

public class ControllerUsuario {
    private AltExcFrame view;
    private Usuario usuario;

    public ControllerUsuario(AltExcFrame view, Usuario usuario) {
        this.view = view;
        this.usuario = usuario;
    }

    public void atualizar(){
        String novaSenha = view.getTxt_senha_altexc().getText();
        String email = view.getLbl_usuario_altexc().getText();
        Usuario usuario = new Usuario("", email, novaSenha);
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
            UsuDao dao = new UsuDao(conn);
            dao.atualizar(usuario);
            JOptionPane.showMessageDialog(view, "Senha de Usuário atualizada com Sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(view, "Falha de conexão!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void remover(){
        String email = usuario.getEmail();
        int option = JOptionPane.showConfirmDialog(view, "Deseja realmente excluir o cadastro",
                                                    "Aviso", JOptionPane.YES_NO_OPTION);
        if(option != 1){
            Conexao conexao = new Conexao();
            try{
                Connection conn = conexao.getConnection();
                UsuDao dao = new UsuDao(conn);
                dao.remover(usuario);
                JOptionPane.showMessageDialog(view, "Usuario removido com Sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }catch(SQLException e){
                JOptionPane.showMessageDialog(view, "Falha de conexão!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}