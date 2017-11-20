package com.example.evelina.pooltraining;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.app.TabActivity;

@SuppressWarnings("deprecation")
public class AggiungiEserciziActivity extends TabActivity {

    private TabHost tabHost;

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

        lun.setIndicator("Lun");
        lun.setContent(new Intent(this,LunActivity.class));

        mar.setIndicator("Mar");
        mar.setContent(new Intent(this,MarActivity.class));

        mer.setIndicator("Mer");
        mer.setContent(new Intent(this,MerActivity.class));

        gio.setIndicator("Gio");
        gio.setContent(new Intent(this,GioActivity.class));

        ven.setIndicator("Ven");
        ven.setContent(new Intent(this,VenActivity.class));

        sab.setIndicator("Sab");
        sab.setContent(new Intent(this,SabActivity.class));

        tabHost.addTab(lun);
        tabHost.addTab(mar);
        tabHost.addTab(mer);
        tabHost.addTab(gio);
        tabHost.addTab(ven);
        tabHost.addTab(sab);
    }
}
