package hr.fer.oprpp1.hw04.db;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to format records for easier reading.
 */
public class RecordFormatter {

    /**
     * Method used for formatting
     * @param list of students needed to adjust display on our output
     * @return formatted list
     */
    public static List<String> format(List<StudentRecord> list) {
        List<String> formatted = new ArrayList<>();
        if(list.size() == 0) {
            System.out.println("Records selected: 0");
            return formatted;
        }
        int maxSurnameLength = 0;
        int maxNameLength = 0;
        for(StudentRecord s : list) {
            if(s.getFirstName().length() > maxNameLength) {
                maxNameLength = s.getFirstName().length();
            }
            if(s.getLastName().length() > maxSurnameLength) {
                maxSurnameLength = s.getLastName().length();
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("+").append("=".repeat(maxSurnameLength + 2)).append("+");
        sb.append("=".repeat(maxNameLength + 2)).append("+").append("=").append("=").append("=").append("+");
        formatted.add(sb.toString());
        sb.delete(0, sb.length());

        for(StudentRecord sc : list) {
            //append lastname
            sb.append("|").append(" ").append(sc.getLastName());
            int emptySpaces = maxSurnameLength + 1 - sc.getLastName().length();
            sb.append(" ".repeat(Math.max(0, emptySpaces)));
            //append firstname
            sb.append("|").append(" ").append(sc.getFirstName());
            emptySpaces = maxNameLength - sc.getFirstName().length();
            sb.append(" ".repeat(Math.max(0, emptySpaces)));
            //append final grade
            sb.append(" | ").append(sc.getFinalGrade()).append(" |");
            formatted.add(sb.toString());
            sb.delete(0, sb.length());
        }

        sb.append("+").append("=".repeat(maxSurnameLength + 2)).append("+");
        sb.append("=".repeat(maxNameLength + 2)).append("+").append("=").append("=").append("=").append("+");
        formatted.add(sb.toString());
        formatted.add("Records selected: " + list.size());
        return formatted;
    }
}
