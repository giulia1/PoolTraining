package com.example.evelina.pooltraining;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Collections;
import java.util.*;

/**
 * Created by Utente on 27/10/2017.
 */

public class NuotatoriLiberiAdapter extends BaseAdapter {
    private List<Nuotatori> nuotatoriLiberi= Collections.emptyList();
    private Context context;
    private String nome;
    private String cognome;

    public NuotatoriLiberiAdapter(Context context) {
            this.context=context;

    }
    @Override
    public int getCount() {
        return nuotatoriLiberi.size();
    }

    @Override
    public Object getItem(int position) {
        return nuotatoriLiberi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public void update(List<Nuotatori> newList) {
        nuotatoriLiberi = newList;
        notifyDataSetChanged();
    }
public String getNome(int position){return nome;}
    public String getCognome(int position){return cognome;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.riga_nuotatore_libero, parent, false);

        TextView textNomeNuotatoreLibero = (TextView)convertView.findViewById(R.id.textNomeNuotatoreLibero);
        TextView textCognomeNuotatoreLibero = (TextView)convertView.findViewById(R.id.textCognomeNuotatoreLibero);
        nome=textNomeNuotatoreLibero.getText().toString();
        cognome=textCognomeNuotatoreLibero.getText().toString();


        // Imposto i valori da visualizzare
        Nuotatori nuotatore = nuotatoriLiberi.get(position);
        textCognomeNuotatoreLibero.setText(nuotatore.getCognomeNuotatore());
        textNomeNuotatoreLibero.setText(nuotatore.getNomeNuotatore());


        return convertView;
    }
}
