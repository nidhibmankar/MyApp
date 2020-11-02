package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {

    Button button;
    EditText emailText, passText;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailText = (EditText)findViewById(R.id.email);
        passText = (EditText)findViewById(R.id.SignUPpassword);


        button = (Button)findViewById(R.id.btnSignUp);

        firebaseAuth = FirebaseAuth.getInstance();


//        authStateListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//            }
//        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailText.getText().toString();
                String pass = passText.getText().toString();

                firebaseAuth.createUserWithEmailAndPassword(email,pass)
                        .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(SignUp.this, "Sign Up Success", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SignUp.this,MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        firebaseAuth.addAuthStateListener(authStateListener);
        super.onStart();
    }
}