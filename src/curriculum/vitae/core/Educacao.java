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

//Esta classe vai criar um objeto que vai guardar as informações relativamente a um certificado
public class Educacao implements Serializable{
    private String qualificacao;
    private String areaEstudo;
    private String instituicao;
    private int mediaFinal;
    private String nivelQEQ;
    private String cidade;
    private String pais;
    private Date dataInic;
    private Date dataFim;
    private String descr;

    //Construtor que cria um objeto com todas as informações definidas no paramêtro do construtor
    public Educacao(String qualificacao, String areaEstudo, String instituicao, int mediaFinal, String nivelQEQ, String cidade, String pais, Date dataInic, Date dataFim, String descr) {
        this.qualificacao = qualificacao;
        this.areaEstudo = areaEstudo;
        this.instituicao = instituicao;
        this.mediaFinal = mediaFinal;
        this.nivelQEQ = nivelQEQ;
        this.cidade = cidade;
        this.pais = pais;
        this.dataInic = dataInic;
        this.dataFim = dataFim;
        this.descr = descr;
    }
    
    //Devolve, em String, o valor da qualificação
    public String getQualificacao() {
        return qualificacao;
    }

    //Define, pelo formato de String, o valor do atributo qualificacao
    public void setQualificacao(String qualificacao) {
        this.qualificacao = qualificacao;
    }

    //Devolve o valor do atributo areaEstudo
    public String getAreaEstudo() {
        return areaEstudo;
    }

    //Define o valor do atributo areaEstudo
    public void setAreaEstudo(String areaEstudo) {
        this.areaEstudo = areaEstudo;
    }

    //Devolve, em String, o Instituto emissor do Certificado
    public String getInstituicao() {
        return instituicao;
    }

    //Define a Instituto emissor do Certificado
    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    //Retorna o valor da média final de um determinado curso
    public int getMediaFinal() {
        return mediaFinal;
    }

    //Define a média final de uma Educacao
    public void setMediaFinal(int mediaFinal) {
        this.mediaFinal = mediaFinal;
    }

    //Devolve o nível da qualificação definida
    public String getNivelQEQ() {
        return nivelQEQ;
    }

    //Define o valor da qualificação 
    public void setNivelQEQ(String nivelQEQ) {
        this.nivelQEQ = nivelQEQ;
    }

    //Devolve a cidade em que foi obtida a experiência educativa
    public String getCidade() {
        return cidade;
    }

    //Define a cidade em que foi obtida a experiência educativa
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    
    //Devolve o país em que decorreu a Educação
    public String getPais() {
        return pais;
    }

    //Define o país em que ocorreu a Educação 
    public void setPais(String pais) {
        this.pais = pais;
    }

    //Retorna a data de início da Educação
    public Date getDataInic() {
        return dataInic;
    }

    //Define a data de início da Educação
    public void setDataInic(Date dataInic) {
        this.dataInic = dataInic;
    }

    //Devolva a data em que foi finalizado a Educação
    public Date getDataFim() {
        return dataFim;
    }

    //Define a data final da Educação
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    //Devolve a descrição sobre a experiência educativa
    public String getDescr() {
        return descr;
    }

    //Define a descrição de um experiência educativa
    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    //Retorna, em formato de String, qual a qualificação e área de estudo de um dada Educação
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Qualificação = ").append(qualificacao);
        sb.append(", Área de Estudo = ").append(areaEstudo);
        return sb.toString();
    }
    
    
    
}
