package com.example.evelina.pooltraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AggiungiEserciziActivity extends AppCompatActivity {
    private TextView nomeNuotatore;
    private TextView cognomeNuotatore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiungi_esercizi);
        nomeNuotatore=(TextView)findViewById(R.id.textViewnomenuotatore);
        cognomeNuotatore=(TextView)findViewById(R.id.textViewcognomenuotatore);
        nomeNuotatore.setText(getIntent().getStringExtra("nomeNuotatore"));
        cognomeNuotatore.setText(getIntent().getStringExtra("cognomeNuotatore"));

    }
}
