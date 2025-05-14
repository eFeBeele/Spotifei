/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import DAO.*;

/**
 *
 * @author ferna
 */
public class TesteConexaoJDBC {
        public static void main(String[] args) {
        Conexao conexao = new Conexao(); // Cria uma instância da classe Conexao
        try {
            Connection conn = conexao.getConnection(); // Obtém a conexão

            if (conn != null) {
                System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
                conn.close(); // Fecha a conexão após o teste
                System.out.println("Conexão encerrada.");
            } else {
                System.out.println("Falha ao conectar ao banco de dados.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
