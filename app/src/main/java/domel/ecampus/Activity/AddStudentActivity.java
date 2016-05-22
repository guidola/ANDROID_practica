package domel.ecampus.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.Display;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import domel.ecampus.Base.BaseActivity;
import domel.ecampus.ImagePicker;
import domel.ecampus.Model.Student;
import domel.ecampus.MyApplication;
import domel.ecampus.R;

public class AddStudentActivity extends BaseActivity implements CalendarDatePickerDialogFragment.OnDateSetListener{

    private static final String FRAG_TAG_DATE_PICKER = "fragment_date_picker_name";
    private EditText setDateEditText;
    private final static int SELECT_PHOTO = 12345;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    public static File _file;
    public static File _dir;
    public static Bitmap bitmap;
    private String photo_temp_path;


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
        if (chooseImage != null) {
            chooseImage.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");
                    startActivityForResult(photoPickerIntent, SELECT_PHOTO);
                }
            });
        }

        //add photo of the camara
        ImageButton newPhoto = (ImageButton) findViewById(R.id.photo_button);
        if (newPhoto != null) {
            newPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent takePictureIntent = ImagePicker.getPickImageIntent(AddStudentActivity.this);
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    //dispatchTakePictureIntent(takePictureIntent);

                }
            });
        }


        //create student
        Button createButton = (Button) findViewById(R.id.submit_button);
        if (createButton != null) {
            createButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //if all good
                    Student student = processForm();
                    if(student != null) {

                        // getApp().addStudent(new Student(10000 ,"test student",R.mipmap.la_salle_logo,new DateTime(1991,11,30,0,0), "Magisterio", "Hombre"));
                        Intent intent = new Intent(AddStudentActivity.this, StudentManagerActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }

                }
            });
        }


        //logout button
        ImageView closeSesionButton = (ImageView) findViewById(R.id.close);
        if (closeSesionButton != null) {
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
    }

    public Student processForm(){

        AppCompatEditText name = (AppCompatEditText) findViewById(R.id.name);
        AppCompatEditText date = (AppCompatEditText) findViewById(R.id.birthdate);
        AppCompatSpinner  carreer = (AppCompatSpinner) findViewById(R.id.student_degree);
        RadioGroup sex = (RadioGroup) findViewById(R.id.gender_radiogroup);
        AppCompatImageView photo = (AppCompatImageView) findViewById(R.id.photo);

        if(name.getText().toString().length() == 0){
            name.setError(getString(R.string.error_field_required));
            name.requestFocus();
            return null;
        }

        if(date.getText().toString().length() == 0){
            date.setError(getString(R.string.error_field_required));
            date.requestFocus();
            return null;
        }

        if(carreer.getSelectedItemPosition() == 0){
            date.setError(getString(R.string.error_must_select_carreer));
            carreer.requestFocus();
            return null;
        }

        AppCompatRadioButton gender = (AppCompatRadioButton) findViewById(sex.getCheckedRadioButtonId());
        Student student = new Student();
        student.setName(name.getText().toString());
        student.setGender(gender.getText().toString());
        /*String[] date_parts = date.getText().toString().split("/");
        student.setBirthdate(new DateTime(
                Integer.parseInt(date_parts[2]),
                Integer.parseInt(date_parts[1].substring(0,2)),
                Integer.parseInt(date_parts[0]),
                0,
                0
        ));*/

        student.setBirthdate(new DateTime("1996-04-06"));
        student.setSpecialty(carreer.getSelectedItem().toString());

        if(photo.getTag() != null){
            student.setPath((Uri)photo.getTag());
        }

        MyApplication.addStudent(student);

        return student;
    }

    //set date text the date to the edit text
    @Override
    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
        setDateEditText.setText(getString(R.string.student_date, year, monthOfYear, dayOfMonth));

    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
                .format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir("pictures");
        if (storageDir != null) {
            storageDir.mkdirs();
        }
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        photo_temp_path = image.getAbsolutePath();
        return image;
    }

    @Override
    public void onResume() {
        super.onResume();
        CalendarDatePickerDialogFragment calendarDatePickerDialogFragment = (CalendarDatePickerDialogFragment) getSupportFragmentManager().findFragmentByTag(FRAG_TAG_DATE_PICKER);
        if (calendarDatePickerDialogFragment != null) calendarDatePickerDialogFragment.setOnDateSetListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK){
            switch(requestCode) {
                case REQUEST_IMAGE_CAPTURE:
                    Bitmap bitmap = ImagePicker.getImageFromResult(this, resultCode, data);
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
                    File f;
                    try {
                        f = createImageFile();
                        FileOutputStream fo = new FileOutputStream(f);
                        fo.write(bytes.toByteArray());
                        fo.close();
                        AppCompatImageView photo_preview = (AppCompatImageView) findViewById(R.id.photo);
                        if (photo_preview != null) {
                            photo_preview.setImageBitmap(bitmap);
                            photo_preview.setTag(Uri.fromFile(f));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.onActivityResult(requestCode, resultCode, data);
                    break;
            }
        }
    }



}
