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


public class ListaEsercizi extends AppCompatActivity {
    private ListView listaEsercizi;
    private ArrayList<Esercizi> list;
    private DatabaseReference ref;
    private ArrayAdapter<Esercizi> adapter;
    //private ValueEventListener listenerStudenti;
    private final String esercizi="Esercizi";
    private String UUID = "sII8wdAxfBWBgg0HBspchchPSDk1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         int weekDay;
        setContentView(R.layout.activity_lista_esercizi);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            int j =(int) b.get("posizione");
            weekDay=j;
        }
        //list=new ArrayList<>();
        listaEsercizi=(ListView)findViewById(R.id.listaEsercizi);
        adapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listaEsercizi.setAdapter(adapter);
        ref=FirebaseDatabase.getInstance().getReference("Nuotatori").child("sII8wdAxfBWBgg0HBspchchPSDk1").child("Esercizi"); //metto id nuotatore?
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data:dataSnapshot.getChildren()){
                    Esercizi e=data.getValue(Esercizi.class);
                    list.add(e);
                }


                }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Log.w(TAG, "failed")

            }
        });
    }
}



