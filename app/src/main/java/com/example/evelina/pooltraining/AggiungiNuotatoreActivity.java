package com.example.evelina.pooltraining;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;

        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import static com.example.evelina.pooltraining.R.id.listaNuotatori;

public class AggiungiNuotatoreActivity extends AppCompatActivity {
    private EditText nomeNuotatore;
    private EditText cognomeNuotatore;
    private Button buttonAggiungi;
    private String stringaNome;
    private String stringaCognome;
    private String idAllenatore;
    private String idNuotatore;
    private ValueEventListener listener;
    private nuotoDatabase archivio=new nuotoDatabase();
    private final static String key_nuotatori="Nuotatori";
    private final static String key_nome="Nome";
    private final static String key_cognome="Cognome";
    private final static String key_idAllenatore="ID_Allenatore";
    private final static String idNull="null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiungi_nuotatore);
        nomeNuotatore = (EditText) findViewById(R.id.editTextNomeNuotatore);
        cognomeNuotatore = (EditText) findViewById(R.id.editTextCognomeNuotatore);
        buttonAggiungi = (Button) findViewById(R.id.buttonAggiungi);
        idAllenatore=getIntent().getStringExtra("chiave");
        buttonAggiungi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                stringaNome = nomeNuotatore.getText().toString();
                stringaCognome = cognomeNuotatore.getText().toString();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference(key_nuotatori);

                listener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Nuotatori n = new Nuotatori(stringaNome, stringaCognome, idNull);

                        for (DataSnapshot elemento : dataSnapshot.getChildren()) {
                            if(elemento.child(key_nome).getValue(String.class)==stringaNome&&elemento.child(key_cognome).getValue(String.class)==stringaCognome&&elemento.child(key_idAllenatore).getValue(String.class).equals(idNull)){
                                idNuotatore=elemento.getKey();
                                archivio.addNuotatoreLista(n, idAllenatore, idNuotatore);


                            }
                            else{
                                //messaggio d errore
                            }




                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }


                };
            }
        });
    }
}