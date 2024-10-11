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
public class dadosInstitucionais implements Serializable{
    private String nome;
    private Date dataFundacao;
    private String natureza;
    private String cidade;
    private String tipoEnsino;
    private String telefone;
    private String sitioWeb;
    private String morada;
    private String distrito;
    private String codPostal;
    private String pais;
    private String descricao;

    public dadosInstitucionais(String nome, Date dataFundacao, String natureza, String cidade, String tipoEnsino, String telefone, String sitioWeb, String morada, String distrito, String codPostal, String pais, String descricao) {
        this.nome = nome;
        this.dataFundacao = dataFundacao;
        this.natureza = natureza;
        this.cidade = cidade;
        this.tipoEnsino = tipoEnsino;
        this.telefone = telefone;
        this.sitioWeb = sitioWeb;
        this.morada = morada;
        this.distrito = distrito;
        this.codPostal = codPostal;
        this.pais = pais;
        this.descricao = descricao;
    }
    
    public dadosInstitucionais (dadosInstitucionais dI){
        this.nome = dI.nome;
        this.dataFundacao = dI.dataFundacao;
        this.natureza = dI.natureza;
        this.cidade = dI.cidade;
        this.tipoEnsino = dI.tipoEnsino;
        this.telefone = dI.telefone;
        this.sitioWeb = dI.sitioWeb;
        this.morada = dI.morada;
        this.distrito = dI.distrito;
        this.codPostal = dI.codPostal;
        this.pais = dI.pais;
        this.descricao = dI.descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public String getNatureza() {
        return natureza;
    }

    public void setNatureza(String natureza) {
        this.natureza = natureza;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTipoEnsino() {
        return tipoEnsino;
    }

    public void setTipoEnsino(String tipoEnsino) {
        this.tipoEnsino = tipoEnsino;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dados Institucionais{");
        sb.append("Nome = ").append(nome);
        sb.append(", Data Fundação = ").append(dataFundacao);
        sb.append(", Natureza = ").append(natureza);
        sb.append(", Cidade = ").append(cidade);
        sb.append(", Tipo de Ensino = ").append(tipoEnsino);
        sb.append(", Telefone = ").append(telefone);
        sb.append(", Sítio Web = ").append(sitioWeb);
        sb.append(", Morada = ").append(morada);
        sb.append(", Distrito = ").append(distrito);
        sb.append(", Código Postal = ").append(codPostal);
        sb.append(", País = ").append(pais);
        sb.append(", Descrição = ").append(descricao);
        sb.append('}');
        return sb.toString();
    }

    
}
