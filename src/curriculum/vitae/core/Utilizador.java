/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package curriculum.vitae.core;

/**
 *
 * @author joaob
 */
public class Utilizador {
    private String email;
    private byte[] password;
    private dadosPessoais dados;

    public Utilizador(String email, byte[] password) {
        this.email = email;
        this.password = password;
    }

    public Utilizador(Utilizador user) {
        this.email = user.email;
        this.password = user.password;
        this.dados = user.dados;
    }

    public dadosPessoais getDados() {
        return dados;
    }

    public void setDados(dadosPessoais dados) {
        this.dados = dados;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("username=").append(email);
        sb.append(", password=").append(password);
        return sb.toString();
    } 
}