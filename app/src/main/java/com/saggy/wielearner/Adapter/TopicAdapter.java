package com.saggy.wielearner.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saggy.wielearner.R;
import com.saggy.wielearner.SubtopicSelection;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.viewholder> {
    private final Context context;
    private final List<String> topiclist;
    private final List<String> subtopiclist;
    private final List<String> videolinklist;
    private final List<String> noteslinklist;

    public TopicAdapter(Context context, List<String> topiclist, List<String> subtopiclist, List<String> videolinklist, List<String> noteslinklist) {
        this.context = context;
        this.topiclist = topiclist;
        this.subtopiclist = subtopiclist;
        this.videolinklist = videolinklist;
        this.noteslinklist = noteslinklist;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_item, parent, false);
        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.topicname.setText(topiclist.get(position));
    }

    @Override
    public int getItemCount() {
        return topiclist.size();
    }

    public class viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView topicname;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            topicname = itemView.findViewById(R.id.topicname);
        }

        @Override
        public void onClick(View v) {
            int position = this.getAdapterPosition();
            Intent intent = new Intent(context, SubtopicSelection.class);
            intent.putExtra("topic", topiclist.get(position));
            intent.putExtra("subtopic", subtopiclist.get(position));
            intent.putExtra("videolink", videolinklist.get(position));
            intent.putExtra("noteslink", noteslinklist.get(position));
            context.startActivity(intent);
        }
    }
}

