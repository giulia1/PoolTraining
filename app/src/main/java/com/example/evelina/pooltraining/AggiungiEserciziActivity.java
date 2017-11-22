package com.example.evelina.pooltraining;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class AggiungiEserciziActivity extends TabActivity {

    private TabHost tabHost;
    private String giorno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiungi_esercizi2);
        tabHost=(TabHost)findViewById(android.R.id.tabhost);

        TabHost.TabSpec lun=tabHost.newTabSpec("lun");
        TabHost.TabSpec mar=tabHost.newTabSpec("mar");
        TabHost.TabSpec mer=tabHost.newTabSpec("mer");
        TabHost.TabSpec gio=tabHost.newTabSpec("gio");
        TabHost.TabSpec ven=tabHost.newTabSpec("ven");
        TabHost.TabSpec sab=tabHost.newTabSpec("sab");

        Intent intent=new Intent(this, GiornoActivity.class);


        lun.setIndicator("Lun");
        lun.setContent(intent);
        //giorno=lun.getTag();

        mar.setIndicator("Mar");
        mar.setContent(intent);
        //intent.putExtra("giorno", "Mar");

        mer.setIndicator("Mer");
        mer.setContent(intent);
        //intent.putExtra("giorno", "Mer");

        gio.setIndicator("Gio");
        gio.setContent(intent);
        //intent.putExtra("giorno", "Gio");

        ven.setIndicator("Ven");
        ven.setContent(intent);
        //intent.putExtra("giorno", "Ven");


        sab.setIndicator("Sab");
        sab.getTag();
        sab.setContent(intent);
        //intent.putExtra("giorno", "Sab");

        tabHost.addTab(lun);
        tabHost.addTab(mar);
        tabHost.addTab(mer);
        tabHost.addTab(gio);
        tabHost.addTab(ven);
        tabHost.addTab(sab);
    }



    }

