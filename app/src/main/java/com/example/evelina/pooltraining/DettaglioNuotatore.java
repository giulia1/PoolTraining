package com.example.evelina.pooltraining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DettaglioNuotatore extends AppCompatActivity {
    private final static String EXTRA_STUDENTE = "studente";
    private TextView mCognome;
    private TextView mNome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettaglio_nuotatore);
        mCognome = (TextView)findViewById(R.id.textCognome);
        mNome = (TextView)findViewById(R.id.textNome);

        // Ottengo i dati passati ed eventualmente visualizzo lo studente
        Intent intent = getIntent();
        Nuotatori n = (Nuotatori)intent.getSerializableExtra(EXTRA_STUDENTE);

        if (n != null) {

            mCognome.setText(n.getCognomeNuotatore());
            mNome.setText(n.getNomeNuotatore());

        }
    }
}
