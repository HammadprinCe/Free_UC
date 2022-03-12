package com.example.free_uc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.utilities.Validation;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button btn, btnAlready;
    EditText editText1;
    EditText editText2;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        FirebaseDatabase fbd = FirebaseDatabase.getInstance();
        DatabaseReference reference = fbd.getReference();
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("chak boh la pecha...");
        btn = findViewById(R.id.btnsign);
        Toolbar toolbar = new Toolbar(this);
        setSupportActionBar(toolbar);
        btnAlready = findViewById(R.id.btnAlready);
        editText1 = findViewById(R.id.editTextEmail);
        editText2 = findViewById(R.id.editTextPassword);

        FirebaseFirestore dbfs = FirebaseFirestore.getInstance();


        btnAlready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
                finish();
            }
        });
//    btn sign in
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                String email = editText1.getText().toString();
                String password = editText2.getText().toString();
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("email", email);
                map.put("password", password);




                dbfs.collection("users").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(@NonNull DocumentReference documentReference) {

                        Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_SHORT).show();

                    }
                });
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        dialog.dismiss();
                        if (task.isSuccessful()) {
                            reference.child("users").push().setValue(map);
                            Toast.makeText(getApplicationContext(), "successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });


    }
}