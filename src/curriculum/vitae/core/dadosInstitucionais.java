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

//Esta classe vai guardar os dados Institucionais
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

    //Cria um objeto com todos os dados Instuticionais introduzidos
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
    
    //Cria um objeto com os dados Institucionais , a partir de outros dados Institucionais
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

    //Devolve o nome de um Instituto
    public String getNome() {
        return nome;
    }

    //Define o nome de um Instituto
    public void setNome(String nome) {
        this.nome = nome;
    }

    //Devolve a data de Fundação de um Instituto
    public Date getDataFundacao() {
        return dataFundacao;
    }

    //Define uma nova data de fundação de um Instituto
    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    //Devolve a Natureza de um Instituto
    public String getNatureza() {
        return natureza;
    }

    //Define a Natureza de um Instituto
    public void setNatureza(String natureza) {
        this.natureza = natureza;
    }

    //Devolve a cidade de um Instituto
    public String getCidade() {
        return cidade;
    }

    //Define a cidade de um Instituto
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    //Devolve o tipo de ensino de um Instituto
    public String getTipoEnsino() {
        return tipoEnsino;
    }

    //Define o tipo de ensino de um Instituto
    public void setTipoEnsino(String tipoEnsino) {
        this.tipoEnsino = tipoEnsino;
    }

    //Devolve o telefone de um Instituto
    public String getTelefone() {
        return telefone;
    }

    //Define o telefone de um Instituto
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    //Devolve o sítioWeb de um Instituto
    public String getSitioWeb() {
        return sitioWeb;
    }

    //Define o sítio web de um Instituto
    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    //Devolve a morada de um Instituto
    public String getMorada() {
        return morada;
    }

    //Define a morada de um Instituto
    public void setMorada(String morada) {
        this.morada = morada;
    }

    //Devolve o distrito em que está situado um Instituto
    public String getDistrito() {
        return distrito;
    }

    //Define o distrito onde está situado o Instituto
    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    //Devolve o código postal do Instituto
    public String getCodPostal() {
        return codPostal;
    }

    //Define o código postal do Instituto
    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    //Devolve o país em que está situado o Instituto
    public String getPais() {
        return pais;
    }

    //Define o país em que está situado o Instituto
    public void setPais(String pais) {
        this.pais = pais;
    }
    
    //Devolve a descrição do Instituto
    public String getDescricao() {
        return descricao;
    }

    //Define a descrição do Instituto
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    //Retorna uma string apresentando todos os dados Institucionais
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
