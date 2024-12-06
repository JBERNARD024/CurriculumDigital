/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package curriculum.vitae.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import utils.Block;
import utils.BlockChain;
import utils.Converter;
import utils.MerkleTree;
import utils.Recursos;

/**
 *
 * @author joaob
 */
//Esta classe vai guardar a lista de certificados emitidos e a blockchain
public class RegistoCertificado implements Serializable {

    private ArrayList<String> registo;
    private ArrayList<String> temp;
    private BlockChain bc;
    public static int DIFICULTY = 5;
    private static final int MERKLE_TREE_SIZE = 1;
    MerkleTree tree;

    //Cria o objeto da classe RegistoCertificado, inicializando o registo de certificados, a blockchain e a lista temporária de certificados
    public RegistoCertificado() {
        registo = new ArrayList<>();
        temp = new ArrayList<>();
        bc = new BlockChain();
    }

    /*Esta função vai apresentar a lista de todos os certificados, que já foram validados pela blockchain.
    De forma a confirmar, queos certificados estão válidos, juntamente com os dados do certificado,
    é apresentado também o Hash fdo bloco anterior, o nonce e o hash do bloco de que faz parte*/
    @Override
    public String toString() {
        String basePath = new File("").getAbsolutePath();
        StringBuilder txt = new StringBuilder();
        txt.append("Registo de Certificados = ")
            .append(registo.size())
            .append("\n\n");
        for (Block b : bc.getChain()) {
            tree = (MerkleTree) Recursos.readObject(basePath + "/resources/merkleTree/" + b.getCurrentHash() + ".mk");
            for (int i = 0; i < registo.size(); i++) {
                String cert = getRegisto().get(i);
                List<String> proof = tree.getProof(cert);
                boolean isProofValid = tree.isProofValid(cert, proof);
                if (isProofValid) {
                    Certificado c = (Certificado) Converter.hexToObject((String) getRegisto().get(i));
                    txt.append(b.getPreviousHash())
                            .append(" ")
                            .append(c.toString())
                            .append(" ")
                            .append(b.getNonce())
                            .append(" ")
                            .append(b.getCurrentHash())
                            .append("\n");
                }
            }
        }
        return txt.toString();
    }

    //Guarda o ficheiro com o registo de Certificado emitidos e a blockchain
    public void save(String fileName) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(fileName))) {
            out.writeObject(this);
        }
    }

    //Carrega o ficheiro guardado que contém o registo de certificados e a blockchain
    public static RegistoCertificado load(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(fileName))) {
            return (RegistoCertificado) in.readObject();
        }
    }

    /*Adiciona à blockchain, um novo bloco em que os dados, são a root de uma árvore de merkle, composta por oito certificados
    No fim de criada, guarda o ficheiro da merkleTree e limpa a lista temporária*/
    public void add(Certificado c) throws Exception {
        String basePath = new File("").getAbsolutePath();
        registo.add(c.toText());
        temp.add(c.toText());
        if (temp.size() == MERKLE_TREE_SIZE) {
            tree = new MerkleTree(temp);
            bc.add(tree.getRoot(), DIFICULTY);
            tree.saveToFile(basePath + "/resources/merkleTree/" + bc.getLastBlockHash() + ".mk");
            temp.clear();
            tree = new MerkleTree();
        }
    }

    //Devolve a blockchain
    public BlockChain getBc() {
        return bc;
    }

    //Define uma blockchain
    public void setBc(BlockChain bc) {
        this.bc = bc;
    }

    //Devolve todos os certificados emitidos
    public ArrayList<String> getRegisto() {
        return registo;
    }

    //Define o registo dos certificados emitidos
    public void setRegisto(ArrayList<String> registo) {
        this.registo = registo;
    }
}
