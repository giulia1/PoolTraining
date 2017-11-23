package com.example.evelina.pooltraining;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;




public class ListaEserciziActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {



    final Context context = this;

    private FloatingActionButton buttonAggiungiEse;
    private Button annullaEsercizioAggiunto;
    private Button aggiungiEsercizio;
    private Button buttonAnnulla;
    private Button buttonConferma;

    private TextView textViewNVasche;
    private EditText editTextNomeEsercizioAggiunto;
    private EditText numeroVascheAggiunte;

    private Spinner spinnerNVasche;
    private Spinner spinnerNomiEse;
    private nuotoDatabase  archivio = new nuotoDatabase();

    private String nomeEsercizioAggiunto;
    private String nomeEsercizioModificato;
    private String idNuotatore;
    private String KEY_GIORNO = "Lunedi";
    private String weekday;
    private int nVascheAggiunte;
    private int nVascheModificate;

    private AlertDialog alertDialogModifica;
    private AlertDialog alertDialogAggiungi;
    private ListView listaEsercizi;
    private EserciziAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_esercizi);

        listaEsercizi = (ListView) findViewById(R.id.listaEsercizi);
        buttonAggiungiEse = (FloatingActionButton) findViewById(R.id.floatingActionButtonAggiungiEse);
        adapter = new EserciziAdapter(this);

        idNuotatore = getIntent().getStringExtra("idNuotatore");
        weekday = getIntent().getStringExtra("giorno");
        
        archivio.leggiEsercizi(idNuotatore, weekday, new nuotoDatabase.UpdateListenerE() {
            @Override
            public void eserciziAggiornati() {
                adapter.update(archivio.elencoEsercizi());
            }
        });

        listaEsercizi.setAdapter(adapter);
        buttonAggiungiEse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.nuovo_esercizio, null);
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ListaEserciziActivity.this);
                alertDialogBuilder.setView(promptsView);

                annullaEsercizioAggiunto = (Button) findViewById(R.id.buttonAnnullaEsercizio);
                aggiungiEsercizio = (Button) findViewById(R.id.buttonAggiungiEsercizio);
                editTextNomeEsercizioAggiunto = (EditText) findViewById(R.id.editTextNomeEsercizioAggiunto);
                numeroVascheAggiunte = (EditText) findViewById(R.id.editTextNumeroVascheAggiunte);

                aggiungiEsercizio.setOnClickListener(new View.OnClickListener() {
                                                         @Override
                                                         public void onClick(View v) {
                                                             nomeEsercizioAggiunto = editTextNomeEsercizioAggiunto.getText().toString();
                                                             nVascheAggiunte = Integer.parseInt(numeroVascheAggiunte.toString());
                                                             archivio.addEsercizio(idNuotatore, KEY_GIORNO, nVascheAggiunte, nomeEsercizioAggiunto);

                                                         }
                                                     }
                );
                annullaEsercizioAggiunto.setOnClickListener((new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialogAggiungi.dismiss();
                    }

                }));

                alertDialogAggiungi = alertDialogBuilder.create();
                alertDialogAggiungi.show();
            }
        });





        listaEsercizi.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                showPopup(view);

                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        archivio.terminaOsservazioneEsercizi(idNuotatore);
    }


    public void showPopup(View v) {

        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(ListaEserciziActivity.this);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_riga_esercizio, popup.getMenu());
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Modifica:

                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.modifica_nvasche, null);
                final AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(ListaEserciziActivity.this);
                alertDialogBuilder2.setView(promptsView);

                buttonAnnulla = (Button) findViewById(R.id.buttonAnnulla);
                buttonConferma = (Button) findViewById(R.id.buttonConferma);
                textViewNVasche = (TextView) findViewById(R.id.textViewNuovoNumeroVasche);

                spinnerNVasche=(Spinner)findViewById(R.id.spinnerNumeriVasche);
                ArrayAdapter<String> vascheArray= new ArrayAdapter<String>(ListaEserciziActivity.this,android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.NumeriVasche));
                vascheArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerNVasche.setAdapter(vascheArray);
               //come prendere il nome dell esercizio su cui ho cliccato?
                nomeEsercizioModificato=item.toString();
                spinnerNVasche.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


                    @Override

                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        nVascheModificate = Integer.parseInt((String) spinnerNVasche.getSelectedItem());
                    }


                    @Override

                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        }


        buttonConferma.setOnClickListener(new View.OnClickListener() {
                                                         @Override
                                                         public void onClick(View v) {
                                                             archivio.modificaEsercizio(idNuotatore, nomeEsercizioModificato, weekday, nVascheModificate);



                                                         }
                                                     }
                );
                buttonAnnulla.setOnClickListener((new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialogModifica.dismiss();
                    }

                }));





                alertDialogModifica = alertDialogBuilder2.create();
                alertDialogModifica.show();

                return true;
            case R.id.Cancella:
                //nomeEse
        //archivio.rimuoviEsercizio();
                return true;
            default:
                return false;
        }
    }




