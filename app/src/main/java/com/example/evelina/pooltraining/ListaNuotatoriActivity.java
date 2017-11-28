package com.example.evelina.pooltraining;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class ListaNuotatoriActivity extends AppCompatActivity  implements PopupMenu.OnMenuItemClickListener {

    private ListView listaNuotatori;
    private FloatingActionButton aggiungi;
    private String idAllenatore;
    private NuotatoriAdapter adapter;
    private ImageButton buttonLogOut;
    final Context context = this;


    private final static String KEY_COGNOME = "cognome";
    private final static String KEY_NOME = "nome";

    private nuotoDatabase archivio = new nuotoDatabase();
    private FirebaseAuth mAuth;
    private String idNuotatore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_nuotatori);
        mAuth = FirebaseAuth.getInstance();
        idAllenatore = getIntent().getStringExtra("chiave");
        //idAllenatore=mAuth.getCurrentUser().getUid();
        aggiungi = (FloatingActionButton) findViewById(R.id.floatingActionButtonAggiungi);
        buttonLogOut = (ImageButton) findViewById(R.id.buttonLogout);

        listaNuotatori = (ListView) findViewById(R.id.listaNuotatori);
        adapter = new NuotatoriAdapter(this);
        archivio.leggiNuotatori(idAllenatore, new nuotoDatabase.UpdateListenerN() {
            @Override
            public void nuotatoriAggiornati() {
                adapter.update(archivio.elencoNuotatori());
            }
        });

        listaNuotatori.setAdapter(adapter);
        listaNuotatori.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                idNuotatore = archivio.idNuotatori.get(position);
                Intent listaSettimana = new Intent(getApplicationContext(), ListaSettimana.class);
                listaSettimana.putExtra("idNuotatore", idNuotatore);
                startActivity(listaSettimana);

            }
        });
        listaNuotatori.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showPopup(view);

                return false;
            }
        });
        aggiungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent aggiungiNuotatori = new Intent(getApplicationContext(), AggiungiNuotatoreActivity.class);
                aggiungiNuotatori.putExtra("chiave", idAllenatore);
                startActivity(aggiungiNuotatori);


            }

            ;
        });
        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.signOut();
                finish();
                Intent loginActivity = new Intent(ListaNuotatoriActivity.this, MainActivity.class);
                startActivity(loginActivity);
            }
        });

    }

    public void showPopup(View v) {

        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(ListaNuotatoriActivity.this);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.elimina_nuotatore, popup.getMenu());
        popup.show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        archivio.terminaOsservazioneNuotatori(idAllenatore);
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {


        switch (item.getItemId()) {

            case R.id.Cancella:
                Toast toast2 = Toast.makeText(context, "Nuotatore cancellato", Toast.LENGTH_SHORT);
                toast2.show();
                archivio.rimuoviNuotatore(idNuotatore, idAllenatore);


                return true;
            default:
                return false;


        }
    }
}


