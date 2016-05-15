package domel.ecampus.Model;

import java.lang.reflect.Array;
import java.util.ArrayList;

import domel.ecampus.R;

public class Subject {

    private String name;
    private int image;
    private  String description;

    private ArrayList<Student> students;
    private ArrayList<SubjectTheme> themes;
    private ArrayList<Exam> exams;



    public Subject() {
        this.students = new ArrayList<>();
        this.exams = new ArrayList<>();
        this.themes = new ArrayList<>();
    }

    public Subject(String name, int image, String description){
        this.name = name;
        this.image = image;
        this.description = description;
        this.students = new ArrayList<>();
        this.exams = new ArrayList<>();
        this.themes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<SubjectTheme> getThemes() {
        return themes;
    }

    public void setThemes(ArrayList<SubjectTheme> themes) {
        this.themes = themes;
    }

    public ArrayList<Exam> getExams() {
        return exams;
    }

    public void setExams(ArrayList<Exam> exams) {
        this.exams = exams;
    }

    public void suspendExams(){
        this.exams.clear();
    }

    public void unrollStudent(Student student) {
        this.students.remove(student);
    }

    public void enrollStudent(Student student) {
        this.students.add(student);
    }

    public void scheduleExam(Exam exam){
        exam.setSubject(this);
        this.exams.add(exam);
    }

    public void suspendExam(Exam exam){
        this.exams.remove(exam);
    }

    public static ArrayList<Subject> getTestCollection(){
        ArrayList<Subject> s = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Subject sub = new Subject("test subject", R.mipmap.la_salle_logo, "this is some dummy text this is some dummy text this is some dummy text this is some dummy text this is some dummy text this is some dummy text this is some dummy text this is some dummy text this is some dummy text ");
            sub.getThemes().add(new SubjectTheme(i, "Dummy Theme"));
            s.add(sub);
        }
        return s;
    }
}
