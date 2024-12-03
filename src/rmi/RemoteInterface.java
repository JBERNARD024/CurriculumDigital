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
package rmi;

import curriculum.vitae.core.Pessoa;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author M@nso 
 */
public interface RemoteInterface extends Remote{
    //remote method
    public String getMessage() throws RemoteException;
    
    public boolean verificaUtilizador(String email) throws RemoteException;
    
    public boolean verificaCamposUser(String email, String password) throws RemoteException;
    
    public Pessoa loginUser(String email, String password) throws RemoteException;
    
    public Pessoa registerUser(String email, String password)throws RemoteException;
    
    public boolean verificaRegistoUser(String email, String password, String confPassword) throws RemoteException;
    
    public boolean verificaEmailRegisto(String email) throws RemoteException;
    
    
    
}
