package domel.ecampus.Model;


public class Exam {

    public String subject;
    public int hour;
    public int date;
    public int nClass;
    public int nStudens;

    public Exam(String subject, int hour, int date, int nClass, int nStudens){
        this.subject = subject;
        this.hour = hour;
        this.date = date;
        this.nClass = nClass;
        this.nStudens = nStudens;
    }

    public String getSubject(Exam exam){
        return exam.subject;
    }
    public int getHour(Exam exam){return  exam.hour;}
    public int getDate(Exam exam){return  exam.date;}
    public int getnClass(Exam exam){return  exam.nClass;}
    public int getnStudens(Exam exam){return  exam.nStudens;}
}
