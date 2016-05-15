package domel.ecampus.Model;


import java.util.ArrayList;

import domel.ecampus.R;

public class Student {

    private int id;
    private String name;
    private int image;
    private int age;
    private String specialty;

    private ArrayList<Subject> subjects;

    public Student(int id, String name, int image, int age, String specialty) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.age = age;
        this.specialty = specialty;
        this.subjects = new ArrayList<>();
    }

    public Student(String name, int image, int age, String specialty){
        this.name = name;
        this.image = image;
        this.age = age;
        this.specialty = specialty;
        this.subjects = new ArrayList<>();
    }

    public Student() {
        this.subjects = new ArrayList<>();
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

    public int getAge() {
        return age;
    }

    public String getAgeString() {return " " + age + " años";}

    public void setAge(int age) {
        this.age = age;
    }

    public String getSpecialty() {return " " +specialty;}

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    public void unrollSubject(Subject subject) {
        this.subjects.remove(subject);
    }

    public void enrollSubject(Subject subject) {
        this.subjects.add(subject);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static ArrayList<Student> getTestCollection(){
        ArrayList<Student> s = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Student st = new Student(i ,"test student",R.mipmap.la_salle_logo, 21, "Computer engineering\n");
            st.getSubjects().add(new Subject("test subject", R.mipmap.la_salle_logo, "this is some dummy text this is some dummy text this is some dummy text "));
            s.add(st);
        }
        return s;
    }
}
