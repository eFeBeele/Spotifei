/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ferna
 */
public class Playlist {
    private int idPlaylist; // Corresponde a id_playlist no banco (geralmente gerado automaticamente)
    private String nome;       // Corresponde a nome_playlist
    private String visibilidade; // Corresponde a visibilidade
    private int idUsuarioDono; // Corresponde a id_usuariodono


    public Playlist(String nome, String visibilidade, int idUsuarioDono) {
        this.nome = nome;
        this.visibilidade = visibilidade;
        this.idUsuarioDono = idUsuarioDono;
    }

    public Playlist(int idPlaylist, String nome, String visibilidade, int idUsuarioDono) {
        this.idPlaylist = idPlaylist;
        this.nome = nome;
        this.visibilidade = visibilidade;
        this.idUsuarioDono = idUsuarioDono;
    }

    // Getters e Setters para todos os atributos
    public int getIdPlaylist() {
        return idPlaylist;
    }

    public void setIdPlaylist(int idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getVisibilidade() {
        return visibilidade;
    }

    public void setVisibilidade(String visibilidade) {
        this.visibilidade = visibilidade;
    }

    public int getIdUsuarioDono() {
        return idUsuarioDono;
    }

    public void setIdUsuarioDono(int idUsuarioDono) {
        this.idUsuarioDono = idUsuarioDono;
    }

public String infoPlaylistCompleta() { //
        return "ID: " + idPlaylist + "\n" + //
               "Nome: " + nome + "\n" + //
               "Visibilidade: " + visibilidade + "\n" + //
               "ID do Dono: " + idUsuarioDono + "\n" + //
               "-----------------------------------\n"; // Adiciona um separador como em infoMusicasCompleta()
    }
}
