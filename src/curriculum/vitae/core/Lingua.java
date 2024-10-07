/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package curriculum.vitae.core;

import java.io.Serializable;

/**
 *
 * @author henry
 */
public class Lingua implements Serializable{
    String lingua;
    String nivel;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Língua: ").append(lingua);
        sb.append(" (").append(nivel);
        sb.append(')');
        return sb.toString();
    }

    public Lingua(String lingua, String nivel) {
        this.lingua = lingua;
        this.nivel = nivel;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    
}
