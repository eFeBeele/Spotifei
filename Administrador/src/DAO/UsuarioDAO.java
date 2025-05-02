/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import model.Usuario;

/**
 *
 * @author Arthur
 */
public class UsuarioDAO {
    private Connection conn;
    
    public UsuarioDAO(Connection conn){
        this.conn = conn;
    }
    
    public ResultSet consultar(String nome) throws SQLException{
        String sql = "select * from prod.usuario where nome_usuario = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, nome);
        
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        
        return resultado;
    }
    
    public ResultSet loginADM(String email, String senha) throws SQLException{
        String sql = "select * from prod.usuario where email = ? AND senha = ? AND adm = true";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, senha);
        
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        
        return resultado;
    }
    
    public ResultSet totalUsuarios() throws SQLException{
        String sql = "SELECT COUNT(*) AS total_usuarios FROM prod.usuario";
        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        
        return resultado;
    }
}
