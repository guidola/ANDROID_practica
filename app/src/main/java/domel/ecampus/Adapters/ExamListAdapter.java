package domel.ecampus.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

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

        return row;
    }
}
