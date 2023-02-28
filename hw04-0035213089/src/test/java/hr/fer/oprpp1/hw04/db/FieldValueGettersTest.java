package hr.fer.oprpp1.hw04.db;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldValueGettersTest {

    @Test
    void testFirstName() {
        StudentRecord student = new StudentRecord("12345678", "Kizivat", "Ana", "3");
        assertEquals("Ana", FieldValueGetters.FIRST_NAME.get(student));
    }

    void testLastName() {
        StudentRecord student = new StudentRecord("65219042", "Horvat", "Filip", "4");
        assertEquals("Horvat", FieldValueGetters.LAST_NAME.get(student));
    }

    void testJMBAGName() {
        StudentRecord student = new StudentRecord("62164289", "Vida", "Domagoj", "2");
        assertEquals("62164289", FieldValueGetters.JMBAG.get(student));
    }
}