package com.saggy.wielearner.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saggy.wielearner.QuizzesTemplate;
import com.saggy.wielearner.R;

import java.util.List;

public class QuizTopicAdapter extends RecyclerView.Adapter<QuizTopicAdapter.vieWholder> {
    private final Context context;
    private final List<String> topiclist;
    private final List<String> quizlist;
    private final String subjectcode;
    private final String subjectname;
    private final String topics;
    private final String quizlinks;

    public QuizTopicAdapter(Context context, List<String> topiclist, List<String> quizlist, String subjectcode,
                            String subjectname,String topics, String quizlinks) {
        this.context = context;
        this.topiclist = topiclist;
        this.quizlist = quizlist;
        this.subjectcode = subjectcode;
        this.subjectname = subjectname;
        this.topics = topics;
        this.quizlinks = quizlinks;
    }


    @NonNull
    @Override
    public vieWholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_item, parent, false);
        return new vieWholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull vieWholder holder, int position) {
        holder.topicname.setText(topiclist.get(position));
    }

    @Override
    public int getItemCount() {
        return topiclist.size();
    }

    public class vieWholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView topicname;
        public vieWholder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            topicname = itemView.findViewById(R.id.topicname);
        }

        @Override
        public void onClick(View v) {
                int position = this.getAdapterPosition();
                String quizlink = quizlist.get(position);
                if(quizlink.equals("empty")) {
                    Toast.makeText(context, "Quizzes are unavailable for this topic!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(context, QuizzesTemplate.class);
                    intent.putExtra("topic", topiclist.get(position));
                    intent.putExtra("quizlink", quizlist.get(position));
                    intent.putExtra("subjectcode", subjectcode);
                    intent.putExtra("subjectname", subjectname);
                    intent.putExtra("topics", topics);
                    intent.putExtra("quizlinks", quizlinks);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }
        }
    }
}
