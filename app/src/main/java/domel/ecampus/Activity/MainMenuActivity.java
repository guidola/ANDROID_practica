package domel.ecampus.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import domel.ecampus.R;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        LinearLayout subject = (LinearLayout)this.findViewById(R.id.linear_subjects);
        LinearLayout student = (LinearLayout)this.findViewById(R.id.linear_students);
        LinearLayout exam = (LinearLayout)this.findViewById(R.id.linear_exam);
        LinearLayout logout = (LinearLayout)this.findViewById(R.id.linear_logout);

        subject.setOnClickListener(new View.OnClickListener() {
            //go to subject manager activity
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, SubjectManagerActivity.class);
                startActivity(intent);

            }
        });

        student.setOnClickListener(new View.OnClickListener() {
            //go to subject manager activity
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, StudentManagerActivity.class);
                startActivity(intent);

            }
        });

        exam.setOnClickListener(new View.OnClickListener() {
            //go to subject manager activity
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, ExamsListActivity.class);
                startActivity(intent);

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //logout
                Intent intent = new Intent(MainMenuActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });
    }
}
