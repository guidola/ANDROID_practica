package domel.ecampus.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import domel.ecampus.Adapters.ExamListAdapter;
import domel.ecampus.Model.Exam;
import domel.ecampus.R;

public class ExamsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams_list);

        ListView listView = (ListView)  findViewById(R.id.listv_exams);

        ArrayList<Exam> exams = new ArrayList<Exam>();

        ExamListAdapter examsAdapter = new ExamListAdapter(ExamsListActivity.this, R.layout.adapter_exam_list, exams);
        listView.setAdapter(examsAdapter);

    }
}
