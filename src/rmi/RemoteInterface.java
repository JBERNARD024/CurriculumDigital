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

import curriculum.vitae.core.Educacao;
import curriculum.vitae.core.Instituto;
import curriculum.vitae.core.Pessoa;
import curriculum.vitae.core.RegistoCertificado;
import curriculum.vitae.core.dadosInstitucionais;
import curriculum.vitae.core.dadosPessoais;
import curriculum.vitae.gui.Registo;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

/**
 *
 * @author M@nso 
 */
public interface RemoteInterface extends Remote{
    //remote method
    public String getMessage() throws RemoteException;
    
    /*########## Login da Pessoa ##########*/
    
    public boolean verificaUtilizador(String email) throws RemoteException;
    
    public boolean verificaCamposUser(String email, String password) throws RemoteException;
    
    public Pessoa loginUser(String password) throws RemoteException;
    
    /*########## Register da Pessoa ##########*/
    
    public Pessoa registerUser(String email, String password)throws RemoteException;
    
    public boolean verificaRegistoUser(String email, String password, String confPassword) throws RemoteException;
    
    public boolean verificaEmailRegisto(String email) throws RemoteException;
    
    //########## Register do Instituto ##########
    
    public Instituto registerInst(String codNome, String password)throws RemoteException;
    
    public boolean verificaRegistoInst(String codNome, String password, String confPassword) throws RemoteException;  
    
    public boolean verificaCodNome(String codNome) throws RemoteException; 
    
    /*########## Login do Instituto ##########*/
    
    public Instituto loginInst(String password) throws RemoteException;
    
    public boolean verificaLoginInst(String codNome, String password) throws RemoteException;
    
    public boolean verificaCodNomeLogin(String codNome) throws RemoteException;
    
    /*########## Adicionar Dados Pessoais #########*/
    public Pessoa adicionaDadosPessoa(String email, dadosPessoais dadosP, ImageIcon icon) throws RemoteException;
    
     /*########## Adicionar Dados Institucionais #########*/
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
}
