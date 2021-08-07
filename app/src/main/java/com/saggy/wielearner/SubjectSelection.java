package com.saggy.wielearner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.saggy.wielearner.Database.Usersdb;
import com.saggy.wielearner.Model.Subject;
import com.saggy.wielearner.Model.UserInfoModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SubjectSelection extends AppCompatActivity {
    AppCompatButton B1,B2,B3,B4,B5,B6,submitnbtn;
    TextView subname1,subcourse1,sem1;
    TextView subname2,subcourse2,sem2;
    TextView subname3,subcourse3,sem3;
    TextView subname4,subcourse4,sem4;
    TextView subname5,subcourse5,sem5;
    TextView subname6,subcourse6,sem6;

    String username;
    FirebaseUser firebaseUser;

    int temp;

    Subject sub;

    List<Subject> selectedsubjects;

    UserInfoModel user = new UserInfoModel();
    Usersdb db = new Usersdb(SubjectSelection.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_selection);

        temp = Objects.requireNonNull(getIntent().getExtras()).getInt("temp");

        B1 = findViewById(R.id.browse1);
        B2 = findViewById(R.id.browse2);
        B3 = findViewById(R.id.browse3);
        B4 = findViewById(R.id.browse4);
        B5 = findViewById(R.id.browse5);
        B6 = findViewById(R.id.browse6);
        submitnbtn = findViewById(R.id.submitbtn);

        subname1 = findViewById(R.id.subname1);
        subname2 = findViewById(R.id.subname2);
        subname3 = findViewById(R.id.subname3);
        subname4 = findViewById(R.id.subname4);
        subname5 = findViewById(R.id.subname5);
        subname6 = findViewById(R.id.subname6);

        subcourse1 = findViewById(R.id.coursename1);
        subcourse2 = findViewById(R.id.coursename2);
        subcourse3 = findViewById(R.id.coursename3);
        subcourse4 = findViewById(R.id.coursename4);
        subcourse5 = findViewById(R.id.coursename5);
        subcourse6 = findViewById(R.id.coursename6);

        sem1 = findViewById(R.id.semestername1);
        sem2 = findViewById(R.id.semestername2);
        sem3 = findViewById(R.id.semestername3);
        sem4 = findViewById(R.id.semestername4);
        sem5 = findViewById(R.id.semestername5);
        sem6 = findViewById(R.id.semestername6);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        assert firebaseUser != null;
        username = firebaseUser.getDisplayName();

        B1.setVisibility(View.VISIBLE);
        B2.setVisibility(View.VISIBLE);
        B3.setVisibility(View.VISIBLE);
        B4.setVisibility(View.VISIBLE);
        B5.setVisibility(View.VISIBLE);
        B6.setVisibility(View.VISIBLE);
        subname1.setVisibility(View.GONE);
        subname2.setVisibility(View.GONE);
        subname3.setVisibility(View.GONE);
        subname4.setVisibility(View.GONE);
        subname5.setVisibility(View.GONE);
        subname6.setVisibility(View.GONE);
        subcourse1.setVisibility(View.GONE);
        subcourse2.setVisibility(View.GONE);
        subcourse3.setVisibility(View.GONE);
        subcourse4.setVisibility(View.GONE);
        subcourse5.setVisibility(View.GONE);
        subcourse6.setVisibility(View.GONE);
        sem1.setVisibility(View.GONE);
        sem2.setVisibility(View.GONE);
        sem3.setVisibility(View.GONE);
        sem4.setVisibility(View.GONE);
        sem5.setVisibility(View.GONE);
        sem6.setVisibility(View.GONE);
        submitnbtn.setVisibility(View.GONE);


        user = db.getuserdetails(username);

        if(temp == 1){
            selectedsubjects = (List<Subject>) Objects.requireNonNull(getIntent().getSerializableExtra("list"));
        }
        else{
            selectedsubjects = new ArrayList<>();
        }
        if(selectedsubjects.size() > 0){
            switch (selectedsubjects.size()){
                case 1:
                    B1.setVisibility(View.GONE);
                    subname1.setVisibility(View.VISIBLE);
                    subcourse1.setVisibility(View.VISIBLE);
                    sem1.setVisibility(View.VISIBLE);

                    sub = selectedsubjects.get(0);
                    subname1.setText(sub.getSubjectname());
                    subcourse1.setText(sub.getCoursename());
                    sem1.setText(sub.getSemester());
                    break;
                case 2:
                    B1.setVisibility(View.GONE);
                    subname1.setVisibility(View.VISIBLE);
                    subcourse1.setVisibility(View.VISIBLE);
                    sem1.setVisibility(View.VISIBLE);
                    B2.setVisibility(View.GONE);
                    subname2.setVisibility(View.VISIBLE);
                    subcourse2.setVisibility(View.VISIBLE);
                    sem2.setVisibility(View.VISIBLE);

                    sub = selectedsubjects.get(0);
                    subname1.setText(sub.getSubjectname());
                    subcourse1.setText(sub.getCoursename());
                    sem1.setText(sub.getSemester());
                    sub = selectedsubjects.get(1);
                    subname2.setText(sub.getSubjectname());
                    subcourse2.setText(sub.getCoursename());
                    sem2.setText(sub.getSemester());
                    break;
                case 3:
                    B1.setVisibility(View.GONE);
                    subname1.setVisibility(View.VISIBLE);
                    subcourse1.setVisibility(View.VISIBLE);
                    sem1.setVisibility(View.VISIBLE);
                    B2.setVisibility(View.GONE);
                    subname2.setVisibility(View.VISIBLE);
                    subcourse2.setVisibility(View.VISIBLE);
                    sem2.setVisibility(View.VISIBLE);
                    B3.setVisibility(View.GONE);
                    subname3.setVisibility(View.VISIBLE);
                    subcourse3.setVisibility(View.VISIBLE);
                    sem3.setVisibility(View.VISIBLE);

                    sub = selectedsubjects.get(0);
                    subname1.setText(sub.getSubjectname());
                    subcourse1.setText(sub.getCoursename());
                    sem1.setText(sub.getSemester());
                    sub = selectedsubjects.get(1);
                    subname2.setText(sub.getSubjectname());
                    subcourse2.setText(sub.getCoursename());
                    sem2.setText(sub.getSemester());
                    sub = selectedsubjects.get(2);
                    subname3.setText(sub.getSubjectname());
                    subcourse3.setText(sub.getCoursename());
                    sem3.setText(sub.getSemester());
                    break;
                case 4:
                    B1.setVisibility(View.GONE);
                    subname1.setVisibility(View.VISIBLE);
                    subcourse1.setVisibility(View.VISIBLE);
                    sem1.setVisibility(View.VISIBLE);
                    B2.setVisibility(View.GONE);
                    subname2.setVisibility(View.VISIBLE);
                    subcourse2.setVisibility(View.VISIBLE);
                    sem2.setVisibility(View.VISIBLE);
                    B3.setVisibility(View.GONE);
                    subname3.setVisibility(View.VISIBLE);
                    subcourse3.setVisibility(View.VISIBLE);
                    sem3.setVisibility(View.VISIBLE);
                    B4.setVisibility(View.GONE);
                    subname4.setVisibility(View.VISIBLE);
                    subcourse4.setVisibility(View.VISIBLE);
                    sem4.setVisibility(View.VISIBLE);

                    sub = selectedsubjects.get(0);
                    subname1.setText(sub.getSubjectname());
                    subcourse1.setText(sub.getCoursename());
                    sem1.setText(sub.getSemester());
                    sub = selectedsubjects.get(1);
                    subname2.setText(sub.getSubjectname());
                    subcourse2.setText(sub.getCoursename());
                    sem2.setText(sub.getSemester());
                    sub = selectedsubjects.get(2);
                    subname3.setText(sub.getSubjectname());
                    subcourse3.setText(sub.getCoursename());
                    sem3.setText(sub.getSemester());
                    sub = selectedsubjects.get(3);
                    subname4.setText(sub.getSubjectname());
                    subcourse4.setText(sub.getCoursename());
                    sem4.setText(sub.getSemester());
                    break;
                case 5:
                    B1.setVisibility(View.GONE);
                    subname1.setVisibility(View.VISIBLE);
                    subcourse1.setVisibility(View.VISIBLE);
                    sem1.setVisibility(View.VISIBLE);
                    B2.setVisibility(View.GONE);
                    subname2.setVisibility(View.VISIBLE);
                    subcourse2.setVisibility(View.VISIBLE);
                    sem2.setVisibility(View.VISIBLE);
                    B3.setVisibility(View.GONE);
                    subname3.setVisibility(View.VISIBLE);
                    subcourse3.setVisibility(View.VISIBLE);
                    sem3.setVisibility(View.VISIBLE);
                    B4.setVisibility(View.GONE);
                    subname4.setVisibility(View.VISIBLE);
                    subcourse4.setVisibility(View.VISIBLE);
                    sem4.setVisibility(View.VISIBLE);
                    B5.setVisibility(View.GONE);
                    subname5.setVisibility(View.VISIBLE);
                    subcourse5.setVisibility(View.VISIBLE);
                    sem5.setVisibility(View.VISIBLE);

                    sub = selectedsubjects.get(0);
                    subname1.setText(sub.getSubjectname());
                    subcourse1.setText(sub.getCoursename());
                    sem1.setText(sub.getSemester());
                    sub = selectedsubjects.get(1);
                    subname2.setText(sub.getSubjectname());
                    subcourse2.setText(sub.getCoursename());
                    sem2.setText(sub.getSemester());
                    sub = selectedsubjects.get(2);
                    subname3.setText(sub.getSubjectname());
                    subcourse3.setText(sub.getCoursename());
                    sem3.setText(sub.getSemester());
                    sub = selectedsubjects.get(3);
                    subname4.setText(sub.getSubjectname());
                    subcourse4.setText(sub.getCoursename());
                    sem4.setText(sub.getSemester());
                    sub = selectedsubjects.get(4);
                    subname5.setText(sub.getSubjectname());
                    subcourse5.setText(sub.getCoursename());
                    sem5.setText(sub.getSemester());
                    break;
                case 6:
                    B1.setVisibility(View.GONE);
                    subname1.setVisibility(View.VISIBLE);
                    subcourse1.setVisibility(View.VISIBLE);
                    sem1.setVisibility(View.VISIBLE);
                    B2.setVisibility(View.GONE);
                    subname2.setVisibility(View.VISIBLE);
                    subcourse2.setVisibility(View.VISIBLE);
                    sem2.setVisibility(View.VISIBLE);
                    B3.setVisibility(View.GONE);
                    subname3.setVisibility(View.VISIBLE);
                    subcourse3.setVisibility(View.VISIBLE);
                    sem3.setVisibility(View.VISIBLE);
                    B4.setVisibility(View.GONE);
                    subname4.setVisibility(View.VISIBLE);
                    subcourse4.setVisibility(View.VISIBLE);
                    sem4.setVisibility(View.VISIBLE);
                    B5.setVisibility(View.GONE);
                    subname5.setVisibility(View.VISIBLE);
                    subcourse5.setVisibility(View.VISIBLE);
                    sem5.setVisibility(View.VISIBLE);
                    B6.setVisibility(View.GONE);
                    subname6.setVisibility(View.VISIBLE);
                    subcourse6.setVisibility(View.VISIBLE);
                    sem6.setVisibility(View.VISIBLE);

                    sub = selectedsubjects.get(0);
                    subname1.setText(sub.getSubjectname());
                    subcourse1.setText(sub.getCoursename());
                    sem1.setText(sub.getSemester());
                    sub = selectedsubjects.get(1);
                    subname2.setText(sub.getSubjectname());
                    subcourse2.setText(sub.getCoursename());
                    sem2.setText(sub.getSemester());
                    sub = selectedsubjects.get(2);
                    subname3.setText(sub.getSubjectname());
                    subcourse3.setText(sub.getCoursename());
                    sem3.setText(sub.getSemester());
                    sub = selectedsubjects.get(3);
                    subname4.setText(sub.getSubjectname());
                    subcourse4.setText(sub.getCoursename());
                    sem4.setText(sub.getSemester());
                    sub = selectedsubjects.get(4);
                    subname5.setText(sub.getSubjectname());
                    subcourse5.setText(sub.getCoursename());
                    sem5.setText(sub.getSemester());
                    sub = selectedsubjects.get(5);
                    subname6.setText(sub.getSubjectname());
                    subcourse6.setText(sub.getCoursename());
                    sem6.setText(sub.getSemester());
                    break;
            }
        }
        else{
            Toast.makeText(this, "Please select your subjects", Toast.LENGTH_SHORT).show();
        }

        if(selectedsubjects.size() == 6){
            submitnbtn.setVisibility(View.VISIBLE);
        }

        B1.setOnClickListener(v -> {
            Intent intent = new Intent(SubjectSelection.this , SearchSubject.class);
            intent.putExtra("course",user.getCourse());
            intent.putExtra("board",user.getBoard());
            intent.putExtra("list",(Serializable)selectedsubjects);
            startActivity(intent);
            finish();
        });
        B2.setOnClickListener(v -> {
            Intent intent = new Intent(SubjectSelection.this , SearchSubject.class);
            intent.putExtra("course",user.getCourse());
            intent.putExtra("board",user.getBoard());
            intent.putExtra("list",(Serializable)selectedsubjects);
            startActivity(intent);
            finish();
        });
        B3.setOnClickListener(v -> {
            Intent intent = new Intent(SubjectSelection.this , SearchSubject.class);
            intent.putExtra("course",user.getCourse());
            intent.putExtra("board",user.getBoard());
            intent.putExtra("list",(Serializable)selectedsubjects);
            startActivity(intent);
            finish();
        });
        B4.setOnClickListener(v -> {
            Intent intent = new Intent(SubjectSelection.this , SearchSubject.class);
            intent.putExtra("course",user.getCourse());
            intent.putExtra("board",user.getBoard());
            intent.putExtra("list",(Serializable)selectedsubjects);
            startActivity(intent);
            finish();
        });
        B5.setOnClickListener(v -> {
            Intent intent = new Intent(SubjectSelection.this , SearchSubject.class);
            intent.putExtra("course",user.getCourse());
            intent.putExtra("board",user.getBoard());
            intent.putExtra("list",(Serializable)selectedsubjects);
            startActivity(intent);
            finish();
        });
        B6.setOnClickListener(v -> {
            Intent intent = new Intent(SubjectSelection.this , SearchSubject.class);
            intent.putExtra("course",user.getCourse());
            intent.putExtra("board",user.getBoard());
            intent.putExtra("list",(Serializable)selectedsubjects);
            startActivity(intent);
            finish();
        });

        submitnbtn.setOnClickListener(v -> {
            if(selectedsubjects.size() == 6){
                String subcode1 = selectedsubjects.get(0).getCoursecode();
                String subcode2 = selectedsubjects.get(1).getCoursecode();
                String subcode3 = selectedsubjects.get(2).getCoursecode();
                String subcode4 = selectedsubjects.get(3).getCoursecode();
                String subcode5 = selectedsubjects.get(4).getCoursecode();
                String subcode6 = selectedsubjects.get(5).getCoursecode();

                boolean i = db.updatesubjects(username,subcode1,subcode2,subcode3,subcode4,subcode5,subcode6);
                if(i) {
                    Toast.makeText(this, "Submitted Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SubjectSelection.this , Home_Activity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
}