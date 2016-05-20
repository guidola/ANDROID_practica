package domel.ecampus.Model;


import org.joda.time.DateTime;

import java.util.Date;

public class Exam {

    private DateTime date;
    private int assigned_class;
    private Subject subject;

    public Exam() {
    }

    public Exam(DateTime date, int assigned_class, Subject subject) {
        this.date = date;
        this.assigned_class = assigned_class;
        this.subject = subject;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
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
