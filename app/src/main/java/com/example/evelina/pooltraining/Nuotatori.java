package com.example.evelina.pooltraining;

import java.io.Serializable;

/**
 * Created by Sony on 20/10/2017.
 */

public class Nuotatori implements Serializable {

    private String Nome;
    private String Cognome;
    private String Id_Allenatore;

    public Nuotatori(String nome, String cognome){

        this.Nome=nome;
        this.Cognome=cognome;
    }
    public Nuotatori(){}

    public Nuotatori(String nome,String cognome,String idAllenatore){

        this.Nome=nome;
        this.Cognome=cognome;
        this.Id_Allenatore=idAllenatore;
    }
    public String getNomeNuotatore() {
        return Nome;
    }

    public void setNomeNuotatore(String nome) {
        this.Nome = nome;
    }

    public String getCognomeNuotatore() {
        return Cognome;
    }

    public void setCognomeNuotatore(String cognome) {
        this.Cognome = cognome;
    }


}
