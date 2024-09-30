/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package curriculum.vitae.core;

import java.util.Date;
/**
 *
 * @author joaob
 */
public class ExpProf{
    private String funcao;
    private String entEmpregadora;
    private String cidade;
    private String pais;
    private String sitioWeb;
    private Date dataInic;
    private Date dataFim;
    private String descr;

    public ExpProf(String funcao, String entEmpregadora, String cidade, String pais, String sitioWeb, Date dataInic, Date dataFim, String descr) {
        this.funcao = funcao;
        this.entEmpregadora = entEmpregadora;
        this.cidade = cidade;
        this.pais = pais;
        this.sitioWeb = sitioWeb;
        this.dataInic = dataInic;
        this.dataFim = dataFim;
        this.descr = descr;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getEntEmpregadora() {
        return entEmpregadora;
    }

    public void setEntEmpregadora(String entEmpregadora) {
        this.entEmpregadora = entEmpregadora;
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

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Experiência Profissional {");
        sb.append("Função =").append(funcao);
        sb.append(", Entidade Empregadora =").append(entEmpregadora);
        sb.append(", Cidade =").append(cidade);
        sb.append(", País =").append(pais);
        sb.append(", Sítio Web =").append(sitioWeb);
        sb.append(", Data de Início =").append(dataInic);
        sb.append(", Data de Fim =").append(dataFim);
        sb.append(", Descrição =").append(descr);
        sb.append('}');
        return sb.toString();
    }
    
    
}
