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
import utils.MerkleTree;
import utils.ObjectUtils;

/**
 *
 * @author joaob
 */
public class RegistoCertificado implements Serializable {

    ArrayList<Certificado> registo;
    private BlockChain bc;
    public static int DIFICULTY = 4;
    private static final int MERKLE_TREE_SIZE = 8;

    public RegistoCertificado() {
        registo = new ArrayList<>();
        bc = new BlockChain();
    }

    public String toString() {
        StringBuilder txt = new StringBuilder();
        for (Block b : bc.getChain()) {
            Certificado c = (Certificado) ObjectUtils.convertBase64ToObject(b.getData());
            txt.append(b.getPreviousHash() + " "
                    + c.toString() + " "
                    + b.getNonce() + " "
                    + b.getCurrentHash()
                    + "\n"
            );
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
        registo.add(c);
        String txtCertificado = ObjectUtils.convertObjectToBase64(c);
        bc.add(txtCertificado, DIFICULTY);
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
}
