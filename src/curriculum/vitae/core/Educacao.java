/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package curriculum.vitae.core;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author joaob
 */
public class Educacao implements Serializable{
    private String qualificacao;
    private String areaEstudo;
    private String instituicao;
    private String mediaFinal;
    private String sitioWeb;
    private String nivelQEQ;
    private String cidade;
    private String pais;
    private Date dataInic;
    private Date dataFim;
    private String descr;

    public Educacao(String qualificacao, String areaEstudo, String instituicao, String mediaFinal, String sitioWeb, String nivelQEQ, String cidade, String pais, Date dataInic, Date dataFim, String descr) {
        this.qualificacao = qualificacao;
        this.areaEstudo = areaEstudo;
        this.instituicao = instituicao;
        this.mediaFinal = mediaFinal;
        this.sitioWeb = sitioWeb;
        this.nivelQEQ = nivelQEQ;
        this.cidade = cidade;
        this.pais = pais;
        this.dataInic = dataInic;
        this.dataFim = dataFim;
        this.descr = descr;
    }
    
    public String getQualificacao() {
        return qualificacao;
    }

    public void setQualificacao(String qualificacao) {
        this.qualificacao = qualificacao;
    }

    public String getAreaEstudo() {
        return areaEstudo;
    }

    public void setAreaEstudo(String areaEstudo) {
        this.areaEstudo = areaEstudo;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getMediaFinal() {
        return mediaFinal;
    }

    public void setMediaFinal(String mediaFinal) {
        this.mediaFinal = mediaFinal;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public String getNivelQEQ() {
        return nivelQEQ;
    }

    public void setNivelQEQ(String nivelQEQ) {
        this.nivelQEQ = nivelQEQ;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Date getDataInic() {
        return dataInic;
    }

    public void setDataInic(Date dataInic) {
        this.dataInic = dataInic;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Educação{");
        sb.append("Qualificação =").append(qualificacao);
        sb.append(", Área de Estudo =").append(areaEstudo);
        sb.append(", Instituição =").append(instituicao);
        sb.append(", Média Final =").append(mediaFinal);
        sb.append(", Sítio Web =").append(sitioWeb);
        sb.append(", Nível QEQ =").append(nivelQEQ);
        sb.append(", Cidade =").append(cidade);
        sb.append(", País=").append(pais);
        sb.append(", Data de Início =").append(dataInic);
        sb.append(", Data de Fim =").append(dataFim);
        sb.append(", Descrição =").append(descr);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}
