package domel.ecampus.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import domel.ecampus.Model.Student;
import domel.ecampus.R;


public class StudentManagerAdapter extends ArrayAdapter{


    private ArrayList<Student> students;

    public StudentManagerAdapter(Context context, int resource, ArrayList<Student> arrayStudents) {

        super(context, resource);
        students = new ArrayList<Student>();

        populateList(arrayStudents);
    }

    public void populateList(ArrayList<Student> arrayStudents){

        students.clear();
        students.addAll(arrayStudents);
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
            row = inflater.inflate(R.layout.adapter_student_manager, parent, false);
            row.setClickable(true);
        }

        return row;
    }
}
