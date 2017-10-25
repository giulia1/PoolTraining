package com.example.evelina.pooltraining;
import android.content.ClipData;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class ListaNuotatori extends AppCompatActivity {

    private ListView listaNuotatori;
    private String idAllenatore;
    private NuotatoriAdapter adapter;
    private Button aggiungi;

    private final static String KEY_COGNOME = "cognome";
    private final static String KEY_NOME = "nome";

    private nuotoDatabase archivio = new nuotoDatabase();
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_nuotatori);
        mAuth = FirebaseAuth.getInstance();
        idAllenatore = getIntent().getStringExtra("chiave");

        listaNuotatori = (ListView) findViewById(R.id.listaNuotatori);
        aggiungi = (Button) findViewById(R.id.buttonAggiungi);
        adapter = new NuotatoriAdapter(this);
        archivio.leggiNuotatori(idAllenatore, new nuotoDatabase.UpdateListenerN() {
            @Override
            public void nuotatoriAggiornati() {
                adapter.update(archivio.elencoNuotatori());
            }
        });

        listaNuotatori.setAdapter(adapter);
        aggiungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aggiungiNuotatori=new Intent(getApplicationContext(), AggiungiNuotatoreActivity.class);
                startActivity(aggiungiNuotatori);



            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        archivio.terminaOsservazioneNuotatori(idAllenatore);
    }
}

