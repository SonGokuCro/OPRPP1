package hr.fer.oprpp1.hw04.db;

import java.util.*;

/**
 * Class used for emulating database of student data.
 */
public class StudentDatabase {
    /**
     * Map used for obtaining record in O(1) using hashCode.
     */
    Map<String, StudentRecord> index;
    /**
     * Internal list of student records.
     * Used for sequential accessing to every list member.
     */
    List<StudentRecord> list;

    public StudentDatabase(List<String> dbInput){
        this.index = parse(dbInput);
    }

    /**
     * Parsing given database, and storing it in structures.
     * @param dbInput list of strings representing records that we need to process
     * @return map of processed student records
     */
    private Map<String, StudentRecord> parse(List<String> dbInput) {
        index = new HashMap<>(dbInput.size());
        list = new ArrayList<>();
        for (String s : dbInput) {
            String[] data = s.split("\\t");
            if (index.containsKey(data[0])) {
                throw new IllegalArgumentException("Student already in database!");
            } else if(Integer.parseInt(data[3]) < 1 || Integer.parseInt(data[3]) > 5) {
                throw new IllegalArgumentException("Invalid grade!");
            }
            index.put(data[0], new StudentRecord(data[0], data[1], data[2], data[3]));
            list.add(new StudentRecord(data[0], data[1], data[2], data[3]));
        }
        return index;
    }

    /**
     * Method to obtain record in O(1)
     * @param jmbag of required student
     * @return all students data kept in record
     */
    public StudentRecord forJMBAG(String jmbag) {
        return index.get(jmbag);
    }

    /**
     * Method for filtering student records.
     * @param filter deciding the way to filter records
     * @return list of records that satisfy given condition
     */
    public List<StudentRecord> filter(IFilter filter) {
        List<StudentRecord> filtered = new ArrayList<>();
        for(StudentRecord s : list) {
            if(filter.accepts(s)) {
                filtered.add(s);
            }
        }
        return filtered;
    }
}
