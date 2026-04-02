package es.upm.pproject.gym;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class GymManagerTest {

    private GymManager manager;

    // ----------------------------------------------------------------------
    // INSTANSIATE MANAGER BEFORE EACH TEST
    // ----------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        manager = new GymManager();
    }

    // ----------------------------------------------------------------------
    // REGISTER PRESON TESTS
    // ----------------------------------------------------------------------

    @Nested
    @DisplayName("Register Person Tests")
    class RegisterPersonTests {

        @Test
        @DisplayName("Test_01: Should register a valid person")
        void registerValidPerson() {
            manager.registerPerson(1, "Juan", "juan@mail.com");

            List<Person> people = manager.allPeople();

            assertEquals(1, people.size());
            assertEquals("juan@mail.com", people.get(0).getEmail());
        }

        @Test
        @DisplayName("Test_02: should throw exception for invalid email (email without @)")
        void registerInvalidEmail() {
            assertThrows(IllegalArgumentException.class, () -> {
                manager.registerPerson(1, "Juan", "juanmail.com");
            });
        }

        @Test
        @DisplayName("Test_03: should throw exception for invalid email (email which ends with .)")
        void registerInvalidEmail2() {
            assertThrows(IllegalArgumentException.class, () -> {
                manager.registerPerson(1, "Juan", "juan@mail.");
            });
        }

        @Test
        @DisplayName("Test_04: Should not allow duplicate emails")
        void registerDuplicateEmail() {
            manager.registerPerson(1, "Juan", "juan@mail.com");

            assertThrows(IllegalArgumentException.class, () -> {
                manager.registerPerson(2, "Pedro", "juan@mail.com");
            });
        }

        @Test
        @DisplayName("Test_05: should throw exception for null name")
        void registerNullName() {
            assertThrows(IllegalArgumentException.class, () -> {
                manager.registerPerson(1, null, "juan@mail.com");
            });
        }

        @Test
        @DisplayName("Test_06: should throw exception for null email")
        void registerNullEmail() {
            assertThrows(IllegalArgumentException.class, () -> {
                manager.registerPerson(1, "Juan", null);
            });
        }

        @Test
        @DisplayName("Test_07: should throw exception for empty name")
        void registerEmptyName() {
            assertThrows(IllegalArgumentException.class, () -> {
                manager.registerPerson(1, "", "juan@mail.com");
            });
        }

        @Test
        @DisplayName("Test_08: should throw exception for empty email")
        void registerEmptyEmail() {
            assertThrows(IllegalArgumentException.class, () -> {
                manager.registerPerson(1, "Juan", "");
            });
        }

    }

    // ----------------------------------------------------------------------
    // REGISTER CLASS TESTS
    // ----------------------------------------------------------------------

    @Nested
    @DisplayName("Register Class Tests")
    class RegisterClassTests {

        @Test
        @DisplayName("Test_09: should register a valid class")
        void registerValidClass() {
            manager.registerClass("Yoga", "Ana");

            List<GymClass> classes = manager.allClasses();

            assertEquals(1, classes.size());
            assertEquals("Yoga", classes.get(0).getName());
        }

        @Test
        @DisplayName("Test_10: should not allow duplicate class names")
        void registerDuplicateClass() {
            manager.registerClass("Yoga", "Ana");

            assertThrows(IllegalArgumentException.class, () -> {
                manager.registerClass("Yoga", "Pedro");
            });
        }

        @Test
        @DisplayName("Test_11: should throw exception for null name")
        void registerInvalidClass() {
            assertThrows(IllegalArgumentException.class, () -> {
                manager.registerClass(null, "Ana");
            });
        }

        @Test
        @DisplayName("Test_12: should throw exception for empty class name")
        void registerEmptyClassName() {
            assertThrows(IllegalArgumentException.class, () -> {
                manager.registerClass("", "Ana");
            });
        }

        @Test
        @DisplayName("Test_13: should throw exception for null instructor")
        void registerNullInstructor() {
            assertThrows(IllegalArgumentException.class, () -> {
                manager.registerClass("Yoga", null);
            });
        }

        @Test
        @DisplayName("Test_14: should throw exception for empty instructor")
        void registerEmptyInstructor() {
            assertThrows(IllegalArgumentException.class, () -> {
                manager.registerClass("Yoga", "");
            });
        }
    }

    // ----------------------------------------------------------------------
    // ENROLL CLASS TESTS
    // ----------------------------------------------------------------------

    @Nested
    @DisplayName("Enroll Class Tests")
    class EnrollClassTests {

        @Test
        @DisplayName("Test_15: should enroll a person correctly")
        void enrollCorrectly() {
            manager.registerPerson(1, "Juan", "juan@mail.com");
            manager.registerClass("Yoga", "Ana");

            manager.enrollClass("juan@mail.com", "Yoga");

            List<Person> people = manager.getClassPeople("Yoga");

            assertEquals(1, people.size());
            assertEquals("Juan", people.get(0).getName());
        }

        @Test
        @DisplayName("Test_16: should throw exception if person does not exist")
        void enrollPersonNotExists() {
            manager.registerClass("Yoga", "Ana");

            assertThrows(IllegalArgumentException.class, () -> {
                manager.enrollClass("no@mail.com", "Yoga");
            });
        }

        @Test
        @DisplayName("Test_17: should throw exception if class does not exist")
        void enrollClassNotExists() {
            manager.registerPerson(1, "Juan", "juan@mail.com");

            assertThrows(IllegalArgumentException.class, () -> {
                manager.enrollClass("juan@mail.com", "Yoga");
            });
        }

        @Test
        @DisplayName("Test_18: should not allow enrolling twice")
        void enrollTwice() {
            manager.registerPerson(1, "Juan", "juan@mail.com");
            manager.registerClass("Yoga", "Ana");

            manager.enrollClass("juan@mail.com", "Yoga");

            assertThrows(IllegalStateException.class, () -> {
                manager.enrollClass("juan@mail.com", "Yoga");
            });
        }

        @Test
        @DisplayName("Test_19: should not allow more than 20 people")
        void classFull() {
            manager.registerClass("Yoga", "Ana");

            for (int i = 0; i < 20; i++) {
                manager.registerPerson(i + 1, "P" + i, "p" + i + "@mail.com");
                manager.enrollClass("p" + i + "@mail.com", "Yoga");
            }

            manager.registerPerson(21, "Extra", "extra@mail.com");

            assertThrows(IllegalStateException.class, () -> {
                manager.enrollClass("extra@mail.com", "Yoga");
            });
        }
    }

    // ----------------------------------------------------------------------
    // CANCEL ENROLL TESTS
    // ----------------------------------------------------------------------

    @Nested
    @DisplayName("Cancel Enroll Tests")
    class CancelEnrollTests {

        @Test
        @DisplayName("Test_20: should cancel enrollment correctly")
        void cancelCorrectly() {
            manager.registerPerson(1, "Juan", "juan@mail.com");
            manager.registerClass("Yoga", "Ana");

            manager.enrollClass("juan@mail.com", "Yoga");
            manager.cancelEnroll("juan@mail.com", "Yoga");

            List<Person> people = manager.getClassPeople("Yoga");

            assertEquals(0, people.size());
        }

        @Test
        @DisplayName("Test_21: should throw exception if person not exists")
        void cancelPersonNotExist() {
            manager.registerClass("Yoga", "Ana");

            assertThrows(IllegalArgumentException.class, () -> {
                manager.cancelEnroll("no@mail.com", "Yoga");
            });
        }

        @Test
        @DisplayName("Test_22: should throw exception if class not exists")
        void cancelClassNotExist() {
            manager.registerPerson(1, "Juan", "juan@mail.com");

            assertThrows(IllegalArgumentException.class, () -> {
                manager.cancelEnroll("juan@mail.com", "Yoga");
            });
        }

        @Test
        @DisplayName("Test_23: should throw exception if not enrolled")
        void cancelNotEnrolled() {
            manager.registerPerson(1, "Juan", "juan@mail.com");
            manager.registerClass("Yoga", "Ana");

            assertThrows(IllegalStateException.class, () -> {
                manager.cancelEnroll("juan@mail.com", "Yoga");
            });
        }
    }

    // ----------------------------------------------------------------------
    // RESTART CLASS TESTS
    // ----------------------------------------------------------------------

    @Nested
    @DisplayName("Restart Class Tests")
    class RestartClassTests {

        @Test
        @DisplayName("Test_24: should remove all people from class")
        void restartClass() {
            manager.registerPerson(1, "Juan", "juan@mail.com");
            manager.registerPerson(2, "Ana", "ana@mail.com");
            manager.registerClass("Yoga", "Ana");

            manager.enrollClass("juan@mail.com", "Yoga");
            manager.enrollClass("ana@mail.com", "Yoga");

            manager.restartClass("Yoga");

            List<Person> people = manager.getClassPeople("Yoga");

            assertEquals(0, people.size());
        }

        @Test
        @DisplayName("Test_25: should throw exception if class does not exist")
        void restartClassNotExist() {
            assertThrows(IllegalArgumentException.class, () -> {
                manager.restartClass("Yoga");
            });
        }

        @Test
        @DisplayName("Test_26: should throw exception for null class name")
        void restartNullClass() {
            assertThrows(IllegalArgumentException.class, () -> {
                manager.restartClass(null);
            });
        }

    }

    // ----------------------------------------------------------------------
    // ALL PEOPLE TESTS
    // ----------------------------------------------------------------------

    @Nested
    @DisplayName("All People Tests")
    class AllPeopleTests {

        @Test
        @DisplayName("Test_27: should return people sorted by email")
        void peopleSortedByEmail() {

            manager.registerPerson(1, "Juan", "juan@mail.com");
            manager.registerPerson(2, "Ana", "ana@mail.com");
            manager.registerPerson(3, "Pedro", "pedro@mail.com");

            List<Person> people = manager.allPeople();

            assertEquals("ana@mail.com", people.get(0).getEmail());
            assertEquals("juan@mail.com", people.get(1).getEmail());
            assertEquals("pedro@mail.com", people.get(2).getEmail());

        }

        @Test
        @DisplayName("Test_28: should return empty list when no people")
        void noPeople() {
            List<Person> people = manager.allPeople();
            assertEquals(0, people.size());
        }

    }

    // ----------------------------------------------------------------------
    // ALL CLASSES TESTS
    // ----------------------------------------------------------------------

    @Nested
    @DisplayName("All Classes Tests")
    class AllClassesTests {

        @Test
        @DisplayName("Test_29: should return classes sorted by name")
        void classesSortedByName() {

            manager.registerClass("Zumba", "Ismael");
            manager.registerClass("Aerobic", "Miguel");
            manager.registerClass("Yoga", "Ana");

            List<GymClass> classes = manager.allClasses();

            assertEquals("Aerobic", classes.get(0).getName());
            assertEquals("Yoga", classes.get(1).getName());
            assertEquals("Zumba", classes.get(2).getName());
        }

        @Test
        @DisplayName("Test_30: should return empty list when no classes")
        void noClasses() {
            List<GymClass> classes = manager.allClasses();
            assertEquals(0, classes.size());
        }

    }

    // ----------------------------------------------------------------------
    // GET CLASS PEOPLE TESTS
    // ----------------------------------------------------------------------

    @Nested
    @DisplayName("Get Class People Tests")
    class GetClassPeopleTests {

        @Test
        @DisplayName("Test_31: should return people sorted by name")
        void peopleClassSortedByName() {

            manager.registerPerson(1, "Carlos", "carlos@mail.com");
            manager.registerPerson(2, "Ana", "ana@mail.com");
            manager.registerPerson(3, "Luis", "luis@mail.com");

            manager.registerClass("Yoga", "Ana");

            manager.enrollClass("carlos@mail.com", "Yoga");
            manager.enrollClass("ana@mail.com", "Yoga");
            manager.enrollClass("luis@mail.com", "Yoga");

            List<Person> people = manager.getClassPeople("Yoga");

            assertEquals("Ana", people.get(0).getName());
            assertEquals("Carlos", people.get(1).getName());
            assertEquals("Luis", people.get(2).getName());

        }

        @Test
        @DisplayName("Test_32: should throw exception if class does not exist")
        void peopleClassClassNotExist() {
            assertThrows(IllegalArgumentException.class, () -> {
                manager.getClassPeople("Yoga");
            });
        }
    }

}
