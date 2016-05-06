package domel.ecampus.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import domel.ecampus.Adapters.StudentManagerAdapter;
import domel.ecampus.Model.Student;
import domel.ecampus.R;

public class SubjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
    }
}
