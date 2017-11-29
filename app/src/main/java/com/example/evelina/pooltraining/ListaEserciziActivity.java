package com.example.evelina.pooltraining;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.support.design.widget.FloatingActionButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.evelina.pooltraining.R.id.parent;


public class ListaEserciziActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    final Context context = this;
    private FloatingActionButton buttonAggiungiEse;
    private nuotoDatabase archivio = new nuotoDatabase();
    private String idNuotatore;
    private String idEsercizio;
    private String weekday;
    private int numeroModificato;
    private ListView listaEsercizi;
    private EserciziAdapter adapter;
    private TextView nuovoNumeroVasche;
    private Spinner numeriVasche;
    private Button confermaModifica;
    private Button annullaModifica;
    private  AlertDialog dialog;
    private AlertDialog.Builder builder;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_esercizi);

        idNuotatore = getIntent().getStringExtra("idNuotatore");
        weekday = getIntent().getStringExtra("giorno");

        listaEsercizi = (ListView) findViewById(R.id.listaEsercizi);
        buttonAggiungiEse = (FloatingActionButton) findViewById(R.id.floatingActionButtonAggiungiEse);
        nuovoNumeroVasche=(TextView) findViewById(R.id.textViewNuovoNumeroVasche) ;

        adapter = new EserciziAdapter(this);
        buttonAggiungiEse.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaEserciziActivity.this, AggiungiEserciziActivity.class);
                intent.putExtra("idNuotatore", idNuotatore);
                intent.putExtra("giorno", weekday);
                startActivity(intent);
            }
        });
        listaEsercizi.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                idEsercizio=archivio.idEsercizi.get(position);
                showPopup(view);

                return true;
            }
        });


        archivio.leggiEsercizi(idNuotatore, weekday, new nuotoDatabase.UpdateListenerE() {
        @Override
        public void eserciziAggiornati() {
            adapter.update(archivio.elencoEsercizi());
        }
    });


        listaEsercizi.setAdapter(adapter);
}
    public void showPopup(View v) {

        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(ListaEserciziActivity.this);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_riga_esercizio, popup.getMenu());
        popup.show();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        archivio.terminaOsservazioneEsercizi(idNuotatore);
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.Modifica:
                AlertDialog.Builder builder= new AlertDialog.Builder(ListaEserciziActivity.this);
                LayoutInflater li = LayoutInflater.from(context);
                View dialogView = li.inflate(R.layout.modifica_nvasche, null);
                confermaModifica=(Button)dialogView.findViewById(R.id.buttonConfermaModifica);
                annullaModifica=(Button)dialogView.findViewById(R.id.buttonAnnullaModifica);
                numeriVasche =(Spinner)dialogView.findViewById(R.id.spinnerNumeriVasche);

                ArrayAdapter<String> array = new ArrayAdapter<String>(ListaEserciziActivity.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.NumeroVasche));array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                numeriVasche.setAdapter(array);
                numeriVasche.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        numeroModificato = Integer.parseInt((String) numeriVasche.getSelectedItem());
                    }
                    @Override

                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                builder.setView(dialogView);

                dialog = builder.create();
                dialog.show();

                confermaModifica.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        archivio.modificaEsercizio(idNuotatore, idEsercizio, weekday, numeroModificato);
                        dialog.dismiss();
                    }


                });






                annullaModifica.setOnClickListener((new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }

                }));

                return true;
            case R.id.Cancella:
                Toast toast2 = Toast.makeText(context, "Esercizio cancellato", Toast.LENGTH_SHORT);
                toast2.show();
                archivio.rimuoviEsercizio(idNuotatore, weekday, idEsercizio);


                return true;
            default:
                return false;
        }
}
}




