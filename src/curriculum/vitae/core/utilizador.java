/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package curriculum.vitae.core;

/**
 *
 * @author joaob
 */
public class utilizador {
    private String username;
    private byte[] password;
    private dadosPessoais dados;

    public utilizador(String username, byte[] password) {
        this.username = username;
        this.password = password;
    }

    public utilizador(String username, byte[] password, dadosPessoais dados) {
        this.username = username;
        this.password = password;
        this.dados = dados;
    }

    public dadosPessoais getDados() {
        return dados;
    }

    public void setDados(dadosPessoais dados) {
        this.dados = dados;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        sb.append("username=").append(username);
        sb.append(", password=").append(password);
        return sb.toString();
    }
    
    
}
