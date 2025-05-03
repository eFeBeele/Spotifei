/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author unifflopes
 */
public class Admin extends Pessoa implements Autenticacao{
    private String email;
    private String senha;
    
    public Admin(String nome, String email, String senha){
        super(nome);
        this.email = email;
        this.senha = senha;
    }
    
    public Admin(String email){
        super("nulo");
        this.email = email;
        this.senha = "nulo";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    @Override
    public boolean login(String senha) {
        return this.senha.equals(senha);
    }
}
