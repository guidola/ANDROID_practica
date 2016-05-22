package domel.ecampus.Activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import domel.ecampus.Base.BaseActivity;
import domel.ecampus.Model.Student;
import domel.ecampus.Model.Subject;
import domel.ecampus.Model.SubjectTheme;
import domel.ecampus.MyApplication;
import domel.ecampus.R;

public class StudentActivity extends BaseActivity {
    private boolean zoomOut =  false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);


        Student st = MyApplication.getStudentById((int)getIntent().getExtras().getInt("id"));


        //id of the info to the layout
        AppCompatImageView image = (AppCompatImageView) findViewById(R.id.profile_picture);
        AppCompatTextView name = (AppCompatTextView) findViewById(R.id.student_name);
        AppCompatTextView age = (AppCompatTextView) findViewById(R.id.student_birthdate);
        AppCompatTextView speciality = (AppCompatTextView) findViewById(R.id.student_career);
        AppCompatTextView gender = (AppCompatTextView) findViewById(R.id.student_gender);

        //set the info

        //for compatibily while whole app refactor is done
        if(st.getPath() == null){
            if(image != null) image.setImageResource(st.getImage());
        }else{
            if(image != null) image.setImageURI(st.getPath());
        }

        if(name != null) name.setText(StringUtils.capitalize(st.getName()));
        if (age != null) age.setText(StringUtils.capitalize(st.getBithdateString()));
        if(speciality != null) speciality.setText(StringUtils.capitalize(st.getSpecialty()));
        if(gender != null) gender.setText(StringUtils.capitalize(st.getGender()));

        ViewGroup subjects_wrapper = (ViewGroup) findViewById(R.id.subject_wrapper);

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


        if (image != null) {
            image.setOnClickListener(new View.OnClickListener() {
                //go to student manager activity
                @Override
                public void onClick(View view) {
                    Log.d("fullscren", "open");
                    View v = findViewById(R.id.full_screen_wrapper);
                    AppCompatImageView image = (AppCompatImageView) findViewById(R.id.full_screen_image);
                    //for compatibily while whole app refactor is done
                    AppCompatImageView miniature = (AppCompatImageView) view;
                    image.setImageDrawable(miniature.getDrawable());
                    /*Animation bottomDown = AnimationUtils.loadAnimation(StudentActivity.this,
                            R.anim.zoom_in);
                    if (v != null) {
                        v.startAnimation(bottomDown);
                        v.setVisibility(View.VISIBLE);
                    }*/
                    v.setVisibility(View.VISIBLE);

                }
            });
        }

        View close_but = findViewById(R.id.btnClose);
        if (close_but != null) {
            close_but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("fullscren", "close");
                    View v = findViewById(R.id.full_screen_wrapper);
                    /*Animation bottomDown = AnimationUtils.loadAnimation(StudentActivity.this,
                            R.anim.zoom_out);
                    if (v != null) {
                        v.startAnimation(bottomDown);
                        v.setVisibility(View.GONE);
                    }*/
                    v.setVisibility(View.GONE);
                }
            });
        }


        //logout button
        ImageView closeSesionButton = (ImageView) findViewById(R.id.close);
        if (closeSesionButton != null) {
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
