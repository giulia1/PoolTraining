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
import android.widget.ListView;

public class GiornoActivity extends Activity {

    final Context context = this;
    private FloatingActionButton aggiungi;
    private Button annullaEsercizio;
    private Button aggiungiEsercizio;
    private nuotoDatabase  archivio = new nuotoDatabase();
    private EditText nomeEsercizioAggiunto;
    private EditText numeroVascheAggiunte;
    private String nomeEsercizio;
    private String idNuotatore;
    private String KEY_GIORNO = "Lunedi";
    private int nVasche;
    private AlertDialog alertDialog;
    private ListView listaEsercizi;
    private EserciziAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giorno);
        //dNuotatore = getIntent().getStringExtra("idNuotatore");
        aggiungi = (FloatingActionButton) findViewById(R.id.buttonAggiungiEse);
        listaEsercizi = (ListView) findViewById(R.id.listaEserciziAllenatore);
        adapter = new EserciziAdapter(this);
        archivio.leggiEsercizi(idNuotatore, KEY_GIORNO, new nuotoDatabase.UpdateListener() {
            @Override
            public void eserciziAggiornati() {
                adapter.update(archivio.elencoEsercizi());
            }
        });

        listaEsercizi.setAdapter(adapter);
        aggiungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.nuovo_esercizio, null);
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(GiornoActivity.this);
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
                annullaEsercizio.setOnClickListener((new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }

                }));

                alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }



    }

