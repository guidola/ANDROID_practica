package domel.ecampus.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import domel.ecampus.Adapters.SubjectManagerAdapter;
import domel.ecampus.Model.Subject;
import domel.ecampus.R;

public class SubjectManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_manager);

        ListView listView = (ListView)  findViewById(R.id.listv_subject);

        ArrayList<Subject> subjects = Subject.getTestCollection();


        SubjectManagerAdapter subjectAdapter = new SubjectManagerAdapter(SubjectManagerActivity.this, R.layout.adapter_subject_manager, subjects);
        if (listView != null) {
            listView.setAdapter(subjectAdapter);
        }
    }
}
