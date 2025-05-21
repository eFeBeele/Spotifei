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
        String url = "jdbc:postgresql://ep-late-morning-acqqpbep-pooler.sa-east-1.aws.neon.tech:5432/neondb";

        Properties props = new Properties();
        props.setProperty("user", "neondb_owner");
        props.setProperty("password", "npg_IVXghS6i0KbN");
        props.setProperty("sslmode", "require"); 
        
        Connection conn = DriverManager.getConnection(url, props);
        return conn;
    }
}
