package com.example.evelina.pooltraining;

import java.io.Serializable;

/**
 * Created by Sony on 20/10/2017.
 */

public class Esercizi implements Serializable {

    private String nomeEsercizio;
    private int numeroVasche;

    public Esercizi()
    {

    }

    public Esercizi (String nomeEsercizio, int numeroVascheEsercizio){

        this.nomeEsercizio=nomeEsercizio;
        this.numeroVasche=numeroVascheEsercizio;
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

    public void setNumeroVascheEsercizio(int numeroVascheEsercizio) {

        this.numeroVasche = numeroVascheEsercizio;
    }



}

