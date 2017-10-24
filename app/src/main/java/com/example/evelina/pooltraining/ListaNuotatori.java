package com.example.evelina.pooltraining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;


public class ListaNuotatori extends AppCompatActivity {

    private ListView listaNuotatori;
    private ArrayList<Nuotatori> array;
    private NuotatoriAdapter adapter;
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
        } else {}

        listaNuotatori = (ListView) findViewById(R.id.listaNuotatori);
        adapter = new NuotatoriAdapter(this);


        archivio.leggiNuotatori(new nuotoDatabase.UpdateListener() {
            @Override
            public void nuotatoriAggiornati() {
                //Log.v("Log1", archivio.elencoNuotatori().get(0).getNomeNuotatore());
               adapter.update(archivio.elencoNuotatori());
            //    for (int i=0; i<archivio.elencoNuotatori().size(); i++){
              //      Log.v("Nuotatore", archivio.elencoNuotatori().get(i).getNomeNuotatore());
               // }

            }
        });

        listaNuotatori.setAdapter(adapter);
        //listaNuotatori.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          //  @Override
            //public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //   Nuotatori n = adapter.getItem(position);
              // Intent intent = new Intent(view.getContext(), DettaglioNuotatore.class);
              //  intent.putExtra(EXTRA_STUDENTE, nuotatore);
             //   startActivity(intent);
            //}
        //});





    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        archivio.terminaOsservazioneNuotatori();
    }
}

