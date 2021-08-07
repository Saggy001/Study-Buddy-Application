package com.saggy.wielearner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.saggy.wielearner.Adapter.QuizTopicAdapter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TopicQuiz extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView subjecthead;
    ImageButton backbutton;

    List<String> topicslist;

    QuizTopicAdapter quizTopicAdapter;

    String quizlink,topic,Subjectname,subjectcode;
    List<String> quizlinklist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_quiz);

        recyclerView = findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        subjecthead = findViewById(R.id.subjectnamehead);
        backbutton = findViewById(R.id.backbutton);

        Subjectname = Objects.requireNonNull(getIntent().getExtras()).getString("subjectname");
        topic = Objects.requireNonNull(getIntent().getExtras()).getString("topic");
        subjectcode = getIntent().getExtras().getString("subjectcode");
        quizlink = getIntent().getExtras().getString("quizlink");


        subjecthead.setText(Subjectname);


        topicslist = Arrays.asList(topic.split("#"));
        quizlinklist = Arrays.asList(quizlink.split("#"));

        quizTopicAdapter = new QuizTopicAdapter(TopicQuiz.this,topicslist,quizlinklist,subjectcode,Subjectname,topic,quizlink);
        recyclerView.setAdapter(quizTopicAdapter);

        backbutton.setOnClickListener(v -> onBackPressed());
    }
}