package com.example.evelina.pooltraining;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.TextView;

        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;



public class AggiungiNuotatoreActivity extends AppCompatActivity {

    private ListView listaNuotatoriLiberi;
    private TextView aggiunta;
    private NuotatoriLiberiAdapter adapter;
    private nuotoDatabase archivio = new nuotoDatabase();
    private final static String idNull = "null";
    private String idAllenatore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiungi_nuotatore);
        aggiunta=(TextView)findViewById(R.id.textViewAggiungiNuotatore);
        idAllenatore=getIntent().getStringExtra("chiave");
        listaNuotatoriLiberi = (ListView) findViewById(R.id.listaNuotatoriLiberi);
        adapter = new NuotatoriLiberiAdapter(this);
        archivio.leggiNuotatoriLiberi(new nuotoDatabase.UpdateListenerNL() {
            @Override
            public void nuotatoriLiberiAggiornati() {
                adapter.update(archivio.elencoNuotatoriLiberi());
            }
        });

        listaNuotatoriLiberi.setAdapter(adapter);
        listaNuotatoriLiberi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String nome=adapter.getNome(position);
                String cognome=adapter.getCognome(position);
                Nuotatori n=new Nuotatori(nome,cognome, idNull);
                String idNuotatore=archivio.restituisciId(nome,cognome);
                archivio.addNuotatoreLista(n,idAllenatore, idNuotatore);
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        archivio.terminaOsservazioneNuotatoriLiberi();
    }


    }
