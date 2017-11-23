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

    private ArrayList<Nuotatori> listaNuotatori;
     private ArrayList<Esercizi> listaEsercizi;
    private ArrayList<Nuotatori> listaNuotatoriLiberi;
    ArrayList<String> idNuotatoriLiberi;
    ArrayList<String> idNuotatori;
    ArrayList<String> nomiNuotatoriLiberi;
    ArrayList<String> cognomiNuotatoriLiberi;


    private ValueEventListener listenerNuotatori;
    private ValueEventListener listenerNuotatoriLiberi;
    private ValueEventListener listenerEsercizi;

    private FirebaseDatabase database=FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;

    private final static String KEY_COGNOME = "Cognome";
    private final static String KEY_NOME = "Nome";
    private final static String KEY_ID_ALLENATORE = "Id_Allenatore";
//    private String idNuotatore;


    public nuotoDatabase() {

        listaNuotatori = new ArrayList<>();
        listaEsercizi=new ArrayList<>();
        listaNuotatoriLiberi=new ArrayList<>();
        idNuotatoriLiberi=new ArrayList<>();
        idNuotatori=new ArrayList<>();
        nomiNuotatoriLiberi=new ArrayList<>();
        cognomiNuotatoriLiberi=new ArrayList<>();
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
    public  String addAllenatoreDB(Allenatori a) { //restituisce id allenatore

        String key = mDatabase.child(allenatori).push().getKey();
        mDatabase.child(allenatori).child(key).setValue(a);
        return key;
    }


    public String addNuotatoreDB(Nuotatori n) {

        String key = mDatabase.child(nuotatori).push().getKey();
        mDatabase.child(nuotatori).child(key).setValue(n);
        return key;
    }

    public  void addNuotatoreLista(Nuotatori n, String idAllenatore, String idNuotatore) {
//aggiungere direttamente l intero oggetto
        mDatabase = database.getReference(allenatori).child(idAllenatore).child(nuotatori);
        mDatabase.child(idNuotatore).child(KEY_NOME).setValue(n.getNomeNuotatore());
        mDatabase.child(idNuotatore).child(KEY_COGNOME).setValue(n.getCognomeNuotatore());
        mDatabase=database.getReference(nuotatori);
        mDatabase.child(idNuotatore).child(KEY_ID_ALLENATORE).setValue(idAllenatore);

    }

    public  void addEsercizio( String idNuotatore, String giorno,int nVasche, String nome) {

        mDatabase = database.getReference(nuotatori);
        mDatabase.child(idNuotatore).child(esercizi).child(giorno).child(nome).child(vasche).setValue(nVasche);
    }
    public void rimuoviEsercizio (String idNuotatore, String giorno){

        mDatabase = database.getReference(nuotatori);
        mDatabase.child(idNuotatore).child(esercizi).child(giorno).removeValue();



    }
    public void modificaEsercizio(String idNuotatore, String nomeEse, String giorno, int nVasche){

        mDatabase=database.getReference(nuotatori);
        mDatabase.child(idNuotatore).child(esercizi).child(giorno).child(nomeEse).child(vasche).setValue(nVasche);

    }


            public void leggiNuotatori(String idAllenatore, final UpdateListenerN notifica) {
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference(allenatori).child(idAllenatore).child(nuotatori);

        listenerNuotatori = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaNuotatori.clear();
                for (DataSnapshot elemento : dataSnapshot.getChildren()) {
                    Nuotatori nuotatore = new Nuotatori();
                    nuotatore.setCognomeNuotatore(elemento.child(KEY_COGNOME).getValue(String.class));
                    nuotatore.setNomeNuotatore(elemento.child(KEY_NOME).getValue(String.class));
                    listaNuotatori.add(nuotatore);
                    idNuotatori.add(elemento.getKey());
                }
                notifica.nuotatoriAggiornati();
            }


            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());

            }


        };
        mDatabase.addValueEventListener(listenerNuotatori);


    }
    public void terminaOsservazioneNuotatori(String idAllenatore) {
        if (listenerNuotatori != null)
            database.getReference(allenatori).child(idAllenatore).child(nuotatori).removeEventListener(listenerNuotatori);
    }
        public List<Nuotatori> elencoNuotatori () {
            return listaNuotatori;
        }

    public void leggiEsercizi(String idNuotatore, String weekDay,final UpdateListenerE notifica) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference(nuotatori).child(idNuotatore).child(esercizi).child(weekDay);

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
        mDatabase.addValueEventListener(listenerEsercizi);


    }
    public void terminaOsservazioneEsercizi(String idNuotatore) {
        if (listenerEsercizi != null)
            database.getReference(nuotatori).child(idNuotatore).child(esercizi).removeEventListener(listenerNuotatori);
    }
    public List<Esercizi> elencoEsercizi () {
        return listaEsercizi;
    }

    public void leggiNuotatoriLiberi(final UpdateListenerNL notifica) {

        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference(nuotatori);
        listenerNuotatoriLiberi= new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaNuotatoriLiberi.clear();
                for (DataSnapshot elemento : dataSnapshot.getChildren()) {
                    if(elemento.child(KEY_ID_ALLENATORE).getValue(String.class).equals("null")) {

                        Nuotatori nuotatore = new Nuotatori();
                        nuotatore.setCognomeNuotatore(elemento.child(KEY_COGNOME).getValue(String.class));
                        nuotatore.setNomeNuotatore(elemento.child(KEY_NOME).getValue(String.class));
                        listaNuotatoriLiberi.add(nuotatore);
                        idNuotatoriLiberi.add(elemento.getKey());
                        nomiNuotatoriLiberi.add(elemento.child(KEY_NOME).getValue(String.class));
                        cognomiNuotatoriLiberi.add(elemento.child(KEY_COGNOME).getValue(String.class));

                    }
                }
                notifica.nuotatoriLiberiAggiornati();
            }


            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());

            }


        };
        mDatabase.addValueEventListener(listenerNuotatoriLiberi);


    }
    public void terminaOsservazioneNuotatoriLiberi() {

        if (listenerNuotatoriLiberi != null)
            database.getReference(nuotatori).removeEventListener(listenerNuotatoriLiberi);
    }
    public List<Nuotatori> elencoNuotatoriLiberi () {
        return listaNuotatoriLiberi;
    }
}






