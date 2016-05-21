package domel.ecampus;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.util.JsonReader;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.DateTime;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import domel.ecampus.Model.Exam;
import domel.ecampus.Model.Student;
import domel.ecampus.Model.Subject;
import domel.ecampus.Model.User;


public class MyApplication extends Application{


    private transient static final String APP_STATUS_FILENAME = "persisted_data.json";
    private transient static final String APP_STATUS_DEFAULT_FILENAME = "default_data.json";

    @SerializedName("su")
    private static ArrayList<Subject> subjects;

    @SerializedName("st")
    private static ArrayList<Student> students;

    @SerializedName("ex")
    private static ArrayList<Exam> exams;

    @SerializedName("us")
    private static User user;


    public MyApplication() {
        students = new ArrayList<>();
        subjects = new ArrayList<>();
        exams = new ArrayList<>();
    }

    public MyApplication(ArrayList<Subject> subjects, ArrayList<Student> students, ArrayList<Exam> exams) {
        MyApplication.subjects = subjects;
        MyApplication.students = students;
        MyApplication.exams = exams;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
        //for now initializing model with ahrdcoded information to start working with it the good way
        for (int i = 0 ; i < 4; i++){
            addStudent(new Student("Menganito", R.mipmap.la_salle_logo, new DateTime("1990-12-12"), "Magisterio", "Mucho"));
        }
        for (int i = 0 ; i < 4; i++){
            addSubject(new Subject("Pinta y colorea", R.mipmap.la_salle_logo, "Pinta dentro de los bordes, no te salgas!"));
        }
        for(Subject s : getSubjects()){
            addExam(new Exam(new DateTime("2016-08-22"), 1, s), s);

        }
        for (Subject s: getSubjects()){
            for (Student student : getStudents()){
                enroll(student, s);
            }
        }
    }

    public void persist(){
        //here we define persisting logic of MyApplication information.
        if(Tools.isExternalStorageWritable()){

            Gson g = new Gson();
            try {
                OutputStreamWriter osw = new OutputStreamWriter(
                        openFileOutput(APP_STATUS_FILENAME, 0),
                        "UTF-8"
                );

                osw.write(g.toJson(this));
                osw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadStatusFromDisk(){
        if(Tools.isExternalStorageReadable()){

            if(Tools.fileExists(getApplicationContext(), APP_STATUS_FILENAME)){
                //load previous data
                try{
                    Gson g = new Gson();
                    InputStreamReader isr = new InputStreamReader(
                            openFileInput(APP_STATUS_FILENAME),
                            "UTF-8"
                    );

                    initializeData(g.fromJson(isr, MyApplication.class));
                    isr.close();

                }catch (FileNotFoundException e){
                    Log.w("LOAD_DATA", "Status file not found");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else{
                //load default data
                try{
                    Gson g = new Gson();
                    InputStreamReader isr = new InputStreamReader(
                            getAssets().open(APP_STATUS_DEFAULT_FILENAME),
                            "UTF-8"
                    );

                    initializeData(g.fromJson(isr, MyApplication.class));
                    isr.close();

                } catch (IOException e) {
                    Log.w("LOAD DATA", "Default status file not found");
                }
            }

        }
    }

    private static void initializeData(MyApplication myApplication) {
        MyApplication.students = myApplication.getStudents();
        MyApplication.subjects = myApplication.getSubjects();
        MyApplication.exams = myApplication.getExams();
    }


    public static void addStudent(Student student){
        students.add(student);
    }

    public static void deleteStudent(Student student){
        for (Subject s : student.getSubjects()){
            s.unrollStudent(student);
        }
        students.remove(student);
    }

    public static void addSubject(Subject subject){
        subjects.add(subject);
    }

    public static void suspendExams(Subject subject){
        for (Exam e: subject.getExams()){
            exams.remove(e);
        }
        subject.suspendExams();
    }

    public static void unroll(Student student, Subject subject){
        student.unrollSubject(subject);
        subject.unrollStudent(student);
    }

    public static void enroll(Student student, Subject subject){
        student.enrollSubject(subject);
        subject.enrollStudent(student);
    }

    public static void deleteSubject(Subject subject){
        for (Student s: subject.getStudents()){
            s.unrollSubject(subject);
        }
        suspendExams(subject);
        subjects.remove(subject);
    }

    public static void addExam(Exam exam, Subject subject){
        exams.add(exam);
        exam.setSubject(subject);
        subject.scheduleExam(exam);
    }

    public static void deleteExam(Exam exam){
        exam.getSubject().getExams().remove(exam);
        exam.setSubject(null);
        exams.remove(exam);
    }


    public static ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public static void setSubjects(ArrayList<Subject> subjects) {
        MyApplication.subjects = subjects;
    }

    public static ArrayList<Student> getStudents() {
        return students;
    }

    public static void setStudents(ArrayList<Student> students) {
        MyApplication.students = students;
    }

    public static ArrayList<Exam> getExams() {
        return exams;
    }

    public static void setExams(ArrayList<Exam> exams) {
        MyApplication.exams = exams;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        MyApplication.user = user;
    }

    public static Subject getSubjectById(int id) {

        for (Subject s : subjects){
            if(s.getId() == id) return s;
        }

        return null;
    }
}
