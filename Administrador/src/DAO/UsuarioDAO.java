/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import model.*;

/**
 *
 * @author Arthur
 */
public class UsuarioDAO {
    private Connection conn;
    
    public UsuarioDAO(Connection conn){
        this.conn = conn;
    }
    
    public ResultSet consultar(Usuario usr) throws SQLException{
        String sql = "select * from prod.usuario where nome_usuario = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, usr.getNome());
        
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        
        return resultado;
    }
    
    public ResultSet loginADM(Admin adm) throws SQLException{
        String sql = "select * from prod.usuario where email = ? AND adm = true";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, adm.getEmail());
        
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
