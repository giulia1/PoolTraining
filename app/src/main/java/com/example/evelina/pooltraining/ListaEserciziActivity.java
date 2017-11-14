package com.example.evelina.pooltraining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ListaEserciziActivity extends AppCompatActivity {

    private ListView listaEsercizi;
    private String idNuotatore;
    private int weekday;
    private ArrayList<Esercizi> list;
    private EserciziAdapter adapter;
    private nuotoDatabase archivio = new nuotoDatabase();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_esercizi);
        idNuotatore=getIntent().getStringExtra("chiave");
        weekday=getIntent().getIntExtra("posizione",1);
        listaEsercizi = (ListView) findViewById(R.id.listaEsercizi);
        adapter = new EserciziAdapter(this);

        listaEsercizi=(ListView)findViewById(R.id.listaEsercizi);
        adapter= new EserciziAdapter(this);
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
        archivio.terminaOsservazioneNuotatori(idNuotatore);
    }



}



