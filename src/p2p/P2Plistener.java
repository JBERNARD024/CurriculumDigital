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

import java.rmi.RemoteException;
import utils.BlockChain;

/**
 * Created on 27/11/2024, 19:46:43 
 * @author manso - computer
 */
public interface P2Plistener {
    //Publica uma mensagem ao iniciar o servidor
    public void onStart(String message);
    
    //Inicia a ligação à rede
    public void onConect(String address);
    
    //Atualiza a lista de certificados, quando executada
    public void onTransaction(String transaction);
    
    //Executada quando encontra um erro
    public void onException(Exception e, String title);
    
    //Adiciona uma nova mensagem à lista de mensagens na interface gráfica
    public void onMessage (String msg) throws RemoteException;
    
    //Atualiza a interface gráfica, quando o Miner inicia
    public void onStartMining(String message, int zeros);

    //Atualiza a interface gráfica, quando o Miner para
    public void onStopMining(String message, int nonce);

    //Atualiza a interface gráfica, quando o nonce é encontrado
    public void onNounceFound(String message, int nonce);
    
    //Atualiza a interface gráfica, quando existe uma alteração na blockchain
    public void onBlockchainUpdate(BlockChain b);
    
}
