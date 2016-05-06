package domel.ecampus.Model;


public class Student {

    public String name;
    public int image;
    public int age;
    public String specialty;

    public Student(String name, int image, int age, String specialty){
        this.name = name;
        this.image = image;
        this.age = age;
        this.specialty = specialty;
    }

    public String getName(Student student){
        return student.name;
    }

    public String getSpecialty(Student student){
        return student.specialty;
    }

    public String getAge(Student student){
        return "" + student.age;
    }


}
