package domel.ecampus.Model;


import java.util.ArrayList;

public class Student {

    private String name;
    private int image;
    private int age;
    private String specialty;

    private ArrayList<Subject> subjects;


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

    public void setAge(int age) {
        this.age = age;
    }

    public String getSpecialty() {
        return specialty;
    }

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
}
