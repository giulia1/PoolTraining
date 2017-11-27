package com.example.evelina.pooltraining;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.design.widget.FloatingActionButton;





public class ListaEserciziActivity extends AppCompatActivity {


    final Context context = this;

    private FloatingActionButton buttonAggiungiEse;
    private nuotoDatabase archivio = new nuotoDatabase();

    private String nomeEsercizioAggiunto;
    private String nomeEsercizioModificato;
    private String idNuotatore;
    private String KEY_GIORNO = "Lunedi";
    private String weekday;
    private int nVascheAggiunte;
    private int nVascheModificate;

    private AlertDialog alertDialogModifica;
    private AlertDialog alertDialogAggiungi;
    private ListView listaEsercizi;
    private EserciziAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_esercizi);

        idNuotatore = getIntent().getStringExtra("idNuotatore");
        weekday = getIntent().getStringExtra("giorno");
        listaEsercizi = (ListView) findViewById(R.id.listaEsercizi);
        buttonAggiungiEse = (FloatingActionButton) findViewById(R.id.floatingActionButtonAggiungiEse);
        adapter = new EserciziAdapter(this);



        archivio.leggiEsercizi(idNuotatore, weekday, new nuotoDatabase.UpdateListenerE() {
            @Override
            public void eserciziAggiornati() {
                adapter.update(archivio.elencoEsercizi());
            }
        });

        listaEsercizi.setAdapter(adapter);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        archivio.terminaOsservazioneEsercizi(idNuotatore);
    }

}




