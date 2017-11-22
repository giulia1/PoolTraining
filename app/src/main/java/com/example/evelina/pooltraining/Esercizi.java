package com.example.evelina.pooltraining;

import java.io.Serializable;

/**
 * Created by Sony on 20/10/2017.
 */

public class Esercizi implements Serializable {

    private String nomeEsercizio;
    private int numeroVascheEsercizio;

    public Esercizi(){}

    public Esercizi (String nomeEsercizio, int numeroVascheEsercizio){

        this.nomeEsercizio=nomeEsercizio;
        this.numeroVascheEsercizio=numeroVascheEsercizio;
    }

    public String getNomeEsercizio() {
        return nomeEsercizio;
    }

    public void setNomeEsercizio(String nomeEsercizio) {
        this.nomeEsercizio = nomeEsercizio;
    }


    public int getNumeroVascheEsercizio() {
        return numeroVascheEsercizio;
    }

    public void setNumeroVascheEsercizio(int numeroVascheEsercizio) {

        this.numeroVascheEsercizio = numeroVascheEsercizio;
    }



}

