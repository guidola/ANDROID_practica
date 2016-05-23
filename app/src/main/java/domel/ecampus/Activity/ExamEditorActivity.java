package domel.ecampus.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.ImageView;

import domel.ecampus.Model.Exam;
import domel.ecampus.Model.Student;
import domel.ecampus.MyApplication;
import domel.ecampus.R;

public class ExamEditorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_editor);

        AppCompatTextView title = (AppCompatTextView) findViewById(R.id.editor_or_new);


        Exam editorExam = MyApplication.getExamById((int)getIntent().getExtras().getInt("id"));

        if(editorExam == null){
            //editor exam
            title.setText(getString(R.string.exam_editor_title));

        }else{
            //create exam
            title.setText(getString(R.string.exam_new_title));

        }


        //logout button
        ImageView closeSesionButton = (ImageView) findViewById(R.id.close);
        closeSesionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //logout
                Intent intent = new Intent(ExamEditorActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }

        });
    }
}
