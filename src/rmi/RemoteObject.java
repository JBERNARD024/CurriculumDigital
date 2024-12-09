//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: 
//::                                                                         ::
//::     Antonio Manuel Rodrigues Manso                                      ::
//::                                                                         ::
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
package rmi;

import curriculum.vitae.core.Certificado;
import curriculum.vitae.core.Educacao;
import curriculum.vitae.core.Instituto;
import curriculum.vitae.core.Pessoa;
import curriculum.vitae.core.RegistoCertificado;
import curriculum.vitae.core.dadosInstitucionais;
import curriculum.vitae.core.dadosPessoais;
import curriculum.vitae.gui.Login;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.security.Security;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import utils.Block;
import utils.Converter;
import utils.MerkleTree;
import utils.Recursos;

/**
 *
 * @author Trabalho
 */
public class RemoteObject extends UnicastRemoteObject implements RemoteInterface {

    MessengerInterface gui;
    String host; // nome do servidor
    ArrayList<Pessoa> listUsers = new ArrayList<>();
    ArrayList<Instituto> listInst = new ArrayList<>();
    RegistoCertificado registoCerti = new RegistoCertificado();
    String basePath = new File("").getAbsolutePath();
    String pathUsers = basePath + "/resources/pessoas/users.user";
    String pathInst = basePath + "/resources/institutos/institutos.inst";
    String pathBlockchain = basePath + "/resources/blockchain/blockchain.bc";
    Pessoa user;
    Instituto inst;
    MerkleTree tree;
    int indexUser;
    int indexInst;

    public RemoteObject(int port, MessengerInterface gui) throws RemoteException, UnknownHostException {
        super(port);
        this.gui = gui;
        try {
            //atualizar o nome do servidor
            host = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            host = "unknow";
        }
        Security.addProvider(new BouncyCastleProvider());
        //Define o array de utilizadores com base no ficheiro de utilizadores
        listUsers = (ArrayList<Pessoa>) Recursos.readObject(pathUsers);
        //Define o array de institutos com base no ficheiro de institutos
        listInst = (ArrayList<Instituto>) Recursos.readObject(pathInst);
        //Define o objeto RegistoCertificado com base no ficheiro RegistoCertificado
        registoCerti = (RegistoCertificado) Recursos.readObject(pathBlockchain);
    }

    @Override
    public String getMessage() throws RemoteException {
        String client = "";
        try {
            //nome do cliente
            client = RemoteServer.getClientHost();
        } catch (ServerNotActiveException ex) {
            Logger.getLogger(RemoteObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        //retornar uma mensagem
        return host + " ligou-se ao servidor  " + client;
    }

    //################################################## P E S S O A ################################################################
    //Dado o email e a password introduzida pela Pessoa, estabelece a sessão da Pessoa, caso sejam válidos
    /**
     *
     * @param password
     * @return
     * @throws java.rmi.RemoteException
     */
    //################################ LOGIN DE UMA PESSOA ################################
    @Override
    public Pessoa loginUser(String password) throws RemoteException {
        listUsers = (ArrayList<Pessoa>) Recursos.readObject(pathUsers);
        user = new Pessoa(listUsers.get(indexUser));
        //Atualiza a data do último login efetuado
        user.setLastLogin(Date.from(Instant.now()));
        //Incrementa o número de logins
        user.setNumLogin(user.getNumLogin() + 1);
        try {
            //Desencript a chave privada
            user.load(password);
        } catch (Exception ex) {
            Logger.getLogger(RemoteObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Retorna a Pessoa que efetuou sessão no sistema
        return user;
    }

    @Override
    //Verifica se a password introduzida desencripta a chave privada de uma pessoa
    public boolean verificaCamposUser(String email, String password) {
        listUsers = (ArrayList<Pessoa>) Recursos.readObject(pathUsers);
        boolean verifica = false;
        for (int i = 0; i < listUsers.size(); i++) {
            try {
                user = new Pessoa(email);
                if (user.load(password)) {
                    verifica = true;
                    break;
                } else {
                    verifica = false;
                }
            } catch (Exception ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return verifica;
    }

    @Override
    //Verifica se a pessoa está registada no sistema
    public boolean verificaUtilizador(String email) {
        listUsers = (ArrayList<Pessoa>) Recursos.readObject(pathUsers);
        boolean verifica = false;
        for (int i = 0; i < listUsers.size(); i++) {
            user = new Pessoa(listUsers.get(i));
            if (!user.getEmail().equals(email)) {
                verifica = false;
            } else {
                indexUser = i;
                verifica = true;
                break;
            }
        }
        return verifica;
    }

    //################################ REGISTAR UMA PESSOA ################################
    @Override
    //Regista uma pessoa no sistema e guarda as suas chaves assimétricas numa pasta
    public Pessoa registerUser(String email, String password) {
        listUsers = (ArrayList<Pessoa>) Recursos.readObject(pathUsers);
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
            Logger.getLogger(RemoteObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Regista o user
        return user;
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
        listInst = (ArrayList<Instituto>) Recursos.readObject(pathInst);
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

    //################################ REGISTAR UMA INSTITUIÇÃO ################################
    @Override
    public Instituto registerInst(String codNome, String password) {
        listInst = (ArrayList<Instituto>) Recursos.readObject(pathInst);
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
            Logger.getLogger(RemoteObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inst;
    }

    @Override
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
    public boolean verificaCodNome(String codNome) {
        listInst = (ArrayList<Instituto>) Recursos.readObject(pathInst);
        boolean verifica = false;
        for (int i = 0; i < listInst.size(); i++) {
            if (listInst.get(i).getCodNome().equals(codNome)) {
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
    public Instituto loginInst(String password) {
        listInst = (ArrayList<Instituto>) Recursos.readObject(pathInst);
        inst = new Instituto(listInst.get(indexInst));
        //Incrementa o número de logins
        inst.setNumLogin(inst.getNumLogin() + 1);
        //Atualiza a data do último login efetuado
        inst.setLastLogin(Date.from(Instant.now()));
        try {
            //Desencript a chave privada
            inst.load(password);
        } catch (Exception ex) {
            Logger.getLogger(RemoteObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inst;
    }

    @Override
    public boolean verificaLoginInst(String codNome, String password) {
        listInst = (ArrayList<Instituto>) Recursos.readObject(pathInst);
        boolean verifica = false;
        for (int i = 0; i < listInst.size(); i++) {
            try {
                inst = new Instituto(codNome);
                if (inst.load(password)) {
                    verifica = true;
                    break;
                } else {
                    verifica = false;
                }
            } catch (Exception ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return verifica;
    }

    @Override
    public boolean verificaCodNomeLogin(String codNome) {
        listInst = (ArrayList<Instituto>) Recursos.readObject(pathInst);
        boolean verifica = false;
        for (int i = 0; i < listInst.size(); i++) {
            inst = new Instituto(listInst.get(i));
            if (!inst.getCodNome().equals(codNome)) {
                verifica = false;
            } else {
                indexInst = i;
                verifica = true;
                break;
            }
        }
        return verifica;
    }

    @Override
    public Pessoa adicionaDadosPessoa(String email, dadosPessoais dadosP, ImageIcon icon) throws RemoteException {
        listUsers = (ArrayList<Pessoa>) Recursos.readObject(pathUsers);
        user = new Pessoa(getPessoa(email));
        if (icon == null) {
            try {
                //Caso não tenha sido adicionado, é atribuída uma imagem por defeito
                String caminhoImag = basePath + "/resources/pessoas/defaultPessoa.png";
                icon = new ImageIcon(caminhoImag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        byte[] byteIcon = null;
        try {
            byteIcon = Recursos.iconToByteArray(icon);
        } catch (IOException ex) {
            Logger.getLogger(RemoteObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Pessoa pessoa : listUsers) {
            if (user.getEmail().equals(pessoa.getEmail())) {
                pessoa.setImagem(byteIcon);
                pessoa.setDados(dadosP);
                user = new Pessoa(pessoa);
            }
        }
        Recursos.writeObject(listUsers, pathUsers);
        return user;
    }

    @Override
    public Instituto adicionaDadosInst(String codNome, dadosInstitucionais dadosInst, ImageIcon icon) throws RemoteException {
        listInst = (ArrayList<Instituto>) Recursos.readObject(pathInst);
        inst = new Instituto(getInstituto(codNome));
        if (icon == null) {
            try {
                //Caso não tenha sido adicionado, é atribuída uma imagem por defeito
                String caminhoImag = basePath + "/resources/institutos/defaultInstituto.png";
                icon = new ImageIcon(caminhoImag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        byte[] byteIcon = null;
        try {
            byteIcon = Recursos.iconToByteArray(icon);
        } catch (IOException ex) {
            Logger.getLogger(RemoteObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Instituto instituto : listInst) {
            if (inst.getCodNome().equals(instituto.getCodNome())) {
                instituto.setImagem(byteIcon);
                instituto.setDadosInst(dadosInst);
                inst = new Instituto(instituto);
            }
        }
        Recursos.writeObject(listInst, pathInst);
        return inst;
    }

    //Devolve a lista de todas as Pessoas registadas no sistema
    @Override
    public ArrayList<Pessoa> getPessoas() throws RemoteException {
        return (ArrayList<Pessoa>) Recursos.readObject(pathUsers);
    }

    @Override
    public void adicionarCertificado(Educacao educacao, String email, String codNome) throws RemoteException {
        try {
            user = new Pessoa(getPessoa(email));
            user.loadPublic();
            inst = new Instituto(getInstituto(codNome));
            inst.loadPublic();
            Certificado c = new Certificado(inst, user, educacao);
            registoCerti.add(c);
            Recursos.writeObject(registoCerti, pathBlockchain);
        } catch (Exception ex) {
            Logger.getLogger(RemoteObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Função que devolve a lista de todos os certificados atribuídos a uma Pessoa
    @Override
    public DefaultListModel getCertificadosPessoa(Pessoa user) throws RemoteException {
        DefaultListModel myCertificados = new DefaultListModel();
        for (Block b : registoCerti.getBc().getChain()) {
            //Carrega o ficheiro da merkle cujo root é igual aos dados do bloco
            tree = (MerkleTree) Recursos.readObject(basePath + "/resources/merkleTree/" + b.getCurrentHash() + ".mk");
            //Iteração da lista dos certificados emitidos
            for (int i = 0; i < registoCerti.getRegisto().size(); i++) {
                String cert = registoCerti.getRegisto().get(i);
                List<String> proof = tree.getProof(cert);
                boolean isProofValid = tree.isProofValid(cert, proof);
                //Vai verificar se o certificado presente na lista pertence à árvore de merkle, através da prova
                if (isProofValid) {
                    //Se pertencer à árvore, vamos verificar se o certificado pertence à Pessoa com sessão inciada
                    Certificado c = (Certificado) Converter.hexToObject(registoCerti.getRegisto().get(i));
                    if (c.getGraduado().getEmail().equals(user.getEmail())) {
                        //Se foi associada à Pessoa com sessão iniciada, é adicionado à lista dos seus certificados
                        myCertificados.addElement(c);
                    }
                }
            }
        }
        return myCertificados;
    }

    //Função que devolve a lista de todos os certificados criados por uma Pessoa
    @Override
    public DefaultListModel getCertificadosInst(Instituto inst) throws RemoteException {
        DefaultListModel myCertificados = new DefaultListModel();
        for (Block b : registoCerti.getBc().getChain()) {
            //Carrega o ficheiro da merkle cujo root é igual aos dados do bloco
            tree = (MerkleTree) Recursos.readObject(basePath + "/resources/merkleTree/" + b.getCurrentHash() + ".mk");
            //Iteração da lista dos certificados emitidos
            for (int i = 0; i < registoCerti.getRegisto().size(); i++) {
                String cert = registoCerti.getRegisto().get(i);
                List<String> proof = tree.getProof(cert);
                boolean isProofValid = tree.isProofValid(cert, proof);
                //Vai verificar se o certificado presente na lista pertence à árvore de merkle, através da prova
                if (isProofValid) {
                    //Se pertencer à árvore, vamos verificar se o certificado foi emitido pelo Instituto com sessão inciada
                    Certificado c = (Certificado) Converter.hexToObject(registoCerti.getRegisto().get(i));
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
    public RegistoCertificado getBlockchain() throws RemoteException {
        return (RegistoCertificado) Recursos.readObject(pathBlockchain);
    }
    
    @Override
    public MerkleTree getTree(String hash) throws RemoteException{
        return (MerkleTree) Recursos.readObject(basePath + "/resources/merkleTree/" + hash + ".mk");
    }
    
    @Override
    public String treeToString() {
        StringBuilder txt = new StringBuilder();
        txt.append("Registo de Certificados = ")
            .append(registoCerti.getRegisto().size())
            .append("\n\n");
        for (Block b : registoCerti.getBc().getChain()) {
            try {
                tree = getTree(b.getCurrentHash());
            } catch (RemoteException ex) {
                Logger.getLogger(RemoteObject.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int i = 0; i < registoCerti.getRegisto().size(); i++) {
                String cert = registoCerti.getRegisto().get(i);
                List<String> proof = tree.getProof(cert);
                boolean isProofValid = tree.isProofValid(cert, proof);
                if (isProofValid) {
                    Certificado c = (Certificado) Converter.hexToObject((String) registoCerti.getRegisto().get(i));
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
    
    @Override
    public void publish(String msg) throws RemoteException{
        gui.publish(msg);
    }

    private Pessoa getPessoa(String email) throws RemoteException {
        for (Pessoa pessoa : listUsers) {
            if (pessoa.getEmail().equals(email)) {
                return pessoa;
            }
        }
        return null;
    }

    private Instituto getInstituto(String codNome) throws RemoteException {
        for (Instituto instituto : listInst) {
            if (instituto.getCodNome().equals(codNome)) {
                return instituto;
            }
        }
        return null;
    }
}

