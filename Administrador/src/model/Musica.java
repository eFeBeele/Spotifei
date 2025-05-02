/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Arthur
 */
public class Musica {
    private int id_musica;
    private String nome_musica;
    private String nome_artista;
    private String genero;
    private String curtidas;
    private String descurtidas;
    private String duracao;

    public Musica(String nome_musica, String curtidas, String descurtidas, String duracao) {
        this.nome_musica = nome_musica;
        this.curtidas = curtidas;
        this.descurtidas = descurtidas;
        this.duracao = duracao;
    }
    
    public int getId_musica() {
        return id_musica;
    }

    public void setId_musica(int id_musica) {
        this.id_musica = id_musica;
    }

    public String getNome_musica() {
        return nome_musica;
    }

    public void setNome_musica(String nome_musica) {
        this.nome_musica = nome_musica;
    }

    public String getNome_artista() {
        return nome_artista;
    }

    public void setNome_artista(String nome_artista) {
        this.nome_artista = nome_artista;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    public String infoMusicas(){

        return  "\nNome: " + nome_musica +
                "\nCurtidas: " + curtidas +
                "\nDescurtidas: " + descurtidas +
                "\nDuração: " + duracao +
                "\n------------------------\n";
    }

    @Override
    public String toString() {
        return "Musica{" + "id_musica=" + id_musica + ", nome_musica=" + nome_musica + ", nome_artista=" + nome_artista + ", genero=" + genero + '}';
    }
}
