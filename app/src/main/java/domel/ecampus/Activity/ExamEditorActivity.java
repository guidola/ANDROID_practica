package domel.ecampus.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
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

    private Exam editorExam;


    private static final String FRAG_TAG_DATE_PICKER = "fragment_date_picker_name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_editor);

        AppCompatTextView title = (AppCompatTextView) findViewById(R.id.editor_or_new);
        Boolean editor = true;

        try{
            editorExam = MyApplication.getExamById((int)getIntent().getExtras().getInt("id"));
            setDateEditText = (EditText) findViewById(R.id.exam_date);
            setDateEditText.setText(editorExam.getDate());
            setHourEditText = (EditText) findViewById(R.id.exam_hour);
            setHourEditText.setText(editorExam.getHour());

            year = editorExam.getYear();
            monthOfYear = editorExam.getMonth();
            dayOfMonth = editorExam.getDay();

        }catch (NullPointerException e){
            editor =false;
        }


        //Set exam date
        setDateEditText = (EditText) findViewById(R.id.exam_date);
        setDateEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
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
                TimePickerBuilder tpb = new TimePickerBuilder()
                        .setFragmentManager(getSupportFragmentManager())
                        .setStyleResId(R.style.BetterPickersDialogFragment);
                tpb.show();
            }
        });


        //degree spinner
        Spinner spinnerDegree = (Spinner) findViewById(R.id.career_spinner);
        ArrayAdapter<CharSequence> adapterDegree = ArrayAdapter.createFromResource(this, R.array.degrees, android.R.layout.simple_spinner_dropdown_item);
        spinnerDegree.setAdapter(adapterDegree);

        ArrayList<String> subjectsName = new ArrayList<>();
        for(int i = 0; i<MyApplication.getSubjects().size(); i++){
            subjectsName.add(MyApplication.getSubjects().get(i).getName());
        }

        //subject spinner
        Spinner spinnerSubject = (Spinner) findViewById(R.id.subject_spinner);
        ArrayAdapter<CharSequence> adapterSubject =  new ArrayAdapter(this,  android.R.layout.simple_spinner_dropdown_item, subjectsName);
        spinnerSubject.setAdapter(adapterSubject);

        //class spinner
        Spinner spinnerClass = (Spinner) findViewById(R.id.class_spinner);
        ArrayAdapter<CharSequence> adapterClass = ArrayAdapter.createFromResource(this, R.array.assigned_class, android.R.layout.simple_spinner_dropdown_item);
        spinnerClass.setAdapter(adapterClass);

        //view if we are going to edit or create an exam
        if(editor){
            //editor exam
            title.setText(getString(R.string.exam_editor_title));
            MyApplication.getExams().remove(editorExam);

        }else{
            //create exam
            title.setText(getString(R.string.exam_new_title));

        }

        //create student
        Button createButton = (Button) findViewById(R.id.save_button);
        if (createButton != null) {
            createButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //if all good
                    Exam exam = processForm();
                    if(exam != null) {

                        Intent intent = new Intent(ExamEditorActivity.this, ExamsListActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }

                }
            });
        }


        //back
        ImageView backButton = (ImageView) findViewById(R.id.back_toolbar);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //logout
                Intent intent = new Intent(ExamEditorActivity.this, MainMenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }

        });



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
        subjects = MyApplication.getSubjects();
        for(int i = 0; i< subjects.size(); i++){
            if (subjects.get(i).getName().equals(subjectName)){
                subjectToAdd = subjects.get(i);
            }
        }
        exam.setSubject(subjectToAdd);
        exam.setAssigned_class(spinnerClass.getSelectedItem().toString());
        exam.setDate(new DateTime(year+"-"+monthOfYear+"-"+dayOfMonth));
        exam.setHour(hour);

        MyApplication.addExam(exam);


        return exam;

    }
}
