package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText eUsername;
    EditText ePassword;
    Button eLogin;
    TextView etextView;

    String Username,Password;

    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eUsername = findViewById(R.id.username);
        ePassword = findViewById(R.id.password);
        eLogin = findViewById(R.id.btnLogin);
        etextView = findViewById(R.id.login);

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Username=eUsername.getText().toString();
                Password=ePassword.getText().toString();

                Log.d(TAG,Username);
                Log.d(TAG,Password);

                Intent newScreenIntent = new Intent(MainActivity.this, SignUp.class);
                newScreenIntent.putExtra("email",Username);
                startActivity(newScreenIntent);
                finish();


            }
        });

    }


    }
