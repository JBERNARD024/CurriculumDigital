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
//Esta classe vai criar um objeto, que vai criar uma entidade emissora de certificados
public class Instituto implements Serializable{

    private String codNome;
    private byte[] imagem;
    private PrivateKey privKey;
    private PublicKey pubKey;
    private dadosInstitucionais dadosInst;
    private int numLogin;
    private Date lastLogin;

    //Construtor que cria o objeto Instituto, com apenas o seu código nome
    public Instituto(String codNome) {
        this.codNome = codNome;
        this.numLogin = 0;
    }

    //Construtor que cria um novo Instituto, com o seu código nome e o par de chaves assimétricas
    public Instituto(String codNome, PrivateKey privKey, PublicKey pubKey) {
        this.codNome = codNome;
        this.privKey = privKey;
        this.pubKey = pubKey;
        this.dadosInst = null;
    }

    //Constutor que cria um novo objeto Instituto, a partir de outro objeto Instituto já existente
    public Instituto(Instituto inst) {
        this.codNome = inst.codNome;
        this.imagem = inst.imagem;
        this.privKey = inst.privKey;
        this.pubKey = inst.pubKey;
        this.numLogin = inst.numLogin;
        this.lastLogin = inst.lastLogin;
        this.dadosInst = inst.dadosInst;
    }

    //Função que vai criar o par de chaves assimétricas e a chave simétrica de um instituto
    public void generateKeys() throws Exception {
        KeyPair keyPair = SecurityUtils.generateECKeyPair(521, "BC");
        this.pubKey = keyPair.getPublic();
        this.privKey = keyPair.getPrivate();
    }

    //Esta função, vai criar uma pasta para guardar as chaves de um instituto
    public void criarPasta() {
        //Definir o caminho da pasta
        String basePath = new File("").getAbsolutePath();
        String caminho = basePath + "/resources/institutos/" + codNome + "/";
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

    //Função que guarda as chaves criadas na pasta do Instituto
    public void save(String password) throws Exception {
        String basePath = new File("").getAbsolutePath();
        String caminho = basePath + "/resources/institutos/" + codNome + "/";
        //Encriptar a chave privada
        byte[] secret = SecurityUtils.encrypt(privKey.getEncoded(), password);
        Files.write(Path.of(caminho + codNome + ".priv"), secret);
        //Guardar a chave pública
        Files.write(Path.of(caminho + codNome + ".pub"), pubKey.getEncoded());
    }

    //Função que vai carregar as chaves guardadas na pasta com o codNome do Instituto
    public boolean load(String password) throws Exception {
        try {
            String basePath = new File("").getAbsolutePath();
            String caminho = basePath + "/resources/institutos/" + codNome + "/";
            //Desencriptar a chave privada
            byte[] privData = Files.readAllBytes(Path.of(caminho + codNome + ".priv"));
            privData = SecurityUtils.decrypt(privData, password);
            //Ler a chave pública
            byte[] pubData = Files.readAllBytes(Path.of(caminho + codNome + ".pub"));
            this.privKey = SecurityUtils.getPrivateKey(privData);
            this.pubKey = SecurityUtils.getPublicKey(pubData);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Pessoa.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    //Função que vai carregar a chave pública do Instituto
    public void loadPublic() throws Exception {
        String basePath = new File("").getAbsolutePath();
        String caminho = basePath + "/resources/institutos/" + codNome + "/";
        //Ler a chave pública
        byte[] pubData = Files.readAllBytes(Path.of(caminho + codNome + ".pub"));
        this.pubKey = SecurityUtils.getPublicKey(pubData);
    }

    //Devolve o código nome de um Instituto
    public String getCodNome() {
        return codNome;
    }

    //Define o código nome de um Instituto
    public void setCodNome(String codNome) {
        this.codNome = codNome;
    }

    //Devolve a chave privada de um Instituto
    public PrivateKey getPrivKey() {
        return privKey;
    }

    //Define a chave privada de um Instituto
    public void setPrivKey(PrivateKey privKey) {
        this.privKey = privKey;
    }

    //Obtêm a chave pública de um Instituto
    public PublicKey getPubKey() {
        return pubKey;
    }

    //Define a chave pública de um Instituto
    public void setPubKey(PublicKey pubKey) {
        this.pubKey = pubKey;
    }

    //Devolve o número de logins efetuados por um Instituto
    public int getNumLogin() {
        return numLogin;
    }

    //Define o número de logins efetuados por um instituto
    public void setNumLogin(int numLogin) {
        this.numLogin = numLogin;
    }

    //Devole a data do último realizado no sistema
    public Date getLastLogin() {
        return lastLogin;
    }

    //Define a data do último login realizado
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    //Obtém os dados Institutcionais de um Instituto
    public dadosInstitucionais getDadosInst() {
        return dadosInst;
    }

    //Define os dados Institucionais de um Instituto
    public void setDadosInst(dadosInstitucionais dadosInst) {
        this.dadosInst = dadosInst;
    }

    //Retorna sobre um array de bytes a imagem de um Instituto
    public byte[] getImagem() {
        return imagem;
    }

    //Define a imagem sobre o formato de array de bytes 
    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
}
