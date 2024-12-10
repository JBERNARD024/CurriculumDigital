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
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.SecurityUtils;

/**
 *
 * @author joaob
 */
/*Esta classe vai criar um objeto Pessoa, um dos utilizadores do sistema, que vai poder consultar quais os certificados,
gerados por um Instituto, caso existam*/
public class Pessoa implements Serializable{
    private String email;
    private byte[] imagem;
    private dadosPessoais dados;
    private int numLogin;
    private Date lastLogin;
    private PublicKey pubKey;
    private PrivateKey privKey;

    //Cria um objeto Pessoa através de um email definido
    public Pessoa(String email) {
        this.email = email;
        this.numLogin = 0;
    }

    //Cria um objeto Pessoa, a partir de outro objeto já existente
    public Pessoa(Pessoa user) {
        this.email = user.email;
        this.imagem = user.imagem;
        this.dados = user.dados;
        this.numLogin = user.numLogin;
        this.lastLogin = user.lastLogin;
        this.pubKey = user.pubKey;
        this.privKey = user.privKey;
    }
    
    //Esta função vai gerar as chaves do Pessoa
    public void generateKeys() throws Exception {
        KeyPair keyPair = SecurityUtils.generateRSAKeyPair(4096, "BC");
        this.pubKey = keyPair.getPublic();
        this.privKey = keyPair.getPrivate();
    }
    
    //Cria a pasta da Pessoa, que vai guardar o par de chaves assimétricas
    public void criarPasta(){
        String basePath = new File("").getAbsolutePath();
        //Definir o caminho da pasta
        String caminho = basePath + "/resources/pessoas/" + email + "/";
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
    
    //Guarda o par de chaves assimétrico na pasta, encriptando a chave privada com a password
    public void save(String password) throws Exception {
        String basePath = new File("").getAbsolutePath();
        String caminho = basePath + "/resources/pessoas/" + email + "/";
        //Encriptar a chave privada
        byte[] secret = SecurityUtils.encrypt(privKey.getEncoded(), password);
        Files.write(Path.of(caminho + email + ".priv"), secret);
         //Guardar a chave pública
        Files.write(Path.of(caminho + email  + ".pub"), pubKey.getEncoded());
    }

    //Carrega as chaves da pasta de uma Pessoa, desencriptando a respetiva chave privada
    public boolean load(String password) throws Exception {
        try {
            String basePath = new File("").getAbsolutePath();
            String caminho = basePath + "/resources/pessoas/" + email + "/";
            //Desencriptar a chave privada
            byte[] privData = Files.readAllBytes(Path.of(caminho + email + ".priv"));
            privData = SecurityUtils.decrypt(privData, password);
            //Ler a chave pública
            byte[] pubData = Files.readAllBytes(Path.of(caminho + email + ".pub"));
            this.privKey = SecurityUtils.getPrivateKey(privData);
            this.pubKey = SecurityUtils.getPublicKey(pubData);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Pessoa.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    //Obtêm a chave pública, presente na pasta atribuída a uma Pessoa
    public void loadPublic() throws Exception {
        String basePath = new File("").getAbsolutePath();
        String caminho = basePath + "/resources/pessoas/" + email + "/";
        //Ler a chave pública
        byte[] pubData = Files.readAllBytes(Path.of(caminho + email + ".pub"));
        this.pubKey = SecurityUtils.getPublicKey(pubData);
    }

    //Devolve os dados de um Pessoa
    public dadosPessoais getDados() {
        return dados;
    }

    //Define os dados de uma Pessoa
    public void setDados(dadosPessoais dados) {
        this.dados = dados;
    }

    //Retorna o email de uma Pessoa
    public String getEmail() {
        return email;
    }

    //Define o email de uma Pessoa
    public void setEmail(String email) {
        this.email = email;
    }
    
    //Retorna, em formato de String, o email de uma Pessoa
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Email=").append(email);
        return sb.toString();
    } 

    //Retorna o valor de logins efetuados no sistema por parte da Pessoa
    public int getNumLogin() {
        return numLogin;
    }

    //Define o número de logins efetuados no sistema
    public void setNumLogin(int numLogin) {
        this.numLogin = numLogin;
    }

    //Obtém a data do último login efetuado
    public Date getLastLogin() {
        return lastLogin;
    }

    //Estabelece uma nova data do útlimo login
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    //Retorna a imagem de uma Pessoa, através de um array de bytes
    public byte[] getImagem() {
        return imagem;
    }

    //Define a imagem, através de um formato de bytes
    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    //Obtém a chave pública de uma Pessoa
    public PublicKey getPubKey() {
        return pubKey;
    }

    //Define a chave pública de uma Pessoa
    public void setPubKey(PublicKey pubKey) {
        this.pubKey = pubKey;
    }

    //Devolve a chave privada de uma Pessoa
    public PrivateKey getPrivKey() {
        return privKey;
    }

    //Define a chave privada de uma Pessoa
    public void setPrivKey(PrivateKey privKey) {
        this.privKey = privKey;
    }  

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pessoa other = (Pessoa) obj;
        return Objects.equals(this.email, other.email);
    }
    
}
