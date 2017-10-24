package com.example.evelina.pooltraining;

/**
 * Created by Sony on 20/10/2017.
 */

public class Esercizi {
    public String getNomeEsercizio() {
        return nomeEsercizio;
    }

    public void setNomeEsercizio(String nomeEsercizio) {
        this.nomeEsercizio = nomeEsercizio;
    }

    public int getGiornoSettimanaEsercizio() {
        return giornoSettimanaEsercizio;
    }

    public void setGiornoSettimanaEsercizio(int giornoSettimanaEsercizio) {
        this.giornoSettimanaEsercizio = giornoSettimanaEsercizio;
    }

    public int getNumeroVascheEsercizio() {
        return numeroVascheEsercizio;
    }

    public void setNumeroVascheEsercizio(int numeroVascheEsercizio) {
        this.numeroVascheEsercizio = numeroVascheEsercizio;
    }

    private String nomeEsercizio;
    private int giornoSettimanaEsercizio;
    private int numeroVascheEsercizio;
public Esercizi(){}
    public Esercizi(String nomeEsercizio, int giornoSettimanaEsercizio,int numeroVascheEsercizio){
        this.nomeEsercizio=nomeEsercizio;
        this.giornoSettimanaEsercizio=giornoSettimanaEsercizio;
        this.numeroVascheEsercizio=numeroVascheEsercizio;
    }

}

