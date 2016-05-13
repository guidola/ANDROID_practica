package domel.ecampus;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import domel.ecampus.Model.Exam;
import domel.ecampus.Model.Student;
import domel.ecampus.Model.Subject;

/**
 * Created by Guillermo on 10/5/16.
 */
public class MyApplication extends Application {

    private static final String APP_STATUS_FILENAME = "persisted_data.json";
    private static final String APP_STATUS_DEFAULT_FILENAME = "default_data.json";

    private static ArrayList<Subject> subjects;

    private static ArrayList<Student> students;

    private static ArrayList<Exam> exams;


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

    public void persist(){
        //here we define persisting logic of MyApplication information.
        if(Tools.isExternalStorageWritable()){

        }
    }

    public void loadStatusFromDisk(){
        if(Tools.isExternalStorageReadable()){

            if(Tools.fileExists(getApplicationContext(), APP_STATUS_FILENAME)){
                //load previous data
                try{
                    FileInputStream fos = openFileInput(APP_STATUS_FILENAME);
                }catch (FileNotFoundException e){
                    Log.w("LOAD_DATA", "Status file not found");
                }

            }else if (Tools.fileExists(getApplicationContext(), APP_STATUS_DEFAULT_FILENAME)){
                //load default data
            }

        }
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public void deleteStudent(Student student){
        for (Subject s : student.getSubjects()){
            s.unrollStudent(student);
        }
        students.remove(student);
    }

    public void addSubject(Subject subject){
        subjects.add(subject);
    }

    public void suspendExams(Subject subject){
        for (Exam e: subject.getExams()){
            exams.remove(e);
        }
        subject.suspendExams();
    }

    public void unroll(Student student, Subject subject){
        student.unrollSubject(subject);
        subject.unrollStudent(student);
    }

    public void enroll(Student student, Subject subject){
        student.enrollSubject(subject);
        subject.enrollStudent(student);
    }

    public void deleteSubject(Subject subject){
        for (Student s: subject.getStudents()){
            s.unrollSubject(subject);
        }
        suspendExams(subject);
        subjects.remove(subject);
    }

    public void addExam(Exam exam, Subject subject){
        exams.add(exam);
        exam.setSubject(subject);
        subject.scheduleExam(exam);
    }

    public void deleteExam(Exam exam){
        exam.getSubject().getExams().remove(exam);
        exam.setSubject(null);
        exams.remove(exam);
    }


    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        MyApplication.subjects = subjects;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        MyApplication.students = students;
    }

    public ArrayList<Exam> getExams() {
        return exams;
    }

    public void setExams(ArrayList<Exam> exams) {
        MyApplication.exams = exams;
    }




}
