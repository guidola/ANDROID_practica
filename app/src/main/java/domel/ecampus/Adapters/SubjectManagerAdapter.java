package domel.ecampus.Adapters;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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

import domel.ecampus.Activity.SubjectActivity;
import domel.ecampus.Base.BaseActivity;
import domel.ecampus.Model.Subject;
import domel.ecampus.MyApplication;
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

        AppCompatImageView image = (AppCompatImageView) row.findViewById(R.id.subject_image);
        AppCompatTextView name = (AppCompatTextView) row.findViewById(R.id.subject_name);
        AppCompatTextView description = (AppCompatTextView) row.findViewById(R.id.subject_description);
        AppCompatImageButton bin = (AppCompatImageButton) row.findViewById(R.id.delete_subject);
        Subject subject = getItem(position);

        image.setImageResource(subject.getImage());
        image.setScaleType(ImageView.ScaleType.FIT_CENTER);
        name.setText(StringUtils.capitalize(subject.getName()));
        description.setText(StringUtils.capitalize(subject.getDescription()));

        row.setClickable(true);
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SubjectActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("item", position); //since we work all time with the same collections we can use the position
                                                   //this should be replaced with some id to query for the real item on a more real situation
                getContext().startActivity(intent);
                //((Activity)getContext()).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                //not changing transition just yet cause it goes really slow even tho im lowing the time of the animation.
            }
        });

        bin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //launch alert dialog to ask for deletion.
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(getContext().getResources().getString(R.string.alert_delete_title));
                builder.setMessage(getContext().getResources().getString(R.string.alert_delete_msg_subject));
                builder.setCancelable(true);
                builder.setPositiveButton(getContext().getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //MyApplication.deleteSubject(getItem(position)); //this is what should go for data consistance and stuff
                        subjects.remove(position);
                        notifyDataSetChanged();
                        dialog.cancel();
                    }
                });
                builder.setNegativeButton(getContext().getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.create().show();
            }
        });

        return row;
    }
}
