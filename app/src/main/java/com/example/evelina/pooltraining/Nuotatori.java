package com.example.evelina.pooltraining;

import java.io.Serializable;

/**
 * Created by Sony on 20/10/2017.
 */

public class Nuotatori implements Serializable {
    private String nomeNuotatore;
    private String cognomeNuotatore;
    private String idAllenatore;

    public Nuotatori(){}

    public Nuotatori(String nomeNuotatore,String cognomeNuotatore,String idAllenatore){
        this.nomeNuotatore=nomeNuotatore;
        this.cognomeNuotatore=cognomeNuotatore;
        this.idAllenatore=idAllenatore;
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
