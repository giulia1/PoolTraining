package com.example.evelina.pooltraining;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MarActivity extends Activity {

    private FloatingActionButton aggiungi;
    final Context context = this;
    private Button annullaEsercizio;
    private Button aggiungiEsercizio;
    private nuotoDatabase  archivio = new nuotoDatabase();
    private EditText nomeEsercizioAggiunto;
    private EditText numeroVascheAggiunte;
    private String nomeEsercizio;
    private String idNuotatore;
    private String KEY_GIORNO = "Martedi";
    private int nVasche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mar);

        Bundle datipassati = getIntent().getExtras();
        idNuotatore = datipassati.getString("idNuotatore");

        //aggiungi = (FloatingActionButton) findViewById(R.id.);
        aggiungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.nuovo_esercizio, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MarActivity.this);
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
