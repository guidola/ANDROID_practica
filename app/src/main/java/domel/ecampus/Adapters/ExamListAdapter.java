package domel.ecampus.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

import domel.ecampus.Activity.ExamEditorActivity;
import domel.ecampus.Activity.StudentActivity;
import domel.ecampus.Model.Exam;
import domel.ecampus.Model.Student;
import domel.ecampus.R;


public class ExamListAdapter extends ArrayAdapter {


    private ArrayList<Exam> exams;

    public ExamListAdapter(Context context, int resource, ArrayList<Exam> arrayExams) {

        super(context, resource);
        exams = new ArrayList<Exam>();

        populateList(arrayExams);
    }

    public void populateList(ArrayList<Exam> arrayExams){

        exams.clear();
        exams.addAll(arrayExams);
    }

    public int getCount(){

        return exams.size();
    }

    public Exam getItem(int i){

        return exams.get(i);
    }


    public View getView(final int position, View convertView, ViewGroup parent){

        View row = convertView;

        if(row == null){

            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.adapter_exam_list, parent, false);
            row.setClickable(true);
        }
//copy of student
        //set the info of the students
        AppCompatImageView image = (AppCompatImageView) row.findViewById(R.id.student_image);
        AppCompatTextView name = (AppCompatTextView) row.findViewById(R.id.student_name);
        AppCompatTextView age = (AppCompatTextView) row.findViewById(R.id.student_age);
        AppCompatTextView speciality = (AppCompatTextView) row.findViewById(R.id.student_speciality);
        AppCompatImageButton bin = (AppCompatImageButton) row.findViewById(R.id.delete_student);
        final Exam exam = getItem(position);
        /*
        image.setImageResource(exam.getImage());
        image.setScaleType(ImageView.ScaleType.FIT_CENTER);
        name.setText(StringUtils.capitalize(exam.getName()));
        age.setText(StringUtils.capitalize(exam.getAgeString()));
        speciality.setText(StringUtils.capitalize(exam.getSpecialty()));*/

        //if click teh row go to the student preview
        row.setClickable(true);
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ExamEditorActivity.class);
                intent.putExtra("id", exam.getId());
                getContext().startActivity(intent);

            }
        });

        return row;
    }
}
