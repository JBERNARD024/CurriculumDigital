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


import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Trabalho
 */
public class RemoteObject extends UnicastRemoteObject implements RemoteInterface {

    String host; // nome do servidor

    public RemoteObject(int port) throws RemoteException {
        super(port);
    }

    @Override
    public String getMessage() throws RemoteException{
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

    
    
}
