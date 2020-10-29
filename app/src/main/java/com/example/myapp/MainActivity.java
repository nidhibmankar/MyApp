package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.screens.Home;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

LinearLayout linearLayoutsignup;
    EditText eUsername;
    EditText ePassword;
    Button eLogin;
    TextView etextView;

    String Username, Password;

    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//            Toast.makeText(this,"Awesome!", Toast.LENGTH_SHORT).show();

        linearLayoutsignup =  findViewById(R.id.signUPLayout);
        eUsername = findViewById(R.id.username);
        ePassword = findViewById(R.id.password);
        eLogin = findViewById(R.id.btnLogin);
        etextView = findViewById(R.id.login);

        linearLayoutsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newScreenIntent = new Intent(MainActivity.this, Home.class);
//                newScreenIntent.putExtra("email",Username);
                startActivity(newScreenIntent);
                finish();

            }
        });

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Username=eUsername.getText().toString();
                Password=ePassword.getText().toString();

                Log.d(TAG,Username);
                Log.d(TAG,Password);



            }
        });

    }


}
