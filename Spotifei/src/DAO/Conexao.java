/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
/**
 *
 * @author ferna
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexao {

    public Connection getConnection() throws SQLException {
        // String de conexão JDBC para o banco de dados PostgreSQL
        String url = "jdbc:postgresql://ep-late-morning-acqqpbep-pooler.sa-east-1.aws.neon.tech:5432/neondb";

        // Propriedades para a conexão
        Properties props = new Properties();
        props.setProperty("user", "neondb_owner");
        props.setProperty("password", "npg_IVXghS6i0KbN");
        props.setProperty("sslmode", "require"); // Garante que o SSL seja usado

        // Carrega o driver JDBC do PostgreSQL (não é mais necessário a partir do JDBC 4.0)
        // Class.forName("org.postgresql.Driver");

        // Estabelece a conexão com o banco de dados usando a URL e as propriedades
        Connection conn = DriverManager.getConnection(url, props);
        return conn;
    }
}
