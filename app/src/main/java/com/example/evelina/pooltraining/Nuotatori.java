package com.example.evelina.pooltraining;

import java.io.Serializable;

/**
 * Created by Sony on 20/10/2017.
 */

public class Nuotatori implements Serializable {
    public Nuotatori(){}



    private String nomeNuotatore;
    private String cognomeNuotatore;

    public Nuotatori(String nomeNuotatore,String cognomeNuotatore){
        this.nomeNuotatore=nomeNuotatore;
        this.cognomeNuotatore=cognomeNuotatore;
    }
    public String getNomeNuotatore() {
        return nomeNuotatore;
    }

    public void setNomeNuotatore(String nomeNuotatore) {
        this.nomeNuotatore = nomeNuotatore;
    }

    public String getCognomeNuotatore() {
        return cognomeNuotatore;
    }

    public void setCognomeNuotatore(String cognomeNuotatore) {
        this.cognomeNuotatore = cognomeNuotatore;
    }


}
