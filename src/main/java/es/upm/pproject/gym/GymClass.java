package es.upm.pproject.gym;
import java.util.ArrayList;
import java.util.List;

public class GymClass {
    private String name;
    private String trainer;
    private List<Person> people;

    public GymClass(String name, String trainer){
        this.name = name;
        this.trainer = trainer;
        this.people = new ArrayList<>();
    }
    public String getName(){
        return name;
    }
    public String getTrainer(){
        return trainer;
    }
    public List<Person> getPeople(){
        return people;
    }
}
