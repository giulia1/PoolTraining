package com.example.evelina.pooltraining;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Sony on 24/10/2017.
 */

public class NuotatoriAdapter2 extends BaseAdapter {
    private ArrayList<Nuotatori> array;
    private LayoutInflater inflater;

    public NuotatoriAdapter2(Context context, ArrayList<Nuotatori> array) {
        this.array = array;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int position) {
        return array.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.riga_nuotatore, parent, false);
        }
        TextView textNomeNuotatore = (TextView)convertView.findViewById(R.id.textViewNomeNuotatore);
        TextView textCognomeNuotatore = (TextView)convertView.findViewById(R.id.textViewCognomeNuotatore);
        // Imposto i valori da visualizzare
        Nuotatori n = array.get(position);
        textCognomeNuotatore.setText(n.getCognomeNuotatore());
        textNomeNuotatore.setText(n.getNomeNuotatore());
        return convertView;
    }}