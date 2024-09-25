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
    private String telemovel;
    private String linkedin;
    private String morada;
    private String descricao;
    private ArrayList<expProf> expProf;
    private ArrayList<educacao> educacao;

    public dadosPessoais(String nome, String nacionalidade, Date dataNasc, String telemovel, String linkedin, String morada, String descricao) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.dataNasc = dataNasc;
        this.telemovel = telemovel;
        this.linkedin = linkedin;
        this.morada = morada;
        this.descricao = descricao;
    }
  
    public dadosPessoais(String nome, String nacionalidade, Date dataNasc, String telemovel, String linkedin, String morada, String descricao, ArrayList<expProf> expProf, ArrayList<educacao> educacao) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.dataNasc = dataNasc;
        this.telemovel = telemovel;
        this.linkedin = linkedin;
        this.morada = morada;
        this.descricao = descricao;
        this.expProf = expProf;
        this.educacao = educacao;
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

    public ArrayList<expProf> getExpProf() {
        return expProf;
    }

    public void setExpProf(ArrayList<expProf> expProf) {
        this.expProf = expProf;
    }

    public ArrayList<educacao> getEducacao() {
        return educacao;
    }

    public void setEducacao(ArrayList<educacao> educacao) {
        this.educacao = educacao;
    }
}
