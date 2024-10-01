/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package curriculum.vitae.core;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author joaob
 */
public class dadosPessoais {

    private String nome;
    private String nacionalidade;
    private Date dataNasc;
    private String sexo;
    private String telemovel;
    private String linkedin;
    private String morada;
    private String localidade;
    private String pais;
    private String descricao;
    private ArrayList<Lingua> linguas = new ArrayList<>();
    private ArrayList<ExpProf> expProf = new ArrayList<>();
    private ArrayList<Educacao> educacao = new ArrayList<>();

    public dadosPessoais(String nome, String nacionalidade, Date dataNasc, String sexo, String telemovel, String linkedin, String morada, String localidade, String pais, String descricao) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.dataNasc = dataNasc;
        this.sexo = sexo;
        this.telemovel = telemovel;
        this.linkedin = linkedin;
        this.morada = morada;
        this.localidade = localidade;
        this.pais = pais;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ArrayList<ExpProf> getExpProf() {
        return expProf;
    }

    public void setExpProf(ArrayList<ExpProf> expProf) {
        this.expProf = expProf;
    }

    public ArrayList<Educacao> getEducacao() {
        return educacao;
    }

    public void setEducacao(ArrayList<Educacao> educacao) {
        this.educacao = educacao;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public ArrayList<Lingua> getLinguas() {
        return linguas;
    }

    public void setLinguas(ArrayList<Lingua> linguas) {
        this.linguas = linguas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DadosPessoais{");
        sb.append("Nome =").append(nome);
        sb.append(", Nacionalidade =").append(nacionalidade);
        sb.append(", Data de Nascimento =").append(dataNasc);
        sb.append(", Sexo =").append(sexo);
        sb.append(", Telemóvel =").append(telemovel);
        sb.append(", Linkedin =").append(linkedin);
        sb.append(", Morada=").append(morada);
        sb.append(", Localidade=").append(localidade);
        sb.append(", Paíss=").append(pais);
        sb.append(", Descrição=").append(descricao);
        sb.append('}');
        return sb.toString();
    }

}
