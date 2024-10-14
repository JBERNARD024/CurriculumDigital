/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package curriculum.vitae.core;

import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import utils.SecurityUtils;

/**
 *
 * @author joaob
 */
public class Certificado implements Serializable {

    private Instituto instituto;
    private Utilizador graduado;
    private String pubKeyInst;
    private String pubKeyUser;
    private Educacao experiencia;
    private String assinatura;

    public Certificado(Instituto instituto, Utilizador graduado, Educacao experiencia) throws Exception {
        this.instituto = instituto;
        this.pubKeyInst = Base64.getEncoder().encodeToString(instituto.getPubKey().getEncoded());
        this.graduado = graduado;
        this.pubKeyUser = Base64.getEncoder().encodeToString(graduado.getPubKey().getEncoded());
        this.experiencia = experiencia;
        sign(instituto.getPrivKey());
    }

    public void sign(PrivateKey priv) throws Exception {
        byte[] dataSign = SecurityUtils.sign((pubKeyInst + pubKeyUser + experiencia).getBytes(), priv);
        this.assinatura = Base64.getEncoder().encodeToString(dataSign);
    }

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

    public Instituto getInstituto() {
        return instituto;
    }

    public void setInstituto(Instituto instituto) {
        this.instituto = instituto;
    }

    public Utilizador getGraduado() {
        return graduado;
    }

    public void setGraduado(Utilizador graduado) {
        this.graduado = graduado;
    }

    public String getPubKeyInst() {
        return pubKeyInst;
    }

    public void setPubKeyInst(String pubKeyInst) {
        this.pubKeyInst = pubKeyInst;
    }

    public String getPubKeyUser() {
        return pubKeyUser;
    }

    public void setPubKeyUser(String pubKeyUser) {
        this.pubKeyUser = pubKeyUser;
    }

    public Educacao getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Educacao experiencia) {
        this.experiencia = experiencia;
    }

    public String getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(String assinatura) {
        this.assinatura = assinatura;
    }
    
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
