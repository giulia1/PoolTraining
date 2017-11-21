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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private Button registrazione;
    private EditText nomeField;
    private EditText cognomeField;
    private EditText mailField;
    private EditText passwordField;
    private nuotoDatabase archivio = new nuotoDatabase();
    private FirebaseAuth firebaseAuth;
    private OnCompleteListener mRegistrationListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        registrazione = (Button) findViewById(R.id.buttonRegistrazione);
        cognomeField = (EditText) findViewById(R.id.editTextCognome);
        nomeField = (EditText) findViewById(R.id.editTextNome);
        mailField = (EditText) findViewById(R.id.editTextMail2);
        passwordField = (EditText) findViewById(R.id.editTextPassword2);

        mRegistrationListener = new OnCompleteListener<Void>()

        {

            @Override

            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    finish();
                    Intent mainActivity = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(mainActivity);
                } else {
                    // If sign in fails, display a message to the user.

                    Log.w("Log", "createUserWithEmail:failure", task.getException());

                    //Toast.makeText(RegisterActivity.this, task.getException().getLocalizedMessage(),

                    //Toast.LENGTH_SHORT).show();
                }
            }
        };


        registrazione.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String password = passwordField.getText().toString().trim();
                String mail = mailField.getText().toString().trim();

                Log.v("Log", "onclick");
                firebaseAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Allenatori a = new Allenatori(nomeField.getText().toString(), cognomeField.getText().toString());
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("Allenatori");
                            //archivio.addAllenatoreDB(a);
                            myRef.child(task.getResult().getUser().getUid()).setValue(a).addOnCompleteListener(mRegistrationListener);
                            Log.d("log", "createUserWithEmail:success");
                            Toast.makeText(RegisterActivity.this, "Registrazione completata con successo", Toast.LENGTH_SHORT).show();
                            //Intent login = new Intent(getApplicationContext(), MainActivity.class);
                            //startActivity(login);

                        }


                        //finish();

                        else {
                            Log.e("errore", task.getException().toString());
                            Toast.makeText(RegisterActivity.this, "Registrazione non è andata a buon fine!", Toast.LENGTH_SHORT).show();


                        }
                    }


                });
            }


        });
    }
    private boolean validateForm() {
        boolean valid = true;

        String name = nomeField.getText().toString();
        if (TextUtils.isEmpty(name)) {
            nomeField.setError("Required.");
            valid = false;
        } else {
            nomeField.setError(null);
        }

        String email = mailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mailField.setError("Required.");
            valid = false;
        } else {
            mailField.setError(null);
        }

        String password = passwordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordField.setError("Required.");
            valid = false;
        } else {
            passwordField.setError(null);
        }

        return valid;
    }

}




