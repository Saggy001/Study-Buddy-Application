package com.saggy.wielearner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.saggy.wielearner.Database.Usersdb;
import com.saggy.wielearner.Model.SubjectModel;
import com.saggy.wielearner.Model.UserInfoModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class Quizzes extends Fragment {

    CardView cardv1 , cardv2, cardv3, cardv4, cardv5, cardv6;
    TextView subject1, subject2, subject3 , subject4 , subject5, subject6;

    FirebaseUser firebaseUser;
    String username;

    ProgressDialog progressDialog;

    List<SubjectModel> mysubjects = new ArrayList<>();

    AsyncHttpClient client;
    Workbook workbook;

    UserInfoModel user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quizzesfragment, container, false);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait...Depending on the Internet speed");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.show();

        cardv1 = view.findViewById(R.id.cardview1);
        cardv2 = view.findViewById(R.id.cardview2);
        cardv3 = view.findViewById(R.id.cardview3);
        cardv4 = view.findViewById(R.id.cardview4);
        cardv5 = view.findViewById(R.id.cardview5);
        cardv6 = view.findViewById(R.id.cardview6);

        subject1 = view.findViewById(R.id.subject1);
        subject2 = view.findViewById(R.id.subject2);
        subject3 = view.findViewById(R.id.subject3);
        subject4 = view.findViewById(R.id.subject4);
        subject5 = view.findViewById(R.id.subject5);
        subject6 = view.findViewById(R.id.subject6);

        Usersdb db = new Usersdb(getActivity());

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        assert firebaseUser != null;
        username = firebaseUser.getDisplayName();


        user = db.getuserdetails(username);

        String url = "https://github.com/Saggy001/StudyApp-Exceltransfer/blob/main/PTU%20Subjects.xls?raw=true";

        client = new AsyncHttpClient();
        client.get(url, new FileAsyncHttpResponseHandler(getContext()) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                Toast.makeText(getContext(), "Check your Internet Connection", Toast.LENGTH_SHORT).show();
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
                        int n=0;
                        for(int i = 0 ; i< sheet.getRows() ; i++){
                            Cell[] rows = sheet.getRow(i);

                            if(rows[0].getContents().equals(user.getSub1()) || rows[0].getContents().equals(user.getSub2())
                                    || rows[0].getContents().equals(user.getSub3()) || rows[0].getContents().equals(user.getSub4())
                                    || rows[0].getContents().equals(user.getSub5()) || rows[0].getContents().equals(user.getSub6())){
                                SubjectModel model = new SubjectModel();
                                model.setSubjectname(rows[4].getContents());
                                model.setCoursename(rows[1].getContents());
                                model.setCoursecode(rows[0].getContents());
                                //model.setSemester(rows[5].getContents());
                                model.setTopic(rows[2].getContents());
                                //model.setSubtopic(rows[3].getContents());
                                //model.setBoardname(rows[6].getContents());
                                //model.setVideolink(rows[7].getContents());
                                model.setQuizlink(rows[9].getContents());

                                mysubjects.add(model);
                                n++;
                            }
                            if(n == 6){
                                break;
                            }
                        }


                        subject1.setText(mysubjects.get(0).getSubjectname());
                        subject2.setText(mysubjects.get(1).getSubjectname());
                        subject3.setText(mysubjects.get(2).getSubjectname());
                        subject4.setText(mysubjects.get(3).getSubjectname());
                        subject5.setText(mysubjects.get(4).getSubjectname());
                        subject6.setText(mysubjects.get(5).getSubjectname());
                        progressDialog.dismiss();

                    } catch (IOException | BiffException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        cardv1.setOnClickListener(v -> {
            Intent into = new Intent(getContext() , TopicQuiz.class);
            into.putExtra("subjectname",mysubjects.get(0).getSubjectname());
            into.putExtra("subjectcode",mysubjects.get(0).getCoursecode());
            into.putExtra("topic",mysubjects.get(0).getTopic());
            into.putExtra("quizlink",mysubjects.get(0).getQuizlink());
            startActivity(into);
        });

        cardv2.setOnClickListener(v -> {
            Intent into = new Intent(getContext() , TopicQuiz.class);
            into.putExtra("subjectname",mysubjects.get(1).getSubjectname());
            into.putExtra("subjectcode",mysubjects.get(1).getCoursecode());
            into.putExtra("topic",mysubjects.get(1).getTopic());
            into.putExtra("quizlink",mysubjects.get(1).getQuizlink());
            startActivity(into);
        });

        cardv3.setOnClickListener(v -> {
            Intent into = new Intent(getContext() , TopicQuiz.class);
            into.putExtra("subjectname",mysubjects.get(2).getSubjectname());
            into.putExtra("subjectcode",mysubjects.get(2).getCoursecode());
            into.putExtra("topic",mysubjects.get(2).getTopic());
            into.putExtra("quizlink",mysubjects.get(2).getQuizlink());
            startActivity(into);
        });

        cardv4.setOnClickListener(v -> {
            Intent into = new Intent(getContext() , TopicQuiz.class);
            into.putExtra("subjectname",mysubjects.get(3).getSubjectname());
            into.putExtra("subjectcode",mysubjects.get(3).getCoursecode());
            into.putExtra("topic",mysubjects.get(3).getTopic());
            into.putExtra("quizlink",mysubjects.get(3).getQuizlink());
            startActivity(into);
        });

        cardv5.setOnClickListener(v -> {
            Intent into = new Intent(getContext() , TopicQuiz.class);
            into.putExtra("subjectname",mysubjects.get(4).getSubjectname());
            into.putExtra("subjectcode",mysubjects.get(4).getCoursecode());
            into.putExtra("topic",mysubjects.get(4).getTopic());
            into.putExtra("quizlink",mysubjects.get(4).getQuizlink());
            startActivity(into);
        });

        cardv6.setOnClickListener(v -> {
            Intent into = new Intent(getContext() , TopicQuiz.class);
            into.putExtra("subjectname",mysubjects.get(5).getSubjectname());
            into.putExtra("subjectcode",mysubjects.get(5).getCoursecode());
            into.putExtra("topic",mysubjects.get(5).getTopic());
            into.putExtra("quizlink",mysubjects.get(5).getQuizlink());
            startActivity(into);
        });

        return view;    }
}