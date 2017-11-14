package com.example.evelina.pooltraining;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.List;

/**
 * Created by Sony on 22/10/2017.
 */

public class NuotatoriAdapter extends BaseAdapter {

    List<Nuotatori> nuotatori=Collections.emptyList();
    private Context context;
    private String nome;
    private String cognome;

    public NuotatoriAdapter(Context context) {
        this.context=context;
        


    }
    @Override
    public int getCount() {
        return nuotatori.size();
    }

    @Override
    public Object getItem(int position) {
        return nuotatori.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public void update(List<Nuotatori> newList) {
        nuotatori = newList;
        notifyDataSetChanged();
    }
    public String getNome(int position){return nome;}
    public String getCognome(int position){return cognome;}


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.riga_nuotatore, parent, false);

        TextView textNomeNuotatore = (TextView)convertView.findViewById(R.id.textViewNomeNuotatore);
        TextView textCognomeNuotatore = (TextView)convertView.findViewById(R.id.textViewCognomeNuotatore);
        nome=textNomeNuotatore.getText().toString();
        cognome=textCognomeNuotatore.getText().toString();



        // Imposto i valori da visualizzare
        Nuotatori nuotatore = nuotatori.get(position);
        textCognomeNuotatore.setText(nuotatore.getCognomeNuotatore());
        textNomeNuotatore.setText(nuotatore.getNomeNuotatore());


        return convertView;
    }
}