package com.example.evelina.pooltraining;

import java.io.Serializable;

/**
 * Created by Sony on 20/10/2017.
 */

public class Esercizi implements Serializable {

    private String nomeEsercizio;
    private int numeroVasche;
    private String idNuotatore;
    private String giornoSettimana;

    public Esercizi()
    {

    }

    public Esercizi (String nomeEsercizio, int numeroVascheEsercizio, String idNuotatore, String giornoSettimana){

        this.nomeEsercizio=nomeEsercizio;
        this.numeroVasche=numeroVascheEsercizio;
        this.idNuotatore=idNuotatore;
        this.giornoSettimana=giornoSettimana;
    }

    public String getNomeEsercizio() {

        return nomeEsercizio;
    }

    public void setNomeEsercizio(String nomeEsercizio) {

        this.nomeEsercizio = nomeEsercizio;
    }


    public int getNumeroVasche() {

        return numeroVasche;
    }

    public void setNumeroVasche(int numeroVascheEsercizio) {

        this.numeroVasche = numeroVascheEsercizio;
    }
    public String getGiornoSettimana(){
        return giornoSettimana;
    }
    public void setGiornoSettimana(String giornoSettimana){
        this.giornoSettimana=giornoSettimana;
    }
    public String getIdNuotatore(){
        return idNuotatore;
    }
    public void setIdNuotatore(String idNuotatore){
        this.idNuotatore=idNuotatore;
    }



}

