package com.saggy.wielearner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Home_Activity extends AppCompatActivity {
    public static int version = 1;
    int appversion;

    FirebaseUser firebaseUser;
    DrawerLayout drawer;
    Toolbar toolbar;
    boolean doublebackpresson = false;

    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);

        toolbar = findViewById(R.id.toolbar);
        TextView mtext = findViewById(R.id.toolbar_title);

        setSupportActionBar(toolbar);
        mtext.setText(toolbar.getTitle());

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        FirebaseDatabase.getInstance().getReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                appversion = dataSnapshot.child("Admin").child("version").getValue(Integer.class);
                if (appversion != version) {
                    //applink = dataSnapshot.child("Admin").child("applink").getValue(String.class);
                    AlertDialog builder = new AlertDialog.Builder(Home_Activity.this)
                            .setTitle("Update Available")
                            .setCancelable(false)
                            .setMessage("A new Update of the StudyBuddy App is available.\n\n-App become more stable.\n-Some new features have been added.\n\nDownload the new apk and install to continue with the StudyBuddy.\n")
                            .setPositiveButton("Download", null)
                            .setNegativeButton("cancel", null)
                            .show();
                    Button postivebutton = builder.getButton(AlertDialog.BUTTON_POSITIVE);
                    postivebutton.setOnClickListener(v -> {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("https://drive.google.com/drive/folders/1lzMlrfRIU1bRAYm-5gb4Bkur6ymov-ll?usp=sharing"));
                        startActivity(intent);
                        finish();

                    });
                    Button negative = builder.getButton(AlertDialog.BUTTON_NEGATIVE);
                    negative.setOnClickListener(v -> {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(Home_Activity.this, MainActivity.class));
                        finish();
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.navbar);
        bottomNavigationView.setOnNavigationItemSelectedListener(navlistner);

        drawer = findViewById(R.id.drawer_layout);
        //actionbar navigation toggling
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,new Homefragment()).commit();


        NavigationView navigationView = findViewById(R.id.nav_view);

        TextView usernametext = navigationView.getHeaderView(0).findViewById(R.id.nametext);
        ImageView profilepicture = navigationView.getHeaderView(0).findViewById(R.id.imageperson);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        assert firebaseUser != null;
        String username = firebaseUser.getDisplayName();

        storageReference = FirebaseStorage.getInstance().getReference().child("images/"+ username + ".jpg");

        usernametext.setText(username);

        try {
            File localFile = File.createTempFile(username, "jpg");
            storageReference.getFile(localFile)
                    .addOnSuccessListener(taskSnapshot -> {
                        // Successfully downloaded data to local file
                        // ...
                        Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                         profilepicture.setImageBitmap(bitmap);
                    }).addOnFailureListener(exception -> {
                // Handle failed download
                // ...
            });
        } catch (IOException e){
            e.printStackTrace();
        }

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.weeklyleaderboard :
                    Intent intenthowt = new Intent(getApplicationContext(),Leaderboard.class);
                    startActivity(intenthowt);
                    break;
                case R.id.myprogress :
                    Intent intent = new Intent(getApplicationContext(),MyProgress.class);
                    startActivity(intent);
                    break;
                case R.id.Bookmark :
                    Intent intochngsub = new Intent(getApplicationContext() , SubjectSelection.class);
                    intochngsub.putExtra("temp",0);
                    startActivity(intochngsub);
                    break;
                case R.id.setting :
                    Intent intentsetting = new Intent(getApplicationContext(),Settings.class);
                    startActivity(intentsetting);
                    break;
                case R.id.more :
                    Intent intentmore = new Intent(getApplicationContext(),MoreActivity.class);
                    startActivity(intentmore);
                    break;
                case R.id.helpdesk :
                    Intent intenthelp = new Intent(getApplicationContext(),Helpdesk.class);
                    startActivity(intenthelp);
                    break;
                case R.id.chatwithus :
                    Intent intentchat = new Intent(getApplicationContext(), AboutUs.class);
                    startActivity(intentchat);
                    break;
                case R.id.logout:
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
            drawer.closeDrawer(GravityCompat.START);
            return true;
        });
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
//                .setDrawerLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // for toolbar
        getMenuInflater().inflate(R.menu.activity_home, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.notification){
            //for tool bar functioning
            Intent intent = new Intent(getApplicationContext(),Notification.class);
            startActivity(intent);
        }
        return true;
    }

//
//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else{
            if (doublebackpresson){
                super.onBackPressed();
                return;
            }
            this.doublebackpresson = true ;
            Toast.makeText(this, "Please Click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doublebackpresson = false;
                }
            },2000);
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navlistner = item -> {
        Fragment selectedFragment = null;

        switch (item.getItemId()){
            case R.id.Home :
                selectedFragment = new Homefragment();
                break;
            case R.id.quizzes:
                selectedFragment = new Quizzes();
                break;
            case R.id.mydoubts:
                selectedFragment = new Mydoubts();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,selectedFragment).commit();
        return true;
    };
}