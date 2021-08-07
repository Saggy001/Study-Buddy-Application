package com.saggy.wielearner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    TextInputEditText emailedit,passedit;
    ImageButton loginbt;
    String email, password;
    TextView forgetpass , register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailedit = findViewById(R.id.emailedit);
        passedit = findViewById(R.id.passedit);
        loginbt = findViewById(R.id.loginbt);
        register = findViewById(R.id.signup);

        firebaseAuth = FirebaseAuth.getInstance();

        loginbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = emailedit.getText().toString();
                password = passedit.getText().toString();

                if (email.isEmpty()) {
                    emailedit.setError("Please Enter Email Id");
                    emailedit.requestFocus();
                } else if (password.isEmpty()) {
                    passedit.setError("Please Enter Your Password");
                    passedit.requestFocus();
                } else {

                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Login Error, Check Internet Connection", Toast.LENGTH_SHORT).show();

                            } else {
                                Intent intent = new Intent(MainActivity.this, CourseDetail.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, Registration_Activity.class);
                startActivity(intent1);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser != null){
            startActivity(new Intent(MainActivity.this , CourseDetail.class));
            finish();
        }
    }
}