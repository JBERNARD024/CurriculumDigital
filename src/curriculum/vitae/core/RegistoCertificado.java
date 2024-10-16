/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package curriculum.vitae.core;

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
import utils.ObjectUtils;
import utils.Recursos;

/**
 *
 * @author joaob
 */
public class RegistoCertificado implements Serializable {

    private ArrayList<String> registo;
    private ArrayList<String> temp;
    private BlockChain bc;
    public static int DIFICULTY = 4;
    private static final int MERKLE_TREE_SIZE = 8;
    MerkleTree tree;

    public RegistoCertificado() {
        registo = new ArrayList<>();
        temp = new ArrayList<>();
        bc = new BlockChain();
    }

    @Override
    public String toString() {
        StringBuilder txt = new StringBuilder();
        txt.append("Registo de Certificados = ")
            .append(registo.size())
            .append("\n\n");
        for (Block b : bc.getChain()) {
            tree = (MerkleTree) Recursos.readObject("resources/merkleTree/" + b.getCurrentHash() + ".mk");
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

    public void save(String fileName) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(fileName))) {
            out.writeObject(this);
        }
    }

    public static RegistoCertificado load(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(fileName))) {
            return (RegistoCertificado) in.readObject();
        }
    }

    public void add(Certificado c) throws Exception {
        registo.add(c.toText());
        temp.add(c.toText());
        if (temp.size() == MERKLE_TREE_SIZE) {
            tree = new MerkleTree(temp);
            bc.add(tree.getRoot(), DIFICULTY);
            tree.saveToFile("resources/merkleTree/" + bc.getLastBlockHash() + ".mk");
            temp.clear();
            tree = new MerkleTree();
        }
        /*String txtCertificado = ObjectUtils.convertObjectToBase64(c);
        bc.add(txtCertificado, DIFICULTY);*/
    }

    public List<Certificado> getCertificadoBlockchain() throws Exception {
        List<Certificado> lst = new ArrayList<>();
        for (Block b : bc.getChain()) {
            Certificado c = (Certificado) ObjectUtils.convertBase64ToObject(b.getData());
            lst.add(c);
        }
        return lst;
    }

    public BlockChain getBc() {
        return bc;
    }

    public void setBc(BlockChain bc) {
        this.bc = bc;
    }

    public ArrayList<String> getRegisto() {
        return registo;
    }

    public void setRegisto(ArrayList<String> registo) {
        this.registo = registo;
    }
}
