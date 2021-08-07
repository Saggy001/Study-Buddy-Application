package com.saggy.wielearner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.saggy.wielearner.Database.Usersdb;
import com.saggy.wielearner.Model.UserInfoModel;


public class CourseDetail extends AppCompatActivity {
    AutoCompleteTextView university,branch,course;
    RelativeLayout R1,R2,R4;
    AppCompatButton submitbtn;

    String username;
    FirebaseUser firebaseUser;

    Usersdb user = new Usersdb(CourseDetail.this);

    int count =0;

    String[] univercities = {"Punjab Technical University"/*,"Chitkara University","Chandigarh University",
            "Delhi University","Kurukshetra University","SRM University","Punjab University"*/};

    String[] coursesnames = {"BTech"/*, "BCA", "BBA", "MBA", "MTech", "MPharma", "BPharma", "MSc", "MCA",
            "BSc", "BEd", "PharmD"*/};

    String[] branchname = {"Computer Science and Engineering"/*, "Mechanical Engineering", "Civil Engineering",
            "Electrical and Electronics Engineering", "Automobile Engineering", "Electronics & Communication",
            "Aeronautical Engineering", "Biotechnology"*/};

    //String[] semesterlist= {/*"1st", "2nd", "3rd", */ "4th" /*, "5th", "6th", "7th", "8th"*/};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        assert firebaseUser != null;
        username = firebaseUser.getDisplayName();

        try {
            UserInfoModel model = user.getuserdetails(username);
            if (model.getUsername().equals(username)) {
                //means already data present
                if (model.getSub1().equals("No-Course")) {
                    //means didn't select subjects yet
                    Intent intent = new Intent(CourseDetail.this, SubjectSelection.class);
                    intent.putExtra("temp",0);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(CourseDetail.this, Home_Activity.class);
                    Toast.makeText(this, "Welcome to Study Buddy", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }
            }
        }
        catch(Exception e){
            Log.d("mylog", "error: " + e);
        }
        university = findViewById(R.id.University);
        course = findViewById(R.id.Courses);
        branch = findViewById(R.id.Branch);
        R1 = findViewById(R.id.R1);
        R2 = findViewById(R.id.R2);
        R4 = findViewById(R.id.R4);
        submitbtn = findViewById(R.id.submitbtn);




        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this,android.R.layout.select_dialog_item, univercities);
        university.setThreshold(1);
        university.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>
                (this,android.R.layout.select_dialog_item, coursesnames);
        course.setThreshold(1);
        course.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<>
                (this,android.R.layout.select_dialog_item, branchname);
        branch.setThreshold(1);
        branch.setAdapter(adapter3);

        university.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String selectedtext = university.getText().toString();

                if(selectedtext.equals("Punjab Technical University")){
                    R1.setVisibility(View.VISIBLE);
                    course.requestFocus();
                }
                else {
                    count = 0;
                    R1.setVisibility(View.GONE);
                    R2.setVisibility(View.GONE);
                    R4.setVisibility(View.GONE);
                }
            }
        });

        course.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String selectedtext = course.getText().toString();

                if(selectedtext.equals("BTech")){
                    R2.setVisibility(View.VISIBLE);
                    branch.requestFocus();
                }
                else {
                    count = 0;
                    R2.setVisibility(View.GONE);
                    R4.setVisibility(View.GONE);
                }
            }
        });

        branch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String selectedtext = branch.getText().toString();

                if(selectedtext.equals("Computer Science and Engineering")){
                    count =1;
                    R4.setVisibility(View.VISIBLE);
                }
                else {
                    count = 0;
                    R4.setVisibility(View.GONE);
                }
            }
        });

        /*semester.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String selectedtext = semester.getText().toString();

                if(selectedtext.equals("4th")){
                    R4.setVisibility(View.VISIBLE);
                    count =1;
                }
                else {
                    count = 0;
                    R4.setVisibility(View.GONE);
                }
            }
        });*/


        submitbtn.setOnClickListener(v -> {
            if(count == 1){
                boolean i = user.insert_data(username , university.getText().toString() , course.getText().toString()
                                +" "+ branch.getText().toString(), "No-Course","No-Course", "No-Course",
                        "No-Course" , "No-Course", "No-Course");
                if(i){
                    Intent intent = new Intent(CourseDetail.this , SubjectSelection.class);
                    intent.putExtra("temp",0);
                    startActivity(intent);
                    finish();
                }
                else
                    Toast.makeText(CourseDetail.this, "Sorry, something went wrong", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(CourseDetail.this, "Sorry, something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
