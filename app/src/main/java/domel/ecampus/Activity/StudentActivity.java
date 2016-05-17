package domel.ecampus.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import domel.ecampus.Model.Student;
import domel.ecampus.Model.Subject;
import domel.ecampus.R;

public class StudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        Student st = new Student(99 ,"test student",R.drawable.student, new DateTime(1991,2,23,0,0), "ADE", "Hombre");

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
