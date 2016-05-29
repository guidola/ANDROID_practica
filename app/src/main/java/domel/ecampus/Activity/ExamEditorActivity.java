package domel.ecampus.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.codetroopers.betterpickers.timepicker.TimePickerBuilder;
import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;

import com.codetroopers.betterpickers.timepicker.TimePickerDialogFragment;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import domel.ecampus.Adapters.ExamListAdapter;
import domel.ecampus.Adapters.SubjectThemeAdapter;
import domel.ecampus.Base.BaseActivity;
import domel.ecampus.Model.Exam;
import domel.ecampus.Model.Student;
import domel.ecampus.Model.Subject;
import domel.ecampus.MyApplication;
import domel.ecampus.R;

public class ExamEditorActivity extends BaseActivity implements CalendarDatePickerDialogFragment.OnDateSetListener, TimePickerDialogFragment.TimePickerDialogHandler{
    private EditText setDateEditText;
    private EditText setHourEditText;
    private int year;
    private int monthOfYear;
    private int dayOfMonth;
    private String hour;
    private Spinner spinnerDegree;
    private Spinner spinnerSubject;

    private Exam editorExam;


    private static final String FRAG_TAG_DATE_PICKER = "fragment_date_picker_name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_editor);
        editorExam = null;
        AppCompatTextView title = (AppCompatTextView) findViewById(R.id.editor_or_new);

        setDateEditText = (EditText) findViewById(R.id.exam_date);
        setHourEditText = (EditText) findViewById(R.id.exam_hour);


        setDateEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment()
                            .setOnDateSetListener(ExamEditorActivity.this);
                    cdp.show(getSupportFragmentManager(), FRAG_TAG_DATE_PICKER);
                }
            }
        });

        setDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment()
                        .setOnDateSetListener(ExamEditorActivity.this);
                cdp.show(getSupportFragmentManager(), FRAG_TAG_DATE_PICKER);
            }
        });


        //set exam hour
        setHourEditText = (EditText) findViewById(R.id.exam_hour);
        setHourEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    TimePickerBuilder tpb = new TimePickerBuilder()
                            .setFragmentManager(getSupportFragmentManager())
                            .setStyleResId(R.style.BetterPickersDialogFragment);
                    tpb.show();
                }
            }
        });
        setHourEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerBuilder tpb = new TimePickerBuilder()
                        .setFragmentManager(getSupportFragmentManager())
                        .setStyleResId(R.style.BetterPickersDialogFragment);
                tpb.show();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setDateEditText.setShowSoftInputOnFocus(false);
            setHourEditText.setShowSoftInputOnFocus(false);
        }else{
            setDateEditText.setInputType(InputType.TYPE_NULL);
            setHourEditText.setInputType(InputType.TYPE_NULL);
        }



        //degree spinner
        spinnerDegree = (Spinner) findViewById(R.id.career_spinner);
        ArrayAdapter<CharSequence> adapterDegree = ArrayAdapter.createFromResource(this, R.array.degrees, android.R.layout.simple_spinner_dropdown_item);
        if (spinnerDegree != null) {
            spinnerDegree.setAdapter(adapterDegree);
        }

        spinnerSubject = (Spinner) findViewById(R.id.subject_spinner);
        int sel_subject = -1;
        //subject spinner
        ArrayList<String> subjectsName = new ArrayList<>();
        for(Subject s : getApp().getSubjects()){
            subjectsName.add(s.getName());
            if(editorExam != null && s.getName().equals(editorExam.getSubject().getName())){
                sel_subject = subjectsName.indexOf(s.getName());
            }
        }

        ArrayAdapter<CharSequence> adapterSubject = new ArrayAdapter(ExamEditorActivity.this, android.R.layout.simple_spinner_dropdown_item, subjectsName);
        spinnerSubject.setAdapter(adapterSubject);
        if(sel_subject != -1) spinnerSubject.setSelection(sel_subject);

        //filter subjects for the degree selected
        spinnerDegree.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
                        ArrayList<String> subjectsName = new ArrayList<>();
                        for (int i = 0; i < getApp().getSubjects().size(); i++) {
                            if (spinnerDegree.getSelectedItem().toString().equals(getApp().getSubjects().get(i).getDegree())){
                                subjectsName.add(getApp().getSubjects().get(i).getName());
                            }
                        }
                        ArrayAdapter<CharSequence> adapterSubject = new ArrayAdapter(ExamEditorActivity.this, android.R.layout.simple_spinner_dropdown_item, subjectsName);
                        spinnerSubject.setAdapter(adapterSubject);
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

        //class spinner
        Spinner spinnerClass = (Spinner) findViewById(R.id.class_spinner);
        ArrayAdapter<CharSequence> adapterClass = ArrayAdapter.createFromResource(this, R.array.assigned_class, android.R.layout.simple_spinner_dropdown_item);
        if (spinnerClass != null) {
            spinnerClass.setAdapter(adapterClass);
        }

        //create student
        Button createButton = (Button) findViewById(R.id.save_button);
        if (createButton != null) {
            createButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //if all good
                    if(processForm() != null) {

                        Intent intent = new Intent(ExamEditorActivity.this, ExamsListActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }

                }
            });
        }


        //back toolbar go to main menu activity
        ImageView backButton = (ImageView) findViewById(R.id.back_toolbar);
        if (backButton != null) {
            backButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ExamEditorActivity.this, MainMenuActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();

                }

            });
        }

        if(getIntent().hasExtra("id")){
            editorExam = getApp().getExamById((int)getIntent().getExtras().getInt("id"));
        }

        if(editorExam != null){
            if (title != null) {
                title.setText(getString(R.string.exam_editor_title));
            }
            setDateEditText.setText(editorExam.getDate());
            setHourEditText.setText(editorExam.getHour());

            if (spinnerDegree != null) {
                int index = 0;
                String[] l = getResources().getStringArray(R.array.degrees);
                for(String s : l){
                    if(s.compareTo(editorExam.getSpeciality()) == 0) break;
                    index++;
                }
                if(index < l.length){
                    spinnerDegree.setSelection(index);
                }
            }

            if (spinnerClass != null) {
                int index = 0;
                String[] l = getResources().getStringArray(R.array.assigned_class);
                for(String s : l){
                    if(s.compareTo(editorExam.getAssigned_class()) == 0) break;
                    index++;
                }
                if(index < l.length){
                    spinnerClass.setSelection(index);
                }
            }

            year = editorExam.getYear();
            monthOfYear = editorExam.getMonth();
            dayOfMonth = editorExam.getDay();
        }else{
            if (title != null) {
                title.setText(getString(R.string.exam_new_title));
            }
        }
    }


    @Override
    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
        setDateEditText.setText(getString(R.string.exam_date, year, monthOfYear, dayOfMonth));
        this.year = year;
        this.monthOfYear = monthOfYear;
        this.dayOfMonth = dayOfMonth;

    }

    @Override
    public void onDialogTimeSet(int reference, int hourOfDay, int minute) {
        setHourEditText.setText(getString(R.string.time_picker_result_value, String.format("%02d", hourOfDay), String.format("%02d", minute)));
        hour = getString(R.string.time_picker_result_value, String.format("%02d", hourOfDay), String.format("%02d", minute));
    }


    public Exam processForm(){
        Exam exam = new Exam();
        setDateEditText = (EditText) findViewById(R.id.exam_date);
        setHourEditText = (EditText) findViewById(R.id.exam_hour);
        Spinner spinnerDegree = (Spinner) findViewById(R.id.career_spinner);
        Spinner spinnerSubject = (Spinner) findViewById(R.id.subject_spinner);
        Spinner spinnerClass = (Spinner) findViewById(R.id.class_spinner);

        if(setDateEditText.getText().toString().length() == 0){
            setDateEditText.setError(getString(R.string.error_field_required));
            setDateEditText.requestFocus();
            return null;
        }
        if(setHourEditText.getText().toString().length() == 0){
            setHourEditText.setError(getString(R.string.error_field_required));
            setHourEditText.requestFocus();
            return null;
        }

        exam.setDate(new DateTime(year+"-"+monthOfYear+"-"+dayOfMonth));
        exam.setHour(hour);
        exam.setSpecialty(spinnerDegree.getSelectedItem().toString());

        String subjectName = spinnerSubject.getSelectedItem().toString();


        //found the subject
        Subject subjectToAdd = new Subject();
        ArrayList<Subject> subjects = new ArrayList<>();
        subjects = getApp().getSubjects();
        for(int i = 0; i< subjects.size(); i++){
            if (subjects.get(i).getName().equals(subjectName)){
                subjectToAdd = subjects.get(i);
            }
        }
        exam.setSubject(subjectToAdd);
        exam.setAssigned_class(spinnerClass.getSelectedItem().toString());
        exam.setDate(new DateTime(year+"-"+monthOfYear+"-"+dayOfMonth));
        exam.getDateTime();

        getApp().addExam(exam);
        getApp().getExams().remove(editorExam);



        return exam;
    }


    //finish this activity going back
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
