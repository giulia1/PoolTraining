package com.example.evelina.pooltraining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;




public class ListaEserciziActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private ListView listaEsercizi;
    private String idNuotatore;
    private String weekday;
    private EserciziAdapter adapter;
    private nuotoDatabase archivio = new nuotoDatabase();
    private FloatingActionButton buttonAggiungiEse;


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

                Intent aggiungiEsercizi = new Intent(getApplicationContext(), AggiungiEserciziActivity.class);
                startActivity(aggiungiEsercizi);


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

                Toast.makeText(getApplicationContext(), "hai cliccato modifica " , Toast.LENGTH_SHORT).show();

                return true;
            case R.id.Cancella:

                Toast.makeText(getApplicationContext(), "hai cliccato cancella " , Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }
}



