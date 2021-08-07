package com.saggy.wielearner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.saggy.wielearner.Adapter.SubjectAdapter;
import com.saggy.wielearner.Model.Subject;
import com.saggy.wielearner.Model.SubjectModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class SearchSubject extends AppCompatActivity {

    RecyclerView recyclerView;
    SearchView searchView;
    SubjectAdapter subjectsAdapter;
    ArrayList<SubjectModel> subjectlist = new ArrayList<>();

    List<Subject> selectedsubjects;

    ProgressDialog progressDialog;

    AsyncHttpClient client;
    Workbook workbook;

    int temp = 0;

    String userboard;
    String usercourse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_subject);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.show();

        recyclerView = findViewById(R.id.recycleview);
        searchView = findViewById(R.id.searchView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userboard = Objects.requireNonNull(getIntent().getExtras()).getString("board");
        usercourse = getIntent().getExtras().getString("course");
        selectedsubjects = (List<Subject>) Objects.requireNonNull(getIntent().getSerializableExtra("list"));


        String url = "https://github.com/Saggy001/StudyApp-Exceltransfer/blob/main/PTU%20Subjects.xls?raw=true";


        client = new AsyncHttpClient();
        client.get(url, new FileAsyncHttpResponseHandler(this) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                Toast.makeText(SearchSubject.this, "Check your Internet Connection", Toast.LENGTH_SHORT).show();
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
                        for(int i = 0 ; i< sheet.getRows() ; i++){

                            Cell[] rows = sheet.getRow(i);

                            if(rows[1].getContents().equals(usercourse) && rows[6].getContents().equals(userboard)){
                                SubjectModel model = new SubjectModel();
                                model.setSubjectname(rows[4].getContents());
                                model.setCoursename(rows[1].getContents());
                                model.setCoursecode(rows[0].getContents());
                                model.setSemester(rows[5].getContents());
                                subjectlist.add(model);

                            }
                         }
                        subjectsAdapter = new SubjectAdapter(SearchSubject.this, subjectlist,selectedsubjects);
                        recyclerView.setAdapter(subjectsAdapter);
                        progressDialog.dismiss();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (BiffException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                subjectsAdapter.getFilter().filter(newText);
                return false;
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}