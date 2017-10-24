package com.example.evelina.pooltraining;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="we" ;
    private TextView textregistrazione;
    private Button accedi;
    private Button registrati;
    private EditText password;
    private EditText email;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }


        };


        if (mAuth.getCurrentUser() != null) {
            finish();
            Intent settimana = new Intent(getApplicationContext(), ListaSettimana.class);
            startActivity(settimana);

        }

        password = (EditText) findViewById(R.id.editTextPassword);
        email = (EditText) findViewById(R.id.editTextMail);
        registrati = (Button) findViewById(R.id.buttonRegistrati);
        accedi = (Button) findViewById(R.id.buttonAccedi);
        textregistrazione = (TextView) findViewById(R.id.textViewRegistrazione);


        registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrazione = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(registrazione);

            }
        });
        accedi.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Login();


                                      }
                                  }
        );
    }

    private void Login() {
        String email2 = email.getText().toString().trim();
        String password2 = password.getText().toString().trim();
        mAuth.signInWithEmailAndPassword(email2, password2).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                if (task.isSuccessful()) {
                    finish();
                    Intent settimana = new Intent(getApplicationContext(), ListaNuotatori.class);
                    startActivity(settimana);

                }


            }
        });

    }
}
