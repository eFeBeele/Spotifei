/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author unifflopes
 */
public class Usuario extends Pessoa implements Autenticacao{
    private String email;
    private String senha;

    public Usuario(String nome_usuario, String email, String senha) {
        super(nome_usuario);
        this.email = email;
        this.senha = senha;
    }

    public Usuario(String email,String senha) {
        super("nulo");
        this.email = email;
        this.senha = senha;
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
    
    public String info(){
        return "Usuário: \nNome do usuario: " + super.getNome() + "\nSenha: " + senha + "\nE-Mail: " + email;
    }
}