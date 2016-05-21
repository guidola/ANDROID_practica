package domel.ecampus.Activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import domel.ecampus.Model.Student;
import domel.ecampus.Model.Subject;
import domel.ecampus.Model.SubjectTheme;
import domel.ecampus.R;

public class StudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
/*
        Student st = new Student("test student",R.drawable.student, new DateTime(1991,2,23,0,0), "ADE", "Hombre");
        for(int i = 0; i<10;i++){
            st.getSubjects().add(new Subject("test subject", R.mipmap.la_salle_logo, "this is some dummy text this is some dummy text this is some dummy text "));
        }
*/

        int nStudent = getIntent().getExtras().getInt("position");
        ArrayList<Student> sts = Student.getTestCollection();
        Student st = sts.get(nStudent);


        //set the info
        AppCompatImageView image = (AppCompatImageView) findViewById(R.id.profile_picture);
        AppCompatTextView name = (AppCompatTextView) findViewById(R.id.student_name);
        AppCompatTextView age = (AppCompatTextView) findViewById(R.id.student_birthdate);
        AppCompatTextView speciality = (AppCompatTextView) findViewById(R.id.student_career);
        AppCompatTextView gender = (AppCompatTextView) findViewById(R.id.student_gender);


        image.setImageResource(st.getImage());
        name.setText(StringUtils.capitalize(st.getName()));
        age.setText(StringUtils.capitalize(st.getBithdateString()));
        speciality.setText(StringUtils.capitalize(st.getSpecialty()));
        gender.setText(StringUtils.capitalize(st.getGender()));

        ViewGroup subjects_wrapper = (ViewGroup) findViewById(R.id.subject_wrapper);

        if(st.getSubjects().size() > 0){

            int index = 1;

            for (Subject t : st.getSubjects()){

                AppCompatTextView subject_row = new AppCompatTextView(this);
                String row_text = index++ + ". " + StringUtils.capitalize(t.getName());
                subject_row.setText(row_text);
                subject_row.setPadding(16,8,8,8);
                subject_row.setGravity(Gravity.CENTER_VERTICAL);


                if (subjects_wrapper != null) {
                    subjects_wrapper.addView(subject_row, subjects_wrapper.getChildCount(), new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    ));
                }

            }
        }

        //logout button
        ImageView closeSesionButton = (ImageView) findViewById(R.id.close);
        closeSesionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //logout
                Intent intent = new Intent(StudentActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }

        });



    }

    //finish this activity going back
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        //not changing transition just yet cause it goes really slow even tho im lowing the time of the animation.
        finish();
    }
}
