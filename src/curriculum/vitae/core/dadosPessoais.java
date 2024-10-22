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
//Esta classe vai guardar os dados pessoais de uma Pessoa
public class dadosPessoais implements Serializable {

    private String nome;
    private String nacionalidade;
    private Date dataNasc;
    private String sexo;
    private String telemovel;
    private String linkedin;
    private String morada;
    private String localidade;
    private String codPostal;
    private String pais;
    private String descricao;

    //Construtor que vai criar um objeto que vai guardar os dados pessoais de uma Pessoa
    public dadosPessoais(String nome, String nacionalidade, Date dataNasc, String sexo, String telemovel, String linkedin, String morada, String localidade, String codPostal, String pais, String descricao) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.dataNasc = dataNasc;
        this.sexo = sexo;
        this.telemovel = telemovel;
        this.linkedin = linkedin;
        this.morada = morada;
        this.localidade = localidade;
        this.codPostal = codPostal;
        this.pais = pais;
        this.descricao = descricao;
    }
    
    //Construtor que cria os dados pessoais a partir de dados pessoais já existentes
    public dadosPessoais(dadosPessoais dP){
        this.nome = dP.nome;
        this.nacionalidade = dP.nacionalidade;
        this.dataNasc = dP.dataNasc;
        this.sexo = dP.sexo;
        this.telemovel = dP.telemovel;
        this.linkedin = dP.linkedin;
        this.morada = dP.morada;
        this.localidade = dP.localidade;
        this.codPostal = dP.codPostal;
        this.pais = dP.pais;
        this.descricao = dP.descricao;
    }

    //Devolve o nome completo da Pessoa
    public String getNome() {
        return nome;
    }
    
    //Define o nome completo da Pessoa
    public void setNome(String nome) {
        this.nome = nome;
    }

    //Devolve a nacionalidade de uma Pessoa
    public String getNacionalidade() {
        return nacionalidade;
    }

    //Define a nacionalidade de uma Pessoa
    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    //Devolve a data de nascimento de uma pessoa
    public Date getDataNasc() {
        return dataNasc;
    }

    //Define a data de nascimento de uma pessoa
    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }
 
    //Devole o número de telemóvel de uma pessoa
    public String getTelemovel() {
        return telemovel;
    }

    //Define um novo número de telemóvel
    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }

    //Devolve o link do perfil de uma pessoa
    public String getLinkedin() {
        return linkedin;
    }

    //Define o link do perfil de uma pessoa
    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    //Devolve a morada de uma pessoa
    public String getMorada() {
        return morada;
    }

    //Define uma nova morada de uma pessoa
    public void setMorada(String morada) {
        this.morada = morada;
    }

    //Devolve a descrição de uma pessoa
    public String getDescricao() {
        return descricao;
    }

    //Define a descrição de uma pessoa
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    //Devolve o género de uma pessoa
    public String getSexo() {
        return sexo;
    }

    //Define o género de uma pessoa
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    //Devolve a localidade em que vive a pessoa
    public String getLocalidade() {
        return localidade;
    }
    
    //Define uma  nova localidade em que vive a pessoa
    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    //Devolve o código postal de onde vive a pessoa
    public String getCodPostal() {
        return codPostal;
    }

    //Define um novo código postal de onde vive a pessoa
    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    //Devolve o país de origem da pessoa
    public String getPais() {
        return pais;
    }

    //Define o país de origem da pessoa
    public void setPais(String pais) {
        this.pais = pais;
    }

    //Apresenta todo os dados pessoais, em formato de String
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DadosPessoais {");
        sb.append("Nome = ").append(nome);
        sb.append(", Nacionalidade = ").append(nacionalidade);
        sb.append(", Data de Nascimento = ").append(dataNasc);
        sb.append(", Sexo = ").append(sexo);
        sb.append(", Telemóvel = ").append(telemovel);
        sb.append(", Linkedin = ").append(linkedin);
        sb.append(", Morada = ").append(morada);
        sb.append(", Localidade = ").append(localidade);
        sb.append(", País = ").append(pais);
        sb.append(", Descrição = ").append(descricao);
        sb.append('}');
        return sb.toString();
    }

}
