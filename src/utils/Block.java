package utils;

//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: 
//::                                                                         ::
//::     Antonio Manuel Rodrigues Manso                                      ::
//::                                                                         ::
//::     I N S T I T U T O    P O L I T E C N I C O   D E   T O M A R        ::
//::     Escola Superior de Tecnologia de Tomar                              ::
//::     e-mail: manso@ipt.pt                                                ::
//::     url   : http://orion.ipt.pt/~manso                                  ::
//::                                                                         ::
//::     This software was build with the purpose of investigate and         ::
//::     learning.                                                           ::
//::                                                                         ::
//::                                                               (c)2022   ::
//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//////////////////////////////////////////////////////////////////////////////
import curriculum.vitae.core.Certificado;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import miner.Miner;

/**
 * Created on 22/08/2022, 09:23:49
 *
 * Block with consensus of Proof of Work
 *
 * @author IPT - computer
 * @version 1.0
 */
public class Block implements Serializable, Comparable<Block> {

    String previousHash; // link to previous block
    String merkleRoot;   // merkleRoot in the block
    int nonce;           // proof of work 
    String currentHash;  // Hash of block

    public Block(String previousHash, List<Certificado> certificados) throws IOException {
        this.previousHash = previousHash;
        MerkleTree mkt = new MerkleTree(certificados);
        this.merkleRoot = mkt.getRoot();
        mkt.saveToFile(new File("").getAbsolutePath() + "\\resources\\merkleTree\\" + merkleRoot + ".mkt");
    }

    public void setNonce(int nonce, int zeros) throws Exception {
        this.nonce = nonce;
        //calcular o hash
        this.currentHash = calculateHash();
        //calcular o prefixo
        String prefix = String.format("%0" + zeros + "d", 0);
        if (!currentHash.startsWith(prefix)) {
            throw new Exception(nonce + " not valid Hash=" + currentHash);
        }

    }

    public String getMinerData() {
        return previousHash + merkleRoot;
    }

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public List<Certificado> certificados() {
        try {
            MerkleTree tree = new MerkleTree();
            tree.loadFromFile(new File("").getAbsolutePath() + "\\resources\\merkleTree\\" + merkleRoot + ".mkt");
            return tree.getElements();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Block.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(Block.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public int getNonce() {
        return nonce;
    }

    public String calculateHash() {
        return Miner.getHash(getMinerData(), nonce);
    }

    public String getCurrentHash() {
        return currentHash;
    }

    @Override
    public String toString() {
        return // (isValid() ? "OK\t" : "ERROR\t")+
                String.format("[ %8s", previousHash) + " <- "
                + String.format("%-10s", merkleRoot) + String.format(" %7d ] = ", nonce)
                + String.format("%8s", currentHash);

    }

    public String getHeaderString() {
        return "prev Hash: " + previousHash
                + "\nMkt Root : " + merkleRoot
                + "\nnonce    : " + nonce
                + "\ncurr Hash: " + currentHash;
    }

    public String getCertificadosString() throws IOException {
        try {
            StringBuilder txt = new StringBuilder();
            MerkleTree tree = new MerkleTree();
            tree = tree.loadFromFile(new File("").getAbsolutePath() + "\\resources\\merkleTree\\" + merkleRoot + ".mkt");
            for (Object certificado : tree.getElements()) {
                txt.append(certificado.toString()).append("\n");
            }
            return txt.toString();
        } catch (FileNotFoundException | ClassNotFoundException ex) {
            Logger.getLogger(Block.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean isValid() {
        return currentHash.equals(calculateHash());
    }

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    private static final long serialVersionUID = 202208220923L;
    //:::::::::::::::::::::::::::  Copyright(c) M@nso  2022  :::::::::::::::::::
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Block other = (Block) obj;
        if (this.nonce != other.nonce) {
            return false;
        }
        if (!Objects.equals(this.previousHash, other.previousHash)) {
            return false;
        }
        if (!Objects.equals(this.merkleRoot, other.merkleRoot)) {
            return false;
        }
        return Objects.equals(this.currentHash, other.currentHash);
    }

    @Override
    public int compareTo(Block o) {
        return this.currentHash.compareTo(o.currentHash);
    }
}
