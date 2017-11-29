package com.example.evelina.pooltraining;
;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class AggiungiEserciziActivity extends AppCompatActivity {

    //private String giorno;
    private String nomeEsercizioAggiunto;
    private String idNuotatore;
    private String weekday;
    private int numeroVascheAggiunte;

    private TextView nomeNuovoEsercizio;
    private TextView numeroVascheEsercizio;

    private Button buttonAggiungi;
    private Button buttonAnnulla;

    private Spinner spinnerNomiEsercizi;
    private EditText editTextNumeroVasche;
    private nuotoDatabase archivio = new nuotoDatabase();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiungi_esercizi);

        idNuotatore = getIntent().getStringExtra("idNuotatore");
        weekday = getIntent().getStringExtra("giorno");

        nomeNuovoEsercizio = (TextView) findViewById(R.id.textViewNomeNuovoEsercizio);
        numeroVascheEsercizio = (TextView) findViewById(R.id.textViewNumeroVasche);
        buttonAggiungi = (Button) findViewById(R.id.buttonAggiungiEsercizio);
        buttonAnnulla = (Button) findViewById(R.id.buttonAnnullaModifica);
        editTextNumeroVasche = (EditText) findViewById(R.id.editTextNumeroVasche);
        spinnerNomiEsercizi = (Spinner) findViewById(R.id.spinnerNomiEsercizi);


        ArrayAdapter<String> array = new ArrayAdapter<String>(AggiungiEserciziActivity.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.NomiEsercizi));array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNomiEsercizi.setAdapter(array);
        spinnerNomiEsercizi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                nomeEsercizioAggiunto = (String) spinnerNomiEsercizi.getSelectedItem();
            }


            @Override

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        buttonAggiungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeroVascheAggiunte = Integer.parseInt(editTextNumeroVasche.getText().toString());
                archivio.addEsercizio(idNuotatore, weekday, nomeEsercizioAggiunto, numeroVascheAggiunte);
                Intent intent =new Intent(AggiungiEserciziActivity.this, ListaEserciziActivity.class);
                startActivity(intent);

            }
        });
        buttonAnnulla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });
    }
}



