package com.saggy.wielearner.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saggy.wielearner.Model.Subject;
import com.saggy.wielearner.Model.SubjectModel;
import com.saggy.wielearner.R;
import com.saggy.wielearner.SubjectSelection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.Viewholder> implements Filterable{
    private Context context;
    private ArrayList<SubjectModel> subjects;
    private ArrayList<SubjectModel> subjectfull;
    private List<Subject> selectedsubject;

    public SubjectAdapter(Context context, ArrayList<SubjectModel> subjects, List<Subject> selectedsubject) {
        this.context = context;
        this.subjects = subjects;
        this.selectedsubject = selectedsubject;
        subjectfull = new ArrayList<>(subjects);
    }

    @Override
    public Filter getFilter() {
        return subjectfilter;
    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.subjectitem, parent, false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  SubjectAdapter.Viewholder holder, int position) {
        SubjectModel subjectModel = subjects.get(position);

        holder.subjectname.setText(subjectModel.getSubjectname());
        holder.coursename.setText(subjectModel.getCoursename());
        holder.semester.setText(String.format("%s Semester", subjectModel.getSemester()));
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView subjectname;
        public TextView coursename;
        public TextView semester;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            subjectname = itemView.findViewById(R.id.subname);
            coursename = itemView.findViewById(R.id.coursename);
            semester = itemView.findViewById(R.id.semestername);
        }

        @Override
        public void onClick(View v) {
            int position = this.getAdapterPosition();
            SubjectModel subjectModel = subjects.get(position);
            Subject subject = new Subject(subjectModel.getCoursecode(),subjectModel.getCoursename()
                    ,subjectModel.getSubjectname(), subjectModel.getSemester()+" Semester");
            int c = 1;
            for (Subject s: selectedsubject){
                if(s.getSubjectname().equals(subject.getSubjectname())){
                    c=0;
                }
            }
            if(c == 1) {
                selectedsubject.add(subject);
                Intent intent = new Intent(context, SubjectSelection.class);
                intent.putExtra("list", (Serializable) selectedsubject);
                intent.putExtra("temp", 1);
                context.startActivity(intent);
                ((Activity) context).finish();
            }
            else{
                Toast.makeText(context, "You have already selected this subject.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private Filter subjectfilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<SubjectModel> filterlist = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filterlist.addAll(subjectfull);
            }
            else{
                String filterpattern = constraint.toString().toLowerCase().trim();

                for(SubjectModel model : subjectfull){
                    if(model.getSubjectname().toLowerCase().contains(filterpattern)){
                        filterlist.add(model);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterlist;

            return  results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            subjects.clear();
            subjects.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };
}
