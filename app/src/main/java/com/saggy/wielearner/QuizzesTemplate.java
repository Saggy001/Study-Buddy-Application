package com.saggy.wielearner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.saggy.wielearner.Database.Scoredb;
import com.saggy.wielearner.Model.QuizModel;
import com.saggy.wielearner.Model.SubjectModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import cz.msebera.android.httpclient.Header;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class QuizzesTemplate extends AppCompatActivity {
    String quizlink ,topic , subjectcode,subjectname;
    String topics,quizlinks;

    AsyncHttpClient client;
    Workbook workbook;

    ProgressDialog progressDialog;

    int temp;

    int no_of_questions = 0;
    int current_question = 1;
    int Score = 0;
    int num;
    String correctanswer, explaination;

    List<QuizModel> quizlist = new ArrayList<>();

    TextView question, noquestion ,topicname, tag;
    RadioButton optiona, optionb, optionc, optiond;
    AppCompatButton next , submitnext;
    CardView cdview;
    TextView explain,correctans,yourans,score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiztempl);


        progressDialog = new ProgressDialog(QuizzesTemplate.this);
        progressDialog.setMessage("Please wait...Depending on the Internet speed");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.show();

        quizlink = Objects.requireNonNull(getIntent().getExtras()).getString("quizlink");
        topic = Objects.requireNonNull(getIntent().getExtras()).getString("topic");
        subjectcode = Objects.requireNonNull(getIntent().getExtras()).getString("subjectcode");
        subjectname = Objects.requireNonNull(getIntent().getExtras()).getString("subjectname");
        topics = Objects.requireNonNull(getIntent().getExtras()).getString("topics");
        quizlinks = Objects.requireNonNull(getIntent().getExtras()).getString("quizlinks");

        Scoredb scoredb = new Scoredb(QuizzesTemplate.this);
        String myprevscore = scoredb.getScore(subjectcode,topic);
        if(myprevscore.equals("noscore")){
            temp =1;
        }
        else{
            temp = 0;
        }

        client = new AsyncHttpClient();
        client.get(quizlink, new FileAsyncHttpResponseHandler(QuizzesTemplate.this) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                Toast.makeText(QuizzesTemplate.this, "Check your Internet Connection", Toast.LENGTH_SHORT).show();
                Log.d("mylog", "onFailure: fail ");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                WorkbookSettings ws = new WorkbookSettings();
                ws.setGCDisabled(true);
                if(file != null){
                    try {
                        workbook = workbook.getWorkbook(file);
                        Sheet sheet = workbook.getSheet(0);
                        for(int i = 0 ; i< sheet.getRows() ; i++) {
                            Cell[] rows = sheet.getRow(i);

                            if (!rows[0].getContents().isEmpty()) {
                                QuizModel quizModel = new QuizModel();
                                quizModel.setQuestion(rows[0].getContents());
                                quizModel.setOptionA(rows[1].getContents());
                                quizModel.setOptionB(rows[2].getContents());
                                quizModel.setOptionC(rows[3].getContents());
                                quizModel.setOptionD(rows[4].getContents());
                                quizModel.setCorrectanswer(rows[5].getContents());
                                quizModel.setExplaination(rows[6].getContents());
                                quizlist.add(quizModel);
                                no_of_questions += 1;
                            }
                            else{
                                break;
                            }
                        }
                        progressDialog.dismiss();
                        refreshquestion();

                    } catch (IOException | BiffException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //so now we have all our questions in the quizlist
        // we just need to show them one ny one

        topicname = findViewById(R.id.topicname);
        optiona = findViewById(R.id.optiona);
        optionb = findViewById(R.id.optionb);
        optionc = findViewById(R.id.optionc);
        optiond = findViewById(R.id.optiond);
        question = findViewById(R.id.question);
        noquestion = findViewById(R.id.qustionno);
        next = findViewById(R.id.next);
        tag = findViewById(R.id.tag);
        submitnext = findViewById(R.id.submitnext);
        correctans = findViewById(R.id.correctanswer);
        yourans = findViewById(R.id.youranswer);
        cdview = findViewById(R.id.cdview2);
        explain = findViewById(R.id.explainationn);
        score = findViewById(R.id.score);

        cdview.setVisibility(View.GONE);
        topicname.setText(topic);

        optiona.setOnClickListener(v -> {
            if(optionb.isChecked()){
                optionb.setChecked(false);
                optiona.setChecked(true);
            }
            else if(optionc.isChecked()){
                optionc.setChecked(false);
                optiona.setChecked(true);
            }
            else if(optiond.isChecked()){
                optiond.setChecked(false);
                optiona.setChecked(true);
            }
            else{
                optiona.setChecked(true);
            }
        });
        optionb.setOnClickListener(v -> {
            if(optiona.isChecked()){
                optiona.setChecked(false);
                optionb.setChecked(true);
            }
            else if(optionc.isChecked()){
                optionc.setChecked(false);
                optionb.setChecked(true);
            }
            else if(optiond.isChecked()){
                optiond.setChecked(false);
                optionb.setChecked(true);
            }
            else{
                optionb.setChecked(true);
            }
        });
        optionc.setOnClickListener(v -> {
            if(optionb.isChecked()){
                optionb.setChecked(false);
                optionc.setChecked(true);
            }
            else if(optiona.isChecked()){
                optiona.setChecked(false);
                optionc.setChecked(true);
            }
            else if(optiond.isChecked()){
                optiond.setChecked(false);
                optionc.setChecked(true);
            }
            else{
                optionc.setChecked(true);
            }
        });
        optiond.setOnClickListener(v -> {
            if(optionb.isChecked()){
                optionb.setChecked(false);
                optiond.setChecked(true);
            }
            else if(optionc.isChecked()){
                optionc.setChecked(false);
                optiond.setChecked(true);
            }
            else if(optiona.isChecked()){
                optiona.setChecked(false);
                optiond.setChecked(true);
            }
            else{
                optiond.setChecked(true);
            }
        });
        next.setOnClickListener(v -> {
            if(!(optiona.isChecked() || optionb.isChecked() || optionc.isChecked() || optiond.isChecked())){
                Toast.makeText(this, "Please select any option", Toast.LENGTH_SHORT).show();
            }
            else if(optiona.isChecked()){
                if(optiona.getText().equals(correctanswer)){
                   Score += 1;
                    optiona.setTextColor(getResources().getColor(R.color.Green));
                }
                else{
                    optiona.setTextColor(getResources().getColor(R.color.Red));
                }
                cdview.setVisibility(View.VISIBLE);
                next.setVisibility(View.GONE);
                correctans.setText(correctanswer);
                yourans.setText(optiona.getText().toString());
                explain.setText(explaination);
                score.setText(String.format("%d/10", Score));
                if(current_question == 10){
                    submitnext.setText("SUBMIT");
                }
            }
            else if(optionb.isChecked()){
                if(optionb.getText().equals(correctanswer)){
                    Score += 1;
                    optionb.setTextColor(getResources().getColor(R.color.Green));
                }
                else {
                    optionb.setTextColor(getResources().getColor(R.color.Red));
                }
                cdview.setVisibility(View.VISIBLE);
                next.setVisibility(View.GONE);
                correctans.setText(correctanswer);
                yourans.setText(optionb.getText().toString());
                explain.setText(explaination);
                score.setText(String.format("%d/10", Score));
                if(current_question == 10){
                    submitnext.setText("SUBMIT");
                }
            }
            else if(optionc.isChecked()){
                if(optionc.getText().equals(correctanswer)){
                    Score += 1;
                    optionc.setTextColor(getResources().getColor(R.color.Green));
                }
                else {
                    optionc.setTextColor(getResources().getColor(R.color.Red));
                }
                cdview.setVisibility(View.VISIBLE);
                next.setVisibility(View.GONE);
                correctans.setText(correctanswer);
                yourans.setText(optionc.getText().toString());
                explain.setText(explaination);
                score.setText(String.format("%d/10", Score));
                if(current_question == 10){
                    submitnext.setText("SUBMIT");
                }
            }
            else{
                //optiond is selected
                if(optiond.getText().equals(correctanswer)){
                    Score += 1;
                    optiond.setTextColor(getResources().getColor(R.color.Green));
                }
                else{
                    optiond.setTextColor(getResources().getColor(R.color.Red));
                }
                cdview.setVisibility(View.VISIBLE);
                next.setVisibility(View.GONE);
                correctans.setText(correctanswer);
                yourans.setText(optiond.getText().toString());
                explain.setText(explaination);
                score.setText(String.format("%d/10", Score));
                if(current_question == 10){
                    submitnext.setText("SUBMIT");
                }
            }
        });

        submitnext.setOnClickListener(v ->{
            if(submitnext.getText().equals("SUBMIT")){
                if(temp == 0){
                    boolean i = scoredb.updatescore(subjectcode,topic,String.valueOf(Score));
                    if(i){
                        Intent intent = new Intent(QuizzesTemplate.this , QuizEnd.class);
                        intent.putExtra("score",String.valueOf(Score));
                        intent.putExtra("subjectname",subjectname);
                        intent.putExtra("topic",topic);
                        intent.putExtra("topics",topics);
                        intent.putExtra("quizlinks",quizlinks);
                        intent.putExtra("subjectcode",subjectcode);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
                else if(temp == 1){
                    boolean i = scoredb.insert_data(subjectcode,topic,String.valueOf(Score));
                    if(i){
                        Intent intent = new Intent(QuizzesTemplate.this , QuizEnd.class);
                        intent.putExtra("score",String.valueOf(Score));
                        intent.putExtra("subjectname",subjectname);
                        intent.putExtra("topic",topic);
                        intent.putExtra("topics",topics);
                        intent.putExtra("quizlinks",quizlinks);
                        intent.putExtra("subjectcode",subjectcode);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else {
                current_question += 1;
                quizlist.remove(num);
                no_of_questions -= 1;
                optiona.setChecked(false);
                optionb.setChecked(false);
                optionc.setChecked(false);
                optiond.setChecked(false);
                optiona.setTextColor(getResources().getColor(R.color.White));
                optionb.setTextColor(getResources().getColor(R.color.White));
                optionc.setTextColor(getResources().getColor(R.color.White));
                optiond.setTextColor(getResources().getColor(R.color.White));
                refreshquestion();
                cdview.setVisibility(View.GONE);
                next.setVisibility(View.VISIBLE);
            }
        });
    }

    private void refreshquestion() {
        Random random = new Random();
        num = random.nextInt(no_of_questions);
        noquestion.setText(String.format("%d.", current_question));
        tag.setText("Q");
        QuizModel quizModel = quizlist.get(num);
        question.setText(Html.fromHtml(quizModel.getQuestion()));
        optiona.setText(quizModel.getOptionA());
        optionb.setText(quizModel.getOptionB());
        optionc.setText(quizModel.getOptionC());
        optiond.setText(quizModel.getOptionD());

        correctanswer = quizModel.getCorrectanswer();
        explaination = quizModel.getExplaination();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "You cannot go back in this position.", Toast.LENGTH_SHORT).show();
    }
}