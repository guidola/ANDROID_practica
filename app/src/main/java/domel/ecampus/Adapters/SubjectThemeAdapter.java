package domel.ecampus.Adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Collections;

import domel.ecampus.Model.Student;
import domel.ecampus.Model.SubjectTheme;
import domel.ecampus.R;

/**
 * Created by Guillermo on 8/5/16.
 */
public class SubjectThemeAdapter extends ArrayAdapter<SubjectTheme> {

    private ArrayList<SubjectTheme> themes;

    public SubjectThemeAdapter(Context context, int resource) {

        super(context, resource);
        themes = new ArrayList<>();


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


        AppCompatTextView number = (AppCompatTextView) row.findViewById(R.id.numeration);
        AppCompatTextView name = (AppCompatTextView) row.findViewById(R.id.theme_name);
        SubjectTheme theme = getItem(position);

        number.setText(getContext().getString(R.string.theme_numeration, position));
        name.setText(theme.getName());

        AppCompatImageButton up = (AppCompatImageButton) row.findViewById(R.id.button_up);
        AppCompatImageButton down = (AppCompatImageButton) row.findViewById(R.id.button_down);
        AppCompatImageButton delete = (AppCompatImageButton) row.findViewById(R.id.button_delete);

        up.setTag(position);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int)v.getTag();
                if(pos != 0) {
                    Collections.swap(themes, pos, pos - 1);
                    notifyDataSetChanged();
                }
            }
        });

        down.setTag(position);
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int)v.getTag();
                if(pos != themes.size() - 1){
                    Collections.swap(themes, pos, pos + 1);
                    notifyDataSetChanged();
                }
            }
        });

        delete.setTag(position);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int)v.getTag();
                themes.remove(pos);
                notifyDataSetChanged();
            }
        });

        return row;
    }

    public ArrayList<SubjectTheme> getThemes() {
        return themes;
    }

    @Override
    public boolean isEmpty() {
        return themes.isEmpty();
    }

    @Override
    public void add(SubjectTheme object) {
        themes.add(object);
    }
}
