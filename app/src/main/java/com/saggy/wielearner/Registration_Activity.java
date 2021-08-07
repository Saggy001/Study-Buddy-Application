package com.saggy.wielearner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Registration_Activity extends AppCompatActivity {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    TextInputEditText editpass1 , editpass2,editname ,editemail;
    ImageButton signup;
    ProgressDialog progressDialog;
    TextView help , login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_);

        editname = findViewById(R.id.nameedit1);
        editpass1 = findViewById(R.id.passedit1);
        editpass2 = findViewById(R.id.passedit2);
        editemail = findViewById(R.id.emailedit1);
        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(Registration_Activity.this);
                progressDialog.setMessage("Processing...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setCancelable(false);
                progressDialog.show();
                final String emailid = editemail.getText().toString();
                final String name = editname.getText().toString();
                final String pass = editpass1.getText().toString();
                String repass = editpass2.getText().toString();

                if (emailid.isEmpty()) {
                    editemail.setError("Please Enter Email Id");
                    editemail.requestFocus();
                    progressDialog.dismiss();
                }
                else if (name.isEmpty()) {
                    editname.setError("Please Enter Your Name");
                    editname.requestFocus();
                    progressDialog.dismiss();
                }
                else if (pass.isEmpty()){
                    editpass1.setError("Please Enter Your Password");
                    editpass1.requestFocus();
                    progressDialog.dismiss();
                }
                else if (repass.isEmpty()){
                    editpass2.setError("Please Re-Enter Your Password");
                    editpass2.requestFocus();
                    progressDialog.dismiss();
                }
                else if (!pass.equals(repass)){
                    editpass2.setError("Entered Password is not same");
                    editpass2.requestFocus();
                    progressDialog.dismiss();
                }
                else if (pass.length()<6){
                    editpass1.setError("Password Length must be greater than 5");
                    editpass1.requestFocus();
                    progressDialog.dismiss();
                }
                else {
                    FirebaseDatabase.getInstance().getReference().addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.child("Users").child(name).exists()){
                                Toast.makeText(Registration_Activity.this, "Username is not available", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                            else {
                                firebaseAuth.createUserWithEmailAndPassword(emailid, pass).addOnCompleteListener(Registration_Activity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(Registration_Activity.this, "Registration unsuccessful! please try again", Toast.LENGTH_SHORT).show();
                                            progressDialog.dismiss();
                                        } else {
                                            progressDialog.dismiss();
                                            float x = (float) 0.0;
                                            Toast.makeText(Registration_Activity.this, "Registration successful", Toast.LENGTH_LONG).show();
                                            FirebaseUser mfirebaseuser = firebaseAuth.getCurrentUser();
                                            UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                                            assert mfirebaseuser != null;
                                            mfirebaseuser.updateProfile(profileChangeRequest);
                                            FirebaseDatabase.getInstance().getReference().child("Users").child(name).child("Name").setValue(name);
                                            FirebaseDatabase.getInstance().getReference().child("Users").child(name).child("EmailId").setValue(emailid);
                                            FirebaseDatabase.getInstance().getReference().child("Users").child(name).child("Password").setValue(pass);
                                            FirebaseAuth.getInstance().signOut();
                                            Intent intoHome = new Intent(Registration_Activity.this, MainActivity.class);
                                            startActivity(intoHome);
                                            finish();
                                        }
                                    }
                                });
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent into = new Intent(Registration_Activity.this, MainActivity.class);
                startActivity(into);
                finish();
            }
        });
    }
}