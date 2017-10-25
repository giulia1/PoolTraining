package com.example.evelina.pooltraining;


import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Sony on 20/10/2017.
 */

public class nuotoDatabase {
    private  final static String allenatori="Allenatori";
    private  final static String nuotatori="Nuotatori";
    private  final static String esercizi="Esercizi";
    private  final  static String vasche="vasche";

    private ArrayList<Nuotatori> listaArray;
    private ValueEventListener listenerNuotatori;
    private static DatabaseReference mDatabase;
    private final static String KEY_COGNOME = "cognome";
    private final static String KEY_NOME = "nome";

    public nuotoDatabase() {
        listaArray = new ArrayList<>();
    }

    public interface UpdateListener {
        void nuotatoriAggiornati();
    }

    public static String addAllenatore(Allenatori a) { //restituisce id allenatore
        String key = mDatabase.child(allenatori).push().getKey();
        mDatabase.child(allenatori).child(key).setValue(a);
        return key;
    }

    public static String addNuotatore(Nuotatori n) {
        String key = mDatabase.child(nuotatori).push().getKey();
        mDatabase.child(nuotatori).child(key).setValue(n);
        return key;
    }

    public static void addEsercizio(Esercizi esercizio, Nuotatori nuotatore) {//corretto addniuotatore(nuiotatore) al posto di getid?
        mDatabase.child(nuotatori).child(addNuotatore(nuotatore)).child(esercizi).child(String.valueOf(esercizio.getGiornoSettimanaEsercizio())).child(esercizio.getNomeEsercizio()).child(vasche).child(String.valueOf(esercizio.getNumeroVascheEsercizio()));


    }

    public void leggiNuotatori(final UpdateListener notifica) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Allenatori").child("CukUMqy9PEWIR9Mp8dsAoJnlyJs2").child("Nuotatori");

        listenerNuotatori = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaArray.clear();
                for (DataSnapshot elemento : dataSnapshot.getChildren()) {
                    Nuotatori nuotatore = new Nuotatori();
                    nuotatore.setCognomeNuotatore(elemento.child(KEY_COGNOME).getValue(String.class));
                    nuotatore.setNomeNuotatore(elemento.child(KEY_NOME).getValue(String.class));
                    listaArray.add(nuotatore);
                }
                notifica.nuotatoriAggiornati();
            }


            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());

            }


        };
        ref.addValueEventListener(listenerNuotatori);


    }
    public void terminaOsservazioneNuotatori() {
        if (listenerNuotatori != null)
            FirebaseDatabase.getInstance().getReference("Allenatori").child("CukUMqy9PEWIR9Mp8dsAoJnlyJs2").child("Nuotatori").removeEventListener(listenerNuotatori);
    }
        public List<Nuotatori> elencoNuotatori () {
            return listaArray;
        }
    }



