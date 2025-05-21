/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ferna
 */
public class Artista extends Pessoa{
    public Artista(String nome){
        super(nome);
    }
    
    public String infoArtista(){
        return "\nNome: " + super.getNome() + 
                "\n---------------------";
    }
}
