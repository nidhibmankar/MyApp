package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    private EditText emailText, passText, mobileText, usernameText, cpass;
    Button button;

    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        // getting the current instance of the database from the firebase
        firebaseAuth = FirebaseAuth.getInstance();

        //TODO: new c_pass ; validate email , password , mobile number;

        // Initialization
        usernameText = (EditText) findViewById(R.id.username);   //username
        emailText = (EditText) findViewById(R.id.email);         //email
        mobileText = (EditText) findViewById(R.id.mobile);       //mobile
        passText = (EditText) findViewById(R.id.SignUPpassword); //password
        cpass = (EditText) findViewById(R.id.SignUPCnfpassword); //confirm password

        button = (Button) findViewById(R.id.btnSignUp);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


              final String email = emailText.getText().toString();
              final String pass = passText.getText().toString();
              final String mobile = mobileText.getText().toString();
              final String username = usernameText.getText().toString();


                Query usermobileQuery = FirebaseDatabase.getInstance().getReference().child("Users").orderByChild("username").equalTo(username);

                usermobileQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.getChildrenCount() > 0) {
                            Toast.makeText(SignUp.this, "Username already exist", Toast.LENGTH_LONG).show();
                        }

                        else {
                            firebaseAuth.createUserWithEmailAndPassword(email, pass)
                                    .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(SignUp.this, "Sign Up Success", Toast.LENGTH_SHORT).show();

                                                // Get current Userid
                                                String Userid = firebaseAuth.getCurrentUser().getUid();

                                                // Get Database reference of current user against userid
                                                DatabaseReference Current_User_Referance = FirebaseDatabase.getInstance().getReference().child("Users").child(Userid);

                                                // Create Map to read and write
                                                Map userPost = new HashMap();
                                                userPost.put("username", username);
                                                userPost.put("email", email);
                                                userPost.put("mobile", mobileText);
                                                userPost.put("password", pass);

                                                // Save data to database
                                                Current_User_Referance.setValue(userPost);
                                            } else {
                                                Toast.makeText(SignUp.this, "SignUP Error", Toast.LENGTH_SHORT).show();
                                            }


//                                           if(TextUtils.isEmpty(pass)){
//                                               passText.setError("Password is required");
//                                               return;
//                                           }
//
//                                           if(pass.length() < 6){
//                                               passText.setError("Password must be greater than 6");
//                                               return;
//                                           }


                                        }
                                    });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(SignUp.this, "SignUp cancel : " + error, Toast.LENGTH_SHORT).show();

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


}