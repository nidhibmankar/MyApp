package com.example.myapp.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity {

    LinearLayout startLayout, welcomeLayout;
    TextView textViewStart, textViewWelcome, textViewDBName;

    EditText dbEditText;

    Button submitButton, deleteDbButton;

    String dbName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        FirebaseDatabase database;
        database = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = database.getReference("ClassDatabase");


        startLayout =(LinearLayout)findViewById(R.id.startNew);
        welcomeLayout = (LinearLayout)findViewById(R.id.welcomeNew);
        textViewStart = (TextView)findViewById(R.id.clickToStart);
        textViewWelcome = (TextView)findViewById(R.id.lestStart);
        textViewDBName = (TextView)findViewById(R.id.databaseName);

        dbEditText = (EditText)findViewById(R.id.nameDB);

        submitButton =(Button)findViewById(R.id.submit);
        deleteDbButton = (Button)findViewById(R.id.deleteDB);


       submitButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
             dbName = dbEditText.getText().toString();
             databaseReference.setValue(dbName);
           }
       });

        deleteDbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               databaseReference.removeValue();

                welcomeLayout.setVisibility(View.GONE);
                startLayout.setVisibility(View.VISIBLE);
            }
        });



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String childDBName = String.valueOf(snapshot.getValue());
                textViewDBName.setText(childDBName);
                if (childDBName == null){
                    Toast.makeText(Home.this, "Empty", Toast.LENGTH_LONG).show();
                    startLayout.setVisibility(View.VISIBLE);
                    welcomeLayout.setVisibility(View.GONE);}
                else {
                startLayout.setVisibility(View.GONE);
                welcomeLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.d("ErrorOnDataCallBack", "Error:"+error);

            }
        });

    }
}