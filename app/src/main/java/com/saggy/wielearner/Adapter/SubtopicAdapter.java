package com.saggy.wielearner.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.saggy.wielearner.R;

import java.util.List;

public class SubtopicAdapter extends RecyclerView.Adapter<SubtopicAdapter.viewHolder> {

    private final Context context;
    private final List<String> subtopiclist;
    private final List<String> videolinklist;
    private final List<String> noteslinklist;
    private int count = -1;


    public SubtopicAdapter(Context context, List<String> subtopiclist, List<String> videolinklist, List<String> noteslinklist) {
        this.context = context;
        this.subtopiclist = subtopiclist;
        this.videolinklist = videolinklist;
        this.noteslinklist = noteslinklist;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.subtopicitem, parent, false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        if(count != -1 && count == position){
            holder.subtopicname.setText(subtopiclist.get(position));
            holder.watchvideo.setVisibility(View.VISIBLE);
            holder.notes.setVisibility(View.VISIBLE);
        }
        else {
            holder.subtopicname.setText(subtopiclist.get(position));
            holder.watchvideo.setVisibility(View.GONE);
            holder.notes.setVisibility(View.GONE);
        }

        holder.watchvideo.setOnClickListener(v -> {
            String url = videolinklist.get(position);
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            context.startActivity(i);
        });
        holder.notes.setOnClickListener(v -> {
            String url = noteslinklist.get(position);
            if(url.equals("empty")){
                Toast.makeText(context, "Notes are not available for this topic", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subtopiclist.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView subtopicname;
        public AppCompatButton watchvideo;
        public AppCompatButton notes;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            subtopicname = itemView.findViewById(R.id.subtopicname);
            watchvideo = itemView.findViewById(R.id.watchvideo);
            notes = itemView.findViewById(R.id.notes);
        }

        @Override
        public void onClick(View v) {

            count = this.getAdapterPosition();
            notifyDataSetChanged();

//            Intent intent = new Intent(context, VideoActivity.class);
//            intent.putExtra("topicname",subtopiclist.get(position));
//            intent.putExtra("videolink",videolinklist.get(position));
//
//            context.startActivity(intent);
        }
    }
}
