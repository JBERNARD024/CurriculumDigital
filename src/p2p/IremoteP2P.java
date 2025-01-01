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

import curriculum.vitae.core.Certificado;
import curriculum.vitae.core.Instituto;
import curriculum.vitae.core.Pessoa;
import curriculum.vitae.core.dadosInstitucionais;
import curriculum.vitae.core.dadosPessoais;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import utils.Block;
import utils.BlockChain;
import utils.MerkleTree;

/**
 * Created on 27/11/2024, 17:42:15
 *
 * @author manso - computer
 */
public interface IremoteP2P extends Remote {

    //:::: N E T WO R K  :::::::::::-
    //Devolve o endereço de IP de um nó da rede
    public String getAdress() throws RemoteException;
    
    //Adiciona um novo nó à rede
    public void addNode(IremoteP2P node) throws RemoteException;
    
    //Retorna a lista de todos os nós da rede
    public List<IremoteP2P> getNetwork() throws RemoteException;

    //::::::::::: M E S S A G E S  :::::::::::
    
    //Adiciona uma nova mensagem
    public void addMessage(String msg) throws RemoteException;

    //Retorna a lista de todas as mensagens
    public List<String> getMessages() throws RemoteException;

    //Sincronia as mensagens entre todos os nós
    public void sinchronizeMessages(IremoteP2P node) throws RemoteException;

    //:::::::::::L O G I N::::::::P E S S O A:::::::::::
    
    //Verifica se a pessoa está registada no sistema
    public boolean verificaUtilizador(String email) throws RemoteException;

    //Verifica se a password introduzida desencripta a chave privada de uma pessoa
    public boolean verificaPasswordPessoa(String email, String password) throws RemoteException;

    //Efetua o login de uma Pessoa no sistema
    public Pessoa loginUser(String password) throws RemoteException;

    //Devolve uma Pessoa específica dado o seu email
    public Pessoa getPessoa(String email) throws RemoteException;

    //::::::::::: R E G I S T O :::::::: P E S S O A :::::::::::
    //Regista uma pessoa no sistema e guarda as suas chaves assimétricas numa pasta
    public void registerUser(String address, String email, String password) throws RemoteException;

    //Verifica se os todos os campos foram preenchidos e cumprem os requisitos
    public int verificaRegistoUser(String email, String password, String confPassword) throws RemoteException;

    //Verifica se os todos os campos foram preenchidos e cumprem os requisitos
    public boolean verificaEmailRegisto(String email) throws RemoteException;

    //::::::::::: R E G I S T O :::::::: I N S T I T U T O :::::::::::
    
    //Regista um Instituto no sistema e guarda o seu par de chaves num pasta
    public void registerInst(String address, String codNome, String password) throws RemoteException;

    //Verifica se os dados introduzidos correspondem aos requisitos definidos no sistema, relativamente ao Instituto
    public int verificaRegistoInst(String codNome, String password, String confPassword) throws RemoteException;

    //Verifica se o código nome do Instituto no sistema existe no sistema
    public boolean verificaCodNome(String codNome) throws RemoteException;

    //::::::::::: L O G I N :::::::: I N S T I T U T O :::::::::::
    //Realiza o login de Instituto no sistema, num dado nó da rede
    public Instituto loginInst(String password) throws RemoteException;

    //Realiza o login de Instituto no sistema, num dado nó da rede
    public boolean verificaPasswordInst(String codNome, String password) throws RemoteException;

    //Retorna um Instituto, dado o seu nome de código
    public Instituto getInstituto(String codNome) throws RemoteException;

    //:::::::::::A D I C I O N A R::::D A D O S::::P E S S O A I S:::::::::::
    //Adiciona os dados pessoais definidos pela Pessoa
    public Pessoa adicionaDadosPessoa(String email, dadosPessoais dadosP, ImageIcon icon) throws RemoteException;

    //:::::::::::A D I C I O N A R::::D A D O S::::I N S T I T U C I O N A I S:::::::::::
    //Adiciona os dados institucionais definidos pelo Instituto
    public Instituto adicionaDadosInst(String codNome, dadosInstitucionais dadosInst, ImageIcon icon) throws RemoteException;
    
    //Devolve a lista de todas as Pessoas registadas no sistema
    public List<Pessoa> getPessoas() throws RemoteException;

    //::::::::::: C E R T I F I C A D O S  :::::::::::
    //Devolve a lista de certificados
    public List<Certificado> getCertificados() throws RemoteException;
    
    //Devolve a lista temporária de certificados
    public List<Certificado> getTemp() throws RemoteException;
    
    //Remove os certificados num nó
    public void removeCertficados(List<Certificado> certificados) throws RemoteException;

    //Sincroniza os certificados num nó da rede
    public void sinchronizeCertificados(IremoteP2P node) throws RemoteException;

    //Função que adiciona um certificado no sistema
    public void adicionarCertificado(Certificado c) throws RemoteException;

    //Função que devolve a lista de todos os certificados atribuídos a uma Pessoa
    public DefaultListModel getCertificadosPessoa(Pessoa user) throws RemoteException;

    //Função que devolve a lista de todos os certificados criados por uma Pessoa
    public DefaultListModel getCertificadosInst(Instituto inst) throws RemoteException;

   //::::::::::::::::: M I N E R :::::::::::::::::::::::::::::::::::::::::::
    //Inicia o Miner para começar a minar um bloco, dado uma mensagem e respetiva dificuldade
    public void startMining(String msg, int zeros) throws RemoteException;

    //Acaba a atividade do Miner, quando é encontrado o nonce na rede
    public void stopMining(int nonce) throws RemoteException;

    //Retorna se o Miner está ou não a minar
    public boolean isMining() throws RemoteException;

    //Inicia a mineração da mensagem
    public int mine(String msg, int zeros) throws RemoteException;

    //::::::::::::::::: B L O C K C H A I N :::::::::::::::::::::::::::::::::::::::::::
    //Função que vai adicionar um bloco à blockchain
    public void addBlock(Block b) throws RemoteException;

    //Retorna o tamanho da blockchain
    public int getBlockchainSize() throws RemoteException;

    //Retorna, em formato de texto, o hash do último bloco
    public String getBlockchainLastHash() throws RemoteException;
    
    //Retorna a blockchain do sistema
    public BlockChain getBlockchain() throws RemoteException;

    //Sincroniza a blockchain pelos nós da rede
    public void synchnonizeBlockchain() throws RemoteException;
    
    //Devolve a lista de transações que estão associadas a um bloco
    public List<Object> getBlockchainTransactions()throws RemoteException;
    
    //Cria uma nova merkleTree a partir de um ficheiro, com um dado merkleRoot
    public MerkleTree getTree(String hash) throws RemoteException;
    
    //Escreve a merkleTree em formato de texto
    public String treeToString() throws RemoteException;
    
    /*Esta função vai apresentar a lista de todos os certificados, que já foram validados pela blockchain.
    De forma a confirmar, que os certificados estão válidos, juntamente com os dados do certificado,
    é apresentado também o Hash do bloco anterior, o nonce e o hash do bloco de que faz parte*/
    public String certificadosToString() throws RemoteException;
}
