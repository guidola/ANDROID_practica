package domel.ecampus.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import domel.ecampus.Adapters.StudentManagerAdapter;
import domel.ecampus.Model.Student;
import domel.ecampus.Model.Subject;
import domel.ecampus.R;

public class StudentManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_manager);

        ListView listView = (ListView)  findViewById(R.id.listv_student);

        ArrayList<Student> students = Student.getTestCollection();



        final StudentManagerAdapter studentAdapter = new StudentManagerAdapter(StudentManagerActivity.this, R.layout.adapter_student_manager, students);
        if (listView != null) {
            listView.setAdapter(studentAdapter);
        }

    }
}
