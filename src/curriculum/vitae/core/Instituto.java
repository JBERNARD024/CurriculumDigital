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
import java.security.Key;
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
public class Instituto implements Serializable{

    private String codNome;
    private byte[] imagem;
    private PrivateKey privKey;
    private PublicKey pubKey;
    private Key simKey;
    private dadosInstitucionais dadosInst;
    private int numLogin;
    private Date lastLogin;

    public Instituto(String codNome) {
        this.codNome = codNome;
        this.numLogin = 0;
    }

    public Instituto(String codNome, PrivateKey privKey, PublicKey pubKey, Key simKey) {
        this.codNome = codNome;
        this.privKey = privKey;
        this.pubKey = pubKey;
        this.simKey = simKey;
        this.dadosInst = null;
    }

    public Instituto(Instituto inst) {
        this.codNome = inst.codNome;
        this.imagem = inst.imagem;
        this.privKey = inst.privKey;
        this.pubKey = inst.pubKey;
        this.simKey = inst.simKey;
        this.numLogin = inst.numLogin;
        this.lastLogin = inst.lastLogin;
        this.dadosInst = inst.dadosInst;
    }

    //Função que vai criar o par de chaves assimétricas e a chave simétrica de um instituto
    public void generateKeys() throws Exception {
        KeyPair keyPair = SecurityUtils.generateECKeyPair(521, "BC");
        this.pubKey = keyPair.getPublic();
        this.privKey = keyPair.getPrivate();
        this.simKey = SecurityUtils.generateKey("AES", 256, "BC");
    }

    //Esta função, vai criar uma pasta para guardar as chaves de um instituto
    public void criarPasta() {
        //Definir o caminho da pasta
        String basePath = new File("").getAbsolutePath();
        String caminho = basePath + "resources/institutos/" + codNome + "/";
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
        String caminho = basePath + "resources/institutos/" + codNome + "/";
        //Encriptar a chave privada
        byte[] secret = SecurityUtils.encrypt(privKey.getEncoded(), password);
        Files.write(Path.of(caminho + codNome + ".priv"), secret);
        //Encriptar a chave simétrica
        byte[] simData = SecurityUtils.encrypt(simKey.getEncoded(), password);
        Files.write(Path.of(caminho + codNome + ".sim"), simData);
        //Guardar a chave pública
        Files.write(Path.of(caminho + codNome + ".pub"), pubKey.getEncoded());
    }

    //Função que vai carregar as chaves guardadas na pasta com o codNome do Instituto
    public boolean load(String password) throws Exception {
        try {
            String basePath = new File("").getAbsolutePath();
            String caminho = basePath + "resources/institutos/" + codNome + "/";
            //Desencriptar a chave privada
            byte[] privData = Files.readAllBytes(Path.of(caminho + codNome + ".priv"));
            privData = SecurityUtils.decrypt(privData, password);
            //Desencriptar a chave simétrica
            byte[] simData = Files.readAllBytes(Path.of(caminho + codNome + ".sim"));
            simData = SecurityUtils.decrypt(simData, password);
            //Ler a chave pública
            byte[] pubData = Files.readAllBytes(Path.of(caminho + codNome + ".pub"));
            this.privKey = SecurityUtils.getPrivateKey(privData);
            this.pubKey = SecurityUtils.getPublicKey(pubData);
            this.simKey = SecurityUtils.getKey(simData);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Utilizador.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    //Função que vai carregar a chave pública do Instituto
    public void loadPublic() throws Exception {
        String basePath = new File("").getAbsolutePath();
        String caminho = basePath + "resources/institutos/" + codNome + "/";
        //Ler a chave pública
        byte[] pubData = Files.readAllBytes(Path.of(caminho + codNome + ".pub"));
        this.pubKey = SecurityUtils.getPublicKey(pubData);
    }

    public String getCodNome() {
        return codNome;
    }

    public void setCodNome(String codNome) {
        this.codNome = codNome;
    }

    public PrivateKey getPrivKey() {
        return privKey;
    }

    public void setPrivKey(PrivateKey privKey) {
        this.privKey = privKey;
    }

    public PublicKey getPubKey() {
        return pubKey;
    }

    public void setPubKey(PublicKey pubKey) {
        this.pubKey = pubKey;
    }

    public Key getSimKey() {
        return simKey;
    }

    public void setSimKey(Key simKey) {
        this.simKey = simKey;
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

    public dadosInstitucionais getDadosInst() {
        return dadosInst;
    }

    public void setDadosInst(dadosInstitucionais dadosInst) {
        this.dadosInst = dadosInst;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
}
