package domel.ecampus.Model;

/**
 * Created by Guillermo on 8/5/16.
 */
public class SubjectTheme {

    private int id;

    private String name;


    public SubjectTheme() {
    }

    public SubjectTheme(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
