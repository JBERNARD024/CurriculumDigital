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
//::                                                               (c)2024   ::
//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//////////////////////////////////////////////////////////////////////////////
package p2p;

import curriculum.vitae.core.Certificado;
import curriculum.vitae.core.Instituto;
import curriculum.vitae.core.Pessoa;
import curriculum.vitae.core.dadosInstitucionais;
import curriculum.vitae.core.dadosPessoais;
import curriculum.vitae.gui.Login;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.Security;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import miner.Miner;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import utils.Block;
import utils.BlockChain;
import utils.MerkleTree;
import utils.RMI;
import utils.Recursos;

/**
 * Created on 27/11/2024, 17:48:32
 *
 * @author manso - computer
 */
public class OremoteP2P extends UnicastRemoteObject implements IremoteP2P {

    String address;
    CopyOnWriteArrayList<IremoteP2P> network;
    CopyOnWriteArrayList<Certificado> certificados;
    CopyOnWriteArrayList<Certificado> temp;
    CopyOnWriteArrayList<String> messages;
    P2Plistener listener;
    CopyOnWriteArrayList<Pessoa> listUsers;
    CopyOnWriteArrayList<Instituto> listInst;
    //objeto da blockchain preparada para cesso concorrente
    BlockChain blockchain;
    //objeto mineiro concorrente e distribuido
    Miner myMiner;
    String basePath = new File("").getAbsolutePath();
    String pathUsers = basePath + "\\resources\\pessoas\\users.user";
    String pathInst = basePath + "\\resources\\institutos\\institutos.inst";
    String pathBlockchain = basePath + "\\resources\\blockchain\\blockchain.bc";
    String pathCertificados = basePath + "\\resources\\certificados\\certificados.cert";
    String pathTemp = basePath + "\\resources\\certificados\\temp.cert";
    MerkleTree tree;
    Pessoa user;
    Instituto inst;
    int indexUser;
    int indexInst;

    public OremoteP2P(String address, P2Plistener listener) throws RemoteException, Exception {
        super(RMI.getAdressPort(address));
        this.address = address;
        this.network = new CopyOnWriteArrayList<>();
        this.certificados = new CopyOnWriteArrayList<>();
        this.messages = new CopyOnWriteArrayList<>();
        this.listUsers = new CopyOnWriteArrayList<>();
        this.listInst = new CopyOnWriteArrayList<>();
        this.temp = new CopyOnWriteArrayList<>();
        this.blockchain = new BlockChain();
        this.myMiner = new Miner(listener);
        this.tree = new MerkleTree();
        Security.addProvider(new BouncyCastleProvider());
        certificados = (CopyOnWriteArrayList<Certificado>) Recursos.readObject(pathCertificados);
        temp = (CopyOnWriteArrayList<Certificado>) Recursos.readObject(pathTemp);
        try {
            blockchain.load(pathBlockchain);
        } catch (Exception ex) {
            Logger.getLogger(OremoteP2P.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.listener = listener;
        listener.onStart("Object " + address + " listening");
    }

    @Override
    //Devolve o endereço de IP de um nó da rede
    public String getAdress() throws RemoteException {
        return address;
    }

    //Verifica se o endereço de IP, já está na rede
    private boolean isInNetwork(String adress) {
        for (int i = network.size() - 1; i >= 0; i--) {
            try {
                if (network.get(i).getAdress().equals(adress)) {
                    return true;
                }
            } catch (RemoteException ex) {
                //Remove os nós que não respondem
                network.remove(i);
            }
        }
        return false;
    }

    @Override
    //Adiciona um novo nó à rede
    public void addNode(IremoteP2P node) throws RemoteException {
        //se já tiver o nó 
        //não faz nada
        if (isInNetwork(node.getAdress())) {
            System.out.println("Já tenho o endereço " + node.getAdress());
            return;
        }

        listener.onMessage("Network addNode ");
        //adicionar o nó à rede
        network.add(node);
        listener.onConect(node.getAdress());
        System.out.println("Adicionei o " + node.getAdress());
        node.addNode(this);

        //propagar o nó pela rede
        for (IremoteP2P iremoteP2P : network) {
            iremoteP2P.addNode(node);
        }

        //Sincroniza os Certificados
        sinchronizeCertificados(node);
        //Sincroniza as Mensagens
        sinchronizeMessages(node);
        //Sincroniza a blockchain
        synchnonizeBlockchain();

        System.out.println("Rede p2p");
        for (IremoteP2P iremoteP2P : network) {
            System.out.println(iremoteP2P.getAdress());
        }
    }

    @Override
    //Retorna a lista de todos os nós da rede
    public List<IremoteP2P> getNetwork() throws RemoteException {
        return new ArrayList<>(network);
    }

    @Override
    //Adiciona uma nova mensagem
    public void addMessage(String msg) throws RemoteException {
        if (messages.contains(msg)) {
            listener.onMessage(msg);
            return;
        }
        messages.add(msg);
        for (IremoteP2P iremoteP2P : network) {
            iremoteP2P.addMessage(msg);
        }

        System.out.println("Messages");
        for (String m : messages) {
            System.out.println(m);
        }
    }

    @Override
    //Retorna a lista de todas as mensagens
    public List<String> getMessages() throws RemoteException {
        return new ArrayList<>(messages);
    }

    @Override
    //Sincronia as mensagens entre todos os nós
    public void sinchronizeMessages(IremoteP2P node) throws RemoteException {
        this.messages.addAll(node.getMessages());
        listener.onMessage(address);
    }

    //################################################## P E S S O A ################################################################
    //################################ LOGIN DE UMA PESSOA ################################
    @Override
    //Efetua o login de uma Pessoa no sistema
    public Pessoa loginUser(String password) throws RemoteException {
        listUsers = (CopyOnWriteArrayList<Pessoa>) Recursos.readObject(pathUsers);
        user = new Pessoa(listUsers.get(indexUser));
        //Atualiza a data do último login efetuado
        user.setLastLogin(Date.from(Instant.now()));
        //Incrementa o número de logins
        user.setNumLogin(user.getNumLogin() + 1);
        try {
            //Desencript a chave privada
            user.load(password);
        } catch (Exception ex) {
            Logger.getLogger(OremoteP2P.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Retorna a Pessoa que efetuou sessão no sistema
        return user;
    }

    @Override
    //Verifica se a password introduzida desencripta a chave privada de uma pessoa
    public boolean verificaPasswordPessoa(String email, String password) {
        listUsers = (CopyOnWriteArrayList<Pessoa>) Recursos.readObject(pathUsers);
        boolean verifica = false;
        try {
            user = new Pessoa(email);
            verifica = user.load(password);
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return verifica;
    }

    @Override
    //Verifica se a pessoa está registada no sistema
    public boolean verificaUtilizador(String email) {
        listUsers = (CopyOnWriteArrayList<Pessoa>) Recursos.readObject(pathUsers);
        boolean verifica = false;
        for (int i = 0; i < listUsers.size(); i++) {
            if (listUsers.get(i).getEmail().equals(email)) {
                indexUser = i;
                verifica = true;
                break;
            } else {
                verifica = false;
            }
        }
        return verifica;
    }

    //################################ REGISTAR UMA PESSOA ################################
    @Override
    //Regista uma pessoa no sistema e guarda as suas chaves assimétricas numa pasta
    public void registerUser(String address, String email, String password) throws RemoteException {
        if (verificaUtilizador(email)) {
            return;
        }
        user = new Pessoa(email);
        try {
            //É criada um pasta do utilizador Pessoa
            user.criarPasta();
            //O par de chaves asssimétricas é criado
            user.generateKeys();
            //O par de chaves assimétricas é guardado na pasta da Pessoa e a chave privada é encriptada com a password
            user.save(password);
            //A pessoa é adicionada ao sistema
            listUsers.add(user);
            //A pessoa é guardada no ficheiro da lista das pessoas
            Recursos.writeObject(listUsers, pathUsers);
        } catch (Exception ex) {
            Logger.getLogger(OremoteP2P.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (IremoteP2P iremoteP2P : network) {
            iremoteP2P.registerUser(address, email, password);
        }
    }

    @Override
    //Verifica se os todos os campos foram preenchidos e cumprem os requisitos
    public int verificaRegistoUser(String email, String password, String confPassword) {
        if (email.equals("") || password.equals("") || confPassword.equals("")) {
            JOptionPane.showConfirmDialog(null, "Um ou mais campos estão vazios", "Campos Vazios", 2);
            return 1;
        } else if (!password.equals(confPassword)) {
            JOptionPane.showConfirmDialog(null, "As passwords não coincidem!!", "Passwords Diferentes", 2);
            return 2;
        } else if (!email.contains("@")) {
            JOptionPane.showConfirmDialog(null, "Email inválido!!", "Email Incorreto", 2);
            return 3;
        } else {
            return 4;
        }
    }

    @Override
    //Verifica se o email introduzido no registo, está disponível ou já está em uso
    public boolean verificaEmailRegisto(String email) {
        listUsers = (CopyOnWriteArrayList<Pessoa>) Recursos.readObject(pathUsers);
        boolean verifica = false;
        for (int i = 0; i < listUsers.size(); i++) {
            if (listUsers.get(i).getEmail().equals(email)) {
                verifica = true;
                break;
            } else {
                verifica = false;
            }
        }
        return verifica;
    }

    //################################ ADICIONAR DADOS PESSOAIS ################################
    //Adiciona os dados pessoais definidos pela Pessoa
    @Override
    public Pessoa adicionaDadosPessoa(String email, dadosPessoais dadosP, ImageIcon icon) throws RemoteException {
        listUsers = (CopyOnWriteArrayList<Pessoa>) Recursos.readObject(pathUsers);
        user = new Pessoa(getPessoa(email));
        if (user.getDados() != null) {
            return user;
        }
        if (icon == null) {
            try {
                //Caso não tenha sido adicionado, é atribuída uma imagem por defeito
                String caminhoImag = basePath + "\\resources\\pessoas\\defaultPessoa.png";
                icon = new ImageIcon(caminhoImag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        byte[] byteIcon = null;
        try {
            byteIcon = Recursos.iconToByteArray(icon);
        } catch (IOException ex) {
            Logger.getLogger(OremoteP2P.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Vai procurar a Pessoa na lista de Pessoas e definir os dados introduzidos
        for (Pessoa pessoa : listUsers) {
            if (user.getEmail().equals(pessoa.getEmail())) {
                pessoa.setImagem(byteIcon);
                pessoa.setDados(dadosP);
                user = new Pessoa(pessoa);
            }
        }

        //Guarda as alterações realizadas na Pessoa na lista de Pessoas
        Recursos.writeObject(listUsers, pathUsers);

        //Propaga os dados definidos pelos nós da rede
        for (IremoteP2P iremoteP2P : network) {
            iremoteP2P.adicionaDadosPessoa(email, dadosP, icon);
        }
        return user;
    }

    //Devolve a lista de todas as Pessoas registadas no sistema
    @Override
    public List<Pessoa> getPessoas() throws RemoteException {
        listUsers = (CopyOnWriteArrayList<Pessoa>) Recursos.readObject(pathUsers);
        return new ArrayList<>(listUsers);
    }

    //Devolve uma Pessoa específica dado o seu email
    @Override
    public Pessoa getPessoa(String email) throws RemoteException {
        listUsers = (CopyOnWriteArrayList<Pessoa>) Recursos.readObject(pathUsers);
        for (Pessoa pessoa : listUsers) {
            if (pessoa.getEmail().equals(email)) {
                return pessoa;
            }
        }
        return null;
    }

    //################################################## I N S T I T U T O ################################################################
    //################################ REGISTAR UMA INSTITUTO ################################
    @Override
    //Regista um Instituto no sistema e guarda o seu par de chaves num pasta
    public void registerInst(String address, String codNome, String password) throws RemoteException {
        if (verificaCodNome(codNome)) {
            return;
        }
        inst = new Instituto(codNome);
        try {
            //É criada um pasta do utilizador Instituto
            inst.criarPasta();
            //O par de chaves asssimétricas é criado
            inst.generateKeys();
            //O par de chaves assimétricas é guardado na pasta da Instituto e a chave privada é encriptada com a password
            inst.save(password);
            //O Instituto é adicionado ao sistema
            listInst.add(inst);
            //O Instituto é guardado no ficheiro da lista de Institutos
            Recursos.writeObject(listInst, pathInst);
        } catch (Exception ex) {
            Logger.getLogger(OremoteP2P.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Propaga o Instituto criado e os dados pela rede
        for (IremoteP2P iremoteP2P : network) {
            iremoteP2P.registerInst(address, codNome, password);
        }
    }

    @Override
    //Verifica se os dados introduzidos correspondem aos requisitos definidos no sistema, relativamente ao Instituto
    public int verificaRegistoInst(String codNome, String password, String confPassword) {
        if (codNome.equals("") || password.equals("") || confPassword.equals("")) {
            JOptionPane.showConfirmDialog(null, "Um ou mais campos estão vazios", "Campos Vazios", 2);
            return 1;
        } else if (!password.equals(confPassword)) {
            JOptionPane.showConfirmDialog(null, "As passwords não coincidem!!", "Passwords Diferentes", 2);
            return 2;
        } else {
            return 3;
        }
    }

    @Override
    //Verifica se o código nome do Instituto no sistema existe no sistema
    public boolean verificaCodNome(String codNome) {
        listInst = (CopyOnWriteArrayList<Instituto>) Recursos.readObject(pathInst);
        boolean verifica = false;
        for (int i = 0; i < listInst.size(); i++) {
            if (listInst.get(i).getCodNome().equals(codNome)) {
                indexInst = i;
                verifica = true;
                break;
            } else {
                verifica = false;
            }
        }
        return verifica;
    }

    //################################ LOGIN DE UMA INSTITUIÇÃO ################################
    @Override
    //Realiza o login de Instituto no sistema, num dado nó da rede
    public Instituto loginInst(String password) {
        listInst = (CopyOnWriteArrayList<Instituto>) Recursos.readObject(pathInst);
        inst = new Instituto(listInst.get(indexInst));
        //Incrementa o número de logins
        inst.setNumLogin(inst.getNumLogin() + 1);
        //Atualiza a data do último login efetuado
        inst.setLastLogin(Date.from(Instant.now()));
        try {
            //Desencript a chave privada
            inst.load(password);
        } catch (Exception ex) {
            Logger.getLogger(OremoteP2P.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inst;
    }

    @Override
    //Realiza o login de Instituto no sistema, num dado nó da rede
    public boolean verificaPasswordInst(String codNome, String password) {
        listInst = (CopyOnWriteArrayList<Instituto>) Recursos.readObject(pathInst);
        boolean verifica = false;
        try {
            inst = new Instituto(codNome);
            verifica = inst.load(password);
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return verifica;
    }

    //################################ ADICIONAR DADOS INSTITUCIONAIS ################################
    //Adiciona os dados institucionais definidos pelo Instituto
    @Override
    public Instituto adicionaDadosInst(String codNome, dadosInstitucionais dadosInst, ImageIcon icon) throws RemoteException {
        listInst = (CopyOnWriteArrayList<Instituto>) Recursos.readObject(pathInst);
        inst = new Instituto(getInstituto(codNome));

        if (inst.getDadosInst() != null) {
            return inst;
        }

        if (icon == null) {
            try {
                //Caso não tenha sido adicionado, é atribuída uma imagem por defeito
                String caminhoImag = basePath + "\\resources\\institutos\\defaultInstituto.png";
                icon = new ImageIcon(caminhoImag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        byte[] byteIcon = null;
        try {
            byteIcon = Recursos.iconToByteArray(icon);
        } catch (IOException ex) {
            Logger.getLogger(OremoteP2P.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Procura na lista de Institutos e aplica as alterações num dado instituto
        for (Instituto instituto : listInst) {
            if (inst.getCodNome().equals(instituto.getCodNome())) {
                instituto.setImagem(byteIcon);
                instituto.setDadosInst(dadosInst);
                inst = new Instituto(instituto);
            }
        }

        //Guarda as alterações efetuadas no ficheiro da lista de Institutos
        Recursos.writeObject(listInst, pathInst);

        //Propaga as alterações realizadas pelos nós da rede
        for (IremoteP2P iremoteP2P : network) {
            iremoteP2P.adicionaDadosInst(codNome, dadosInst, icon);
        }

        return inst;
    }

    @Override
    //Retorna um Instituto, dado o seu nome de código
    public Instituto getInstituto(String codNome) throws RemoteException {
        listInst = (CopyOnWriteArrayList<Instituto>) Recursos.readObject(pathInst);
        for (Instituto instituto : listInst) {
            if (instituto.getCodNome().equals(codNome)) {
                return instituto;
            }
        }
        return null;
    }

    //####################################### C E R T I F I C A D O ####################################################
    @Override
    //Adiciona um certificado no sistema e propaga-o pelos nós da rede
    public void adicionarCertificado(Certificado c) throws RemoteException {
        certificados = (CopyOnWriteArrayList<Certificado>) Recursos.readObject(pathCertificados);
        //Verifica se o certificado existe na sistema
        if (certificados.contains(c)) {
            listener.onTransaction("Certificado repetido " + c.toString());
            return;
        }

        //Senão não existir o certficado no sistema, é adicionado à lista de certificados e à lista temporária
        if (!certificados.contains(c)) {
            temp.add(c);
            certificados.add(c);
            //As alterações às listas são guardadas nos respetivos ficheiros
            Recursos.writeObject(certificados, pathCertificados);
            Recursos.writeObject(temp, pathTemp);
        }

        //Propaga o nó pela rede
        for (IremoteP2P iremoteP2P : network) {
            iremoteP2P.adicionarCertificado(c);
        }
    }

    @Override
    //Sincroniza os certificados num nó da rede
    public void sinchronizeCertificados(IremoteP2P node) throws RemoteException {
        this.certificados.addAll(node.getCertificados());
        listener.onTransaction(address);
    }

    @Override
    //Devolve a lista de certificados
    public List<Certificado> getCertificados() throws RemoteException {
        certificados = (CopyOnWriteArrayList<Certificado>) Recursos.readObject(pathCertificados);
        return new ArrayList<>(certificados);
    }

    @Override
    //Devolve a lista temporária de certificados
    public List<Certificado> getTemp() throws RemoteException {
        temp = (CopyOnWriteArrayList<Certificado>) Recursos.readObject(pathTemp);
        return new ArrayList<>(temp);
    }

    @Override
    //Remove os certificados num nó
    public void removeCertficados(List<Certificado> myCertificados) throws RemoteException {
        //remover as transações da lista atural
        certificados.removeAll(myCertificados);
        listener.onTransaction("remove " + myCertificados.size() + "transactions");
        //propagar as remoções
        for (IremoteP2P iremoteP2P : network) {
            //se houver algum elemento em comum nas transações remotas
            if (iremoteP2P.getCertificados().retainAll(certificados)) {
                //remover as transaçoies
                iremoteP2P.removeCertficados(myCertificados);
            }
        }
    }

    //Função que devolve a lista de todos os certificados atribuídos a uma Pessoa
    @Override
    public DefaultListModel getCertificadosPessoa(Pessoa user) throws RemoteException {
        try {
            certificados = (CopyOnWriteArrayList<Certificado>) Recursos.readObject(pathCertificados);
            blockchain = new BlockChain(pathBlockchain);
        } catch (Exception ex) {
            Logger.getLogger(OremoteP2P.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultListModel myCertificados = new DefaultListModel();
        for (Block b : blockchain.getChain()) {
            //Carrega o ficheiro da merkle cujo root é igual aos dados do bloco
            tree = (MerkleTree) Recursos.readObject(basePath + "\\resources\\merkleTree\\" + b.getMerkleRoot() + ".mkt");
            //Iteração da lista dos certificados emitidos
            for (int i = 0; i < certificados.size(); i++) {
                Certificado cert = certificados.get(i);
                List<String> proof = tree.getProof(cert);
                boolean isProofValid = tree.isProofValid(cert, proof);
                //Vai verificar se o certificado presente na lista pertence à árvore de merkle, através da prova
                if (isProofValid) {
                    //Se pertencer à árvore, vamos verificar se o certificado pertence à Pessoa com sessão inciada
                    Certificado c = certificados.get(i);
                    if (c.getGraduado().getEmail().equals(user.getEmail())) {
                        //Se foi associada à Pessoa com sessão iniciada, é adicionado à lista dos seus certificados
                        myCertificados.addElement(c);
                    }
                }
            }
        }
        return myCertificados;
    }

    //Função que devolve a lista de todos os certificados criados por um Instituto
    @Override
    public DefaultListModel getCertificadosInst(Instituto inst) throws RemoteException {
        try {
            certificados = (CopyOnWriteArrayList<Certificado>) Recursos.readObject(pathCertificados);
            blockchain = new BlockChain(pathBlockchain);
        } catch (Exception ex) {
            Logger.getLogger(OremoteP2P.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultListModel myCertificados = new DefaultListModel();
        for (Block b : blockchain.getChain()) {
            //Carrega o ficheiro da merkle cujo root é igual aos dados do bloco
            tree = (MerkleTree) Recursos.readObject(basePath + "\\resources\\merkleTree\\" + b.getMerkleRoot() + ".mkt");
            //Iteração da lista dos certificados emitidos
            for (int i = 0; i < certificados.size(); i++) {
                Certificado cert = certificados.get(i);
                List<String> proof = tree.getProof(cert);
                boolean isProofValid = MerkleTree.isProofValid(cert, proof);
                //Vai verificar se o certificado presente na lista pertence à árvore de merkle, através da prova
                if (isProofValid) {
                    //Se pertencer à árvore, vamos verificar se o certificado foi emitido pelo Instituto com sessão inciada
                    Certificado c = certificados.get(i);
                    if (c.getInstituto().getCodNome().equals(inst.getCodNome())) {
                        //Se foi o Instituto o emissor, é adicionado à lista dos seus certificados
                        myCertificados.addElement(c);
                    }
                }
            }
        }
        return myCertificados;
    }

    @Override
    //Cria uma nova merkleTree a partir de um ficheiro, com um dado merkleRoot
    public MerkleTree getTree(String hash) throws RemoteException {
        try {
            tree.loadFromFile(basePath + "\\resources\\merkleTree\\" + hash + ".mkt");
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(OremoteP2P.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tree;
    }

    @Override
    //Escreve a merkleTree em formato de texto
    public String treeToString() throws RemoteException {
        try {
            blockchain.load(pathBlockchain);
        } catch (Exception ex) {
            Logger.getLogger(OremoteP2P.class.getName()).log(Level.SEVERE, null, ex);
        }
        StringBuilder txt = new StringBuilder();
        txt.append("Registo de Certificados = ")
                .append(certificados.size())
                .append("\n\n");
        for (Block b : blockchain.getChain()) {
            try {
                tree = getTree(b.getMerkleRoot());
            } catch (RemoteException ex) {
                Logger.getLogger(OremoteP2P.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int i = 0; i < certificados.size(); i++) {
                Certificado cert = certificados.get(i);
                List<String> proof = tree.getProof(cert);
                boolean isProofValid = tree.isProofValid(cert, proof);
                if (isProofValid) {
                    Certificado c = certificados.get(i);
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

    /*Esta função vai apresentar a lista de todos os certificados, que já foram validados pela blockchain.
    De forma a confirmar, queos certificados estão válidos, juntamente com os dados do certificado,
    é apresentado também o Hash fdo bloco anterior, o nonce e o hash do bloco de que faz parte*/
    @Override
    public String certificadosToString() throws RemoteException {
        StringBuilder txt = new StringBuilder();
        txt.append("Registo de Certificados = ")
                .append(certificados.size())
                .append("\n\n");
        for (Block b : blockchain.getChain()) {
            try {
                tree = MerkleTree.loadFromFile(basePath + "\\resources\\merkleTree\\" + b.getMerkleRoot() + ".mkt");
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(OremoteP2P.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int i = 0; i < certificados.size(); i++) {
                Certificado cert = certificados.get(i);
                List<String> proof = tree.getProof(cert);
                boolean isProofValid = tree.isProofValid(cert, proof);
                if (isProofValid) {
                    Certificado c = certificados.get(i);
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

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    //:::::::::::::::::      M I N E R   :::::::::::::::::::::::::::::::::::::::
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    ////////////////////////////////////////////////////////////////////////////
    @Override
    //Inicia o Miner para começar a minar um bloco, dado uma mensagem e respetiva dificuldade
    public void startMining(String msg, int zeros) throws RemoteException {
        try {
            //colocar a mineiro a minar
            myMiner.startMining(msg, zeros);
            listener.onStartMining(msg, zeros);
            //mandar minar a rede
            for (IremoteP2P iremoteP2P : network) {
                //se o nodo nao estiver a minar
                if (!iremoteP2P.isMining()) {
                    listener.onStartMining(iremoteP2P.getAdress() + " mining", zeros);
                    //iniciar a mineracao no nodo
                    iremoteP2P.startMining(msg, zeros);
                }
            }
        } catch (Exception ex) {
            listener.onException(ex, "startMining");
        }

    }

    @Override
    //Acaba a atividade do Miner, quando é encontrado o nonce na rede
    public void stopMining(int nonce) throws RemoteException {
        //parar o mineiro e distribuir o nonce
        myMiner.stopMining(nonce);
        //mandar parar a rede
        for (IremoteP2P iremoteP2P : network) {
            //se o nodo estiver a minar   
            if (iremoteP2P.isMining()) {
                //parar a mineração no nodo 
                iremoteP2P.stopMining(nonce);
            }
        }
    }

    @Override
    //Inicia a mineração da mensagem
    public int mine(String msg, int zeros) throws RemoteException {
        try {
            //começar a minar a mensagem
            startMining(msg, zeros);
            //esperar que o nonce seja calculado
            return myMiner.waitToNonce();
        } catch (InterruptedException ex) {
            listener.onException(ex, "Mine");
            return -1;
        }

    }

    @Override
    //Retorna se o Miner está ou não a minar
    public boolean isMining() throws RemoteException {
        return myMiner.isMining();
    }

    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    //::::::::::::::::: B L O C K C H A I N :::::::::::::::::::::::::::::::::::::::
    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    //////////////////////////////////////////////////////////////////////////////
    @Override
    //Função que vai adicionar um bloco à blockchain
    public void addBlock(Block b) throws RemoteException {
        try {
            //se não for válido
            if (!b.isValid()) {
                throw new RemoteException("invalid block");
            }
            //se encaixar adicionar o bloco
            if (blockchain.getLastBlockHash().equals(b.getPreviousHash())) {
                blockchain.add(b);
                //guardar a blockchain
                blockchain.save(pathBlockchain);

                temp = (CopyOnWriteArrayList<Certificado>) Recursos.readObject(pathTemp);
                MerkleTree mkt = new MerkleTree(temp);
                //Guarda a merkleTree num ficheiro
                mkt.saveToFile(new File("").getAbsolutePath() + "\\resources\\merkleTree\\" + mkt.getRoot() + ".mkt");
                //Limpa a lista temporária de certificados
                temp.clear();
                //Atualiza a blockchain na rede
                listener.onBlockchainUpdate(blockchain);
                //Guarda a lista temporária
                Recursos.writeObject(temp, pathTemp);
            }
            //propagar o bloco pela rede
            for (IremoteP2P iremoteP2P : network) {
                //se encaixar na blockcahin dos nodos remotos
                if (!iremoteP2P.getBlockchainLastHash().equals(b.getPreviousHash())
                        || //ou o tamanho da remota for menor
                        iremoteP2P.getBlockchainSize() < blockchain.getSize()) {
                    //adicionar o bloco ao nodo remoto
                    iremoteP2P.addBlock(b);
                }
            }
            //se não encaixou)
            if (!blockchain.getLastBlockHash().equals(b.getCurrentHash())) {
                //sincronizar a blockchain
                synchnonizeBlockchain();
            }
        } catch (Exception ex) {
            listener.onException(ex, "Add bloco " + b);
        }
    }

    @Override
    //Retorna o tamanho da blockchain
    public int getBlockchainSize() throws RemoteException {
        return blockchain.getSize();
    }

    @Override
    //Retorna, em formato de texto, o hash do último bloco
    public String getBlockchainLastHash() throws RemoteException {
        return blockchain.getLastBlockHash();
    }

    @Override
    //Retorna a blockchain do sistema
    public BlockChain getBlockchain() throws RemoteException {
        return blockchain;
    }

    @Override
    //Sincroniza a blockchain pelos nós da rede
    public void synchnonizeBlockchain() throws RemoteException {
        //para todos os nodos da rede
        for (IremoteP2P iremoteP2P : network) {
            //se a blockchain for maior
            if (iremoteP2P.getBlockchainSize() > blockchain.getSize()) {
                BlockChain remote = iremoteP2P.getBlockchain();
                //e a blockchain for válida
                if (remote.isValid()) {
                    //atualizar toda a blockchain
                    blockchain = remote;
                    //deveria sincronizar apenas os blocos que faltam
                    listener.onBlockchainUpdate(getBlockchain());
                }
            }
        }
    }

    @Override
    //Devolve a lista de transações que estão associadas a um bloco
    public ArrayList<Object> getBlockchainTransactions() throws RemoteException {
        ArrayList<Object> allTransactions = new ArrayList<>();
        for (Block b : blockchain.getChain()) {
            try {
                allTransactions.addAll(b.certificados());
            } catch (Exception ex) {
                Logger.getLogger(OremoteP2P.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return allTransactions;
    }
}
