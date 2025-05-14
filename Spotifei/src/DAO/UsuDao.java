
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import model.Usuario;


public class UsuDao {
    private Connection conn;

    public UsuDao(Connection conn) {
        this.conn = conn;
    }

    public ResultSet consultar(Usuario usuario) throws SQLException{
        String sql = "SELECT * FROM prod.usuario WHERE email = ? AND senha = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, usuario.getEmail());
        statement.setString(2, usuario.getSenha());
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        return resultado;
    }

    public void inserir(Usuario usuario) throws SQLException{
        String sql = "INSERT INTO prod.usuario (nome_usuario,senha, email) VALUES (?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, usuario.getNome());
        statement.setString(3, usuario.getEmail());
        statement.setString(2, usuario.getSenha());
        statement.execute();
        conn.close();
    }

    public void atualizar(Usuario usuario) throws SQLException{
        String sql = "UPDATE usuario SET senha = ? WHERE email = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, usuario.getSenha());
        statement.setString(2, usuario.getEmail());
        statement.execute();
        conn.close();
    }

    public void remover(Usuario usuario) throws SQLException{
        String sql = "DELETE FROM usuario WHERE email = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, usuario.getEmail());
        statement.execute();
        conn.close();
    }

}