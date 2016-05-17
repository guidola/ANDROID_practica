package domel.ecampus.Activity;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.zip.Inflater;

import domel.ecampus.Adapters.StudentManagerAdapter;
import domel.ecampus.Model.Student;
import domel.ecampus.Model.Subject;
import domel.ecampus.Model.SubjectTheme;
import domel.ecampus.R;

public class SubjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        AppCompatTextView name = (AppCompatTextView) findViewById(R.id.subject_name);
        AppCompatTextView description = (AppCompatTextView) findViewById(R.id.subject_description);
        Subject subject = new Subject("test subject", R.mipmap.la_salle_logo, "this is some dummy text this is some dummy text this is some dummy text this is some dummy text this is some dummy text this is some dummy text this is some dummy text this is some dummy text this is some dummy text ");

        for (int i = 0; i < 10; i++) {
            subject.getThemes().add(new SubjectTheme(i, "Dummy theme"));
        }

        for (int i = 0; i < 10; i++) {
            subject.getStudents().add(new Student(i, "John Doe", R.mipmap.la_salle_logo, new DateTime(1991,2,23,0,0), "Paleontologist", "Hombre"));
        }

        if (name != null) {
            name.setText(StringUtils.capitalize(subject.getName()));
        }

        if (description != null) {
            description.setText(StringUtils.capitalize(subject.getDescription()));
        }

        ViewGroup themes_wrapper = (ViewGroup) findViewById(R.id.themes_wrapper);

        if(subject.getThemes().size() > 0){

            int index = 1;

            for (SubjectTheme t : subject.getThemes()){

                AppCompatTextView theme_row = new AppCompatTextView(this);
                String row_text = index++ + ". " + StringUtils.capitalize(t.getName());
                theme_row.setText(row_text);
                theme_row.setPadding(16,8,8,8);
                theme_row.setGravity(Gravity.CENTER_VERTICAL);


                if (themes_wrapper != null) {
                    themes_wrapper.addView(theme_row, themes_wrapper.getChildCount(), new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    ));
                }

            }
        }else{

            AppCompatTextView no_themes = new AppCompatTextView(this);
            no_themes.setText(R.string.no_themes_for_subject);
            no_themes.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
            no_themes.setPadding(0,32,0,0);
            no_themes.setGravity(Gravity.CENTER_HORIZONTAL);

            if (themes_wrapper != null) {
                themes_wrapper.addView(no_themes, themes_wrapper.getChildCount(), new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
            }

        }

        ViewGroup students_wrapper = (ViewGroup) findViewById(R.id.students_wrapper);
        int index = 1;
        if(subject.getStudents().size() > 0){

            for (Student student: subject.getStudents()){

                View v = getLayoutInflater().inflate(R.layout.row_enrolled_students, students_wrapper, false);

                AppCompatImageView student_image = (AppCompatImageView) v.findViewById(R.id.imageView);
                AppCompatTextView student_name = (AppCompatTextView) v.findViewById(R.id.textView2);
                AppCompatTextView student_spec = (AppCompatTextView) v.findViewById(R.id.textView3);

                student_image.setImageResource(student.getImage());
                student_name.setText(student.getName());
                student_spec.setText(student.getSpecialty());

                v.setTag(student.getId());
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SubjectActivity.this, StudentActivity.class);
                        //intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        intent.putExtra("item", (int)v.getTag());
                        startActivity(intent);
                        //((Activity)getContext()).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        //not changing transition just yet cause it goes really slow even tho im lowing the time of the animation.
                    }
                });

                if (students_wrapper != null) {
                    students_wrapper.addView(v);
                }

            }

        }else{

            AppCompatTextView no_themes = new AppCompatTextView(this);
            no_themes.setText(R.string.no_students_for_subject);
            no_themes.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
            no_themes.setPadding(0,32,0,0);
            no_themes.setGravity(Gravity.CENTER_HORIZONTAL);

        if (students_wrapper != null) {
            students_wrapper.addView(no_themes, students_wrapper.getChildCount(), new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
        }

    }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        //not changing transition just yet cause it goes really slow even tho im lowing the time of the animation.
        finish();
    }
}
