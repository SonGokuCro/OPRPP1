package hr.fer.oprpp1.hw04.db;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentDatabaseTest {

    @Test
    void forJMBAG() throws IOException {
        List<String> lines = Files.readAllLines(
                Paths.get("src//main//resources//database.txt").toAbsolutePath(), StandardCharsets.UTF_8
        );
        StudentDatabase db = new StudentDatabase(lines);
        StudentRecord sr = new StudentRecord("0000000003", "Bosnić", "Andrea", "4");

        assertEquals(sr, db.forJMBAG("0000000003"));
    }

    @Test
    void filter() throws IOException {
        List<String> lines = Files.readAllLines(
                Paths.get("src//main//resources//database.txt").toAbsolutePath(), StandardCharsets.UTF_8
        );
        StudentDatabase db = new StudentDatabase(lines);

        assertEquals(db.list.size(), db.filter(record -> true).size());
        assertEquals(0, db.filter(record -> false).size());
    }
}