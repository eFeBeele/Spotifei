/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Arthur
 */
public class Musica {
    private int id_musica;
    private String nome_musica;
    private String nome_artista;
    private String genero;
    private int curtidas;
    private int descurtidas;
    private int duracao;

    public Musica(int id_musica, String nome_musica, String nome_artista, String genero, int curtidas, int descurtidas, int duracao) {
        this.id_musica = id_musica;
        this.nome_musica = nome_musica;
        this.nome_artista = nome_artista;
        this.genero = genero;
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

    @Override
    public String toString() {
        return "Musica{" + "id_musica=" + id_musica + ", nome_musica=" + nome_musica + ", nome_artista=" + nome_artista + ", genero=" + genero + '}';
    }
}
