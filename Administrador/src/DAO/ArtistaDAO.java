/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Exception.ErroInesperado;
import Exception.InfoNula;
import java.sql.*;

/**
 *
 * @author Arthur
 */
public class ArtistaDAO {
    private Connection conn;
    
    public ArtistaDAO(Connection conn){
        this.conn = conn;
    }
    
    public void inserir(String nome_digitado) throws SQLException, ErroInesperado, InfoNula{
        String consulta = "SELECT nome_artista FROM artista WHERE nome_artista = ?";
        PreparedStatement busca = conn.prepareStatement(consulta);
        busca.setString(1, nome_digitado);
        busca.execute();
        
        ResultSet consultaNome = busca.getResultSet();
        
        if(consultaNome.next()){
            throw new ErroInesperado("ERRO! Não é possível adicionar o mesmo Artista");
        }else if(nome_digitado.trim().equals("")){
            throw new InfoNula("ERRO! Não é possível adicionar Artista nulo");
        }
        
        String sql = "INSERT into artista (nome_artista) VALUES ('"
                      + nome_digitado + "')";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        conn.close();
    }
    
    public void excluir(String nome_digitado) throws SQLException{
        String sql = "Delete from artista where nome_artista = ?";      
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, nome_digitado);        
        statement.execute();
        conn.close();
    }
    
    public ResultSet consultar() throws SQLException{
        String sql = "SELECT * FROM artista";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        
        return resultado;
    }
}
