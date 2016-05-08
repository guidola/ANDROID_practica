package domel.ecampus.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import domel.ecampus.Model.Student;
import domel.ecampus.Model.SubjectTheme;
import domel.ecampus.R;

/**
 * Created by Guillermo on 8/5/16.
 */
public class SubjectThemeAdapter extends ArrayAdapter<SubjectTheme> {

    private ArrayList<SubjectTheme> themes;

    public SubjectThemeAdapter(Context context, int resource, ArrayList<SubjectTheme> arrayStudents) {

        super(context, resource);
        themes = new ArrayList<>();

        populateList(arrayStudents);
    }

    public void populateList(ArrayList<SubjectTheme> arrayStudents){

        themes.clear();

        Log.d("DEBUG", "on populateList method");
        themes.addAll(arrayStudents);
    }

    public int getCount(){

        return themes.size();
    }

    public SubjectTheme getItem(int i){

        return themes.get(i);
    }


    public View getView(final int position, View convertView, ViewGroup parent){

        View row = convertView;

        if(row == null){

            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.adapter_subject_themes, parent, false);
            row.setClickable(true);
        }

        return row;
    }

}
