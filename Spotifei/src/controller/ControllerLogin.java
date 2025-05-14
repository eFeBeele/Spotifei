package controller;

import DAO.UsuDao;
import DAO.Conexao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Usuario;
import view.AltExcFrame;
import view.LoginUsu;


public class ControllerLogin {
    private LoginUsu view;

    public ControllerLogin(LoginUsu view) {
        this.view = view;
    }

    public void loginUsuario(){
        Usuario usuario = new Usuario(null,
                                         view.getTxt_usuario_login().getText(),
                                         view.getTxt_senha_login().getText());
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
            UsuDao dao = new UsuDao(conn);
            ResultSet res = dao.consultar(usuario);
            if(res.next()){
                JOptionPane.showMessageDialog(view,
                                                    "Login efetuado!",
                                                    "Aviso",
                                                    JOptionPane.INFORMATION_MESSAGE);
                Usuario usuario2 = new Usuario(res.getString("nome"),
                                                 res.getString("email"),
                                                 res.getString("senha"));
                AltExcFrame aec = new AltExcFrame(usuario2);
                aec.setVisible(true);
            } else{
                JOptionPane.showMessageDialog(view,
                                                    "Login NÃO efetuado!",
                                                    "Aviso",
                                                    JOptionPane.ERROR_MESSAGE);
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(view,
                                                    "Erro de conexão!",
                                                    "Aviso",
                                                    JOptionPane.ERROR_MESSAGE);
        }
    }
}