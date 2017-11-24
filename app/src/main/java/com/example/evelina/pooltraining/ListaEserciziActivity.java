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




public class ListaEserciziActivity extends AppCompatActivity {


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
    private nuotoDatabase archivio = new nuotoDatabase();

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


        archivio.leggiEsercizi(idNuotatore, weekday, new nuotoDatabase.UpdateListener() {
            @Override
            public void eserciziAggiornati() {
                adapter.update(archivio.elencoEsercizi());
            }
        });

        listaEsercizi.setAdapter(adapter);

    }
}




