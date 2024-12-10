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


package p2p;

import curriculum.vitae.core.Educacao;
import curriculum.vitae.core.Instituto;
import curriculum.vitae.core.Pessoa;
import curriculum.vitae.core.RegistoCertificado;
import curriculum.vitae.core.dadosInstitucionais;
import curriculum.vitae.core.dadosPessoais;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import utils.MerkleTree;

/**
 * Created on 27/11/2024, 17:42:15 
 * @author manso - computer
 */
public interface IremoteP2P extends Remote {
    //:::: N E T WO R K  :::::::::::
    public String getAdress() throws RemoteException;
    public void addNode(IremoteP2P node) throws RemoteException;
    public List<IremoteP2P> getNetwork() throws  RemoteException;
    //::::::::::: T R A NS A C T IO N S  :::::::::::
    public void addTransaction(String data) throws RemoteException;
    public List<String> getTransactions() throws RemoteException;
    public void removeTransaction(String data )throws RemoteException;
    public void sinchronizeTransactions(IremoteP2P node) throws RemoteException;
    
    //::::::::::: M E S S A G E S  :::::::::::
    public void addMessage(String msg) throws RemoteException;
    public List<String> getMessages() throws RemoteException;
    public void sinchronizeMessages(IremoteP2P node) throws RemoteException;
        
    //::::::::::: C L I E N T E:::::::::::
    public String getLastClient() throws RemoteException;
    
    public void registerClient(String host) throws RemoteException;
    
    //:::::::::::L O G I N::::::::P E S S O A:::::::::::
    
    public boolean verificaUtilizador(String email) throws RemoteException;
    
    public boolean verificaCamposUser(String email, String password) throws RemoteException;
    
    public Pessoa loginUser(String password) throws RemoteException;
    
    //:::::::::::R E G I S T O::::::::P E S S O A:::::::::::
    
    public Pessoa registerUser(String email, String password)throws RemoteException;
    
    public int verificaRegistoUser(String email, String password, String confPassword) throws RemoteException;
    
    public boolean verificaEmailRegisto(String email) throws RemoteException;
    
    //:::::::::::R E G I S T O::::::::I N S T I T U T O :::::::::::
    
    public Instituto registerInst(String codNome, String password)throws RemoteException;
    
    public int verificaRegistoInst(String codNome, String password, String confPassword) throws RemoteException;  
    
    public boolean verificaCodNome(String codNome) throws RemoteException; 
    
    //:::::::::::L O G I N::::::::I N S T I T U T O:::::::::::
    
    public Instituto loginInst(String password) throws RemoteException;
    
    public boolean verificaLoginInst(String codNome, String password) throws RemoteException;
    
    public boolean verificaCodNomeLogin(String codNome) throws RemoteException;
    
    //:::::::::::A D I C I O N A R::::D A D O S::::P E S S O A I S:::::::::::
    public Pessoa adicionaDadosPessoa(String email, dadosPessoais dadosP, ImageIcon icon) throws RemoteException;
    
    //:::::::::::A D I C I O N A R::::D A D O S::::I N S T I T U C I O N A I S:::::::::::
    public Instituto adicionaDadosInst(String codNome, dadosInstitucionais dadosInst, ImageIcon icon) throws RemoteException;
    
    //Devolve a lista de todas as Pessoas registadas no sistema
    public ArrayList<Pessoa> getPessoas () throws RemoteException;
    
    //Função que adiciona um certificado no sistema
    public void adicionarCertificado(Educacao educacao, String email, String codNome) throws RemoteException;

    //Função que devolve a lista de todos os certificados atribuídos a uma Pessoa
    public DefaultListModel getCertificadosPessoa(Pessoa user) throws RemoteException;
    
    //Função que devolve a lista de todos os certificados criados por uma Pessoa
    public DefaultListModel getCertificadosInst(Instituto inst) throws RemoteException;
    
    //Função que devolve a Blockchain
    public RegistoCertificado getBlockchain() throws RemoteException;
    
    //Função que retorna uma merkle tree
    public MerkleTree getTree(String hash) throws RemoteException;
    
    //Função que retorna a árvore em formato de texto
    public String treeToString() throws RemoteException;
}
