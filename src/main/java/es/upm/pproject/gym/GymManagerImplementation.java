package es.upm.pproject.gym;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GymManagerImplementation implements GymManager {
    private static final Logger logger = LoggerFactory.getLogger(GymManagerImplementation.class);
    private Map<String, GymClass> classes = new HashMap<>();
    private Map<String, Person> people = new HashMap<>();

    @Override
    public void registerClass(String name, String trainer) {
        logger.info("Registering class: {}", name);
        if (name == null || name.isEmpty() || trainer == null || trainer.isEmpty()) {
            logger.error("Invalid class name or trainer");
            throw new IllegalArgumentException();
        }
        classes.put(name, new GymClass(name, trainer));
        logger.info("Class {} registered correctly", name);
    }

    @Override
    public void registerPerson(int id, String name, String email) {
        logger.info("Reggistering person: {}", email);
        if (id <= 0 || name == null || email == null || name.isEmpty() || email.isEmpty()) {
            logger.error("Invalid data for register");
            throw new IllegalArgumentException();
        }
        if (!email.contains("@") || !email.endsWith(".com")) {
            logger.error("Invalid email: {}", email);
            throw new IllegalArgumentException();
        }
        if (people.containsKey(email)) {
            logger.error("Person with email {} already exists", email);
            throw new IllegalArgumentException();
        }

        people.put(email, new Person(id, name, email));
        logger.info("Person {} registered correctly", email);
    }

    @Override
    public void enrollClass(String email, String className) {
        logger.info("Enroll person {} in class {}", email, className);
        Person per = people.get(email);
        GymClass gc = classes.get(className);
        if (per == null) {
            logger.error("Person with email {} not found", email);
            throw new IllegalArgumentException();
        }
        if (gc == null) {
            logger.error("Class {} not found", className);
            throw new IllegalArgumentException();
        }
        if (gc.getPeople().size() >= 20) {
            logger.error("Class {} is full", className);
            throw new IllegalStateException();
        }
        if (gc.getPeople().contains(per)) {
            logger.error("Person with email {} already enrolled in {}", email, className);
            throw new IllegalStateException();
        }
        gc.getPeople().add(per);
        logger.info("Enroll successful {} to {}", email, className);
    }

    @Override
    public List<Person> getClassPeople(String className) {
        logger.info("Getting poeple for class {}", className);
        GymClass gc = classes.get(className);
        if (gc == null) {
            logger.error("Class {} not found", className);
            throw new IllegalArgumentException();
        }
        List<Person> listP = new ArrayList<>(gc.getPeople());
        listP.sort(Comparator.comparing(Person::getName));
        return listP;
    }

    @Override
    public void cancelEnroll(String email, String className) {
        logger.info("Cancelling {} enrollment in {}", email, className);
        Person per = people.get(email);
        GymClass gc = classes.get(className);
        if (per == null) {
            logger.error("Person {} not found", email);
            throw new IllegalArgumentException();
        }
        if (gc == null) {
            logger.error("Class {} not found", className);
            throw new IllegalArgumentException();
        }
        if (!gc.getPeople().contains(per)) {
            logger.error("Person {} is not enrrolled in class {}", email, className);
            throw new IllegalStateException();
        }
        gc.getPeople().remove(per);
        logger.info("Enroll cancelled {} from {}", email, className);
    }

    @Override
    public void restartClass(String className) {
        logger.info("Restart class {}", className);
        GymClass gc = classes.get(className);
        if (gc == null) {
            logger.error("Class {} not found", className);
            throw new IllegalArgumentException();
        }
        gc.getPeople().clear();
        logger.info("Class {} restarted", className);
    }

    @Override
    public List<Person> allPeople() {
        logger.info("Getting all people");
        List<Person> listP = new ArrayList<>(people.values());
        listP.sort(Comparator.comparing(Person::getEmail));
        return listP;
    }

    @Override
    public List<GymClass> allClasses() {
        logger.info("Getting all classes");
        List<GymClass> listGC = new ArrayList<>(classes.values());
        listGC.sort(Comparator.comparing(GymClass::getName));
        return listGC;
    }
}
