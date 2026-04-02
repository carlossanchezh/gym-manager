package es.upm.pproject.gym;

import java.util.List;

public interface IGymManager {

    /**
     * Registers a new gym class in the system.
     * 
     * @param name    the name of the class
     * @param trainer the name of the trainer responsible for the class
     * @throws IllegalArgumentException if name or trainer is null or blank
     */
    void registerClass(String name, String trainer);

    /**
     * Registers a new person in the gym system.
     *
     * @param id    the identification number of the person
     * @param name  the name of the person
     * @param email the email address of the person
     * @throws IllegalArgumentException if any parameter is invalid or the email is
     *                                  being used by other user
     */
    void registerPerson(int id, String name, String email);

    /**
     * Enrolls a registered person into a registered class.
     *
     * @param email     the email of the person
     * @param className the name of the class
     * @throws IllegalArgumentException if the person or class does not exist
     * @throws IllegalStateException    if the class is full (max 20 people)
     *                                  or the person is already enrolled
     */
    void enrollClass(String email, String className);

    /**
     * Returns the list of people enrolled in a given class.
     *
     * @param className the name of the class
     * @return a list of enrolled people sorted alphabetically by name
     * @throws IllegalArgumentException if the class does not exist
     */
    List<Person> getClassPeople(String className);

    /**
     * Cancels the enrollment of a person in a class.
     *
     * @param email     the email of the person
     * @param className the name of the class
     * @throws IllegalArgumentException if the person or class does not exist
     * @throws IllegalStateException    if the person is not enrolled in the class
     */
    void cancelEnroll(String email, String className);

    /**
     * Restarts a class by removing all enrolled people.
     *
     * @param className the name of the class
     * @throws IllegalArgumentException if the class does not exist
     */
    void restartClass(String className);

    /**
     * Returns all registered people in the system.
     *
     * @return a list of people sorted by email
     */
    List<Person> allPeople();

    /**
     * Returns all registered gym classes.
     *
     * @return a list of classes sorted alphabetically by name
     */
    List<GymClass> allClasses();
}
