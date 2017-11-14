package com.example.evelina.pooltraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private Button registrazione;
    private EditText nome;
    private EditText cognome;
    private EditText mail;
    private EditText password;
    private nuotoDatabase archivio = new nuotoDatabase();
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();
        registrazione = (Button) findViewById(R.id.buttonRegistrazione);
        cognome = (EditText) findViewById(R.id.editTextCognome);
        nome = (EditText) findViewById(R.id.editTextNome);
        mail = (EditText) findViewById(R.id.editTextMail2);
        password = (EditText) findViewById(R.id.editTextPassword2);
        registrazione.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String nome2 = nome.getText().toString().trim();
                String cognome2 = cognome.getText().toString().trim();
                String password2 = password.getText().toString().trim();
                String mail2 = mail.getText().toString().trim();
                Allenatori a=new Allenatori(nome2,cognome2);

                Log.v("Log", "onclick");
                firebaseAuth.createUserWithEmailAndPassword(mail2, password2).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //archivio.addAllenatore(a);
                            Intent login = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(login);
                            //finish();
                            Toast.makeText(RegisterActivity.this, "Registrazione completata con successo", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e("errore", task.getException().toString());
                            Toast.makeText(RegisterActivity.this, "Registrazione non è andata a buon fine!", Toast.LENGTH_SHORT).show();




                        }
                    }







                });
            }


        });
    }
}


