package domel.ecampus.Model;


import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class Exam {

    private static int auto_inc_id = 0;

    private int id;
    private DateTime date;
    private int assigned_class;
    private Subject subject;

    public Exam() {
    }

    public Exam(DateTime date, int assigned_class, Subject subject) {
        this.id = auto_inc_id++;
        this.date = date;
        this.assigned_class = assigned_class;
        this.subject = subject;
    }


    public void setDate(DateTime date) {
        this.date = date;
    }

    public String getDate(){

        DateTimeFormatter timeFormat = DateTimeFormat.forPattern("dd/MM/yyyy");
        String str = timeFormat.print(date);
        return str;
    }

    public String getHour(){
        DateTimeFormatter timeFormat = DateTimeFormat.forPattern("HH:mm");
        String str = timeFormat.print(date);
        return str;
    }

    public int getAssigned_class() {
        return assigned_class;
    }

    public void setAssigned_class(int assigned_class) {
        this.assigned_class = assigned_class;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
