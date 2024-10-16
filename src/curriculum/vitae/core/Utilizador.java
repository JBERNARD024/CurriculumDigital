/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package curriculum.vitae.core;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.SecurityUtils;

/**
 *
 * @author joaob
 */
public class Utilizador implements Serializable{
    private String email;
    private byte[] imagem;
    private dadosPessoais dados;
    private int numLogin;
    private Date lastLogin;
    private PublicKey pubKey;
    private PrivateKey privKey;

    public Utilizador(String email) {
        this.email = email;
        this.numLogin = 0;
    }

    public Utilizador(Utilizador user) {
        this.email = user.email;
        this.imagem = user.imagem;
        this.dados = user.dados;
        this.numLogin = user.numLogin;
        this.lastLogin = user.lastLogin;
        this.pubKey = user.pubKey;
        this.privKey = user.privKey;
    }
    
    //Esta função vai gerar as chaves do Utilizador
    public void generateKeys() throws Exception {
        KeyPair keyPair = SecurityUtils.generateRSAKeyPair(4096, "BC");
        this.pubKey = keyPair.getPublic();
        this.privKey = keyPair.getPrivate();
    }
    
    public void criarPasta(){
        //Definir o caminho da pasta
        String caminho = "resources/utilizadores/" + email + "/";
        File diretoria = new File(caminho);
        //Verificar se a pasta já existe, caso contrário criar a pasta
        if (!diretoria.exists()) {
            boolean created = diretoria.mkdirs(); // mkdirs() cria também subpastas se necessário
            if (created) {
                System.out.println("Pasta criada com sucesso: " + caminho);
            } else {
                System.out.println("Falha ao criar a pasta.");
            }
        } else {
            System.out.println("A pasta já existe.");
        }
    }
    
    public void save(String password) throws Exception {
        String caminho = "resources/utilizadores/" + email + "/";
        //Encriptar a chave privada
        byte[] secret = SecurityUtils.encrypt(privKey.getEncoded(), password);
        Files.write(Path.of(caminho + email + ".priv"), secret);
         //Guardar a chave pública
        Files.write(Path.of(caminho + email  + ".pub"), pubKey.getEncoded());
    }

    public boolean load(String password) throws Exception {
        try {
            String caminho = "resources/utilizadores/" + email + "/";
            //Desencriptar a chave privada
            byte[] privData = Files.readAllBytes(Path.of(caminho + email + ".priv"));
            privData = SecurityUtils.decrypt(privData, password);
            //Ler a chave pública
            byte[] pubData = Files.readAllBytes(Path.of(caminho + email + ".pub"));
            this.privKey = SecurityUtils.getPrivateKey(privData);
            this.pubKey = SecurityUtils.getPublicKey(pubData);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Utilizador.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void loadPublic() throws Exception {
        String caminho = "resources/utilizadores/" + email + "/";
        //Ler a chave pública
        byte[] pubData = Files.readAllBytes(Path.of(caminho + email + ".pub"));
        this.pubKey = SecurityUtils.getPublicKey(pubData);
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
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Email=").append(email);
        return sb.toString();
    } 

    public int getNumLogin() {
        return numLogin;
    }

    public void setNumLogin(int numLogin) {
        this.numLogin = numLogin;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public PublicKey getPubKey() {
        return pubKey;
    }

    public void setPubKey(PublicKey pubKey) {
        this.pubKey = pubKey;
    }

    public PrivateKey getPrivKey() {
        return privKey;
    }

    public void setPrivKey(PrivateKey privKey) {
        this.privKey = privKey;
    }  
}
