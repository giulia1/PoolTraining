package com.example.evelina.pooltraining;

/**
 * Created by Sony on 20/10/2017.
 */

public class Allenatori{

    private String nomeAllenatore;
    private String cognomeAllenatore;

    public Allenatori(){}

    public Allenatori(String nomeAllenatore,String cognomeAllenatore){

        this.nomeAllenatore=nomeAllenatore;
        this.cognomeAllenatore=cognomeAllenatore;
    }

    public String getNome(){
        return nomeAllenatore;
    }
    public String getCognome(){
        return cognomeAllenatore;
    }
    public void setNome(String nomeAllenatore) {
        this.nomeAllenatore = nomeAllenatore;
    }

    public void setCognome(String cognomeAllenatore) {
        this.cognomeAllenatore = cognomeAllenatore;
    }
}

