package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.screens.Home;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayoutsignup;
    EditText eUsername;
    EditText ePassword;
    Button eLogin;
    TextView etextView;


    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    String Username, Password;

    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//            Toast.makeText(this,"Awesome!", Toast.LENGTH_SHORT).show();

        firebaseAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Toast.makeText(MainActivity.this, "Logged IN", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Home.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(MainActivity.this, "Please Log In to continue", Toast.LENGTH_SHORT).show();
                }
            }
        };

        linearLayoutsignup = findViewById(R.id.signUPLayout);
        eUsername = findViewById(R.id.username);
        ePassword = findViewById(R.id.password);
        eLogin = findViewById(R.id.btnLogin);
        etextView = findViewById(R.id.login);

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Username = eUsername.getText().toString();
                Password = ePassword.getText().toString();

                Log.d(TAG, Username);
                Log.d(TAG, Password);

                validateUser(Username, Password);

            }
        });

        linearLayoutsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newScreenIntent = new Intent(MainActivity.this, SignUp.class);
//                newScreenIntent.putExtra("email",Username);
                startActivity(newScreenIntent);
                finish();

            }
        });


    }

    private void validateUser(String username, String password) {
        if (TextUtils.isEmpty(username)){
            Toast.makeText(this, "Please enter email address", Toast.LENGTH_SHORT).show();
        }
        else if (password.isEmpty()){
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(username).matches()){
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
        }
        else if(password.length()<6){
            Toast.makeText(this, "Password should be more than 6 char", Toast.LENGTH_SHORT).show();
        }
        else{
            LoginUserInFirebase(username,password);
        }



//


    }

    private void LoginUserInFirebase(String validUsername, String validPassword) {
        firebaseAuth.signInWithEmailAndPassword(validUsername, validPassword).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intentLoginSuccess = new Intent(MainActivity.this, Home.class);
                    startActivity(intentLoginSuccess);
                    Toast.makeText(MainActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    protected void onStart() {
        firebaseAuth.addAuthStateListener(authStateListener);
        super.onStart();
    }


}
