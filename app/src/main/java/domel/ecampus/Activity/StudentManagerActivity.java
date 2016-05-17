package domel.ecampus.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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

        ImageButton addStudentButton = (ImageButton) findViewById(R.id.addStudent_toolbar);



        final StudentManagerAdapter studentAdapter = new StudentManagerAdapter(StudentManagerActivity.this, R.layout.adapter_student_manager, students);
        if (listView != null) {
            listView.setAdapter(studentAdapter);
        }

        addStudentButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentManagerActivity.this, AddStudentActivity.class);
                startActivity(intent);

            }

        });

        //logout button
        ImageView closeSesionButton = (ImageView) findViewById(R.id.close);
        closeSesionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //logout
                Intent intent = new Intent(StudentManagerActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }

        });


    }
}
