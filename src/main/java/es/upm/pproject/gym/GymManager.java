package es.upm.pproject.gym;
import java.util.List;
public interface GymManager {
    
    void registerClass(String name, String trainer);
    void registerPerson(int id, String name, String email);
    void enrollClass(String email, String className);
    List<Person> getClassPeople(String className);
    void cancelEnroll(String email, String className);
    void restartClass(String className);
    List<Person> allPeople();
    List<GymClass> allClasses();
}
