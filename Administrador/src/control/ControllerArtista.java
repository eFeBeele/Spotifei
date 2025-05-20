/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import DAO.*;
import Exception.ErroInesperado;
import Exception.InfoNula;
import view.menuAdm;
import model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Arthur
 */
public class ControllerArtista {
    private menuAdm view;

    public ControllerArtista(menuAdm mAdm) {
        this.view = mAdm;
    }
    
    public void adicionarArtista() {
        Conexao conexao = new Conexao();
        String nome_artista = view.getTxt_nome_artista().getText();
        
        try (Connection conn = conexao.getConnection()) {
            ArtistaDAO aDAO = new ArtistaDAO(conn);
            aDAO.inserir(nome_artista);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, 
                                          e.getMessage(), 
                                          "Erro",
                                          JOptionPane.ERROR_MESSAGE);
        } catch (ErroInesperado ex) {
            JOptionPane.showMessageDialog(view, 
                                          ex.getMessage(), 
                                          "Erro",
                                          JOptionPane.ERROR_MESSAGE);
        } catch (InfoNula ex) {
            JOptionPane.showMessageDialog(view, 
                                          ex.getMessage(), 
                                          "Erro",
                                          JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void excluirArtista() {
        Conexao conexao = new Conexao();
        String nome_artista = view.getTxt_nome_artista().getText();
        
        try (Connection conn = conexao.getConnection()) {
            ArtistaDAO aDAO = new ArtistaDAO(conn);
            aDAO.excluir(nome_artista);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, 
                                          e.getMessage(), 
                                          "Erro",
                                          JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void consultar(){
        Conexao conexao = new Conexao();
        
        try (Connection conn = conexao.getConnection()) {
            ArtistaDAO aDAO = new ArtistaDAO(conn);
            ResultSet res = aDAO.consultar();
            
            ArrayList<Artista> listaArtista = new ArrayList();
            while(res.next()){
                String nomeArtista = res.getString("nome_artista");
                listaArtista.add(new Artista(nomeArtista)); 
            }
            
            String resultado = "";
            for(int i=0; i<listaArtista.size(); i++){
                String artista = listaArtista.get(i).infoArtista();
                resultado += artista;
            }
            view.getTxt_artistas().setText(resultado);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, 
                                          e.getMessage(), 
                                          "Erro",
                                          JOptionPane.ERROR_MESSAGE);
        }
    }
}
