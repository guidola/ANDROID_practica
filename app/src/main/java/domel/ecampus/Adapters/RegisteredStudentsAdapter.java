package domel.ecampus.Adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;

import java.util.ArrayList;

import domel.ecampus.Model.Student;
import domel.ecampus.MyApplication;
import domel.ecampus.R;

/**
 * Created by Guillermo on 8/5/16.
 */
public class RegisteredStudentsAdapter extends ArrayAdapter<Student> {

    private ArrayList<Student> students;
    private ArrayList<Student> selected_students;

    public RegisteredStudentsAdapter(Context context, int resource) {

        super(context, resource);
        students = MyApplication.getStudents();
        selected_students = new ArrayList<>();
    }


    public int getCount(){

        return students.size();
    }

    public Student getItem(int i){

        return students.get(i);
    }


    public View getView(final int position, View convertView, ViewGroup parent){

        View row = convertView;

        if(row == null){

            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.adapter_registered_students, parent, false);
            row.setClickable(true);
        }


        AppCompatImageView image = (AppCompatImageView) row.findViewById(R.id.profile_picture);
        AppCompatTextView name = (AppCompatTextView) row.findViewById(R.id.student_name);
        AppCompatCheckBox check = (AppCompatCheckBox) row.findViewById(R.id.checkbox);
        Student student = getItem(position);

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatCheckBox cbox = (AppCompatCheckBox) v.findViewById(R.id.checkbox);
                cbox.setChecked(!cbox.isChecked());
            }
        });

        image.setImageResource(student.getImage());
        name.setText(student.getName());
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    selected_students.add(getItem(position));
                }else{
                    selected_students.remove(getItem(position));
                }
            }
        });


        return row;
    }

    public ArrayList<Student> getSelectedStudents() {
        return selected_students;
    }
}
