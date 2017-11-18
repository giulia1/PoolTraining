package com.example.evelina.pooltraining;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LunActivity extends AppCompatActivity {

    private FloatingActionButton aggiungi;
    final Context context = this;
    private Button annullaEsercizio;
    private Button aggiungiEsercizio;
    private nuotoDatabase archivio;
    private EditText nomeEsercizioAggiunto;
    private EditText numeroVascheAggiunte;
    private String nomeEsercizio;
    private String idNuotatore;
    private String KEY_GIORNO = "Lunedi";
    private int nVasche;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lun);
        archivio = new nuotoDatabase();
        idNuotatore = getIntent().getStringExtra("idNuotatore");
        aggiungi = (FloatingActionButton) findViewById(R.id.buttonAggiungiEsercizio);



        aggiungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.nuovo_esercizio, null);
               AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LunActivity.this);
                alertDialogBuilder.setView(promptsView);
                annullaEsercizio = (Button) findViewById(R.id.buttonAnnullaEsercizio);
                aggiungiEsercizio = (Button) findViewById(R.id.buttonAggiungiEsercizio);
                nomeEsercizioAggiunto = (EditText) findViewById(R.id.editTextNomeEsercizioAggiunto);
                numeroVascheAggiunte = (EditText) findViewById(R.id.editTextNumeroVascheAggiunte);

                aggiungiEsercizio.setOnClickListener(new View.OnClickListener() {
                                                         @Override
                                                         public void onClick(View v) {
                                                             nomeEsercizio = nomeEsercizioAggiunto.getText().toString();
                                                             nVasche = Integer.parseInt(numeroVascheAggiunte.toString());
                                                             archivio.addEsercizio(idNuotatore, KEY_GIORNO, nVasche, nomeEsercizio);

                                                         }
                                                     }
                );
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }
}