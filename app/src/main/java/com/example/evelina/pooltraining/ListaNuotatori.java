package com.example.evelina.pooltraining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ListaNuotatori extends AppCompatActivity {

    private ListView listaNuotatori;
    private ArrayList<Nuotatori> array;
    private NuotatoriAdapter2 adapter;
    //private ValueEventListener listenerNuotatori;
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
        } else {}

        listaNuotatori = (ListView) findViewById(R.id.listaNuotatori);
        array = new ArrayList<Nuotatori>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Allenatori").child("CukUMqy9PEWIR9Mp8dsAoJnlyJs2").child("Nuotatori");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                array.clear();
                for (DataSnapshot elemento : dataSnapshot.getChildren()) {
                    Nuotatori nuotatore = new Nuotatori();
                    nuotatore.setCognomeNuotatore(elemento.child(KEY_COGNOME).getValue(String.class));
                    nuotatore.setNomeNuotatore(elemento.child(KEY_NOME).getValue(String.class));
                    array.add(nuotatore);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        adapter = new NuotatoriAdapter2(this, array);

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

