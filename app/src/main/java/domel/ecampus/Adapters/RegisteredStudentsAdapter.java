package domel.ecampus.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import domel.ecampus.Model.Student;
import domel.ecampus.R;

/**
 * Created by Guillermo on 8/5/16.
 */
public class RegisteredStudentsAdapter extends ArrayAdapter<Student> {

    private ArrayList<Student> students;

    public RegisteredStudentsAdapter(Context context, int resource, ArrayList<Student> arrayStudents) {

        super(context, resource);
        students = new ArrayList<>();

        populateList(arrayStudents);
    }

    public void populateList(ArrayList<Student> arrayStudents){

        students.clear();

        Log.d("DEBUG", "on populateList method");
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
            row = inflater.inflate(R.layout.adapter_registered_students, parent, false);
            row.setClickable(true);
        }

        return row;
    }

}
