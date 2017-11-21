package com.example.evelina.pooltraining;
import android.content.ClipData;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class ListaNuotatori extends AppCompatActivity {

    private ListView listaNuotatori;
    private FloatingActionButton aggiungi;
    private String idAllenatore;
    private NuotatoriAdapter adapter;
    private ImageButton buttonLogOut;


    private final static String KEY_COGNOME = "cognome";
    private final static String KEY_NOME = "nome";

    private nuotoDatabase archivio = new nuotoDatabase();
    private FirebaseAuth mAuth;
    private String idNuotatore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_nuotatori);
        mAuth=FirebaseAuth.getInstance();
        //idAllenatore = getIntent().getStringExtra("chiave");
        idAllenatore=mAuth.getCurrentUser().getUid();
        aggiungi = (FloatingActionButton) findViewById(R.id.floatingActionButtonAggiungi);
        //buttonLogOut = (ImageButton)findViewById(R.id.button);

        listaNuotatori = (ListView) findViewById(R.id.listaNuotatori);
        adapter = new NuotatoriAdapter(this);
        archivio.leggiNuotatori(idAllenatore, new nuotoDatabase.UpdateListenerN() {
            @Override
            public void nuotatoriAggiornati() {
                adapter.update(archivio.elencoNuotatori());
            }
        });

        listaNuotatori.setAdapter(adapter);
        listaNuotatori.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                idNuotatore=archivio.idNuotatori.get(position);
                Intent aggiungiEsercizi = new Intent(getApplicationContext(), AggiungiEserciziActivity.class);
                aggiungiEsercizi.putExtra("idNuotatore",idNuotatore);
                startActivity(aggiungiEsercizi);

            }
        });
        aggiungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent aggiungiNuotatori = new Intent(getApplicationContext(), AggiungiNuotatoreActivity.class);
                startActivity(aggiungiNuotatori);


            }

            ;
        });
        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.signOut();
                finish();
                Intent loginActivity = new Intent(ListaNuotatori.this, MainActivity.class);
                startActivity(loginActivity);
            }
        });

    }
}

