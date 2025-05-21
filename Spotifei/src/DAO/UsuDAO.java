/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.*;

/**
 *
 * @author ferna
 */
public class UsuDAO {
        private Connection conn;

    public UsuDAO(Connection conn) {
        this.conn = conn;
    }
    
    public ResultSet consultar(Usuario usuario) throws SQLException{
        String sql = "select id_usuario from usuario where email = ? and senha = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, usuario.getEmail());
        statement.setString(2, usuario.getSenha());
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        return resultado;
    }
    
    public void inserir(Usuario usuario) throws SQLException{
        String sql = "insert into usuario (nome_usuario,senha,email,adm) values ('"+ usuario.getNome()    + "', '"+ usuario.getSenha() + "', '"+ usuario.getEmail()   + "',false)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        conn.close();
    }
    public ResultSet loginUsu(Usuario usu) throws SQLException{
        String sql = "select * from usuario where email = ? AND senha = ? ";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, usu.getEmail());
        statement.setString(2, usu.getSenha());
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        
        return resultado;
    }
    
}
