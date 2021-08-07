package com.saggy.wielearner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.drjacky.imagepicker.ImagePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.saggy.wielearner.Database.Usersdb;
import com.saggy.wielearner.Model.UserInfoModel;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;


public class Settings extends AppCompatActivity {

    ImageButton backbutton, editbutton;
    TextView university, course;
    TextView name,email;
    ImageButton uploadimagebutton;
    CircleImageView profilepicture;


    Uri uri ;

    private StorageReference storageReference;

    String username;
    FirebaseUser firebaseUser;

    Usersdb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading... Please Wait");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.show();

        backbutton = findViewById(R.id.backbutton);
        editbutton = findViewById(R.id.editbutton);
        name = findViewById(R.id.name);
        email = findViewById(R.id.useremail);
        university = findViewById(R.id.useruniversity);
        course = findViewById(R.id.usercourse);
        profilepicture = findViewById(R.id.profileimage);
        uploadimagebutton = findViewById(R.id.uploadimage);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        assert firebaseUser != null;
        username = firebaseUser.getDisplayName();

        db = new Usersdb(Settings.this);

        UserInfoModel userdetail = db.getuserdetails(username);
        if(userdetail.getUsername().equals("DataNotFound")){
            FirebaseAuth.getInstance().signOut();
            progressDialog.dismiss();
            startActivity(new Intent(Settings.this, MainActivity.class));
        }

        name.setText(username);
        email.setText(firebaseUser.getEmail());
        university.setText(userdetail.getBoard());
        course.setText(userdetail.getCourse());

        //firebaseAuth = FirebaseAuth.getInstance();
        //databaseReference = FirebaseDatabase.getInstance().getReference().child("User");
        storageReference = FirebaseStorage.getInstance().getReference().child("images/"+ username + ".jpg");

        try {
            File localFile = File.createTempFile(username, "jpg");
            storageReference.getFile(localFile)
                    .addOnSuccessListener(taskSnapshot -> {
                        // Successfully downloaded data to local file
                        // ...
                        Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        profilepicture.setImageBitmap(bitmap);
                        progressDialog.dismiss();
                    }).addOnFailureListener(exception -> {
                        progressDialog.dismiss();
                        // Handle failed download
                        // ...
                    });
        } catch (IOException e){
            progressDialog.dismiss();
            e.printStackTrace();
        }
        uploadimagebutton.setOnClickListener(view -> ImagePicker.Companion.with(Settings.this)
//                    .crop()
//                    .cropOval()
//                    .compress(1024)
                .maxResultSize(1080,1080)
                .start(1));

        backbutton.setOnClickListener(view -> onBackPressed());

        editbutton.setOnClickListener(view -> Toast.makeText(this, "This option will enable when more courses are added.", Toast.LENGTH_SHORT).show());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == 1 && data != null && data.getData() != null) {
            uri = data.getData();
            profilepicture.setImageURI(uri);
            uploadimage();
        }
    }

    private void uploadimage() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Uploading Image...");
        pd.setCanceledOnTouchOutside(false);
        pd.setCancelable(false);
        pd.show();

        storageReference.putFile(uri)
                .addOnSuccessListener(taskSnapshot -> {
                    // Get a URL to the uploaded content
                    pd.dismiss();
                    Toast.makeText(getApplicationContext(), "Profile Uploaded", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(exception -> {
                    // Handle unsuccessful uploads
                    // ...
                    pd.dismiss();
                    Toast.makeText(getApplicationContext(), "Failed To Upload", Toast.LENGTH_SHORT).show();
                });
    }
}