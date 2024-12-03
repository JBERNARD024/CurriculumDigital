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

import curriculum.vitae.core.Instituto;
import curriculum.vitae.core.Pessoa;
import curriculum.vitae.core.RegistoCertificado;
import curriculum.vitae.gui.Login;
import curriculum.vitae.gui.adicionarDadosPessoais;
import curriculum.vitae.gui.perfilUser;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import utils.Recursos;

/**
 *
 * @author Trabalho
 */

public class RemoteObject extends UnicastRemoteObject implements RemoteInterface {

    String host; // nome do servidor
    ArrayList<Pessoa> listUsers = new ArrayList<>();
    ArrayList<Instituto> listInst = new ArrayList<>();
    RegistoCertificado registoCerti = new RegistoCertificado();
    String basePath = new File("").getAbsolutePath();
    String pathUsers = basePath + "/resources/pessoas/users.user";
    String pathInst = basePath + "/resources/institutos/institutos.inst";
    String pathBlockchain = basePath + "/resources/blockchain/blockchain.bc";
    File fichUsers;
    File fichInst;
    File fichRegisto;
    Pessoa user;
    int indexUser;

    public RemoteObject(int port) throws RemoteException {
        super(port);
        try {
            //atualizar o nome do servidor
            host = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            host = "unknow";
        }
        Security.addProvider(new BouncyCastleProvider());
        fichUsers = new File(pathUsers);
        fichInst = new File(pathInst);
        fichRegisto = new File(pathBlockchain);
        //Define o array de utilizadores com base no ficheiro de utilizadores
        listUsers = (ArrayList<Pessoa>) Recursos.readObject(fichUsers.getAbsolutePath());
        //Define o array de institutos com base no ficheiro de institutos
        listInst = (ArrayList<Instituto>) Recursos.readObject(fichInst.getAbsolutePath());
        //Define o objeto RegistoCertificado com base no ficheiro RegistoCertificado
        registoCerti = (RegistoCertificado) Recursos.readObject(fichRegisto.getAbsolutePath());
    }

    @Override
    public String getMessage() throws RemoteException {
        String client = "";
        try {
            //nome do cliente
            client = RemoteServer.getClientHost();
            System.out.println("Message to " + client);
        } catch (ServerNotActiveException ex) {
            Logger.getLogger(RemoteObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        //retornar uma mensagem
        return host + " say Hello to " + client;
    }

    //################################################## U T I L I Z A D O R ################################################################
    //Dado o email e a password introduzida pela Pessoa, estabelece a sessão da Pessoa, caso sejam válidos
    /**
     *
     * @param email
     * @param password
     * @return
     * @throws java.rmi.RemoteException
     */
    @Override
    public Pessoa loginUser(String email, String password) throws RemoteException {
        //Retorna a Pessoa que efetuou sessão no sistema
        return user = new Pessoa(listUsers.get(indexUser));
    }

    @Override
    //Verifica se a password introduzida desencripta a chave privada de uma pessoa
    public boolean verificaCamposUser(String email, String password) {
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
    
        
    @Override
    public Pessoa registerUser(String email, String password){
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
            //A lista das pessoas é guardado num ficheiro
            Recursos.writeObject(listUsers, fichUsers.getAbsolutePath());
        } catch (Exception ex) {
            Logger.getLogger(RemoteObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Regista o user
        return user;
    }
    
    @Override
    public  boolean verificaRegistoUser(String email, String password, String confPassword){
        if (email.equals("") || password.equals("") || confPassword.equals("")) {
            JOptionPane.showConfirmDialog(null, "Um ou mais campos estão vazios", "Campos Vazios", 2);
            return false;
        } else if (!password.equals(confPassword)) {
            JOptionPane.showConfirmDialog(null, "As passwords não coincidem!!", "Passwords Diferentes", 2);
            return false;
        } else if (!email.contains("@")) {
            JOptionPane.showConfirmDialog(null, "Email inválido!!", "Email Incorreto", 2);
            return false;
        } else {
            return true;
        }
    }
    
    @Override
    public boolean verificaEmailRegisto(String email){
        boolean verifica = false;
        for (int i = 0; i < listUsers.size(); i++) {
            if (listUsers.get(i).getEmail().equals(email)) {
                verifica = true;
                JOptionPane.showConfirmDialog(null, "Email já está a ser utilizado!!", "Email Indisponível", 2);
                break;
            } else {
                verifica = false;
            }
        }
        return verifica;
    }

}
