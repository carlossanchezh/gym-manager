package es.upm.pproject.gym;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GymManagerImplementation implements GymManager{
    private static final Logger logger = LoggerFactory.getLogger(GymManagerImplementation.class);
    private Map<String, GymClass> classes = new HashMap<>();
    private Map<String, Person> people = new HashMap<>();

    @Override
    public void registerClass(String name, String trainer){}
    @Override
    public void registerPerson(int id, String name, String email){
        logger.info("Reggistering person: {}", email);
        if(name == null || email == null || name.isEmpty() || email.isEmpty()){
            logger.error("Invalid data for register");
            throw new IllegalArgumentException();
        }
        if(!email.contains("@") || !email.endsWith(".com")){
            logger.error("Invalid email: {}", email);
            throw new IllegalArgumentException();
        }

        people.put(email, new Person(id, name, email));
        logger.info("Person registered correctly: {}", email);
    }
    @Override
    public void enrollClass(String email, String className){}
    @Override
    public List<Person> getClassPeople(String className){}
    @Override
    public void cancelEnroll(String email, String className){}
    @Override
    public void restartClass(String className){}
    @Override
    public List<Person> allPeople(){}
    @Override
    public List<GymClass> allClasses(){}
}
