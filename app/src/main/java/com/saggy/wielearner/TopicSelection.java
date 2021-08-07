package com.saggy.wielearner;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saggy.wielearner.Adapter.TopicAdapter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TopicSelection extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView subjecthead;
    ImageButton backbutton;

    List<String> topicslist;
    List<String> subtopicslist;
    List<String> videolinklist;
    List<String> noteslinklist;
    List<String> quizlinklist;

    String Subjectname, topic , subtopic, videolink, quizlink,noteslink, subjectcode;
    TopicAdapter topicAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_selection);

        recyclerView = findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        subjecthead = findViewById(R.id.subjectnamehead);
        backbutton = findViewById(R.id.backbutton);

        Subjectname = Objects.requireNonNull(getIntent().getExtras()).getString("subjectname");
        topic = Objects.requireNonNull(getIntent().getExtras()).getString("topic");

        subjecthead.setText(Subjectname);

        topicslist = Arrays.asList(topic.split("#"));

        subtopic = Objects.requireNonNull(getIntent().getExtras()).getString("subtopic");
        videolink = Objects.requireNonNull(getIntent().getExtras()).getString("videolink");
        noteslink = Objects.requireNonNull(getIntent().getExtras()).getString("noteslink");

        subtopicslist = Arrays.asList(subtopic.split("-"));

        videolinklist = Arrays.asList(videolink.split(";"));

        noteslinklist = Arrays.asList(noteslink.split(";"));

        topicAdapter = new TopicAdapter(TopicSelection.this, topicslist, subtopicslist, videolinklist, noteslinklist);
        recyclerView.setAdapter(topicAdapter);

        backbutton.setOnClickListener(v -> onBackPressed());
    }
}