package com.example.free_uc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {



    Button next,btn;
    EditText editText;
    EditText editText0;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        auth=FirebaseAuth.getInstance();
        btn=findViewById(R.id.btnsign2);
        editText=findViewById(R.id.editTextEmail2);
        editText0=findViewById(R.id.editTextPassword2);


        next=findViewById(R.id.next);



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity2.this,readData.class);
                startActivity(intent);
                finish();

            }
        });


btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String email=editText.getText().toString();
        String password=editText0.getText().toString();
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful()){

                    Intent intent= new Intent(MainActivity2.this,wlcome_screen.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(getApplicationContext(), "successful", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
});

    }
}