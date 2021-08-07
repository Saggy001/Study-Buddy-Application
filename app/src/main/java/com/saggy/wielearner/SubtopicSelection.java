package com.saggy.wielearner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.saggy.wielearner.Adapter.SubtopicAdapter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SubtopicSelection extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView subjecthead;
    ImageButton backbutton;

    String  topic, subtopic, videolink, noteslink;

    SubtopicAdapter subtopicAdapter;

    List<String> subtopiclist;
    List<String> videolinklist;
    List<String> noteslinklist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtopic_selection);

        recyclerView = findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        subjecthead = findViewById(R.id.subjectnamehead);
        backbutton = findViewById(R.id.backbutton);

        topic = Objects.requireNonNull(getIntent().getExtras()).getString("topic");
        subtopic = getIntent().getExtras().getString("subtopic");
        videolink = getIntent().getExtras().getString("videolink");
        noteslink = getIntent().getExtras().getString("noteslink");

        subjecthead.setText(topic);

        subtopiclist = Arrays.asList(subtopic.split("#"));

        videolinklist = Arrays.asList(videolink.split("#"));

        noteslinklist = Arrays.asList(noteslink.split("#"));

        subtopicAdapter = new SubtopicAdapter(this , subtopiclist, videolinklist, noteslinklist);
        recyclerView.setAdapter(subtopicAdapter);

        backbutton.setOnClickListener(v -> {
            onBackPressed();
        });
    }
}