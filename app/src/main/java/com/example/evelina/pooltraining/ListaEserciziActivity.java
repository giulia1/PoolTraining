package com.example.evelina.pooltraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;


import android.widget.ListView;



public class ListaEserciziActivity extends AppCompatActivity {

    private ListView listaEsercizi;
    private String idNuotatore;
    private String weekday;
    private EserciziAdapter adapter;
    private nuotoDatabase archivio = new nuotoDatabase();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_esercizi);

        idNuotatore=getIntent().getStringExtra("idNuotatore");
        weekday=getIntent().getStringExtra("giorno");

        listaEsercizi = (ListView) findViewById(R.id.listaEsercizi);
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



