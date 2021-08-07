package com.saggy.wielearner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Helpdesk extends AppCompatActivity {
    RelativeLayout register, profile, video, notes, quiz, changesub, changecourse, forgetpass;
    ImageButton backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpdesk);

        register = findViewById(R.id.registration);
        profile = findViewById(R.id.profile);
        video = findViewById(R.id.video);
        quiz = findViewById(R.id.quiz);
        notes = findViewById(R.id.notes);
        forgetpass = findViewById(R.id.forgetpass);
        changecourse = findViewById(R.id.changecourse);
        changesub = findViewById(R.id.changesub);
        backbutton = findViewById(R.id.backbutton);

        backbutton.setOnClickListener(view -> onBackPressed());

        register.setOnClickListener(view -> {
            Intent intent = new Intent(Helpdesk.this , HelpdeskNext.class);
            intent.putExtra("extra",1);
            startActivity(intent);
        });
        profile.setOnClickListener(view -> {
            Intent intent = new Intent(Helpdesk.this , HelpdeskNext.class);
            intent.putExtra("extra",2);
            startActivity(intent);
        });
        forgetpass.setOnClickListener(view -> {
            Intent intent = new Intent(Helpdesk.this , HelpdeskNext.class);
            intent.putExtra("extra",3);
            startActivity(intent);
        });
        quiz.setOnClickListener(view -> {
            Intent intent = new Intent(Helpdesk.this , HelpdeskNext.class);
            intent.putExtra("extra",4);
            startActivity(intent);
        });
        video.setOnClickListener(view -> {
            Intent intent = new Intent(Helpdesk.this , HelpdeskNext.class);
            intent.putExtra("extra",5);
            startActivity(intent);
        });
        notes.setOnClickListener(view -> {
            Intent intent = new Intent(Helpdesk.this , HelpdeskNext.class);
            intent.putExtra("extra",6);
            startActivity(intent);
        });
        changesub.setOnClickListener(view -> {
            Intent intent = new Intent(Helpdesk.this , HelpdeskNext.class);
            intent.putExtra("extra",7);
            startActivity(intent);
        });
        changecourse.setOnClickListener(view -> {
            showdialog("abc","abc");
        });
    }

    private void showdialog(String headingcontent, String materialcontent) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Helpdesk.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.help_dialog,viewGroup,false);
        TextView heading = dialogView.findViewById(R.id.heading);
        TextView content1 = dialogView.findViewById(R.id.content1);
        //ImageButton closebtn = dialogView.findViewById(R.id.closebtn);
                heading.setText(headingcontent);
        content1.setText(materialcontent);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();

        Window window = alertDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();


    }

}