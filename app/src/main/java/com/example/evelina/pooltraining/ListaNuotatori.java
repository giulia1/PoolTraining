package com.example.evelina.pooltraining;
import android.content.ClipData;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class ListaNuotatori extends AppCompatActivity {

    private ListView listaNuotatori;

    private NuotatoriAdapter adapter;

    private final static String KEY_COGNOME = "cognome";
    private final static String KEY_NOME = "nome";

    private nuotoDatabase archivio = new nuotoDatabase();
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_nuotatori);
        mAuth = FirebaseAuth.getInstance();

        // Comportamento differenziato
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            // Utente non autenticato, voglio impedire l'accesso
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
        }

        listaNuotatori = (ListView) findViewById(R.id.listaNuotatori);
        adapter = new NuotatoriAdapter(this);
        archivio.leggiNuotatori(new nuotoDatabase.UpdateListener() {
            @Override
            public void nuotatoriAggiornati() {
                adapter.update(archivio.elencoNuotatori());
            }
        });

        listaNuotatori.setAdapter(adapter);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        archivio.terminaOsservazioneNuotatori();
    }
}

