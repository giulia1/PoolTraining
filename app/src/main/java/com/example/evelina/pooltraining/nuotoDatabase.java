package com.example.evelina.pooltraining;


import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.*;

import static android.content.ContentValues.TAG;

/**
 * Created by Sony on 20/10/2017.
 */

public class nuotoDatabase {
    private  final static String allenatori="Allenatori";
    private  final static String nuotatori="Nuotatori";
    private  final static String esercizi="Esercizi";
    private  final  static String vasche="vasche";
    private  final static String nome="Nome";

    private ArrayList<Nuotatori> listaNuotatori;
    private ArrayList<Esercizi> listaEsercizi;
    private ArrayList<Nuotatori> listaNuotatoriLiberi;

    private ValueEventListener listenerNuotatori;
    private ValueEventListener listenerNuotatoriLiberi;
    private ValueEventListener listenerEsercizi;
    private ValueEventListener listener;

    private static DatabaseReference mDatabase;
    private final static String KEY_COGNOME = "Cognome";
    private final static String KEY_NOME = "Nome";
    private final static String KEY_ID_ALLENATORE = "Id_Allenatore";
    private String idNuotatore;


    public nuotoDatabase() {
        listaNuotatori = new ArrayList<>();
        listaEsercizi=new ArrayList<>();
        listaNuotatoriLiberi=new ArrayList<>();
    }

    public interface UpdateListenerN {
        void nuotatoriAggiornati();
    }
    public interface UpdateListenerE {
        void eserciziAggiornati();
    }
    public interface UpdateListenerNL {
        void nuotatoriLiberiAggiornati();
    }
    public static String addAllenatore(Allenatori a) { //restituisce id allenatore
        String key = mDatabase.child(allenatori).push().getKey();
        mDatabase.child(allenatori).child(key).setValue(a);
        return key;
    }


    public static String addNuotatoreDB(Nuotatori n) {
        String key = mDatabase.child(nuotatori).push().getKey();
        mDatabase.child(nuotatori).child(key).setValue(n);
        return key;
    }

    public static void addNuotatoreLista(Nuotatori n, String idAllenatore, String idNuotatore) {
        mDatabase.child(allenatori).child(idAllenatore).child(nuotatori).child(idNuotatore).setValue(n);
        mDatabase.child(nuotatori).child(idNuotatore).child(KEY_ID_ALLENATORE).setValue(idAllenatore);

    }

    public static void addEsercizio(Esercizi esercizio, Nuotatori nuotatore) {//corretto addniuotatore(nuiotatore) al posto di getid?
        mDatabase.child(nuotatori).child(addNuotatoreDB(nuotatore)).child(esercizi).child(String.valueOf(esercizio.getGiornoSettimanaEsercizio())).child(esercizio.getNomeEsercizio()).child(vasche).child(String.valueOf(esercizio.getNumeroVascheEsercizio()));
    }
    public String restituisciId(final String nome, final String cognome) {
       final String id;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(nuotatori);

        listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot elemento : dataSnapshot.getChildren()) {
                    if (elemento.child(KEY_NOME).getValue(String.class) == nome && elemento.child(KEY_COGNOME).getValue(String.class) == cognome) {
                        idNuotatore = elemento.getKey();
                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
       return idNuotatore;
    }

            public void leggiNuotatori(String idAllenatore, final UpdateListenerN notifica) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //bisogna implementare l impostazione automatica dell ID
        DatabaseReference ref = database.getReference(allenatori).child(idAllenatore).child(nuotatori);

        listenerNuotatori = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaNuotatori.clear();
                for (DataSnapshot elemento : dataSnapshot.getChildren()) {
                    Nuotatori nuotatore = new Nuotatori();
                    nuotatore.setCognomeNuotatore(elemento.child(KEY_COGNOME).getValue(String.class));
                    nuotatore.setNomeNuotatore(elemento.child(KEY_NOME).getValue(String.class));
                    listaNuotatori.add(nuotatore);
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
    public void terminaOsservazioneNuotatori(String idAllenatore) {
        if (listenerNuotatori != null)
            FirebaseDatabase.getInstance().getReference(allenatori).child(idAllenatore).child(nuotatori).removeEventListener(listenerNuotatori);
    }
        public List<Nuotatori> elencoNuotatori () {
            return listaNuotatori;
        }

    public void leggiEsercizi(String idNuotatore, int weekDay,final UpdateListenerE notifica) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(nuotatori).child(idNuotatore).child(esercizi).child("1"); //fare il casting

        listenerEsercizi = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaEsercizi.clear();
                for (DataSnapshot elemento : dataSnapshot.getChildren()) {
                    Esercizi esercizio = new Esercizi();
                    esercizio.setNomeEsercizio(elemento.getValue(String.class));
                    esercizio.setNumeroVascheEsercizio(elemento.child(vasche).getValue(Integer.class));
                    listaEsercizi.add(esercizio);
                }
                notifica.eserciziAggiornati();
            }


            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());

            }


        };
        ref.addValueEventListener(listenerEsercizi);


    }
    public void terminaOsservazioneEsercizi(String idNuotatore) {
        if (listenerEsercizi != null)
            FirebaseDatabase.getInstance().getReference(nuotatori).child(idNuotatore).child(esercizi).child("1").removeEventListener(listenerNuotatori);
    }
    public List<Esercizi> elencoEsercizi () {
        return listaEsercizi;
    }

    public void leggiNuotatoriLiberi(final UpdateListenerNL notifica) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(nuotatori);

        listenerNuotatoriLiberi= new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaNuotatoriLiberi.clear();
                for (DataSnapshot elemento : dataSnapshot.getChildren()) {

                    if(elemento.child(KEY_ID_ALLENATORE).getValue(String.class)=="senzaallenatore"){
                        Nuotatori nuotatore = new Nuotatori();
                    nuotatore.setCognomeNuotatore(elemento.child(KEY_COGNOME).getValue(String.class));
                    nuotatore.setNomeNuotatore(elemento.child(KEY_NOME).getValue(String.class));
                    listaNuotatoriLiberi.add(nuotatore);
                }
                }
                notifica.nuotatoriLiberiAggiornati();
            }


            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());

            }


        };
        ref.addValueEventListener(listenerNuotatoriLiberi);


    }
    public void terminaOsservazioneNuotatoriLiberi() {
        if (listenerNuotatoriLiberi != null)
            FirebaseDatabase.getInstance().getReference(nuotatori).removeEventListener(listenerNuotatoriLiberi);
    }
    public List<Nuotatori> elencoNuotatoriLiberi () {
        return listaNuotatoriLiberi;
    }
}






