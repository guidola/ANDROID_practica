package domel.ecampus.Model;

public class Subject {
    public String name;
    public int image;
    public String description;

    public Subject(String name, int image, String description){
        this.name = name;
        this.image = image;
        this.description = description;
    }

    public String getName(Subject subject){
        return subject.name;
    }

    public String getDescription(Subject subject){return subject.description;}

}
