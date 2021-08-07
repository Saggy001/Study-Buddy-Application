package com.saggy.wielearner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class QuizEnd extends AppCompatActivity {

    String subjectname,topic,score;
    String topics,quizlinks,subjectcode;

    TextView text;
    AppCompatButton home,retry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizend);

        subjectname = Objects.requireNonNull(getIntent().getExtras()).getString("subjectname");
        subjectcode = Objects.requireNonNull(getIntent().getExtras()).getString("subjectcode");
        score = Objects.requireNonNull(getIntent().getExtras()).getString("score");
        topic = Objects.requireNonNull(getIntent().getExtras()).getString("topic");
        topics = Objects.requireNonNull(getIntent().getExtras()).getString("topics");
        quizlinks = Objects.requireNonNull(getIntent().getExtras()).getString("quizlinks");

        text = findViewById(R.id.text);
        home = findViewById(R.id.home);
        retry = findViewById(R.id.retry);

        text.setText(String.format("You have scored %s/10 in %s from %s", score, topic, subjectname));

        home.setOnClickListener(v -> {
            startActivity(new Intent(QuizEnd.this, Home_Activity.class));
            finish();
        });

        retry.setOnClickListener(v -> {
            Intent into = new Intent(QuizEnd.this , TopicQuiz.class);
            into.putExtra("subjectname",subjectname);
            into.putExtra("subjectcode",subjectcode);
            into.putExtra("topic",topics);
            into.putExtra("quizlink",quizlinks);
            startActivity(into);
            finish();
        });
    }
}