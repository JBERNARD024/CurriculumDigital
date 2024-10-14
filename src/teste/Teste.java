/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

import curriculum.vitae.core.Certificado;
import curriculum.vitae.core.Educacao;
import curriculum.vitae.core.Instituto;
import curriculum.vitae.core.Utilizador;
import curriculum.vitae.core.dadosPessoais;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Key;
import java.security.Provider;
import java.security.Security;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import utils.ObjectUtils;
import utils.SecurityUtils;

/**
 *
 * @author joaob
 */
public class Teste {

    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        loadProviders();
        // I N S T I T U T O
        Instituto inst = new Instituto("TESTE");
        inst.generateKeys();
        //inst.criarPasta("123qwe#");
        inst.save("123qwe#");
        // U T I L I Z A D O R
        Utilizador user = new Utilizador("teste@teste.com");
        user.generateKeys();
        user.criarPasta();
        user.save("123qwe#");
        user.setDados(new dadosPessoais("", "", Date.from(Instant.now()), "", "", "", "", "", "", "", ""));
        user.setImagem(new byte[Integer.SIZE]);
        // E D U C A Ç Ã O
        Educacao educa = new Educacao("TESTE", "TESTE", "TESTE", 15, "T", "TESTE", "TESTE", Date.from(Instant.now()), Date.from(Instant.now()), "TESTE");
        //LOGIN INSTITUTO
        inst.load("123qwe#");
        //CRIAR CERTIFICADO 
        Certificado c = new Certificado(inst, user, educa);
        System.out.println("Certificado Original = " + ObjectUtils.convertObjectToBase64(c).getBytes());

        //ENCRIPTAR CERTIFICADO 
//        String cert = ObjectUtils.convertObjectToBase64(c);
//        System.out.println("Certificado Original 2 = " + cert);
        byte[] certificado = serializar(c);
        byte[] certificao_encript = SecurityUtils.encrypt(certificado, inst.getSimKey());
        System.out.println("Certificado Encriptado = " + Base64.getEncoder().encodeToString(certificao_encript));
        Certificado c_encript = (Certificado) desserializar(certificao_encript);
        System.out.println("Certificado Classe Encriptada " + c_encript);
        //ENCRIPTAR CHAVE SIMÉTRICA
        System.out.println("Chave Desencriptada = " + Base64.getEncoder().encodeToString(inst.getSimKey().getEncoded()));
        byte[] simKey = SecurityUtils.encrypt(inst.getSimKey().getEncoded(), user.getPubKey());
        System.out.println("Chave Encriptada = " + Base64.getEncoder().encodeToString(simKey));
        Files.write(Path.of("../Curriculum Vitae/institutos/" + inst.getCodNome() + "/" + inst.getCodNome() + ".sim"), simKey);

        //DESENCRIPTAR CHAVE SIMÉTRICA
        user.load("123qwe#");
        byte[] simKeyData = Files.readAllBytes(Path.of("../Curriculum Vitae/institutos/" + inst.getCodNome() + "/" + inst.getCodNome() + ".sim"));
        System.out.println("\nChave Encriptada = " + Base64.getEncoder().encodeToString(simKeyData));
        simKeyData = SecurityUtils.decrypt(simKeyData, user.getPrivKey());
        System.out.println("Chave Desencriptada = " + Base64.getEncoder().encodeToString(simKeyData));
        Key k = SecurityUtils.getKey(simKeyData);

        //certificao_encript = SecurityUtils.decrypt(z, k);
        System.out.println("Certificado Encriptado = " + Base64.getEncoder().encodeToString(certificao_encript));

        //DESENCRIPTAR CHAVE SIMÉTRICA
    }

    public void encriptar_desencriptar_classe() {

        /*String inst = ObjectUtils.convertObjectToBase64(instituto);
        System.out.println("Instituto Base64 = " + inst);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(instituto); // Escrever o objeto no stream

        byte[] i_encrypt = baos.toByteArray();
        System.out.println("Instituto = " + Base64.getEncoder().encodeToString(i_encrypt));

        Instituto i = (Instituto) ObjectUtils.convertBase64ToObject(inst);
        System.out.println("Instituto Objeto = " + i.getCodNome());

        byte[] i_encrypted = SecurityUtils.encrypt(i_encrypt, instituto.getSimKey());
        System.out.println("Instituto Encriptado = " + Base64.getEncoder().encodeToString(i_encrypted));

        byte[] i_decrypted = SecurityUtils.decrypt(i_encrypted, instituto.getSimKey());
        System.out.println("Instituto Desencriptado = " + Base64.getEncoder().encodeToString(i_decrypted));

        ByteArrayInputStream bais = new ByteArrayInputStream(i_decrypted);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Instituto insti = (Instituto) ois.readObject();
        System.out.println("Instituto Desencriptado Classe = " + insti.getCodNome());*/
    }

    public static void loadProviders() {
        Provider providers[] = Security.getProviders();
        //todos os fornecedores do segurança
        for (Provider provider : providers) {
            //System.out.println(provider.toString() + "\n");
            StringBuilder txt = new StringBuilder();
            List<String> lst = new ArrayList<>();
            //serviços fornecidos
            Set<Provider.Service> services = provider.getServices();
            for (Provider.Service service : services) {
                lst.add(String.format("%-20s %s\n", service.getType(), service.getAlgorithm()));
            }
            Collections.sort(lst);
            for (String service : lst) {
                txt.append(service);
            }
            //System.out.println(txt);
        }
    }

    // Método para serializar um objeto em um array de bytes
    public static byte[] serializar(Object obj) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(obj); // Escrever o objeto no stream
            return baos.toByteArray(); // Retornar o array de bytes
        }
    }

    // Método para desserializar um array de bytes em um objeto
    public static Object desserializar(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes); ObjectInputStream ois = new ObjectInputStream(bais)) {
            return ois.readObject(); // Ler o objeto do stream
        }
    }
}
