package domel.ecampus.Model;


import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import domel.ecampus.R;

public class Student {

    private int id;
    private String name;
    private int image;
   //private int age;
    private GregorianCalendar calendar;
    private Date date;
    private DateFormat dateFormat;
    private String specialty;
    private String gender;

    public String getGender() {return gender;}

    public void setGender(String gender) {this.gender = gender;}

    private ArrayList<Subject> subjects;

    public Student(int id, String name, int image, GregorianCalendar dateC, String specialty, String gender) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.calendar = dateC;
        calendar.set(dateC.get(Calendar.YEAR),dateC.get(Calendar.MONTH),  dateC.get(Calendar.DAY_OF_WEEK));
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");


        Log.d("DEBUG", "Date --> " + calendar.toString());
        this.specialty = specialty;
        this.gender = gender;
        this.subjects = new ArrayList<>();
    }
/*
    public Student(String name, int image, Calendar date, String specialty, String gender){
        this.name = name;
        this.image = image;
        this.date = date;
        this.specialty = specialty;
        this.gender = gender;
        this.subjects = new ArrayList<>();
    }*/

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

    public String getDate() {

        // Jan = 0, dec = 11
        return calendar.get(Calendar.DAY_OF_MONTH) + "/" +calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR);
    }

    public String getAgeString() {
/*
        Calendar actualDate = Calendar.getInstance();
        //Calendar bornDate = dateFormat.parse(dateFormat.);
        int y, m, d, a;

        y = actualDate.get(Calendar.YEAR);
        m = actualDate.get(Calendar.MONTH);
        d = actualDate.get(Calendar.DAY_OF_MONTH);

        actualDate.set(date.getYear(), date.getMonth(), date.getDay());
        a = y - actualDate.get(Calendar.YEAR);
        if ((m < actualDate.get(Calendar.MONTH)) || ((m == actualDate.get(Calendar.MONTH)) && (d < actualDate.get(Calendar.DAY_OF_MONTH)))) {
            --a;
        }
        if(a < 0) throw new IllegalArgumentException("Age < 0");
*/
        return " " + "" + " años";



    }

    public void setDate(GregorianCalendar dateC) {
        this.calendar = dateC;
    }

    public String getSpecialty() {return " " + specialty;}

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

            Student st = new Student(i ,"test student",R.mipmap.la_salle_logo,new GregorianCalendar(91,11,30), "Magisterio", "Hombre");
            st.getSubjects().add(new Subject("test subject", R.mipmap.la_salle_logo, "this is some dummy text this is some dummy text this is some dummy text "));
            s.add(st);
        }
        return s;
    }
}
