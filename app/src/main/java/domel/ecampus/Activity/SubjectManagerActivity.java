package domel.ecampus.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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

        //logout button
        ImageView closeSesionButton = (ImageView) findViewById(R.id.close);
        closeSesionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //logout
                Intent intent = new Intent(SubjectManagerActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }

        });
    }
}
