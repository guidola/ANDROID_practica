package domel.ecampus.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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

        if (subject != null) {
            subject.setOnClickListener(new View.OnClickListener() {
                //go to subject manager activity
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainMenuActivity.this, SubjectManagerActivity.class);
                    startActivity(intent);
                }
            });
        }

        if (student != null) {
            student.setOnClickListener(new View.OnClickListener() {
                //go to student manager activity
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainMenuActivity.this, StudentManagerActivity.class);
                    startActivity(intent);
                }
            });
        }

        if (exam != null) {
            exam.setOnClickListener(new View.OnClickListener() {
                //go to subject manager activity
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainMenuActivity.this, ExamsListActivity.class);
                    startActivity(intent);
                }
            });
        }

        if (logout != null) {
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //close the app
                    finish();

                }
            });
        }


    }
    //finish this activity going back
    public void onBackPressed() {
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }
}
