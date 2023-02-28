package hr.fer.oprpp1.hw04.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudentDB {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(
                Paths.get("src//main//resources//database.txt").toAbsolutePath(), StandardCharsets.UTF_8
        );

        StudentDatabase db = new StudentDatabase(lines);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while (true){
            try {
                line = reader.readLine();
                line = line.strip();
                if(line.startsWith("query")) {
                    line = line.substring(5);
                } else if(line.contains("exit")) {
                    System.out.println("Goodbye!");
                    System.exit(0);
                } else {
                    throw new IllegalArgumentException("Not a query!");
                }
            } catch (IOException e) {
                throw new RuntimeException("Invalid query!");
            }

            QueryParser parser = new QueryParser(line);
            List<StudentRecord> list = new ArrayList<>();
            if (parser.isDirectQuery()) {
                list.add(db.forJMBAG(parser.getQueriedJMBAG()));
                System.out.println("Using index for record retrieval.");
            }  else {
                list = db.filter(new QueryFilter(parser.getQuery()));
            }

            List<String> output = RecordFormatter.format(list);
            output.forEach(System.out::println);
        }
    }
}
