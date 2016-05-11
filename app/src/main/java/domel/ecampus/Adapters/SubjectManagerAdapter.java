package domel.ecampus.Adapters;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


import java.util.ArrayList;

import domel.ecampus.Model.Subject;
import domel.ecampus.R;

public class SubjectManagerAdapter extends ArrayAdapter {
    private ArrayList<Subject> subjects;

    public SubjectManagerAdapter(Context context, int resource, ArrayList<Subject> arraySubjects) {

        super(context, resource);
        subjects = new ArrayList<Subject>();

        populateList(arraySubjects);
    }

    public void populateList(ArrayList<Subject> arraySubjects){

        subjects.clear();
        subjects.addAll(arraySubjects);
    }

    public int getCount(){

        return subjects.size();
    }

    public Subject getItem(int i){

        return subjects.get(i);
    }


    public View getView(final int position, View convertView, ViewGroup parent){

        View row = convertView;

        if(row == null){

            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.adapter_subject_manager, parent, false);
            row.setClickable(true);
        }

        return row;
    }
}
