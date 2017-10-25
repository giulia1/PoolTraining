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
    private List<Nuotatori> nuotatori=Collections.emptyList();
    private Context context;

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.riga_nuotatore, parent, false);

        TextView textNomeNuotatore = (TextView)convertView.findViewById(R.id.textViewNomeNuotatore);
        TextView textCognomeNuotatore = (TextView)convertView.findViewById(R.id.textViewCognomeNuotatore);


        // Imposto i valori da visualizzare
        Nuotatori nuotatore = nuotatori.get(position);
        textCognomeNuotatore.setText(nuotatore.getCognomeNuotatore());
        textNomeNuotatore.setText(nuotatore.getNomeNuotatore());


        return convertView;
    }
}