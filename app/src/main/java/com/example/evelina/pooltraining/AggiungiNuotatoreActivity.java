package com.example.evelina.pooltraining;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ListView;
        import android.widget.TextView;


public class AggiungiNuotatoreActivity extends AppCompatActivity {

    private ListView listaNuotatoriLiberi;
    private TextView aggiunta;
    private NuotatoriAdapter adapter;
    private nuotoDatabase archivio = new nuotoDatabase();
    private final static String idNull = "null";
    private String idAllenatore;
    private String idNuotatore;
    private String nome;
    private String cognome;
    //private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiungi_nuotatore);
        idAllenatore=getIntent().getStringExtra("chiave");
      // firebaseAuth= FirebaseAuth.getInstance();
        //idAllenatore=firebaseAuth.getCurrentUser().getUid();
        listaNuotatoriLiberi = (ListView) findViewById(R.id.listaNuotatoriLiberi);
        adapter = new NuotatoriAdapter(this);
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

                //listaNuotatoriLiberi.getItemAtPosition(position);
                nome=archivio.nomiNuotatoriLiberi.get(position);
                cognome=archivio.cognomiNuotatoriLiberi.get(position);
                Nuotatori n=new Nuotatori(nome,cognome);
                idNuotatore=archivio.idNuotatoriLiberi.get(position);
                archivio.addNuotatoreLista(n,idAllenatore, idNuotatore);
                Intent listaNuotatori=new Intent(getApplicationContext(), ListaNuotatoriActivity.class);
                startActivity(listaNuotatori);

            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        archivio.terminaOsservazioneNuotatoriLiberi();
    }


    }
