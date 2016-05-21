package domel.ecampus.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;

import org.joda.time.DateTime;

import java.io.File;
import java.util.Calendar;

import domel.ecampus.Base.BaseActivity;
import domel.ecampus.Model.Student;
import domel.ecampus.R;

public class AddStudentActivity extends BaseActivity implements CalendarDatePickerDialogFragment.OnDateSetListener{
    private static final String FRAG_TAG_DATE_PICKER = "fragment_date_picker_name";
    private EditText setDateEditText;
    private final static int SELECT_PHOTO = 12345;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    public static File _file;
    public static File _dir;
    public static Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        //set name
        EditText setNameEditText = (EditText) findViewById(R.id.birthdate);

        //Set the birthdate
        setDateEditText = (EditText) findViewById(R.id.birthdate);
        setDateEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment()
                        .setOnDateSetListener(AddStudentActivity.this);
                cdp.show(getSupportFragmentManager(), FRAG_TAG_DATE_PICKER);
            }
        });

        //degree spinner
        Spinner spinner = (Spinner) findViewById(R.id.student_degree);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.degrees, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //radio buttons of gender
        RadioGroup checked = (RadioGroup) findViewById(R.id.gender_radiogroup);

        //choose image of the galery
        final ImageView chooseImage = (ImageView) findViewById(R.id.photo);
        chooseImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });

        //add photo of the camara
        ImageButton newPhoto = (ImageButton) findViewById(R.id.photo_button);
        newPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }

            }
        });



        //create student
        Button createButton = (Button) findViewById(R.id.submit_button);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //if all good
                if(setDateEditText.getText().toString() != null) {

                   // getApp().addStudent(new Student(10000 ,"test student",R.mipmap.la_salle_logo,new DateTime(1991,11,30,0,0), "Magisterio", "Hombre"));
                    Intent intent = new Intent(AddStudentActivity.this, MainMenuActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }

            }
        });


        //logout button
        ImageView closeSesionButton = (ImageView) findViewById(R.id.close);
        closeSesionButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                //logout
                Intent intent = new Intent(AddStudentActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });
    }

    //set date text the date to the edit text
    @Override
    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
        setDateEditText.setText(getString(R.string.student_date, year, monthOfYear, dayOfMonth));

    }


    @Override
    public void onResume() {
        super.onResume();
        CalendarDatePickerDialogFragment calendarDatePickerDialogFragment = (CalendarDatePickerDialogFragment) getSupportFragmentManager().findFragmentByTag(FRAG_TAG_DATE_PICKER);
        if (calendarDatePickerDialogFragment != null) calendarDatePickerDialogFragment.setOnDateSetListener(this);
    }


}
