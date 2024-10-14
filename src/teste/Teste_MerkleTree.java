/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

import curriculum.vitae.core.Certificado;
import curriculum.vitae.core.Educacao;
import curriculum.vitae.core.Instituto;
import curriculum.vitae.core.Utilizador;
import curriculum.vitae.core.dadosInstitucionais;
import curriculum.vitae.core.dadosPessoais;
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
import utils.MerkleTree;

/**
 *
 * @author joaob
 */
public class Teste_MerkleTree {

    public static void main(String[] args) throws Exception {
        /*ArrayList<Integer> inteiros = new ArrayList<>();
        inteiros.add(1);
        inteiros.add(2);
        inteiros.add(3);
        inteiros.add(4);
        inteiros.add(5);
        inteiros.add(6);
        inteiros.add(7);
        inteiros.add(8);
        MerkleTree tree = new MerkleTree(inteiros);
        System.out.println(tree.toString());
        
        System.out.println(tree.getProof(9));
        System.out.println(tree.getRoot());*/
        Security.addProvider(new BouncyCastleProvider());
        loadProviders();
        ArrayList<Certificado> certificados = new ArrayList<>();
        Instituto i = new Instituto("TESTE");
        i.criarPasta();
        i.generateKeys();
        i.setDadosInst(new dadosInstitucionais("EST", Date.from(Instant.now()), "EST", "EST", "EST", "EST", "EST", "EST", "EST", "EST", "EST", "EST"));

        Utilizador u = new Utilizador("teste@gmail.com");
        u.criarPasta();
        u.generateKeys();
        u.setDados(new dadosPessoais("NA", "NA", Date.from(Instant.now()), "NA", "NA", "NA", "NA", "NA", "NA", "NA", "NA"));

        Educacao educa = new Educacao("ka", "ka", "ka", 0, "ka", "ka", "ka", Date.from(Instant.now()), Date.from(Instant.now()), "ka");

        Certificado c = new Certificado(i, u, educa);
        System.out.println(c.isValid());
        Utilizador u2 = new Utilizador("ola@gmail.com");
        u2.criarPasta();
        u2.generateKeys();
        u2.setDados(new dadosPessoais("ola", "ola", Date.from(Instant.now()), "ola", "ola", "ola", "ola", "ola", "ola", "ola", "ola"));
        
        c.setGraduado(u2);
        c.setPubKeyUser(Base64.getEncoder().encodeToString(u2.getPubKey().getEncoded()));
        c.isValid();
        System.out.println(c.isValid());
        
        certificados.add(c);
        MerkleTree tree1 = new MerkleTree(certificados);
        System.out.println(tree1.getProof(c));

    }

    public static void loadProviders() {
        Provider providers[] = Security.getProviders();
        //todos os fornecedores do segurança
        for (Provider provider : providers) {
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
        }
    }

}
