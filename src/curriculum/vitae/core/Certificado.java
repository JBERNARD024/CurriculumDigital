/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package curriculum.vitae.core;

import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import utils.Converter;
import utils.SecurityUtils;

/**
 *
 * @author joaob
 */
/*Esta classe vai gerar um objeto que é composto pelo instituto, uma pessoa, os dados relativos ao Certificado (experiencia),
as chaves públicas do instituto e da pessoa e a assinatura*/
public class Certificado implements Serializable {

    private Instituto instituto;
    private Pessoa graduado;
    private String pubKeyInst;
    private String pubKeyUser;
    private Educacao experiencia;
    private String assinatura;

    //Contrutor do Certificado, que cria um objeto com base num Instituto, Pessoa e a Experiencia
    public Certificado(Instituto instituto, Pessoa graduado, Educacao experiencia) throws Exception {
        this.instituto = instituto;
        this.pubKeyInst = Base64.getEncoder().encodeToString(instituto.getPubKey().getEncoded());
        this.graduado = graduado;
        this.pubKeyUser = Base64.getEncoder().encodeToString(graduado.getPubKey().getEncoded());
        this.experiencia = experiencia;
        sign(instituto.getPrivKey());
    }

    //Função que assina o objeto certificado, de forma a validar a informação gerada
    public void sign(PrivateKey priv) throws Exception {
        byte[] dataSign = SecurityUtils.sign((pubKeyInst + pubKeyUser + experiencia).getBytes(), priv);
        this.assinatura = Base64.getEncoder().encodeToString(dataSign);
    }

    //Função que verifica se a assinatura é válida e se nenhum dado foi alterado.
    public boolean isValid() {
        try {
            PublicKey pub = SecurityUtils.getPublicKey(Base64.getDecoder().decode(pubKeyInst));
            byte[] data = (pubKeyInst + pubKeyUser + experiencia).getBytes();
            byte[] sign = Base64.getDecoder().decode(assinatura);
            return SecurityUtils.verifySign(data, sign, pub);
        } catch (Exception ex) {
            return false;
        }
    }

    //Devolve o instituto correspondente ao Certificado
    public Instituto getInstituto() {
        return instituto;
    }

    //Define o instituto de um dado certificado
    public void setInstituto(Instituto instituto) {
        this.instituto = instituto;
    }

    //Devolve a pessoa associada ao Certificado
    public Pessoa getGraduado() {
        return graduado;
    }

    //Define a pessoa a um Certificado
    public void setGraduado(Pessoa graduado) {
        this.graduado = graduado;
    }

    //Retorna a chave pública do Instituto em formato String
    public String getPubKeyInst() {
        return pubKeyInst;
    }

    //Define a chave pública do Instituto através de uma String
    public void setPubKeyInst(String pubKeyInst) {
        this.pubKeyInst = pubKeyInst;
    }

    //Retorna a chave pública da Pessoa associada ao Cetificado, em formato String
    public String getPubKeyUser() {
        return pubKeyUser;
    }

    //Define a chave pública da Pessoa correspondente ao Certificado, através de uma String
    public void setPubKeyUser(String pubKeyUser) {
        this.pubKeyUser = pubKeyUser;
    }

    //Devolve os dados correspondente a um Certificado
    public Educacao getExperiencia() {
        return experiencia;
    }

    //Define os dados de um Certificado
    public void setExperiencia(Educacao experiencia) {
        this.experiencia = experiencia;
    }

    
    //Devolve a assinatura em formato de String
    public String getAssinatura() {
        return assinatura;
    }

    
    //Define o atributo assinatura em formato String
    public void setAssinatura(String assinatura) {
        this.assinatura = assinatura;
    }

    /*Esta função transforma um objeto em Hexadecimal.
    Primeiramente, vai transformar o objeto num array de bytes e depois transformar o array de bytes num hexadecimal*/
    public String toText() {
        return Converter.objectToHex(this);
    }

    /*Esta função transforma um número em Hexadecimal num Objeto
    Inicialmente, o número hexadecimal, vai ser transformado num array de bytes e posteriormente no objeto do tipo Certificado*/
    public static Certificado fromText(String obj) {
        return (Certificado) Converter.hexToObject(obj);
    }

    //Devolve uma string com os dados de um Certificado, como o Instituto, a Pessoa e a respetiva Qualificação e Área de Estudo
    @Override
    public String toString() {
        StringBuilder txt = new StringBuilder();
        txt.append(instituto.getCodNome())
                .append(" --> ")
                .append(experiencia.getQualificacao())
                .append(" em ")
                .append(experiencia.getAreaEstudo())
                .append("  ")
                .append(isValid())
                .append(" --> ")
                .append(graduado.getDados().getNome());
        return txt.toString();
    }
}
